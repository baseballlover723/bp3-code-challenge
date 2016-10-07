package computation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.Edge;
import data.EdgeSerializer;
import data.Node;

public class Solver {
	private String outputPath;
	private String inputPath;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;

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

		this.nodes = parseNodes(jsonNodes);
		this.edges = parseEdges(jsonEdges);
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

	private ArrayList<Edge> parseEdges(JsonArray jsonEdges) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (JsonElement jsonElement : jsonEdges) {
			JsonObject jsonEdge = jsonElement.getAsJsonObject();
			int fromIndex = jsonEdge.get("from").getAsInt();
			int toIndex = jsonEdge.get("to").getAsInt();
			
			Node fromNode = this.nodes.get(fromIndex);
			Node toNode = this.nodes.get(toIndex);
			
			Edge edge = new Edge(fromNode, toNode);
			edges.add(edge);
		}
		return edges;
	}

	public void solve() throws IOException {
		ArrayList<Node> nodesToRemove = new ArrayList<Node>(); // to avoid ConcurrentModificationException
		for (Node node : this.nodes) {
			if (node.isServiceTask()) {
				node.remove(this.nodes, this.edges);
				nodesToRemove.add(node);
			}
		}
		for (Node node : nodesToRemove) {
			this.nodes.remove(node);
		}
		this.writeOutput();
	}

	public void writeOutput() throws IOException {
		Gson gson = new GsonBuilder().registerTypeAdapter(Edge.class, new EdgeSerializer()).setPrettyPrinting().create();
		JsonObject outputJson = new JsonObject();
		
		outputJson.add("nodes", gson.toJsonTree(this.nodes));
		outputJson.add("edges", gson.toJsonTree(this.edges));
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputPath));
		writer.write(gson.toJson(outputJson));
		writer.close();
	}

	public String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
