import info.gridworld.actor.Bug;

/**
 * A <code>CircleBug</code> traces out a circle "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class CircleBug extends Bug {

	//keep track of how much steps moved on current side
	private int steps;
	//define the number of steps move on each side
	private int sideLength; 

	/**
     * Constructs a circle bug that traces a octagon of a given side length
     * @param length the side length
     */
	public CircleBug(int length) {
		sideLength = length;
		steps = 0;
	}

	/**
	 * define the act of the circle bug
	 * note the turn method only call once when need 	
	 */
	public void act() {
		if (steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			turn();
			steps = 0;
		}
	}
}