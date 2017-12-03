package de.aboutflash.tryout.scaling;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;


/**
 * Class
 *
 * @author falk@aboutflash.de on 03.12.2017.
 */
public class ScalingTester extends Application {

  private final static Rectangle2D DEFAULT_SIZE = new Rectangle2D(0.0, 0.0, 1920.0 * 0.25, 1080.0 * 0.25);


  private Pane rootPane;
  private Stage primaryStage;

  @Override
  public void start(final Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    rootPane = new StackPane();

    Label labelA = new Label("links");
    Label labelB = new Label("rechts");

    final HBox hBox = new HBox(labelA, labelB);
    hBox.prefHeight(Region.USE_COMPUTED_SIZE);
    rootPane.getChildren().setAll(hBox);
    StackPane.setAlignment(hBox, Pos.CENTER);

    primaryStage.setWidth(DEFAULT_SIZE.getWidth());
    primaryStage.setHeight(DEFAULT_SIZE.getHeight());
    final Scene scene = new Scene(new Group(rootPane));
    primaryStage.setScene(scene);
    primaryStage.show();

    letterbox(scene, rootPane);
    registerHotKeys();
  }

  private void registerHotKeys() {
    // fullscreen F
    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      if (event.getCode() == KeyCode.F) {
        if (primaryStage.isFullScreen())
          exitFullScreenMode();
        else
          enterFullScreenMode();
        event.consume();
      }
    });

    // exit Ctrl-C
    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      if (event.getCode() == KeyCode.C
          && event.isControlDown()) {
        Platform.exit();
        System.exit(0);
      }
    });
  }


  private void enterFullScreenMode() {
    primaryStage.setFullScreen(true);
  }

  private void exitFullScreenMode() {
    primaryStage.setFullScreen(false);
  }


  private void letterbox(final Scene scene, final Pane contentPane) {
    final double initWidth = scene.getWidth();
    final double initHeight = scene.getHeight();
    final double ratio = initWidth / initHeight;

    SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
    scene.widthProperty().addListener(sizeListener);
    scene.heightProperty().addListener(sizeListener);
  }

  private static class SceneSizeChangeListener implements ChangeListener<Number> {
    private final Scene scene;
    private final double ratio;
    private final double initHeight;
    private final double initWidth;
    private final Pane contentPane;

    public SceneSizeChangeListener(Scene scene, double ratio, double initHeight, double initWidth, Pane contentPane) {
      this.scene = scene;
      this.ratio = ratio;
      this.initHeight = initHeight;
      this.initWidth = initWidth;
      this.contentPane = contentPane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
      final double newWidth = scene.getWidth();
      final double newHeight = scene.getHeight();

      double scaleFactor =
          newWidth / newHeight > ratio
              ? newHeight / initHeight
              : newWidth / initWidth;

      if (scaleFactor >= 1) {
        Scale scale = new Scale(scaleFactor, scaleFactor);
        scale.setPivotX(0);
        scale.setPivotY(0);
        scene.getRoot().getTransforms().setAll(scale);

        contentPane.setPrefWidth(newWidth / scaleFactor);
        contentPane.setPrefHeight(newHeight / scaleFactor);
      } else {
        contentPane.setPrefWidth(Math.max(initWidth, newWidth));
        contentPane.setPrefHeight(Math.max(initHeight, newHeight));
      }
    }
  }

}
