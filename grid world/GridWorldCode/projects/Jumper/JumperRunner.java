import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;



/**
 * This class runs a world that contains a jumper and a bug and a rock and a flower, added at random
 * locations. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * JumperRunner for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class JumperRunner {

	public static void main(String args[]) {
		ActorWorld world = new ActorWorld();
		Jumper lws = new Jumper(Color.GREEN);
		world.add(lws);
		world.add(new Jumper());
		world.add(new Rock());
		world.add(new Flower());
		world.add(new Bug());
		world.show();
	}
}