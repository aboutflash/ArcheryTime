package de.aboutflash.example4.discovery;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.logging.Logger;

import static de.aboutflash.example4.discovery.CommonConstants.*;
import static de.aboutflash.net.InetSubnetDetector.getSubnetBroadcastAddress;

/**
 * Class
 *
 * @author falk@aboutflash.de on 20.11.2017.
 */
public class DiscoClient {
  private final static Logger log = Logger.getAnonymousLogger();

  private DatagramSocket socket;
  private InetAddress serverIp;


  public InetAddress getServerIp() {
    startDiscovery();

    return serverIp;
  }

  public void stop() {
    closeSocket();
  }

  /**
   * Find the server using UDP broadcast.
   */
  private void startDiscovery() {
    setupSocket();

    broadcastToLocalNetwork();
    //  broadcastOverAllInterfaces();

    receiveData();
    closeSocket();
  }

  /**
   * Open a random port to send the package
   */
  private void setupSocket() {
    closeSocket();

    try {
      socket = new DatagramSocket();
      socket.setBroadcast(true);
    } catch (SocketException e) {
      log.severe(e.getMessage());
      closeSocket();
    }
  }

  /**
   * Don't forget to close the socket again.
   */
  private void closeSocket() {
    if (socket != null && socket.isConnected())
      socket.close();
  }

  /**
   * The discovery challenge bytes.
   */
  private byte[] getTxBytes() {
    return DISCOVERY_IDENTIFIER.getBytes();
  }

  /**
   * Send a broadcast to the local default network we are on.
   * The detection whether this truly is the default network may not be perfectly reliable.
   * Therefor the method {@link DiscoClient::broadcastOverAllInterfaces} exists.
   */
  private void broadcastToLocalNetwork() {
    final byte[] txBytes = getTxBytes();
    try {
      DatagramPacket sendPacket = new DatagramPacket(txBytes, txBytes.length, getSubnetBroadcastAddress(), BROADCAST_PORT);
      socket.send(sendPacket);
      log.info(">>> Request packet sent to: " + sendPacket.getAddress());

    } catch (IOException e) {
      log.severe(e.getMessage());
      closeSocket();
    }
  }

  /**
   * Sends out broadcast messages via all detectable network interfaces.
   * This may be overly broad and I would prefer to use broadcastToLocalNetwork() instead.
   */
  private void broadcastOverAllInterfaces() {
    final byte[] txBytes = getTxBytes();

    try {
      // Broadcast the message over all the network interfaces
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();

        if (networkInterface.isLoopback() || !networkInterface.isUp()) {
          continue; // Don't want to broadcast to the loopback interface
        }

        for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
          InetAddress broadcast = interfaceAddress.getBroadcast();
          if (broadcast == null) {
            continue;
          }

          // Send the broadcast package!
          try {
            DatagramPacket sendPacket = new DatagramPacket(txBytes, txBytes.length, broadcast, BROADCAST_PORT);
            socket.send(sendPacket);

          } catch (IOException ignored) { }

          log.info(">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
        }
      }
      log.info(">>> Done looping over all network interfaces.");

    } catch (SocketException e) {
      log.severe(e.getMessage());
      closeSocket();
    }
  }


  private void receiveData() {
    log.info(">>> Now waiting for a reply!");

    //Wait for a response
    byte[] recvBuf = new byte[REC_BUF_SIZE_BYTES];

    DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);

    try {
      socket.receive(receivePacket);

      //We have a response
      log.info(">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

      //Check if the message is correct
      String message = StringUtils.trimToEmpty(new String(receivePacket.getData()).trim());

      if (message.equals(DISCOVERY_IDENTIFIER)) {
        serverIp = receivePacket.getAddress();

      }

    } catch (IOException e) {
      log.severe(e.getMessage());
    }
  }


}
