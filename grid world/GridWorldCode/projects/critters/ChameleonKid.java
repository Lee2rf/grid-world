import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;


public class ChameleonKid extends ModifiedChameleonCritter
{
    /**
     * get location from the array and test if it is invalid.
     */

    public ArrayList<Location> getLocationsInDirections(int[] dirs) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor>gr = getGrid();
        Location loc = getLocation();

        for (int d:dirs) {
            if (gr.isValid(loc.getAdjacentLocation(getDirection()+d)))
                locs.add(loc.getAdjacentLocation(getDirection()+d));
        }

        return locs;
    }

    /**
     * get actors from the location and test if it is invalid.
     * if not, return the actors
     */

    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs = {Location.AHEAD, Location.HALF_CIRCLE};
        for (Location loc:getLocationsInDirections(dirs)) {
            Actor actor = getGrid().get(loc);
            if (actor != null)
                actors.add(actor);
        }
        return actors;
    }
}
