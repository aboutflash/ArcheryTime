package de.aboutflash.archerytime.disco;

import java.net.*;
import java.util.Enumeration;

import static java.net.InetAddress.getLocalHost;
import static java.net.NetworkInterface.getNetworkInterfaces;

/**
 * Class
 *
 * @author falk@aboutflash.de on 20.11.2017.
 */
public class NetworkTestingApp {

  public static void main(String[] args) throws SocketException, UnknownHostException {
    final NetworkInterface netIf = getDefaultIf("43");
    final InetAddress netIf_2 = getLocalHost();
    final NetworkInterface udpIf = discoByUdp();
    System.out.println(netIf);
  }

  private static NetworkInterface getDefaultIf(String hostname) throws UnknownHostException, SocketException {
    final Enumeration<NetworkInterface> netifs = getNetworkInterfaces();

    // hostname is passed to your method
    InetAddress myAddr = InetAddress.getByName(hostname);

    while (netifs.hasMoreElements()) {
      NetworkInterface networkInterface = netifs.nextElement();
      Enumeration<InetAddress> inAddrs = networkInterface.getInetAddresses();
      while (inAddrs.hasMoreElements()) {
        InetAddress inAddr = inAddrs.nextElement();
        if (inAddr.equals(myAddr)) {
          return networkInterface;
        }
      }
    }
    return null;
  }

  private static NetworkInterface discoByUdp() throws SocketException, UnknownHostException {
    try (DatagramSocket s = new DatagramSocket()) {
      s.connect(InetAddress.getByAddress(new byte[]{1, 1, 1, 1}), 0);
//      return NetworkInterface.getByInetAddress(s.getLocalAddress()).getHardwareAddress();
      return NetworkInterface.getByInetAddress(s.getLocalAddress());
    }
  }


}
