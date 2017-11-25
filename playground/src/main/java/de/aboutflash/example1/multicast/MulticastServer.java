package de.aboutflash.example1.multicast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */

public class MulticastServer {
  public static void main(String[] args) throws java.io.IOException {
    new MulticastServerThread().start();
  }
}
