package de.aboutflash.archerytime.disco;

import java.net.InetAddress;
import java.util.logging.Logger;

/**
 * Class
 *
 * @author falk@aboutflash.de on 20.11.2017.
 */
public class DiscoClientApp {

  private static final Logger log = Logger.getAnonymousLogger();

  private static DiscoClient discoClient;

  public static void main(String[] args) {
    discoClient = new DiscoClient();

    final InetAddress serverIp = discoClient.getServerIp();

    log.info("--> " + serverIp.getHostAddress());
  }
}
