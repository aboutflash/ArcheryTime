package de.aboutflash.archerytime.client.model;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;


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
  private final IntegerProperty currentCountdownSeconds = new SimpleIntegerProperty(0);


  public BooleanProperty runningProperty() {
    return running;
  }

  public boolean isRunning() {
    return running.get();
  }

  public IntegerProperty warmUpSecondsProperty() {
    return warmUpSeconds;
  }

  public int getWarmUpSeconds() {
    return warmUpSeconds.get();
  }

  public void setWarmUpSeconds(final int seconds) {
    warmUpSeconds.set(seconds);
  }

  public IntegerProperty shootingSecondsProperty() {
    return shootingSeconds;
  }

  public int getShootingSeconds() {
    return shootingSeconds.get();
  }

  public void setShootingSeconds(final int seconds) {
    shootingSeconds.set(seconds);
  }

  public IntegerProperty turnSwapSecondsProperty() {
    return turnSwapSeconds;
  }

  public int getTurnSwapSeconds() {
    return turnSwapSeconds.get();
  }

  public void setTurnSwapSeconds(final int seconds) {
    turnSwapSeconds.set(seconds);
  }

  public IntegerProperty currentCountdownSecondsProperty() {
    return currentCountdownSeconds;
  }

  public int getCurrentCountdownSeconds() {
    return currentCountdownSeconds.get();
  }

  private void setCurrentCountdownSeconds(final int currentCountdownSeconds) {
    this.currentCountdownSeconds.set(currentCountdownSeconds);
  }

  public void startSequence() {
    setCurrentCountdownSeconds(getWarmUpSeconds());
    final Timer timer = new Timer(true);
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> setCurrentCountdownSeconds(getCurrentCountdownSeconds() - 1));
      }
    };
    timer.scheduleAtFixedRate(task, 1000, 1000);
  }

  @Override
  public String toString() {
    return "CountdownModel{" +
        "running=" + running +
        ", warmUpSeconds=" + warmUpSeconds +
        ", shootingSeconds=" + shootingSeconds +
        ", turnSwapSeconds=" + turnSwapSeconds +
        ", currentCountdownSeconds=" + currentCountdownSeconds +
        '}';
  }
}
