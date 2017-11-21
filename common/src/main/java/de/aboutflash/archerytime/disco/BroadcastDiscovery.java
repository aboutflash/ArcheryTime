package de.aboutflash.archerytime.disco;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

import static de.aboutflash.archerytime.disco.CommonConstants.BROADCAST_PORT;
import static de.aboutflash.archerytime.disco.CommonConstants.REC_BUF_SIZE_BYTES;

/**
 * Class
 *
 * @author falk@aboutflash.de on 20.11.2017.
 */
public class BroadcastDiscovery extends DiscoveryThread {

  private final static Logger log = Logger.getAnonymousLogger();
  private final String serverIdentifier;

  private DatagramSocket socket;


  public BroadcastDiscovery(final String identifier) {
    serverIdentifier = identifier.substring(0, Math.min(identifier.length(), REC_BUF_SIZE_BYTES));
  }

  @Override
  public void run() {

    //noinspection OverlyBroadCatchBlock
    try {
      createSocket();

      //noinspection InfiniteLoopStatement
      while (true) {
        log.info(">>>Ready to receive broadcast packets!");

        //Receive a packet
        byte[] recvBuf = new byte[REC_BUF_SIZE_BYTES];
        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);

        // thread stops here waiting for a packet
        socket.receive(packet);

        //Packet received
        log.info(">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
        log.info(">>>Packet received; data: " + new String(packet.getData()));

        //See if the packet holds the right command (message)
        String message = new String(packet.getData()).trim();

        if (message.equals(serverIdentifier)) {
          byte[] sendData = serverIdentifier.getBytes();

          //Send a response
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
          socket.send(sendPacket);

          log.info(">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
        }
      }
    } catch (IOException e) {
      log.severe(e.getMessage());
    }
  }

  private void createSocket() throws UnknownHostException, SocketException {
    //Keep a socket open to listen to all the UDP traffic that is destined for this port
    socket = new DatagramSocket(BROADCAST_PORT, InetAddress.getByName("0.0.0.0"));
    socket.setBroadcast(true);
  }
}
