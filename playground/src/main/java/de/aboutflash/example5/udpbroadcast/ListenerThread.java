package de.aboutflash.example5.udpbroadcast;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
public class ListenerThread extends TransmissionThread {

  private InetAddress serverIp;

  public ListenerThread() throws UnknownHostException, SocketException { }

  @Override
  public void run() {
    super.run();

    detectServer();
  }

  private void detectServer() {

    //Wait for a response
    byte[] buffer = new byte[TX_BUF_SIZE_BYTES];

    DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

    do {
      try {
        socket.receive(receivePacket);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(receivePacket.getAddress().getHostAddress());
      //We have received data

      //Check if the message is correct
      String message = StringUtils.trimToEmpty(new String(receivePacket.getData()).trim());

      if (message.equals(DISCOVERY_IDENTIFIER)) {
        serverIp = receivePacket.getAddress();
        System.out.printf("server found: %s", serverIp.getHostAddress());
      }

    } while (true);

  }

}
