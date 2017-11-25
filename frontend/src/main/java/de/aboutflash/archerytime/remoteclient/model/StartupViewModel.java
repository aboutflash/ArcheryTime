package de.aboutflash.archerytime.remoteclient.model;

import javafx.beans.property.*;

/**
 * Class
 *
 * @author falk@aboutflash.de on 19.11.2017.
 */
public class StartupViewModel {
  private StringProperty serverAddress = new SimpleStringProperty("0.0.0.0");
  private BooleanProperty connected = new SimpleBooleanProperty(false);
  private IntegerProperty attempts = new SimpleIntegerProperty(0);

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


  public int getAttempts() {
    return attempts.get();
  }

  public IntegerProperty attemptsProperty() {
    return attempts;
  }

  public void setAttempts(final int attempts) {
    this.attempts.set(attempts);
  }

  public int nextAttempt() {
    setAttempts(getAttempts() + 1);
    return getAttempts();
  }
}
