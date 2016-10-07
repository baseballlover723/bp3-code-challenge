package node;

import java.util.HashSet;

public class Node {
	private int id;
	private String name;
	private String type;
	protected HashSet<Node> connections;

	public Node(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.connections = new HashSet<Node>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isServiceTask() {
		return this.type == "ServiceTask";
	}
	
	public boolean isHumanTask()  {
		return this.type == "HumanTask";
	}
	
	public boolean isGateway() {
		return this.type == "Gateway";
	}
	
	public boolean isStart() {
		return this.type == "Start";
	}
	
	public boolean isEnd() {
		return this.type == "End";
	}
	
	public void addConnection(Node newConnection) {
		this.connections.add(newConnection);
	}
	
	public void removeConnection(Node toRemove) {
		this.connections.remove(toRemove);
	}
	
	public boolean containsConnection(Node node) {
		return this.connections.contains(node);
	}
	
	public boolean hasConnections() {
		return this.connections.isEmpty();
	}
}
