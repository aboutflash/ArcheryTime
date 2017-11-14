package de.aboutflash.archerytime.client.model;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public interface ClockState {

  void startCountdown();

  void finishCountdown();

  void skipToNext();

  void pause();

}
