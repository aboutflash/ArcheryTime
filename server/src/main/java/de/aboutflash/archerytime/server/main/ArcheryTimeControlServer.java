package de.aboutflash.archerytime.server.main;

import de.aboutflash.archerytime.server.net.Server;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class ArcheryTimeControlServer extends Application {

  private Server server;

  public static void main(final String... args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    connect();
  }

  private void connect() {

    try {
      server = new Server();
    } catch (IOException e) {
      e.printStackTrace();
    }

    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(server);

  }

}
