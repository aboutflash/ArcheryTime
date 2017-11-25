package de.aboutflash.example1.multicast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 * Class
 *
 * @author falk@aboutflash.de on 24.11.2017.
 */
@SuppressWarnings({"OverlyBroadThrowsClause", "UseOfSystemOutOrSystemErr", "ObjectAllocationInLoop"})
public class QuoteServerThread extends Thread {

  private static int packetCount = 0;

  protected DatagramSocket socket = null;
  protected BufferedReader in = null;
  protected boolean moreQuotes = true;

  public QuoteServerThread() throws IOException {
    this("QuoteServerThread");
  }

  public QuoteServerThread(final String name) throws IOException {
    super(name);
    socket = new DatagramSocket(Constants.TX_PORT);

    // in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/example1-one-liners.txt")));
    in = new BufferedReader(new FileReader("example1-one-liners.txt"));
  }

  @Override
  public void run() {

    while (moreQuotes) {
      try {
        byte[] buf = new byte[Constants.BUF_SIZE];

        // receive request
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // figure out response
        String dString = null;
        if (in == null)
          dString = new Date().toString();
        else
          dString = getNextQuote();

        buf = dString.getBytes();

        // send the response to the client at "address" and "port"
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
        System.out.println("sent " + packetCount++);
      } catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }

  protected String getNextQuote() {
    String returnValue = null;
    try {
      if ((returnValue = in.readLine()) == null) {
        in.close();
        moreQuotes = false;
        returnValue = "No more quotes. Goodbye.";
      }
    } catch (IOException e) {
      returnValue = "IOException occurred in server.";
    }
    return returnValue;
  }

  @Override
  public String toString() {
    return "QuoteServerThread{" +
        "socket=" + socket +
        ", in=" + in +
        ", moreQuotes=" + moreQuotes +
        '}';
  }
}
