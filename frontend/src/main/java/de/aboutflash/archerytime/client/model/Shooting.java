package de.aboutflash.archerytime.client.model;

import static de.aboutflash.archerytime.client.model.States.STOPPED;
import static de.aboutflash.archerytime.client.model.States.SWAPPING;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public class Shooting extends ClockStateBase {

  public Shooting(final StateMachine sm) {
    super(sm);
  }

  @Override
  public void startCountdown() {
    getSm().getCountdownModel().startCounting(
        getSm().getCountdownModel().getShootingSeconds()
    );
  }

  @Override
  public void finishCountdown() {
    if (getSm().getCountdownModel().isLastTurn()) {
      getSm().setState(STOPPED);
      getSm().getCountdownModel().reset();
    } else {
      getSm().getCountdownModel().nextTurn();
      getSm().setState(SWAPPING);
      getSm().startCountdown();
    }
  }

//  @Override
//  public void skipToNext() {
//    if (getSm().getCountdownModel().isLastTurn())
//      getSm().setState(STOPPED);
//    else
//      getSm().setState(SWAPPING);
//  }
//
//  @Override
//  public void pause() {
//    getSm().setState(PAUSED);
//  }
}
