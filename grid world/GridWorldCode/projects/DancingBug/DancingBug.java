import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces in different turns. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class DancingBug extends Bug{

	//reference to which turn should be changed
	private int steps;
	//record turns
	private int dancingTurns[];

	public DancingBug(int []turns) {
		steps = 0;
		//inite the array
		dancingTurns = new int[turns.length];
		for (int i = 0; i < turns.length; i++) 
			dancingTurns[i] = turns[i];
	}


	/**
	 * define the act of the dancing bug
	 * the turn is circle used, if cannot move,just turn 	
	 */
	public void act() {
		for (int i = 0; i < dancingTurns[steps%dancingTurns.length]; i++)
			turn();
		if (canMove()) {
			move();
			steps++;
		} else {
			turn();
		}
	}
}