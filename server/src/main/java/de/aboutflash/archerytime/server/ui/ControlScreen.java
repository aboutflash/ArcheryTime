package de.aboutflash.archerytime.server.ui;

import de.aboutflash.archerytime.server.model.ControlViewModel;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Class
 *
 * @author falk@aboutflash.de on 28.11.2017.
 */
public class ControlScreen extends StackPane {
  public static final String DEFAULT_STYLE_CLASS = "control-screen";
  private final ControlViewModel viewModel;
  private final Label status = new Label();

  public ControlScreen(ControlViewModel model) {
    viewModel = model;

    getStyleClass().add(DEFAULT_STYLE_CLASS);
    drawUi();
  }

  private void drawUi() {
    status.textProperty().bind(viewModel.statusProperty());
    getChildren().add(status);
  }

}
