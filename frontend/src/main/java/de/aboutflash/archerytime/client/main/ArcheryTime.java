package de.aboutflash.archerytime.client.main;

import de.aboutflash.archerytime.client.model.CountdownModel;
import de.aboutflash.archerytime.client.model.StateMachine;
import de.aboutflash.archerytime.client.ui.CountDownScreen;
import de.aboutflash.archerytime.client.ui.StopScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ArcheryTime extends Application {

  private final static Rectangle2D DEFAULT_SIZE = new Rectangle2D(0.0, 0.0, 480.0, 320.0);
  private final Pane rootPane = new StackPane();
  private final CountdownModel countdownModel = new CountdownModel();
  private final StateMachine csm = new StateMachine(countdownModel);
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

    registerHotKeys();
//    enterFullScreenMode();
    showStop();
  }

  private void registerHotKeys() {
    // fullscreen F
    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      if (!primaryStage.isFullScreen() && event.getCode() == KeyCode.F) {
        enterFullScreenMode();
        event.consume();
      }
    });

    // exit Ctrl-Alt-X
    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      if (event.getCode() == KeyCode.X
          && event.isControlDown()
          && event.isAltDown()) {
        Platform.exit();
      }
    });

    // start SPACE
    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      if (event.getCode() == KeyCode.SPACE)
        showCountdown();
    });
  }

  private void enterFullScreenMode() {
    primaryStage.setFullScreen(true);
  }

  private void showStop() {
    rootPane.getChildren().add(new StopScreen());
  }

  private void showCountdown() {
    final CountDownScreen countDownScreen = new CountDownScreen();
    countDownScreen.setCountdownModel(countdownModel);
    rootPane.getChildren().setAll(countDownScreen);

    csm.startCountdown();
  }
}
