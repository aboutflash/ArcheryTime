package de.aboutflash.archerytime.client.model;

import static de.aboutflash.archerytime.client.model.States.SHOOTING;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public class Swapping extends ClockStateBase {

  public Swapping(final StateMachine sm) {
    super(sm);
  }

  @Override
  public void startCountdown() {
    getSm().getCountdownModel().startCounting(
        getSm().getCountdownModel().getTurnSwapSeconds()
    );
  }

  @Override
  public void finishCountdown() {
    getSm().setState(SHOOTING);
    getSm().startCountdown();
  }

//  @Override
//  public void pause() {
//    getSm().setState(PAUSED);
//  }
//
}
