package de.aboutflash.example1.multicast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */

@SuppressWarnings("UtilityClass")
final class Constants {
  static final String IP_GROUP = "225.0.113.0";
  static final int TX_PORT = 10005;
  static final int RC_PORT = 10006;

  static final int BUF_SIZE = 0xff;
  static final long INTERVAL = 1_000L;

  private Constants() {}
}
