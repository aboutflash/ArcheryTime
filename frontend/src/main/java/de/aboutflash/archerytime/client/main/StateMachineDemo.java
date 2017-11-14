package de.aboutflash.archerytime.client.main;

import de.aboutflash.archerytime.client.model.CountdownModel;
import de.aboutflash.archerytime.client.model.StateMachine;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class
 *
 * @author falk@aboutflash.de on 13.11.2017.
 */
public class StateMachineDemo extends Application {

  public static void main(final String... args) {
    launch(args);
  }

  private final StateMachine fsm = new StateMachine(new CountdownModel());
  private final int[] msgs = {0, 1, 2, 3, 2, 1, 0};

  @Override
  public void start(final Stage primaryStage) throws Exception {
    for (int msg : msgs) {
      switch (msg) {
        case 0:
          fsm.startCountdown();
          break;
        case 1:
          fsm.finishCountdown();
          break;
        case 2:
          fsm.skipToNext();
          break;
        case 3:
          fsm.pause();
          break;
      }
    }
  }
}
