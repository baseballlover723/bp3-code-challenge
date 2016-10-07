package node;

public class Gateway extends Node {

	public Gateway(int id, String name) {
		super(id, name);
	}

	@Override
	public boolean isServiceTask() {
		return false;
	}

	@Override
	public boolean isHumanTask() {
		return false;
	}

	@Override
	public boolean isGateway() {
		return true;
	}

}
