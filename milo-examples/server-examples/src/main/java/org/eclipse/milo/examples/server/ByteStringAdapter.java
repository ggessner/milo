package org.eclipse.milo.examples.server;

import static org.eclipse.milo.examples.server.ByteStringHelper.bytesFromHex;
import static org.eclipse.milo.examples.server.ByteStringHelper.bytesToHex;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

public class ByteStringAdapter implements JsonSerializer<ByteString>, JsonDeserializer<ByteString> {

    @Override
    public JsonElement serialize(ByteString byteString, Type type,
        JsonSerializationContext jsonSerializationContext) {
        if (byteString == null || byteString.bytes() == null)
            return new JsonPrimitive("");
        /*System.out.println(bytesToHex(byteString.bytes()));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("bytes", new JsonPrimitive(bytesToHex(byteString.bytes())));
        return  jsonObject;*/
        return new JsonPrimitive(bytesToHex(byteString.bytes()));
    }

    @Override
    public ByteString deserialize(JsonElement jsonElement, Type type,
        JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement != null) {
            String tmp = jsonElement.getAsString();
            if (tmp != null) {
                return new ByteString(bytesFromHex(tmp));
            }
        }
        throw new JsonParseException("failed to parse ByteString element!");
    }
}
