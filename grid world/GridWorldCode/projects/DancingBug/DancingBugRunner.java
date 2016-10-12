import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.*;

/**
 * This class runs a world that contains dancing bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class DancingBugRunner {

	public static void main(String[] args) {
		//create the array
		int turns[] = new int[5];
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			int degree= Math.abs(random.nextInt() % 8);
			//not go back, not go straight
			while(degree == 4 || degree == 8)
				degree= Math.abs(random.nextInt() % 8);
			
			turns[i] = degree;
		}

		ActorWorld world = new ActorWorld();
		DancingBug lws = new DancingBug(turns);
		lws.setColor(Color.RED);
		world.add(new Location(3,3), lws);
		world.show();
	}
}