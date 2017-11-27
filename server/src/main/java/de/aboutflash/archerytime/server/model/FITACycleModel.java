package de.aboutflash.archerytime.server.model;

import de.aboutflash.archerytime.model.ScreenState;

/**
 * Class
 *
 * @author falk@aboutflash.de on 27.11.2017.
 */
public class FITACycleModel {

  private double remainingTimeMillis = 0.0;

  public double getRemainingTimeMillis() {
    return remainingTimeMillis;
  }

  public void setRemainingTimeMillis(double value) {
    remainingTimeMillis = value;
  }


  synchronized public ScreenState getScreenState() {
    return new ScreenState(ScreenState.Screen.SHOOT, ScreenState.Sequence.AB, (int) Math.floor(remainingTimeMillis));
  }
}
