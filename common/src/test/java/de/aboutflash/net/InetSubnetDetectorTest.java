package de.aboutflash.net;

import org.junit.Test;

import java.net.InetAddress;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SuppressWarnings("OverlyBroadThrowsClause")
public class InetSubnetDetectorTest {

  @Test
  public void getSubnetBroadcastAddress() throws Exception {
    final InetAddress subnetBroadcastAddress = InetSubnetDetector.getSubnetBroadcastAddress();
    assertThat(subnetBroadcastAddress, notNullValue());
    assertThat(subnetBroadcastAddress.getAddress()[3], equalTo((byte) 0xff));
  }

}
