package de.aboutflash.example5.udpbroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Class
 *
 * @author falk@aboutflash.de on 25.11.2017.
 */
@SuppressWarnings("WeakerAccess")
public class SenderThread extends TransmissionThread {

  private static final long DISCOVERY_INTERVAL_MILLIS = 1_000L;
  private final Logger log;
  private long announcementCount;

  public SenderThread() throws UnknownHostException, SocketException {
    log = Logger.getLogger(getClass().getName());
  }


  @Override
  public void run() {
    announceServer();
  }

  @SuppressWarnings({"BusyWait", "NestedTryStatement", "InfiniteLoopStatement"})
  private void announceServer() {
    byte[] sendData = DISCOVERY_IDENTIFIER.getBytes();

    try (DatagramSocket s = new DatagramSocket()) {
      s.setBroadcast(true);

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, getSubnetAddress(), RESPONSE_PORT);

      do {
        try {
          //Send server announcement
          s.send(sendPacket);
          announcementCount++;
          log.info(toString());

          sleep(DISCOVERY_INTERVAL_MILLIS);

        } catch (IOException | InterruptedException e) {
          log.severe(e.getMessage());
        }

      } while (true);

    } catch (SocketException e) {
      log.severe(e.getMessage());
    }

  }

  @Override
  public String toString() {
    return String.format("Sender: announced server %12d times %n", announcementCount);
  }
}
