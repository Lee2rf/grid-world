import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.grid.UnboundedGrid;
import java.awt.Color;


/**
 * This class runs a world that contains spiral bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class SpiralBugRunner {

	public static void main(String[] args) {
		UnboundedGrid<Actor> grid = new UnboundedGrid<Actor>();
		ActorWorld world = new ActorWorld(grid);
		//Set the initial sideLength = 2
		SpiralBug lws = new SpiralBug(2);
		//set the color
		lws.setColor(Color.RED);
		//set the initial location
		world.add(new Location(20,18), lws);
		world.show();
	}
}