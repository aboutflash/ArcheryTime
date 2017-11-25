package de.aboutflash.example3.remoteclient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public class RemoteClientApp {
  private static Client client;

  public static void main(String[] args) {
    connect();
  }


  private static void connect() {

    try {
      client = new Client(1);
    } catch (Exception e) {
      e.printStackTrace();
    }

    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(client);

  }
}
