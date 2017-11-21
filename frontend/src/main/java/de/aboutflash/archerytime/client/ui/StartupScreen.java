package de.aboutflash.archerytime.client.ui;

import de.aboutflash.archerytime.client.model.StartupViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class StartupScreen extends StackPane {
  @SuppressWarnings("WeakerAccess")
  public static final String DEFAULT_STYLE_CLASS = "startup-screen";
  private final StartupViewModel viewModel;

  public StartupScreen(StartupViewModel viewModel) {
    this.viewModel = viewModel;
    getStyleClass().add(DEFAULT_STYLE_CLASS);

    final Label connecting = new Label("connecting ...");
    getChildren().add(connecting);
    StackPane.setAlignment(connecting, Pos.CENTER);
  }


}
