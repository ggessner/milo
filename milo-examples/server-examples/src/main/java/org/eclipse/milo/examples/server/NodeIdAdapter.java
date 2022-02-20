package org.eclipse.milo.examples.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.UUID;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class NodeIdAdapter implements JsonSerializer<NodeId>, JsonDeserializer<NodeId> {

    @Override
    public NodeId deserialize(JsonElement jsonElement, Type type,
        JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String nodeIdType = jsonObject.getAsJsonPrimitive().getAsString().toLowerCase(Locale.ROOT);
        int namespace = jsonObject.getAsJsonPrimitive("namespace").getAsInt();
        switch (nodeIdType) {
            case "numeric":
                return new NodeId(namespace,
                    jsonObject.getAsJsonPrimitive("identifier").getAsInt()
                );
            case "guid":
                return new NodeId(namespace,
                    UUID.fromString(jsonObject.getAsJsonPrimitive("identifier").getAsString())
                );
            case "string":
                return new NodeId(namespace,
                    jsonObject.getAsJsonPrimitive("identifier").getAsString()
                );
            case "opaque":
                return new NodeId(namespace,
                    (ByteString) jsonDeserializationContext.deserialize(
                        jsonObject.getAsJsonObject("identifier"), ByteString.class));
            default:
                throw new JsonParseException("could not determine type of NodeId");
        }
    }

    @Override
    public JsonElement serialize(NodeId nodeId, Type type,
        JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("namespace", nodeId.getNamespaceIndex().intValue());
        switch (nodeId.getType()) {
            case Numeric:
                UInteger numericIdentifier = (UInteger) nodeId.getIdentifier();
                obj.add("identifier", jsonSerializationContext.serialize(numericIdentifier));
                obj.addProperty("type", "numeric");
                break;
            case String:
                String stringIdentifier = (String) nodeId.getIdentifier();
                obj.add("identifier", jsonSerializationContext.serialize(stringIdentifier));
                obj.addProperty("type", "string");
                break;
            case Guid:
                UUID uuidIdentifier = (UUID) nodeId.getIdentifier();
                obj.add("identifier", jsonSerializationContext.serialize(uuidIdentifier));
                obj.addProperty("type", "guid");
                break;
            case Opaque:
                ByteString byteString = (ByteString) nodeId.getIdentifier();
                obj.add("identifier",
                    jsonSerializationContext.serialize(byteString, ByteString.class));
                obj.addProperty("type", "opaque");
                break;
        }
        return obj;
    }
}