import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Critter;


import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
	public ArrayList<Location> getQuickLocation() {
		ArrayList<Location> locs = new ArrayList<Location>();
		Grid gr = getGrid();
		
		Location loc = getLocation();
		Location left = loc.getAdjacentLocation(getDirection()+Location.LEFT);
		Location right = loc.getAdjacentLocation(getDirection()+Location.RIGHT);
		//check the location two spaces to its left
		if (gr.isValid(left) && gr.get(left) == null) {
			Location neighbor = left.getAdjacentLocation(getDirection()+Location.LEFT);
			if (gr.isValid(neighbor) && gr.get(neighbor) == null)
				locs.add(neighbor);
		}
		//check the location two spaces to its right
		if (gr.isValid(right) && gr.get(right) == null) {
			Location neighbor = right.getAdjacentLocation(getDirection()+Location.RIGHT);
			if (gr.isValid(neighbor) && gr.get(neighbor) == null)
				locs.add(neighbor);
		}
		return locs;
	}

	/**
	 * if the locs is not size 0, than go. else do the base class does.
	 */
	public ArrayList<Location>getMoveLocations() {
		ArrayList<Location> quickLoc = getQuickLocation();
		if (quickLoc.size() != 0)
			return quickLoc;
		
		//ArrayList<Location>locs = new ArrayList<Location>();
		// int[] dirs={Location.LEFT, Location.RIGHT};

		// for (Location l:getLocationsInDirections(dirs)) 
		// 	if (getGrid().get(l) == null)
		// 		locs.add(l);

		// return locs;
		return super.getMoveLocations();
	}
}