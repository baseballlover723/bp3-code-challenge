package computation;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Solver solver = new Solver("./diagram.json", "output.json");
			solver.solve();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find input file");
		}
	}
}
