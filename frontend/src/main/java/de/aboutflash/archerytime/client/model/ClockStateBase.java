package de.aboutflash.archerytime.client.model;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public abstract class ClockStateBase implements ClockState {


  private final StateMachine sm;

  public ClockStateBase(final StateMachine sm) {
    this.sm = sm;
  }

  protected StateMachine getSm() {
    return sm;
  }

  @Override
  public void startCountdown() {
    System.out.println("NOT AVAILABLE: startCountdown ");
  }

  @Override
  public void finishCountdown() {
    System.out.println("NOT AVAILABLE: finishCountdown ");
  }

  @Override
  public void skipToNext() {
    System.out.println("NOT AVAILABLE: skipToNext ");
  }

  @Override
  public void pause() {
    System.out.println("NOT AVAILABLE: pause ");
  }
}
