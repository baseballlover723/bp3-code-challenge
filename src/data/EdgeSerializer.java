package data;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EdgeSerializer implements JsonSerializer<Edge> {
	@Override
	public JsonElement serialize(Edge edge, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();
		object.addProperty("from", edge.getFrom().getId());
		object.addProperty("to", edge.getTo().getId());
		return object;
	}
}
