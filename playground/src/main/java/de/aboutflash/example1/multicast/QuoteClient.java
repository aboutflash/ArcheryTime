package de.aboutflash.example1.multicast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@SuppressWarnings({"UseOfSystemOutOrSystemErr", "OverlyBroadThrowsClause"})
public class QuoteClient {

  public static void main(final String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Usage: java QuoteClient <hostname>");
      return;
    }

    // get a datagram socket
    try (DatagramSocket socket = new DatagramSocket()) {

      // send request
      byte[] buf = new byte[Constants.BUF_SIZE];
      InetAddress address = InetAddress.getByName(args[0]);
      DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Constants.RC_PORT);
      socket.send(packet);

      // get response
      packet = new DatagramPacket(buf, buf.length);
      socket.receive(packet);

      // display response
      String received = new String(packet.getData(), 0, packet.getLength());
      System.out.println("Quote of the Moment: " + received);

      socket.close();
    }
  }
}
