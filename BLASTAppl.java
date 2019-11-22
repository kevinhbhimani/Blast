/*
 * This class contains the main method which creates and runs the simulation.
 * 
 * Created by: Kevin H. Bhimani
 * Date: November 11, 2017
*/

public class BLASTAppl {

	public static void main(String[] args) {
		Debug.turnOff();
		Simulation sim = new Simulation();

		sim.run();

		sim.showResult();
	}

}
