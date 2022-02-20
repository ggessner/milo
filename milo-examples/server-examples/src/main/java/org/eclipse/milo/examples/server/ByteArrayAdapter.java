package org.eclipse.milo.examples.server;

import static org.eclipse.milo.examples.server.ByteStringHelper.bytesFromHex;
import static org.eclipse.milo.examples.server.ByteStringHelper.bytesToHex;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ByteArrayAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
    @Override
    public JsonElement serialize(byte[] byteString, Type type,
        JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(bytesToHex(byteString));
    }

    @Override
    public byte[] deserialize(JsonElement jsonElement, Type type,
        JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement != null) {
            String tmp = jsonElement.getAsString();
            if (tmp != null) {
                return bytesFromHex(tmp);
            }
        }
        throw new JsonParseException("failed to parse ByteString element!");
    }
}
