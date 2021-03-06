import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
/**
* This class runs a world with additional grid choices.
*/
public class SparseBoundedGrid2Runner
{
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("SparseBoundedGrid2");
    // world.addGridClass("SparseBoundedGrid2");
    // world.addGridClass("SparseBoundedGrid3");
    // world.addGridClass("UnboundedGrid2");
    world.add(new Location(2, 2), new Critter());
    world.add(new Location(0, 0), new Rock());
    world.add(new Location(0, 1), new Bug());
    world.add(new Location(0, 2), new Flower());
    world.show();
  }
}