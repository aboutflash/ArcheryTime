package de.aboutflash.archerytime.client.ui;

import de.aboutflash.archerytime.client.model.CountdownModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Class
 *
 * @author falk@aboutflash.de on 12.11.2017.
 */
public class CountDownScreen extends StackPane {
  @SuppressWarnings("WeakerAccess")
  public static final String DEFAULT_STYLE_CLASS = "count-down-screen";

  private final Label counter;

  private ObjectProperty<CountdownModel> countdownModel = new SimpleObjectProperty<CountdownModel>() {
    @Override
    public void set(final CountdownModel newValue) {
      super.set(newValue);

      if (newValue != null) {
        counter.textProperty().bind(newValue.currentCountdownSecondsProperty().asString());
      }
    }
  };

  public CountDownScreen() {
    getStyleClass().add(DEFAULT_STYLE_CLASS);

    counter = new Label("0");
    getChildren().add(counter);
    StackPane.setAlignment(counter, Pos.CENTER);
  }

  public CountdownModel getCountdownModel() {
    return countdownModel.get();
  }

  public void setCountdownModel(final CountdownModel countdownModel) {
    this.countdownModel.set(countdownModel);
  }

  public ObjectProperty<CountdownModel> countdownModelProperty() {
    return countdownModel;
  }

}
