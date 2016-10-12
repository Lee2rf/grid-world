import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * This class runs a world that contains circle bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class CircleBugRunner {
	
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        //Set the sideLength = 3
        CircleBug lws = new CircleBug(3);
        //Set color
        lws.setColor(Color.RED);
        //Set start location
        world.add(new Location(6, 0), lws);
        world.show();
    }
}