package de.aboutflash.archerytime.model;

import java.io.Serializable;

/**
 * Class
 *
 * @author falk@aboutflash.de on 27.11.2017.
 */
public class ScreenState implements Serializable {
  private String label;

  public ScreenState(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
