package de.aboutflash.archerytime.client.model;

import com.google.common.base.Strings;
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

  private static final long INTERVAL_MILLIS = 50L; // interval for the internal timer clock
  private static final double SPEED_FACTOR = 1.0; // for less boring debugging
  private static final double STEP_WIDTH = (double) INTERVAL_MILLIS / 1_000.0d; // fraction of second to subtract
  private static final double OPTICAL_ADDITION_SECONDS = 0.95; // used to create the illusion the 000 is actually the end of the countdown

  public static final double DEFAULT_WARM_UP_SECONDS = 10.0;
  public static final double DEFAULT_TURN_SWAP_SECONDS = 30.0;
  public static final double DEFAULT_SHOOTING_SECONDS = 120.0;

  private final LinkedList<String> turns = new LinkedList<>(FXCollections.observableArrayList("A B", "C D", "E F"));
  private final BooleanProperty running = new SimpleBooleanProperty(false);
  private final DoubleProperty warmUpSeconds = new SimpleDoubleProperty(DEFAULT_WARM_UP_SECONDS);
  private final DoubleProperty shootingSeconds = new SimpleDoubleProperty(DEFAULT_SHOOTING_SECONDS);
  private final DoubleProperty turnSwapSeconds = new SimpleDoubleProperty(DEFAULT_TURN_SWAP_SECONDS);
  private final DoubleProperty currentCountdownSeconds = new SimpleDoubleProperty(0.0);
  private final StringProperty currentTurn = new SimpleStringProperty(turns.getFirst());
  private final StringProperty pattern = new SimpleStringProperty("000");

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


  public DoubleProperty warmUpSecondsProperty() {
    return warmUpSeconds;
  }

  public double getWarmUpSeconds() {
    return warmUpSeconds.get();
  }

  public void setWarmUpSeconds(final double seconds) {
    warmUpSeconds.set(seconds);
  }


  public DoubleProperty shootingSecondsProperty() {
    return shootingSeconds;
  }

  public double getShootingSeconds() {
    return shootingSeconds.get();
  }

  public void setShootingSeconds(final double seconds) {
    shootingSeconds.set(seconds);
  }


  public DoubleProperty turnSwapSecondsProperty() {
    return turnSwapSeconds;
  }

  public double getTurnSwapSeconds() {
    return turnSwapSeconds.get();
  }

  public void setTurnSwapSeconds(final double seconds) {
    turnSwapSeconds.set(seconds);
  }


  public DoubleProperty currentCountdownSecondsProperty() {
    return currentCountdownSeconds;
  }

  public double getCurrentCountdownSeconds() {
    return currentCountdownSeconds.get();
  }

  private void updateCurrentCountdownSeconds(final double seconds) {
    currentCountdownSeconds.set(seconds);
  }


  public String getPattern() {
    return pattern.get();
  }

  public StringProperty patternProperty() {
    return pattern;
  }

  public void setPattern(final String pattern) {
    this.pattern.set(pattern);
  }


  public String getCurrentTurn() {
    return currentTurn.get();
  }

  public StringProperty currentTurnProperty() {
    return currentTurn;
  }

  public void setCurrentTurn(final String turn) {
    currentTurn.set(turn);
  }

  public void nextTurn() {
    int idx = turns.indexOf(getCurrentTurn());
    setCurrentTurn(turns.get(++idx % turns.size()));
  }

  public boolean isLastTurn() {
    return turns.getLast().equals(getCurrentTurn());
  }


  final Timer timer = new Timer(true);

  public void startCounting(final double startSeconds) {
    checkState(!isRunning(), "Cannot start over an already running timer");
    initCountdownTimer(startSeconds);
    setRunning(true);
    countTask = new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> {
          updateCurrentCountdownSeconds(getCurrentCountdownSeconds() - STEP_WIDTH);
          if (getCurrentCountdownSeconds() <= 0.0) {
            updateCurrentCountdownSeconds(0.0);
            cancel();
          }
        });
      }
    };
    timer.scheduleAtFixedRate(countTask, INTERVAL_MILLIS, Math.round((double) INTERVAL_MILLIS * (1.0 / SPEED_FACTOR)));
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

  private void initCountdownTimer(final double startSeconds) {
    updateCurrentCountdownSeconds(startSeconds + OPTICAL_ADDITION_SECONDS);

    // digits of the new initial value
    final int length = String.valueOf((int) startSeconds).length();
    setPattern(Strings.repeat("0", length));
  }

  @Override
  public String toString() {
    return "CountdownModel{" +
        "turns=" + turns +
        ", running=" + running +
        ", warmUpSeconds=" + warmUpSeconds +
        ", shootingSeconds=" + shootingSeconds +
        ", turnSwapSeconds=" + turnSwapSeconds +
        ", currentCountdownSeconds=" + currentCountdownSeconds +
        ", currentTurn=" + currentTurn +
        ", countTask=" + countTask +
        ", timer=" + timer +
        '}';
  }
}
