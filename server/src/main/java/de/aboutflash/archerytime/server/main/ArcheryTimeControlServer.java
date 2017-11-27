package de.aboutflash.archerytime.server.main;

import de.aboutflash.archerytime.server.model.FITACycleModel;
import de.aboutflash.archerytime.server.net.Announcer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class ArcheryTimeControlServer extends Application {

  private Announcer announcer;
  private FITACycleModel model;

  public static void main(final String... args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    model = new FITACycleModel();
    announceServer();
  }

  private void announceServer() {
    announcer = new Announcer(model);
  }

}
