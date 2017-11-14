package de.aboutflash.archerytime.client.model;

import static de.aboutflash.archerytime.client.model.States.STEADY;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public class Stopped extends ClockStateBase {

  public Stopped(final StateMachine sm) {
    super(sm);
  }

  @Override
  public void startCountdown() {
    getSm().setState(STEADY);
    getSm().startCountdown();
  }

}
