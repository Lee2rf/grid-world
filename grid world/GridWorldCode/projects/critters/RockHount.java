import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

public class RockHount extends Critter {

	public void processActors(ArrayList<Actor> actors) {
		for (Actor actor:actors) {
			if (actor instanceof Rock)
				actor.removeSelfFromGrid();
		}
	}
}