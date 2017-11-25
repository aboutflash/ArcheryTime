package de.aboutflash.example4.discovery;

import java.util.concurrent.*;

import static de.aboutflash.example4.discovery.CommonConstants.DISCOVERY_IDENTIFIER;

/**
 * Class
 *
 * @author falk@aboutflash.de on 20.11.2017.
 */
public class DiscoServerApp {

  private static DiscoveryThread discoThread;

  public static void main(String[] args) {
    discoThread = new BroadcastDiscovery(DISCOVERY_IDENTIFIER);
    runForever();
  }

  private static void runForever() {
    discoThread.run();
  }

  private static void runWithTimeout() {
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    final Future<?> discoFuture = executor.submit(discoThread);

    try {
      discoFuture.get(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }

    if (executor.isTerminated()) {
      executor.shutdown();
    }
  }
}
