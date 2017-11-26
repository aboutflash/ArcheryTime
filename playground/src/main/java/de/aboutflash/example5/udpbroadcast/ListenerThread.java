package de.aboutflash.example5.udpbroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
@SuppressWarnings({"Duplicates", "WeakerAccess"})
public class ListenerThread extends TransmissionThread {

  private final Logger log;
  private long announcementCount;

  public ListenerThread() throws UnknownHostException, SocketException {
    log = Logger.getLogger("Listener Thread");
    log.setLevel(Level.INFO);
  }

  @Override
  public void run() {
    detectServer();
  }

  @SuppressWarnings("ObjectAllocationInLoop")
  private void detectServer() {

    byte[] buffer = new byte[RECEIVE_BUFFER_SIZE_BYTES];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, getLocalAddress(), RESPONSE_PORT);

    try (DatagramSocket socket = new DatagramSocket(packet.getPort(), packet.getAddress())) {
      socket.setBroadcast(true);

      do {
        try {

          socket.receive(packet);
          processData(packet);

        } catch (IOException ignored) { }

      } while (true);

    } catch (SocketException ignored) { }

  }

  private void processData(DatagramPacket packet) {
    final String s = new String(packet.getData()).trim();

    if (isAnnouncement(s)) {
      serverAnnouncementReceived();
    }
  }

  private boolean isAnnouncement(String message) {
    return message.equals(DISCOVERY_IDENTIFIER);
  }

  private void serverAnnouncementReceived() {
    announcementCount++;
    log.info(String.format("Announcements received: %12d", announcementCount));
  }

}
