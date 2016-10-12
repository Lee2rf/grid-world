import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */

 public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
 	
 	/**
	 * private data record the position and store the obj
	 */
 	private Map<Location, E> occupantMap;
 	private int colNum;
	private int rowNum;

	/**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
	public SparseBoundedGrid2(int rows, int cols) {
		rowNum = rows;
		colNum = cols;
		occupantMap = new HashMap<Location, E>();
	}

	@Override
	public int getNumRows() {return rowNum;}
	@Override
	public int getNumCols() {return colNum;}

	@Override
	public boolean isValid(Location loc) {
		return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
	}

	@Override
    public ArrayList<Location> getOccupiedLocations() {
    	ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    @Override
    public E get(Location loc) {
    	if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }

    @Override
    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    @Override
    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
 }