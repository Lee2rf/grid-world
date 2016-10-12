import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>ZBug</code> traces out a Z "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class ZBug extends Bug {

	//keep track of how much steps moved on current side
	private int steps;
	//define the number of steps move on each side
	private int sideLength; 
	//stage means that which side the bug on
	private int stage;

	/**
     * Constructs a z bug that traces a "z" of a given side length
     * @param length the side length
     */
	public ZBug(int length) {
		sideLength = length;
		steps = 0;
		stage = 1;
		//set the initial direction,the head faces east
		setDirection(Location.EAST);
	}

	/**
	 * define the act of the Z bug
	 * note the turn method call diferent times in different stage
	 */
	public void act() {
		if (steps < sideLength && stage <= 3 && canMove()) {
			move();
			steps++;
		} 
		else if(stage == 1){ // if the bug cannot move and the bug is on the stage 1,
							//just turn to southwest direction,that is 135 degree
			turn();
			turn();
			turn();
			steps = 0;
			// go into the next stage
			stage++;
		}
		else if (stage==2) { // at the end of the stage 2, the bug turn east again
			setDirection(Location.EAST);
			steps = 0;
			stage = 3;
		}
	}
}