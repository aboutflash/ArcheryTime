package de.aboutflash.example4.discovery;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public class DiscoveryTestingApp {

  public static void main(String[] args) {
    discoverServer();
  }

  private static DiscoClient discoClient;

  @SuppressWarnings({"MagicNumber", "UseOfSystemOutOrSystemErr", "BusyWait"})
  private static void discoverServer() {
    discoClient = new DiscoClient();


    final AtomicInteger attempts = new AtomicInteger();

    final ExecutorService discoExecService = Executors.newSingleThreadExecutor();
    final Future<?> discoFuture = discoExecService.submit(
        () -> {
          InetAddress serverIp = null;
          while (serverIp == null && attempts.get() < 10) {

            attempts.addAndGet(1);
            serverIp = discoClient.getServerIp();

            try {

              out.printf("Server IP:%s @ %d attempts", serverIp, attempts.get());
              sleep(1_000L);

            } catch (InterruptedException ignored) {
              out.println(ignored);
            }
          }

          discoExecService.shutdown();
        });

  }
}
