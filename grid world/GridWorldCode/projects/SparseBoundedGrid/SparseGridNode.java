/**
 * use row list nodes
 */

public class SparseGridNode {
	/**
	 * private data record the position and store the obj
	 */
	private Object obj;
	private int col;
	private SparseGridNode next;

	/**
	 * the Construct
	 */
	public SparseGridNode(Object nobj, int ncol, SparseGridNode nnext) {
		obj = nobj;
		col = ncol;
		next = nnext;
	}

	/**
	 * get attribute
	 */
	public Object getObject() { return obj;}
	public int getCol(){return col;}
	public SparseGridNode getNext() {return next;}

	/**
	 * set attribute
	 */
	public void setObject(Object nobj) {obj = nobj;}
	public void setCol (int ncol) {col = ncol;}
	public void setNext( SparseGridNode nnext) {next = nnext;}
}