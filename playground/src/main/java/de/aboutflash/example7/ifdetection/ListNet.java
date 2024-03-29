package de.aboutflash.example7.ifdetection;

/**
 * From
 * https://docs.oracle.com/javase/tutorial/networking/nifs/listing.html
 */

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import static java.lang.System.out;

public class ListNet {

  public static void main(String args[]) throws SocketException {
    Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
    for (NetworkInterface netint : Collections.list(nets))
      displayInterfaceInformation(netint);
  }

  static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
    out.printf("Display name: %s\n", netint.getDisplayName());
    out.printf("Name: %s\n", netint.getName());
    Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
    for (InetAddress inetAddress : Collections.list(inetAddresses)) {
      out.printf("InetAddress: %s\n", inetAddress);
    }
    out.printf("\n");
  }
}
