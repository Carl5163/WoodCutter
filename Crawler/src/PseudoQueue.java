import java.util.Vector;

@SuppressWarnings("serial")
public class PseudoQueue extends Vector<WebLocation> {
	
	private int head = 0;

	//******************************************************************
	public PseudoQueue() {
		super();
	}
	//******************************************************************

	//******************************************************************
	public void enQueue(WebLocation location) {
		add(location);
	}
	//******************************************************************

	//******************************************************************
	public void dequeue() {
		head += 1;
	}
	//******************************************************************

	//******************************************************************
	public boolean isEmpty() {
		return head == size();
	}
	//******************************************************************

	//******************************************************************
	public WebLocation deQueue(){

		head++;
		return get(head-1);		
		
	}
	//******************************************************************
		
	//******************************************************************
	//Vector.contains() is overwritten in order to compare strings instead of 
	//pointers to WebLoataion.
	public boolean contains(Object item) {
		for(WebLocation w : this) {
			if(w.toString().equalsIgnoreCase(item.toString().trim())) {
				return true;
			}
		}
		return false;
	}
	//******************************************************************	

	//******************************************************************
	//Vector.add() is overwritten in order to avoid duplicates.
	public boolean add(WebLocation item) {
		
		if(!contains(item)) {
			super.add(item);
		}
		return true;
	}
	//******************************************************************

	//******************************************************************
	//Vector.size() is overwritten so that it returns the logical size
	//of the queue.
	public int size() {
		return super.size() - head;
	}
	//******************************************************************

	//******************************************************************
	//Returns the "physical" size of the queue. (This does not mean capacity)
	public int realSize() {
		return super.size();
	}
	//******************************************************************
}
