package de.aboutflash.archerytime.client.model;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public class StateMachine implements ClockState {

  private static final States INITIAL_STATE = States.STOPPED;

  private final CountdownModel countdownModel;

  private ClockState previous;
  private ClockState current;

  public StateMachine(CountdownModel countdownModel) {
    this.countdownModel = countdownModel;
    initStatesEnum();
    reset();
    this.countdownModel.currentCountdownSecondsProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.equals(0)) {
        finishCountdown();
      }
    });
  }

  private void transition(ClockState to) {
    previous = current;
    current = to;
  }

  private void initStatesEnum() {
    for (States s : States.values()) {
      s.init(this);
    }
  }

  CountdownModel getCountdownModel() {
    return countdownModel;
  }

  void setState(States state) {
    transition(state.get());
  }

  void reset() {
    previous = null;
    current = INITIAL_STATE.get();
  }

  @Override
  public void startCountdown() {
    countdownModel.reset();
    current.startCountdown();
  }

  @Override
  public void finishCountdown() {
    current.finishCountdown();
  }

  @Override
  public void skipToNext() {
    current.skipToNext();
  }

  @Override
  public void pause() {
    current.pause();
  }

}
