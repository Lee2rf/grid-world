import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * This class runs a world that contains z bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class ZBugRunner {

	 public static void main(String[] args) {
	 	ActorWorld world = new ActorWorld();
	 	ZBug lws = new ZBug(3);
	 	lws.setColor(Color.RED);
	 	world.add(new Location(3,3), lws);
	 	world.show();
	 }
}