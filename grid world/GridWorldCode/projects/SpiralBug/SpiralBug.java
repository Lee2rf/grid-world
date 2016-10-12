import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces out a Spiral "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class SpiralBug extends Bug {
	//keep track of how much steps moved on current side
	private int steps;
	//define the number of steps move on each side
	private int sideLength; 

	/**
     * Constructs a spiral bug that traces a spiral of a changing side length
     * @param length the side initial length
     */
	public SpiralBug(int length) {
		sideLength = length;
		steps = 0;
	}

	/**
	 * define the act of the spiral bug
	 * note the turn method call twice when need as box bug
	 * and the sideLength increase after turn 	
	 */
	public void act() {
		if (steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			turn();
			turn();
			steps = 0;
			sideLength++;
		}
	}
}