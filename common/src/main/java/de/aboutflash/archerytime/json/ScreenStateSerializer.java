package de.aboutflash.archerytime.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.aboutflash.archerytime.model.ScreenState;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A JSON/Jackson Serializer for {@link ScreenState}.
 */
public class ScreenStateSerializer extends StdSerializer<ScreenState> {

  /**
   * Constructor.
   */
  public ScreenStateSerializer() {
    super(ScreenState.class);
  }

  @Override
  public void serialize(ScreenState value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    checkNotNull(value, "No value given");

    gen.writeStartObject();
    gen.writeStringField("label", value.getLabel());
    gen.writeEndObject();
  }
}
