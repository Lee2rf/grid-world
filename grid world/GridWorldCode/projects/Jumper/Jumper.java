import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can "jump" and turn. <br />
 * It jumps over Rock or Flower in front of it or turn<br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class Jumper extends Actor {

	/**
     * Constructs a red jumper by default.
     */
	public Jumper() {
		setColor(Color.RED);
	}

	/**
     * Constructs a jumper of a given color.
     * @param jumoerColor the color for this jumper
     */
	public Jumper(Color jumperColor) {
		setColor(jumperColor);
	}

	/**
     * Jumps if it can jump, turns otherwise.
     */
	public void act() {
		if (canJump())
			jump();
		else
			turn();
	}

	/**
     * Turns the jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the jumper forward two location and occupied.
     * If the location will jump is invaild, remove the jumper.
     */
    public void jump() {
    	Grid<Actor> gr = getGrid();
    	if (gr == null)
    		return;
    	Location loc = getLocation();
    	Location next = loc.getAdjacentLocation(getDirection());
    	Location nextNext = next.getAdjacentLocation(getDirection());
    	if (gr.isValid(nextNext))
    		moveTo(nextNext);
    	else removeSelfFromGrid();
    }

    /**
     * Tests whether this jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this jumper can move.
     */
    public boolean canJump() {
    	Grid<Actor> gr = getGrid();
    	if (gr == null)
    		return false;
    	Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextNext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next) || !gr.isValid(nextNext))
        	return false;
        Actor neighbor = gr.get(next);
        Actor nnNeighbor = gr.get(nextNext);
        if (!((neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Rock)))
        	return false;
        return (nnNeighbor == null) || (nnNeighbor instanceof Flower);
    }
}