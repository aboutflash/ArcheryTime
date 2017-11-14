package de.aboutflash.archerytime.client.model;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import static com.google.common.base.Preconditions.checkState;


/**
 * Class
 *
 * @author falk@aboutflash.de on 11.11.2017.
 */
@SuppressWarnings("WeakerAccess") // public constants for testing reasons
public class CountdownModel {

  private static final int INTERVAL_MILLIS = 200;

  public static final int DEFAULT_WARM_UP_SECONDS = 5;
  public static final int DEFAULT_TURN_SWAP_SECONDS = 15;
  public static final int DEFAULT_SHOOTING_SECONDS = 30;

  private final LinkedList<String> turns = new LinkedList<>(FXCollections.observableArrayList("A B", "C D"));
  private final BooleanProperty running = new SimpleBooleanProperty(false);
  private final IntegerProperty warmUpSeconds = new SimpleIntegerProperty(DEFAULT_WARM_UP_SECONDS);
  private final IntegerProperty shootingSeconds = new SimpleIntegerProperty(DEFAULT_SHOOTING_SECONDS);
  private final IntegerProperty turnSwapSeconds = new SimpleIntegerProperty(DEFAULT_TURN_SWAP_SECONDS);
  private final IntegerProperty currentCountdownSeconds = new SimpleIntegerProperty(0);
  private final StringProperty currentTurn = new SimpleStringProperty(turns.getFirst());
  private TimerTask countTask;


  public BooleanProperty runningProperty() {
    return running;
  }

  public boolean isRunning() {
    return running.get();
  }

  private void setRunning(boolean value) {
    running.set(value);
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

  public String getCurrentTurn() {
    return currentTurn.get();
  }

  public StringProperty currentTurnProperty() {
    return currentTurn;
  }

  public void setCurrentTurn(final String currentTurn) {
    this.currentTurn.set(currentTurn);
  }

  public void nextTurn() {
    int idx = turns.indexOf(getCurrentTurn());
    setCurrentTurn(turns.get(++idx % turns.size()));
  }

  public boolean isLastTurn() {
    return turns.getLast().equals(getCurrentTurn());
  }


  final Timer timer = new Timer(true);

  public void startCounting(int startSeconds) {
    checkState(!isRunning(), "Cannot start over an already running timer");
    initCountdownTimer(startSeconds);
    setRunning(true);
    countTask = new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> {
          setCurrentCountdownSeconds(getCurrentCountdownSeconds() - 1);
          if (getCurrentCountdownSeconds() <= 0)
            cancel();
        });
      }

      @Override
      public boolean cancel() {
        return super.cancel();
      }
    };
    timer.scheduleAtFixedRate(countTask, INTERVAL_MILLIS, INTERVAL_MILLIS);
  }

  public void pause() {
    checkState(isRunning(), "Cannot pause timer when it is not running.");
    clearTimer();
    setRunning(false);
  }

  public void resume() {
    checkState(!isRunning(), "Cannot resume an already running timer");
    checkState(getCurrentCountdownSeconds() > 0, "Timer is already finished.");
//    startCounting(getCurrentCountdownSeconds());
    countTask.run();
  }

  public void reset() {
    clearTimer();
    setRunning(false);
  }

  private void clearTimer() {
    if (countTask != null)
      countTask.cancel();
  }

  private void initCountdownTimer(int startSeconds) {
    setCurrentCountdownSeconds(startSeconds);
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
