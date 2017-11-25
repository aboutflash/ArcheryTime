package de.aboutflash.example4.discovery;

/**
 * Class
 *
 * @author falk@aboutflash.de on 20.11.2017.
 */
public class DiscoveryThread implements Runnable {

  @Override
  public void run() { }

  public static DiscoveryThread getInstance() {
    return Holder.INSTANCE;
  }

  @SuppressWarnings("UtilityClass")
  private static final class Holder {
    private static final DiscoveryThread INSTANCE = new DiscoveryThread();
  }

}
