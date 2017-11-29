package de.aboutflash.archerytime.json;

import de.aboutflash.archerytime.model.ScreenState;
import org.junit.Before;
import org.junit.Test;

import static de.aboutflash.archerytime.model.ScreenState.Screen.MESSAGE;
import static de.aboutflash.archerytime.model.ScreenState.Screen.SHOOT_UP30;
import static de.aboutflash.archerytime.model.ScreenState.Sequence.AB;

public class JSONObjectSerializerTest {

  JSONObjectSerializer jos;

  @Before
  public void setUp() throws Exception {
    jos = new JSONObjectSerializer();
  }

  @Test
  public void serializeScreenState() throws Exception {
    String json;

    json = jos.serializeScreenState(new ScreenState(MESSAGE, "Alice likes Bob"));
    System.out.println(json);

    json = jos.serializeScreenState(new ScreenState(SHOOT_UP30, AB, 120));
    System.out.println(json);
  }

  @Test
  public void deserializeScreenState() throws Exception {
    ScreenState obj;

    obj = jos.deserializeScreenState("{\"scr\":\"MESSAGE\",\"msg\":\"Alice likes Bob\"}");
    System.out.println(obj);

    obj = jos.deserializeScreenState("{\"scr\":\"SHOOT_T30\",\"seq\":\"AB\",\"time\":120}");
    System.out.println(obj);
  }


}
