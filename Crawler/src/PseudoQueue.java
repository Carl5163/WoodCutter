import java.util.Vector;

@SuppressWarnings("serial")
public class PseudoQueue extends Vector<WebLocation> {
	
	private int head = 0;
	
	public PseudoQueue() {
		super();
	}
	
	public void Enqueue(WebLocation location) {
		add(location);
	}
	
	public void dequeue() {
		head += 1;
	}
	
	public boolean isEmpty() {
		return head == size();
	}

	public WebLocation deQueue(){

		head++;
		return get(head-1);		
		
	}
	public boolean contains(Object item) {
		for(WebLocation w : this) {
			if(w.toString().equals(item.toString())) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean add(WebLocation item) {
		
		if(!contains(item)) {
			super.add(item);
		}
		return true;
	}
	
	public int size() {
		return super.size() - head;
	}

	public int realSize() {
		return super.size();
	}
}
