package computation;

public class Main {

	public static void main(String[] args) {
		Solver solver = new Solver(".diagram.json", "output.json");
		solver.solve();
	}

}
