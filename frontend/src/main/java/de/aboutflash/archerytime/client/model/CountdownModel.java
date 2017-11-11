package de.aboutflash.archerytime.client.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Class
 *
 * @author falk@aboutflash.de on 11.11.2017.
 */
@SuppressWarnings("WeakerAccess") // public constants for testing reasons
public class CountdownModel {

  public static final int DEFAULT_WARM_UP_SECONDS = 10;
  public static final int DEFAULT_TURN_SWAP_SECONDS = 30;
  public static final int DEFAULT_SHOOTING_SECONDS = 120;

  private final BooleanProperty running = new SimpleBooleanProperty(false);
  private final IntegerProperty warmUpSeconds = new SimpleIntegerProperty(DEFAULT_WARM_UP_SECONDS);
  private final IntegerProperty shootingSeconds = new SimpleIntegerProperty(DEFAULT_SHOOTING_SECONDS);
  private final IntegerProperty turnSwapSeconds = new SimpleIntegerProperty(DEFAULT_TURN_SWAP_SECONDS);


  public boolean isRunning() {
    return running.get();
  }

  public BooleanProperty runningProperty() {
    return running;
  }

  public int getWarmUpSeconds() {
    return warmUpSeconds.get();
  }

  public IntegerProperty warmUpSecondsProperty() {
    return warmUpSeconds;
  }

  public void setWarmUpSeconds(final int seconds) {
    this.warmUpSeconds.set(seconds);
  }

  public int getShootingSeconds() {
    return shootingSeconds.get();
  }

  public IntegerProperty shootingSecondsProperty() {
    return shootingSeconds;
  }

  public void setShootingSeconds(final int seconds) {
    this.shootingSeconds.set(seconds);
  }

  public int getTurnSwapSeconds() {
    return turnSwapSeconds.get();
  }

  public IntegerProperty turnSwapSecondsProperty() {
    return turnSwapSeconds;
  }

  public void setTurnSwapSeconds(final int seconds) {
    this.turnSwapSeconds.set(seconds);
  }

  @Override
  public String toString() {
    return "CountdownModel{" +
        "running=" + running +
        ", warmUpSeconds=" + warmUpSeconds +
        ", shootingSeconds=" + shootingSeconds +
        ", turnSwapSeconds=" + turnSwapSeconds +
        '}';
  }
}
