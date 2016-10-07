package computation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import node.Node;

public class Solver {
	private String outputPath;
	private String inputPath;

	public Solver(String inputPath, String outputPath) throws IOException {
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.parse();
	}

	public void parse() throws IOException {
		String jsonString = readFile(inputPath, StandardCharsets.UTF_8);
		JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
		JsonArray jsonNodes = jsonObject.get("nodes").getAsJsonArray();
		JsonArray jsonEdges = jsonObject.get("edges").getAsJsonArray();

		ArrayList<Node> nodes = parseNodes(jsonNodes);
		parseEdges(nodes, jsonEdges);

		System.out.println((new Gson()).toJson(nodes));
	}

	private ArrayList<Node> parseNodes(JsonArray jsonNodes) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (JsonElement jsonElement : jsonNodes) {
			JsonObject jsonNode = jsonElement.getAsJsonObject();
			int id = jsonNode.get("id").getAsInt();
			String name = jsonNode.get("name").getAsString();
			String type = jsonNode.get("type").getAsString();
			Node node = new Node(id, name, type);
			nodes.add(node);
		}
		
		nodes.sort((Node node1, Node node2) -> node1.getId() - node2.getId());
		return nodes;
	}

	private void parseEdges(ArrayList<Node> nodes, JsonArray jsonEdges) {
		for (JsonElement jsonElement : jsonEdges) {
			JsonObject jsonEdge = jsonElement.getAsJsonObject();
			int fromIndex = jsonEdge.get("from").getAsInt();
			int toIndex = jsonEdge.get("to").getAsInt();
			
			Node fromNode = nodes.get(fromIndex);
			Node toNode = nodes.get(toIndex);
			
			fromNode.addConnection(toNode);
		}
	}

	public void solve() {

		this.writeOutput();
	}

	public void writeOutput() {

	}

	public String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
