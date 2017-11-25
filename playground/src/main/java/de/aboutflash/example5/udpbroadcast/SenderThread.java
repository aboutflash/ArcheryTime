package de.aboutflash.example5.udpbroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Class
 *
 * @author falk@aboutflash.de on 25.11.2017.
 */
public class SenderThread extends TransmissionThread {

  private static final long DISCOVERY_INTERVAL_MILLIS = 1_000L;
  private long announcementCount;

  public SenderThread() throws UnknownHostException, SocketException { }


  @Override
  public void run() {
    super.run();

    announceServer();
  }

  private void announceServer() {

    byte[] sendData = DISCOVERY_IDENTIFIER.getBytes();
    try {
      address = InetAddress.getByAddress(new byte[]{(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff});
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, RESPONSE_PORT);

    do {
      try {
        //Send server announcement
        socket.send(sendPacket);
        System.out.printf("sent announcements count: %12d %n", announcementCount++);
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        sleep(DISCOVERY_INTERVAL_MILLIS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    } while (true);
  }

}
