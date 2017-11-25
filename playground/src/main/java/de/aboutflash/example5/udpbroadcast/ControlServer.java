package de.aboutflash.example5.udpbroadcast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public class ControlServer {

  private static Thread senderThread;

  public static void main(String[] args) {

    try {
      senderThread = new SenderThread();
    } catch (Exception e) {
      e.printStackTrace();
    }

    runForever();
  }

  private static void runForever() {
    senderThread.run();
  }
}
