package de.aboutflash.archerytime.client.ui;

import de.aboutflash.archerytime.client.model.CountdownModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.converter.NumberStringConverter;

import static java.lang.Math.floor;

/**
 * Class
 *
 * @author falk@aboutflash.de on 12.11.2017.
 */
public class CountDownScreen extends StackPane {
  @SuppressWarnings("WeakerAccess")
  public static final String DEFAULT_STYLE_CLASS = "count-down-screen";

  private final Label counter;
  private StringBinding counterBinding;

  private ObjectProperty<CountdownModel> countdownModel = new SimpleObjectProperty<CountdownModel>() {
    @Override
    public void set(final CountdownModel newValue) {
      final CountdownModel oldValue = get();
      super.set(newValue);

      // discard old binding if exists
      if (oldValue != null) {
        Bindings.unbindBidirectional(counter.textProperty(), oldValue.currentCountdownSecondsProperty());
      }

      final NumberStringConverter formatter = new NumberStringConverter(newValue.getPattern()) {
        @Override
        public String toString(final Number value) {
          return super.toString(floor((double) value));
        }
      };

      // install new binding
      Bindings.bindBidirectional(counter.textProperty(), newValue.currentCountdownSecondsProperty(), formatter);

    }
  };

  public CountDownScreen() {
    getStyleClass().add(DEFAULT_STYLE_CLASS);

    counter = new Label("");
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
