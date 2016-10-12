package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	private int[] probility = {1,1,1,1};
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) {
			Location loc = getLocation();
			ArrayList<Location> begin = new ArrayList<Location>();
			begin.add(loc);
			crossLocation.add(begin);
		}
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else 
			backTrack();
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		ArrayList<Location> distLoc = new ArrayList<Location>();
		Location[] nextLoc = {loc.getAdjacentLocation(Location.NORTH),
							  loc.getAdjacentLocation(Location.WEST), 
							  loc.getAdjacentLocation(Location.EAST), 
							  loc.getAdjacentLocation(Location.SOUTH) };
		for (int i = 0; i < 4; i++) {
			if (!gr.isValid(nextLoc[i])) continue;
			Actor a = gr.get(nextLoc[i]);
			//if next is the goal:
			if ((a instanceof Rock) && a.getColor().equals(Color.RED)) {
				next = nextLoc[i];
				distLoc.add(nextLoc[i]);
				return distLoc;
			}
			else if (a == null)
				valid.add(nextLoc[i]);
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Location current = getLocation();
		ArrayList<Location>validLocation = getValid(current);
		if (validLocation.size() == 0) return false;
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location current = getLocation();
		ArrayList<Location> validLocation = getValid(current);
		int index = 0;
		int max = 0;
		// get the direction with biggest probility
		for (int i = 0; i < validLocation.size(); i++) {
			int dir = current.getDirectionToward(validLocation.get(i));
			if (probility[dir/90] > max) {
				max = probility[dir/90];
				index =  i;
			} 
		}

		int judge = (int)(Math.random()*10);
		
		//select which location to go
		if (validLocation.size() == 1) index = 0;
		// else if (judge > 8) 
		// 	index = (int)(0+Math.random()*(3+0-1));
		//go
		int j = current.getDirectionToward(validLocation.get(index)) / 90;
		probility[j]++;
		next = validLocation.get(index);
		
		if (gr.isValid(next)) {
			Actor a = gr.get(next);
			if (a instanceof Rock && a.getColor().equals(Color.RED)) {
				isEnd = true;
			}
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);

			ArrayList<Location> temp = new ArrayList<Location>();
			ArrayList<Location> toGo = new ArrayList<Location>();
			temp = crossLocation.pop();
			temp.add(next);
			crossLocation.push(temp);

			toGo.add(next);
			crossLocation.push(toGo);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, current);
	}

	// go back
	private void backTrack() {
		if (crossLocation.size() > 0) {
			crossLocation.pop();
			if (crossLocation.size() > 0) {
				Grid<Actor> gr = getGrid();
				if (gr == null) return;
				ArrayList<Location>back = crossLocation.peek();
				Location backLocation = back.get(0);
				Location current = getLocation();
				int direction = current.getDirectionToward(backLocation);
				int j = direction / 90;
				if (gr.isValid(backLocation)) {
					setDirection(direction);
					moveTo(backLocation);
					stepCount++;
				} 
				else removeSelfFromGrid();
				// decrease the probility when go back
				switch(j) {
					case 0: {
						probility[2]--;
						if (getMax() == 2)
							probility[2]--;
						break;
					}
					case 1:{
						probility[3]--;
						if (getMax() == 3)
							probility[3]--;
						break;
					}
					case 2:{
						probility[0]--;
						if (getMax() == 0)
							probility[0]--;
						break;
					}
					case 3:{
						probility[1]--;
						if (getMax() == 1)
							probility[1]--;
						break;
					}
					default:
						break;
				}
				Flower flower = new Flower(getColor());
				flower.putSelfInGrid(gr, current);
			}
		}
	}
	//get the biggest probility's index
	private int getMax() {
		int index = 0;
		for (int i = 0; i < 4; i++) {
			if (probility[i] >= probility[index])
				index = i;
		}
		return index;
	}
}
