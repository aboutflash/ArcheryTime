package de.aboutflash.archerytime.remoteclient.ui;

import de.aboutflash.archerytime.remoteclient.model.StartupViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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

    final Label connecting = new Label("finding server");

    final Label attempts = new Label("");
    attempts.textProperty().bind(viewModel.attemptsProperty().asString("attempts %d"));

    final VBox vBox = new VBox(connecting, attempts);
    vBox.setAlignment(Pos.CENTER);
    StackPane.setAlignment(vBox, Pos.CENTER);

    getChildren().add(vBox);
  }


}
