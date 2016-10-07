package data;

import java.util.ArrayList;

public class Node {
	private int id;
	private String name;
	private String type;

	public Node(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}
	
	public boolean isServiceTask() {
		return this.type.equals("ServiceTask");
	}

	public boolean isHumanTask() {
		return this.type.equals("HumanTask");
	}

	public boolean isGateway() {
		return this.type.equals("Gateway");
	}

	public boolean isStart() {
		return this.type.equals("Start");
	}

	public boolean isEnd() {
		return this.type.equals("End");
	}

	// could probably break this into a few more methods
	public void remove(ArrayList<Node> nodes, ArrayList<Edge> edges) {
		ArrayList<Node> nodesFromThisNode = new ArrayList<Node>();
		ArrayList<Node> nodesToThisNode = new ArrayList<Node>();
		ArrayList<Edge> edgesToDelete = new ArrayList<Edge>();
		for (Edge edge : edges) {
			if (this.equals(edge.getFrom())) {
				nodesFromThisNode.add(edge.getTo());
				edgesToDelete.add(edge);
			} else if (this.equals(edge.getTo())) {
				nodesToThisNode.add(edge.getFrom());
				edgesToDelete.add(edge);
			}
		}
	
		for (Node from : nodesToThisNode) {
			for (Node to : nodesFromThisNode) {
				Edge newEdge = new Edge(from, to);
				edges.add(newEdge);
			}
		}
		
		for (Edge edge: edgesToDelete) {
			edges.remove(edge);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node other = (Node) o;
			return this.id == other.id && this.name == other.name && this.type == other.type;
		}
		return false;
	}
}
