package de.aboutflash.archerytime.server.main;

import de.aboutflash.archerytime.server.model.ControlViewModel;
import de.aboutflash.archerytime.server.model.FITACycleModel;
import de.aboutflash.archerytime.server.net.Announcer;
import de.aboutflash.archerytime.server.ui.ControlScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class ArcheryTimeControlServer extends Application {

  private final static Rectangle2D DEFAULT_SIZE = new Rectangle2D(0.0, 0.0, 1920.0 * 0.33, 1080.0 * 0.33);

  private Stage primaryStage;
  private StackPane rootPane;
  private ControlViewModel controlViewModel;

  private Announcer announcer;
  private FITACycleModel model;

  public static void main(final String... args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    model = new FITACycleModel();
    announceServer();
  }

  private void announceServer() {
    announcer = new Announcer(model);
  }

  @Override
  public void start(final Stage stage) throws Exception {
    primaryStage = stage;

    primaryStage.setOnCloseRequest(e -> {
      announcer.stop();
      Platform.exit();
      System.exit(0);
    });

    layout();
    showControlScreen();
  }

  private void layout() {
    setUserAgentStylesheet("css/main.css");

    primaryStage.setWidth(DEFAULT_SIZE.getWidth());
    primaryStage.setHeight(DEFAULT_SIZE.getHeight());

    rootPane = new StackPane();
    primaryStage.setScene(new Scene(rootPane));

    primaryStage.show();
  }

  private void showControlScreen() {
    controlViewModel = new ControlViewModel();
    rootPane.getChildren().setAll(new ControlScreen(controlViewModel));
  }

}
