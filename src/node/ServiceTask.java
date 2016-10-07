package node;

public class ServiceTask extends Node {

	public ServiceTask(int id, String name) {
		super(id, name);
	}

	@Override
	public boolean isServiceTask() {
		return true;
	}

	@Override
	public boolean isHumanTask() {
		return false;
	}

	@Override
	public boolean isGateway() {
		return false;
	}

}
