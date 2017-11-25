package de.aboutflash.example1.multicast;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static de.aboutflash.example1.multicast.Constants.*;

@SuppressWarnings({"ObjectAllocationInLoop", "OverlyBroadThrowsClause"})
public class MulticastClient {

  public static void main(final String[] args) throws IOException {

    MulticastSocket socket = new MulticastSocket(RC_PORT);
    InetAddress address = InetAddress.getByName(IP_GROUP);
    socket.joinGroup(address);

    DatagramPacket packet;

    // get a few quotes
    for (int i = 0; i < 5; i++) {

      byte[] buf = new byte[BUF_SIZE];
      packet = new DatagramPacket(buf, buf.length);
      socket.receive(packet);

      String received = new String(packet.getData(), 0, packet.getLength());
      System.out.println("Quote of the Moment: " + received);
    }

    socket.leaveGroup(address);
    socket.close();
  }

}
