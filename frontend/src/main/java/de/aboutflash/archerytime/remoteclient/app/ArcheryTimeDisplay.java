package de.aboutflash.archerytime.remoteclient.app;

import de.aboutflash.archerytime.remoteclient.model.CountdownViewModel;
import de.aboutflash.archerytime.remoteclient.model.StartupViewModel;
import de.aboutflash.archerytime.remoteclient.net.Listener;
import de.aboutflash.archerytime.remoteclient.ui.CountDownScreen;
import de.aboutflash.archerytime.remoteclient.ui.StartupScreen;
import de.aboutflash.archerytime.remoteclient.ui.StopScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The dumb display application.
 * It simply shows, what the control server says.
 *
 * @author falk@aboutflash.de on 22.11.2017.
 */
public class ArcheryTimeDisplay extends Application {

  private final static Rectangle2D DEFAULT_SIZE = new Rectangle2D(0.0, 0.0, 1920.0 * 0.5, 1080.0 * 0.5);
  private final Pane rootPane = new StackPane();
  private Stage primaryStage = null;

  private Listener listener;
  private StartupViewModel startupViewModel;
  private CountdownViewModel countdownViewModel;


  @Override
  public void start(final Stage stage) throws Exception {

    primaryStage = stage;
    primaryStage.setOnCloseRequest(e -> {
      listener.stop();
      Platform.exit();
      System.exit(0);
    });

    primaryStage.setWidth(DEFAULT_SIZE.getWidth());
    primaryStage.setHeight(DEFAULT_SIZE.getHeight());

    final Scene rootScene = new Scene(rootPane);
    primaryStage.setScene(rootScene);
    primaryStage.show();

    setUserAgentStylesheet("css/display.css");

    listener = new Listener();

//    enterFullScreenMode();
    showStartup();
  }

  @Override
  public void stop() throws Exception {
    System.out.println();
  }

  private void enterFullScreenMode() {
    primaryStage.setFullScreen(true);
  }

  private void showStop() {
    rootPane.getChildren().add(new StopScreen());
  }

  private void showStartup() {
    startupViewModel = new StartupViewModel();
    rootPane.getChildren().add(new StartupScreen(startupViewModel));
  }

  private void showReady() {
    countdownViewModel = new CountdownViewModel();
    rootPane.getChildren().add(new CountDownScreen(countdownViewModel));
  }

}
