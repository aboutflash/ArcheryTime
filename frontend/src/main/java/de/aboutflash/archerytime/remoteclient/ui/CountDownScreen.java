package de.aboutflash.archerytime.remoteclient.ui;

import de.aboutflash.archerytime.remoteclient.model.CountdownViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Class
 *
 * @author falk@aboutflash.de on 23.11.2017.
 */
public class CountDownScreen extends StackPane {
  @SuppressWarnings("WeakerAccess")
  public static final String DEFAULT_STYLE_CLASS = "countdown-screen";
  private final CountdownViewModel viewModel;

  public CountDownScreen(CountdownViewModel viewModel) {
    this.viewModel = viewModel;
    getStyleClass().add(DEFAULT_STYLE_CLASS);

    final Label connecting = new Label(viewModel.getTimeFormatted());
    getChildren().add(connecting);
    StackPane.setAlignment(connecting, Pos.CENTER);
  }
}
