package de.aboutflash.archerytime.client.main;

import de.aboutflash.archerytime.remoteclient.model.StartupViewModel;
import de.aboutflash.archerytime.remoteclient.ui.StartupScreen;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class RemoteClient extends Application {
  private final static Rectangle2D DEFAULT_SIZE = new Rectangle2D(0.0, 0.0, 960.0, 560.0);
  private final Pane rootPane = new StackPane();
  private Stage primaryStage = null;

  public static void main(final String... args) {
    launch(args);
  }

  @Override
  public void start(final Stage stage) {
    primaryStage = stage;
    primaryStage.setWidth(DEFAULT_SIZE.getWidth());
    primaryStage.setHeight(DEFAULT_SIZE.getHeight());

    final Scene rootScene = new Scene(rootPane);
    primaryStage.setScene(rootScene);
    primaryStage.show();

    setUserAgentStylesheet("css/main.css");

    // enterFullScreenMode();
    showStartupScreen();
  }

  private void enterFullScreenMode() {
    primaryStage.setFullScreen(true);
  }

  private void showStartupScreen() {
    rootPane.getChildren().add(new StartupScreen(new StartupViewModel()));
  }

}
