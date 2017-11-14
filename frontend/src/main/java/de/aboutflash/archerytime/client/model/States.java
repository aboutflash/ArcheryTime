package de.aboutflash.archerytime.client.model;

import java.lang.reflect.InvocationTargetException;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public enum States {
  STOPPED(Stopped.class),
  SHOOTING(Shooting.class),
  STEADY(Steady.class),
  SWAPPING(Swapping.class),
  PAUSED(Paused.class);

  private final Class<? extends ClockState> state;

  private ClockState instance;

  public void init(StateMachine stateMachine) {
    try {
      //noinspection JavaReflectionMemberAccess
      instance = state.getConstructor(StateMachine.class)
          .newInstance(stateMachine);

    } catch (InstantiationException
        | IllegalAccessException
        | NoSuchMethodException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  public ClockState get() {
    return instance;
  }

  States(final Class<? extends ClockState> state) {
    this.state = state;
  }
}
