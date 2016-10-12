import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
 public class AnotherUnboundedGrid<E> extends AbstractGrid<E> {
 	private Object[][] occupantArray;
 	private int width;

 	/**
     * Constructs an empty unbounded grid with default width.
     */
 	public AnotherUnboundedGrid() {
 		width = 16;
 		occupantArray = new Object[width][width];
 	}
    //get function
 	@Override
 	public int getNumRows() {return -1;}
    @Override
    public int getNumCols(){return -1;}
    @Override

    //judge function
    public boolean isValid(Location loc) {
    	return loc.getRow() >= 0 && loc.getCol() >= 0;
	}

    @Override
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        // Look at all grid locations.
        for (int i = 0; i < width; i++)
        	for (int j = 0; j < width; j++) {
        		Location loc = new Location(i, j);
        		if (get(loc) == null) continue;
        		 // If there's an object at this location, put it in the array.
        		a.add(loc);
        	}
        return a;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(Location loc) {
    	if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
       	
       	if (loc.getRow() < width && loc.getCol() < width)
	       	return (E) occupantArray[loc.getRow()][loc.getCol()];
    	return null;
    }

    @Override
    public E put(Location loc, E obj) {
    	if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        if (loc.getRow() >= width || loc.getCol() >= width) {
        	int size = width;
        	while (size <= loc.getRow() || size <= loc.getCol())
        		size *= 2;
        	//store the old data
            Object[][] temp = occupantArray;
        	//create a "new" array which is bigger
            occupantArray = new Object[size][size];
            //get the old data
        	for (int i = 0; i < width; i++)
        		for (int j = 0; j < width; j++)
        			occupantArray[i][j] = temp[i][j];
    		width = size;
        }
    	E old = get(loc);
    	occupantArray[loc.getRow()][loc.getCol()] = obj;
    	return old;       
    }

    @Override
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        if (loc.getRow() >= width || loc.getCol() >= width)
        	return null;
        E temp = get(loc);
        if (temp != null)
        	occupantArray[loc.getRow()][loc.getCol()] = null;
        return temp;
    }
 }