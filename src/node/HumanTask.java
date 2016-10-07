package node;

public class HumanTask extends Node {

	public HumanTask(int id, String name) {
		super(id, name);
	}

	@Override
	public boolean isServiceTask() {
		return false;
	}

	@Override
	public boolean isHumanTask() {
		return true;
	}

	@Override
	public boolean isGateway() {
		return false;
	}

}
