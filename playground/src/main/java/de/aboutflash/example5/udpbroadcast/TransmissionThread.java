package de.aboutflash.example5.udpbroadcast;

import de.aboutflash.net.InetSubnetDetector;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Base class for sender/receiver threads.
 *
 * @author falk@aboutflash.de on 25.11.2017.
 */
@SuppressWarnings("WeakerAccess")
abstract public class TransmissionThread extends Thread {

  private final static Logger LOG = Logger.getAnonymousLogger();
  final static int RECEIVE_BUFFER_SIZE_BYTES = 0x1000;
  final static int RESPONSE_PORT = 10101;
  final static String DISCOVERY_IDENTIFIER = "BROADCAST_ARCHERY_TIME_CONTROL_SERVER_V0.0.1";

  private final InetAddress subnetAddress;
  private final InetAddress localAddress;


  protected TransmissionThread() throws UnknownHostException, SocketException {
    setDaemon(true);

    localAddress = InetSubnetDetector.getLocalAddress();
    LOG.info(localAddress.getHostAddress());

    subnetAddress = InetSubnetDetector.getSubnetBroadcastAddress();
    LOG.info(subnetAddress.getHostAddress());
  }

  public InetAddress getSubnetAddress() {
    return subnetAddress;
  }

  public InetAddress getLocalAddress() {
    return localAddress;
  }
}
