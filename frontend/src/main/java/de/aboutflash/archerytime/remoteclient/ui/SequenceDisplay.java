package de.aboutflash.archerytime.remoteclient.ui;

import de.aboutflash.archerytime.model.ScreenState;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;

/**
 * Class
 *
 * @author falk@aboutflash.de on 30.11.2017.
 */
public class SequenceDisplay extends StackPane {
  private static final String DEFAULT_STYLE_CLASS = "sequence-display";

  final ObjectProperty<ScreenState.Sequence> sequenceProperty = new SimpleObjectProperty<>(ScreenState.Sequence.AB);

  public SequenceDisplay() {
    getStyleClass().add(DEFAULT_STYLE_CLASS);
  }

  public ObjectProperty<ScreenState.Sequence> sequencePropertyProperty() {
    return sequenceProperty;
  }

  public ScreenState.Sequence getSequence() {
    return sequenceProperty.get();
  }

  public void setSequence(final ScreenState.Sequence value) {
    sequenceProperty.set(value);
  }


}
