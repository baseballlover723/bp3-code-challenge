package node;

public abstract class Node {
	protected int id;
	protected String name;

	public Node(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public abstract boolean isServiceTask();
	public abstract boolean isHumanTask();
	public abstract boolean isGateway();
}
