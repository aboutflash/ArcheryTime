package de.aboutflash.archerytime.client.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class StartupViewModel {
  private StringProperty serverAddress = new SimpleStringProperty("0.0.0.0");
  private BooleanProperty connected = new SimpleBooleanProperty(false);

  public String getServerAddress() {
    return serverAddress.get();
  }

  public StringProperty serverAddressProperty() {
    return serverAddress;
  }

  public void setServerAddress(final String serverAddress) {
    this.serverAddress.set(serverAddress);
  }


  public boolean isConnected() {
    return connected.get();
  }

  public BooleanProperty connectedProperty() {
    return connected;
  }

  public void setConnected(final boolean connected) {
    this.connected.set(connected);
  }

}
