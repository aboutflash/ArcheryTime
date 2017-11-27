package de.aboutflash.archerytime.json;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.aboutflash.archerytime.model.ScreenState;

import java.io.IOException;


/**
 * A JSON/Jackson Deserializer for {@link ScreenState}.
 */
public class ScreenStateDeserializer extends BasicDeserializer<ScreenState> {

  /**
   * Constructor.
   */
  public ScreenStateDeserializer(ObjectMapper mapper) {
    super(ScreenState.class, mapper);
  }

  @Override
  protected ScreenState deserialize(JsonNode node) throws IOException {
    final String label = node.get("label").asText();
    final ScreenState screenState = new ScreenState(label);

    return screenState;
  }

}
