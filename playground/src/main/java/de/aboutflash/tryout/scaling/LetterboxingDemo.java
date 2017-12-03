package de.aboutflash.tryout.scaling;

/**
 * Class
 *
 * @author falk@aboutflash.de on 03.12.2017.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;

/* Main JavaFX application class */
public class LetterboxingDemo extends Application {
  private static final String IMAGE_URL = "http://farm9.staticflickr.com/8205/8264285440_f5617efb71_b.jpg";
  private Image image;

  public static void main(String[] args) { launch(args); }

  @Override
  public void init() throws Exception {
    image = new Image(IMAGE_URL);
  }

  @Override
  public void start(final Stage stage) throws IOException {
    Pane root = createPane();

    Scene scene = new Scene(new Group(root));
    stage.setScene(scene);
    stage.show();

    letterbox(scene, root);
    stage.setFullScreen(true);
  }

  private StackPane createPane() {
    final int MAX_HEIGHT = 400;

    StackPane stack = new StackPane();

    Pane content = new Pane();

    ImageView imageView = new ImageView(image);
    imageView.setPreserveRatio(true);
    imageView.setFitHeight(MAX_HEIGHT);
    double width = imageView.getLayoutBounds().getWidth();
    content.getChildren().add(imageView);

    content.setMaxSize(width, MAX_HEIGHT);
    content.setClip(new Rectangle(width, MAX_HEIGHT));

    stack.getChildren().add(content);
    stack.setStyle("-fx-background-color: midnightblue");

    return stack;
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

