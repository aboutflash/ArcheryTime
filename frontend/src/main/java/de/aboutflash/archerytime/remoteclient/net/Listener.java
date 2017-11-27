package de.aboutflash.archerytime.remoteclient.net;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public final class Listener {

  private Thread listenerThread;

  public Listener() {

    try {
      listenerThread = new ListenerThread();
    } catch (Exception e) {
      e.printStackTrace();
    }

    runForever();
  }

  private void runForever() {
    listenerThread.run();
  }

}
