package de.aboutflash.example5.udpbroadcast;

/**
 * Working example of a sender and a receiver thread.
 * The sender permanently pushes messages to the local subnet.
 * The receiver simply takes them w/o further acknowledge.
 *
 * @author falk@aboutflash.de on 26.11.2017.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import static java.net.InetAddress.getByName;

@SuppressWarnings({"ObjectAllocationInLoop", "InfiniteLoopStatement", "NestedTryStatement"})
public class DatagramSocketTest {

  private static final String SENDER_BROADCAST_ADDRESS = "192.168.178.255"; // the subnet to broadcast to
  private static final String RECEIVER_BIND_ADDRESS = "192.168.178.43"; // your hosts local address (do not use localhost)
  private static final int RESPONSE_PORT = 10101;
  private static final int REC_BUFFER_SIZE = 0x1000;
  private static final long SEND_INTERVAL = 1_000L;

  public static void main(String[] args) throws IOException {
    startReceiver(); // server
    startSender(); // client
  }

  public static void startSender() {
    new Thread() {
      @Override
      public void run() {

        try (DatagramSocket s = new DatagramSocket()) {
          s.setBroadcast(true);


          do {

            final byte[] bytes = String.format("Hello %1$tT.%1tN", new Date()).getBytes();
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, getByName(SENDER_BROADCAST_ADDRESS), RESPONSE_PORT);
            s.send(dp);
            sleep(SEND_INTERVAL);

          } while (true);

        } catch (IOException | InterruptedException ignored) { }
      }
    }.start();
  }

  public static void startReceiver() {
    new Thread() {

      @Override
      public void run() {

        DatagramPacket d = null;

        try {
          byte[] buffer = new byte[REC_BUFFER_SIZE];
          d = new DatagramPacket(buffer, buffer.length, getByName(RECEIVER_BIND_ADDRESS), RESPONSE_PORT);
        } catch (UnknownHostException ignored) { }

        assert d != null;

        try (DatagramSocket ds = new DatagramSocket(d.getPort(), d.getAddress())) {
          ds.setBroadcast(true);

          do {
            try {

              ds.receive(d);
              final String s = new String(d.getData());
              System.out.println(s);

            } catch (IOException ignored) { }

          } while (true);

        } catch (SocketException ignored) { }
      }
    }.start();
  }
}
