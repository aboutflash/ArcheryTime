package de.aboutflash.archerytime.remoteclient.net;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public final class Listener {

  private final ExecutorService executorService;

  public Listener() {
    executorService = newSingleThreadExecutor();
    runForever();
  }

  public void stop() {
    executorService.shutdownNow();
  }

  private void runForever() {
    try {
      executorService.submit(new ListenerThread());
    } catch (UnknownHostException | SocketException ignored) { }
  }

}
