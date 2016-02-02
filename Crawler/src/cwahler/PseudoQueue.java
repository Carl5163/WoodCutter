package cwahler;

import java.io.IOException;
import java.util.Vector;

@SuppressWarnings("serial")
public class PseudoQueue extends Vector<WebLocation> {
	
	private int startIndex = 0;
	private boolean verbose;
	
	public PseudoQueue(boolean verbose) {
		super();
		this.verbose = verbose;
	}
	
	public void Enqueue(WebLocation location) {
		add(location);
	}
	
	public void dequeue() {
		startIndex += 1;
	}
	
	public boolean isEmpty() {
		return startIndex == size();
	}

	public WebLocation visit(){

		startIndex++;
		return get(startIndex-1);		
		
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
		return super.size() - startIndex;
	}

	public int realSize() {
		return super.size();
	}
}
