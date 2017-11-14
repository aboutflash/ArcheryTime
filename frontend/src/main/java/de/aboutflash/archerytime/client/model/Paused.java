package de.aboutflash.archerytime.client.model;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public class Paused extends ClockStateBase {

  public Paused(final StateMachine sm) {
    super(sm);
  }

  @Override
  public void startCountdown() {
    getSm().getCountdownModel().resume();
  }

}
