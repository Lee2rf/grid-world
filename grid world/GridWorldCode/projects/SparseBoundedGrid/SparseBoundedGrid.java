import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {

	/**
	 * private data record the position and store the obj
	 */
	private SparseGridNode[] SparseArray; //use row list node.
	private int colNum;
	private int rowNum;

	/**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
	public SparseBoundedGrid(int rows, int cols) {
        colNum = (cols <= 0) ? 1 : cols;
        rowNum = (rows <= 0) ? 1 : rows;
        SparseArray = new SparseGridNode[rowNum];
	}
    
    @Override
	public int getNumRows() {return rowNum;}
    @Override
	public int getNumCols() {return colNum;}

    @Override
	public boolean isValid(Location loc)
    {	// coopy from BoundedGrid class...
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
    	ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
        	SparseGridNode p = SparseArray[r];
            // If there's an object at this location, put it in the array.
            while (p!=null) {
            	Location loc = new Location(r, p.getCol());
                theLocations.add(loc);
            	p = p.getNext();
            }
        }

        return theLocations;
    }

    /**
	 * get object in location loc.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(Location loc) {
    	if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        SparseGridNode p = SparseArray[loc.getRow()];
        while (p != null) {
        	if (p.getCol() == loc.getCol())
        		return (E)p.getObject();
        	p = p.getNext();
        }
        return null;
    }

    /**
	 * put object in location loc.
     */
    @Override
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = remove(loc);
        SparseGridNode p = SparseArray[loc.getRow()];
        SparseArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), p);
        return oldOccupant;
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        E r = get(loc);

        //find the node in row list.
        SparseGridNode p = SparseArray[loc.getRow()];
        if (p != null) {
            // if remove the head node;
            if (p.getCol() == loc.getCol()) 
                SparseArray[loc.getRow()] = p.getNext();    
            else {
                SparseGridNode q  = p.getNext();
                //find the node
                while (q != null && q.getCol() != loc.getCol()) {
                    p = p.getNext();
                    q = q.getNext();
                }
                if (q != null)
                    p.setNext(q.getNext());
            }
        }
        return r;
    }
}	