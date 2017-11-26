package de.aboutflash.example6.socekttest;

/**
 * Class
 *
 * @author falk@aboutflash.de on 26.11.2017.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {
  private static final int RESPONSE_PORT = 101010;

  public static void main(String[] args) throws IOException {

    startServer();
    startSender();
  }

  public static void startSender() {
    (new Thread() {
      @Override
      public void run() {
        try {
          Socket s = new Socket("localhost", RESPONSE_PORT);
          BufferedWriter out = new BufferedWriter(
              new OutputStreamWriter(s.getOutputStream()));

          while (true) {
            out.write("Hello World!");
            out.newLine();
            out.flush();

            Thread.sleep(200);
          }

        } catch (UnknownHostException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public static void startServer() {
    (new Thread() {
      @Override
      public void run() {
        ServerSocket ss;
        try {
          ss = new ServerSocket(RESPONSE_PORT);

          Socket s = ss.accept();

          BufferedReader in = new BufferedReader(
              new InputStreamReader(s.getInputStream()));
          String line = null;
          while ((line = in.readLine()) != null) {
            System.out.println(line);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
