package de.aboutflash.example5.udpbroadcast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public final class DumbClient {

  private static Thread listenerThread;

  public static void main(final String... args) {

    try {
      listenerThread = new ListenerThread();
    } catch (Exception e) {
      e.printStackTrace();
    }

    runForever();
  }

  private static void runForever() {
    listenerThread.run();
  }

}
