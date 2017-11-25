package de.aboutflash.example5.udpbroadcast;

import de.aboutflash.net.InetSubnetDetector;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Class
 *
 * @author falk@aboutflash.de on 25.11.2017.
 */
public class TransmissionThread extends Thread {

  final static int TX_BUF_SIZE_BYTES = 0xff;
  final static int RESPONSE_PORT = 10101;
  final static String DISCOVERY_IDENTIFIER = "BROADCAST_ARCHERY_TIME_CONTROL_SERVER_V0.0.1";

  protected DatagramSocket socket;
  protected InetAddress address;


  public TransmissionThread() throws UnknownHostException, SocketException {
    setDaemon(true);
    address = InetSubnetDetector.getSubnetBroadcastAddress();
    System.out.println(address.getHostAddress());
  }

  @Override
  public void run() {
    try {
      initializeSocketForBroadcasting();
    } catch (SocketException ignored) { }
  }

  protected void initializeSocketForBroadcasting() throws SocketException {
    socket = new DatagramSocket();
    socket.setBroadcast(true);
  }


}
