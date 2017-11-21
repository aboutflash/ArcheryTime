package de.aboutflash.archerytime.client.net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class Client extends Thread {

  private DatagramSocket socket;
  private InetAddress address;
  private int expectedServerCount;
  private byte[] buf;

  public Client(int expectedServerCount) throws Exception {
    setDaemon(true);
    this.expectedServerCount = expectedServerCount;
    this.address = InetAddress.getByName("255.255.255.255");
    System.out.println(address);
  }

  @Override
  public void run() {
    try {
      discoverServers("end");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int discoverServers(String msg) throws IOException {
    initializeSocketForBroadcasting();
    copyMessageOnBuffer(msg);

    // When we want to broadcast not just to local network, call listAllBroadcastAddresses() and execute broadcastPacket for each value.
    broadcastPacket(address);

    return receivePackets();
  }

  List<InetAddress> listAllBroadcastAddresses() throws SocketException {
    List<InetAddress> broadcastList = new ArrayList<>();
    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
    while (interfaces.hasMoreElements()) {
      NetworkInterface networkInterface = interfaces.nextElement();

      if (networkInterface.isLoopback() || !networkInterface.isUp()) {
        continue;
      }

      broadcastList.addAll(networkInterface.getInterfaceAddresses()
                               .stream()
                               .filter(address -> address.getBroadcast() != null)
                               .map(address -> address.getBroadcast())
                               .collect(Collectors.toList()));
    }
    return broadcastList;
  }

  private void initializeSocketForBroadcasting() throws SocketException {
    socket = new DatagramSocket();
    socket.setBroadcast(true);
  }

  private void copyMessageOnBuffer(String msg) {
    buf = msg.getBytes();
  }

  private void broadcastPacket(InetAddress address) throws IOException {
    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
    socket.send(packet);
  }

  private int receivePackets() throws IOException {
    int serversDiscovered = 0;
    while (serversDiscovered != expectedServerCount) {
      receivePacket();
      serversDiscovered++;
    }
    return serversDiscovered;
  }

  private void receivePacket() throws IOException {
    clearBuffer();
    DatagramPacket packet = new DatagramPacket(buf, buf.length);
    socket.receive(packet);
  }

  private void clearBuffer() {
    buf = new byte[]{};
  }

  public void close() {
    socket.close();
  }
}
