package de.aboutflash.archerytime.client.main;

import de.aboutflash.archerytime.client.model.StartupViewModel;
import de.aboutflash.archerytime.client.net.Client;
import de.aboutflash.archerytime.client.ui.StartupScreen;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class RemoteClient extends Application {
  private final static Rectangle2D DEFAULT_SIZE = new Rectangle2D(0.0, 0.0, 960.0, 560.0);
  private final Pane rootPane = new StackPane();
  private Client client;
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
    connect();
  }

  private void enterFullScreenMode() {
    primaryStage.setFullScreen(true);
  }

  private void showStartupScreen() {
    rootPane.getChildren().add(new StartupScreen(new StartupViewModel()));
  }

  private void connect() {

    try {
      client = new Client(1);
    } catch (Exception e) {
      e.printStackTrace();
    }

    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(client);

  }
}
