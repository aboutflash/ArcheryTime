package de.aboutflash.archerytime.server.net;

import de.aboutflash.archerytime.server.model.FITACycleModel;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public class Announcer {

  private Thread announceThread;
  private final FITACycleModel model;

  public Announcer(final FITACycleModel model) {
    this.model = model;

    try {
      announceThread = new AnnounceThread(model);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    runForever();
  }

  private void runForever() {
    announceThread.run();
  }
}
