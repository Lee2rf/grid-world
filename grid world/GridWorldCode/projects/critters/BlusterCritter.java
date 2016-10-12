import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter {
	
	private static final double DARKENING_FACTOR = 0.05;
	private int count;

	public BlusterCritter(int c) {
		super();
		count = c;
	}
	/**
     * get actors from neighbor over 2 cells
     */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Grid<Actor>gr = getGrid();
		Location loc = getLocation();

		int minRow, minCol, maxRow, maxCol;
		int row = loc.getRow();
		int col = loc.getCol();

		minRow = (row-2) < 0? 0 : (row-2);
		minCol = (col-2) < 0? 0 : (col-2);
		maxRow = (row+2) >= gr.getNumRows() ? (gr.getNumRows()-1) : (row+2);
		maxCol = (col+2) >= gr.getNumCols() ? (gr.getNumCols()-1) : (col+2);

		for (int i = minRow; i <= maxRow; i++)
			for (int j = minCol; j <= maxCol; j++) {
				Location l = new Location(i,j);
				Actor actor = gr.get(l);
				if (actor!=null)
					actors.add(actor);
			}
		return actors;
	}
	/**
     * dark or bright determined by the count.
     */
	public void processActors(ArrayList<Actor> actors) {
		int t = 0;
		for (Actor a:actors) 
			if (a instanceof Critter)
				t++;
		if (t >= count) {
			Color c = getColor();
        	int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        	int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        	int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        	red = (red  < 0) ? 0 : red;
        	green = (green < 0) ? 0 : green;
        	blue = (blue < 0) ? 0 : blue;
        	setColor(new Color(red, green, blue));
		} else {
			Color c = getColor();
        	int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
        	int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
        	int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
        	red = (red  >255) ? 255 : red;
        	green = (green > 255) ? 255 : green;
        	blue = (blue > 255) ? 255 : blue;
        	setColor(new Color(red, green, blue));
		}
	}
}