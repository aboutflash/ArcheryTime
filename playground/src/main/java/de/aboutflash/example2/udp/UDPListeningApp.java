package de.aboutflash.example2.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.System.out;

class UDPListeningApp {

  public static void main(String args[]) throws Exception {

    final ExecutorService executorService = Executors.newSingleThreadExecutor(
        runnable -> {
          Thread t = new Thread(runnable);
          t.setDaemon(true);
          return t;
        });

    final Callable<String> task = new Callable<String>() {
      private final static int MAXWAIT_MILLIS = 3_000;
      private final static int BUF_SIZE = 0xff;

      @Override
      public String call() throws Exception {
        byte[] rcBuffer = new byte[BUF_SIZE];
        DatagramPacket rcPackage = new DatagramPacket(rcBuffer, rcBuffer.length);
        DatagramSocket rcSocket = null;
        try {
          rcSocket = new DatagramSocket();
          rcSocket.setSoTimeout(MAXWAIT_MILLIS);
          rcSocket.setBroadcast(true);

          rcSocket.receive(rcPackage);

        } catch (SocketException e) {
          out.println(e.getMessage());
          return null;
        } catch (SocketTimeoutException e) {
          out.println(e.getMessage());
          return null;
        } catch (IOException e) {
          out.println(e.getMessage());
        } finally {
          rcSocket.close();
          out.println("listening socket closed");
        }

        final String result = new String(rcPackage.getData());
        return result.isEmpty() ? "---* empty *---" : result;
      }
    };


    final Future<String> future = executorService.submit(task);

    while (!future.isDone() && !future.isCancelled()) {
      out.println(Date.from(Instant.now()));
    }

    out.println(future.get());

  }
}
