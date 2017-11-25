package de.aboutflash.example1.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */

@SuppressWarnings({"ObjectAllocationInLoop", "CallToDateToString", "ImplicitNumericConversion", "OverlyBroadCatchBlock", "UseOfSystemOutOrSystemErr"})
public class MulticastServerThread extends QuoteServerThread {

  private static int packetCount = 0;

  public MulticastServerThread() throws IOException {
    super("MulticastServerThread");
  }

  @Override
  public void run() {
    while (moreQuotes) {
      try {

        // construct quote
        String dString = null;
        if (in == null)
          dString = new Date().toString();
        else
          dString = getNextQuote();
        byte[] buf = dString.getBytes();

        // send it
        InetAddress group = InetAddress.getByName(Constants.IP_GROUP);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, Constants.TX_PORT);
        socket.send(packet);
        System.out.println("sent " + packetCount++);

        // sleep for a while
        try {
          sleep((long) (Math.random() * Constants.INTERVAL));
        } catch (InterruptedException ignored) { }
      } catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }
}
