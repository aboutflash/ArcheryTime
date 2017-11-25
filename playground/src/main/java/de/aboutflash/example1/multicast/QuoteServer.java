package de.aboutflash.example1.multicast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */

import java.io.IOException;

public class QuoteServer {

  public static void main(String[] args) throws IOException {
    new QuoteServerThread().start();
  }
}
