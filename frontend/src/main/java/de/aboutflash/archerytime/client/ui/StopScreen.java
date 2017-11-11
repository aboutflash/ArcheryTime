package de.aboutflash.archerytime.client.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


/**
 * Class
 *
 * @author falk@aboutflash.de on 11.11.2017.
 */
@SuppressWarnings("ClassHasNoToStringMethod")
public class StopScreen extends StackPane {

  @SuppressWarnings("WeakerAccess")
  public static final String DEFAULT_STYLE_CLASS = "stop-screen";

  public StopScreen() {
    getStyleClass().add(DEFAULT_STYLE_CLASS);

    final Label stop = new Label("STOP");
    getChildren().add(stop);
    StackPane.setAlignment(stop, Pos.CENTER);
  }
}
