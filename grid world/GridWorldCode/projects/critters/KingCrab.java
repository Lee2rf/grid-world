import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter {

	//uesd to calculate the distance between the empty location and the current location
	private int distance(Location l1, Location l2) {
		return (int)Math.floor(
			Math.sqrt((l1.getRow()-l2.getRow()) * (l1.getRow()-l2.getRow()) +
				(l1.getCol()-l2.getCol()) * (l1.getCol()-l2.getCol())) + 0.5
			);
	}

	private boolean moveOne(Actor a) {
		ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(a.getLocation());

		for (Location l:locs) 
			if (distance(getLocation(), l) > 1) {
				a.moveTo(l);
				return true;
			}
		return false;	
	}

	public void processActors(ArrayList<Actor>actors) {
		// Grid gr = getGrid();
		// Location loc = getLocation();

		// for (Actor actor: actors) {
		// 	int t = loc.getDirectionToward(actor.getLocation());
		// 	Location neighbor = actor.getLocation().getAdjacentLocation(t);
		// 	if (gr.isValid(neighbor))
		// 		actor.moveTo(neighbor);
		// 	else 
		// 		actor.removeSelfFromGrid();
		// }
		for (Actor a : actors) 
			if (!moveOne(a))
				a.removeSelfFromGrid();
	}
}