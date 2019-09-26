package heaps;

import java.util.Random;
import java.util.UUID;

import javax.swing.JOptionPane;

import heaps.util.HeapToStrings;
import heaps.validate.MinHeapValidator;
import timing.Ticker;

public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {

	private Decreaser<T>[] array;
	private int size;
	private final Ticker ticker;

	/**
	 * I've implemented this for you.  We create an array
	 *   with sufficient space to accommodate maxSize elements.
	 *   Remember that we are not using element 0, so the array has
	 *   to be one larger than usual.
	 * @param maxSize
	 */
	@SuppressWarnings("unchecked")
	public MinHeap(int maxSize, Ticker ticker) {
		this.array = new Decreaser[maxSize+1]; //makes big enough heap
		this.size = 0;
		this.ticker = ticker;
	}

	//
	// Here begin the methods described in lecture
	//
	

	public Decreaser<T> insert(T thing) {
			//make an decreaser to store the new thing and insert it at the end
		Decreaser<T> ans = new Decreaser<T>(thing, this, ++size); //instantializes a 'decreaser' to keep track of the current thing
		//you want it to move it to the correct place
		array[size]= ans;
		ticker.tick();
		int currentP=size;
		decrease(currentP);
		return ans;
	}

	public T extractMin() {
		T ans = array[1].getValue();
		array[1]=array[size];
		array[1].loc=1;
		array[size]=null;
		size--;
		heapify(1);
		ticker.tick();
		return ans;
	}

	private int minChildIndex(int parent){// WORKING
		if(size==parent*2){
			return parent*2;
		}
		if(	compareValue(parent*2,parent*2+1)> 0)
		{
		return parent*2+1;
		}
		else{
			return parent*2;}
	}
	
	void decrease(int index){
		while(index/2>=1){
			if(compareValue(index/2, index)>0){
				ticker.tick();
				swap(index/2,index);
				index=index/2;
			}
			else{break;};
		}
	}
	private void heapify(int where) {// WORKING?
		int parent=where;
		while(parent*2<=size){
			int minChild=minChildIndex(parent); //gets the smallest child
			if(compareValue(parent,minChild)>0){
				ticker.tick();
				swap(parent, minChild);
				parent=minChild;
				}	
			else{break;}
		}
	}
	private int compareValue(int p, int c){
		return array[p].getValue().compareTo(array[c].getValue());
	}
	private void swap(int p, int c){
		Decreaser <T> temp=array[p];
		
		array[p]=array[c];
		array[p].loc=p;
		
		array[c]=temp;
		array[c].loc=c;
	}
	/**
	 * Does the heap contain anything currently?
	 * I implemented this for you.  Really, no need to thank me!
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T peek(int loc) {
		if (array[loc] == null)
			return null;
		else return array[loc].getValue();
	}

	/**
	 * Return the loc information from the Decreaser stored at loc.  They
	 *   should agree.  This method is used by the heap validator.
	 * @param loc
	 * @return the Decreaser's view of where it is stored
	 */
	public int getLoc(int loc) {
		return array[loc].loc;
	}

	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return this.array.length-1;
	}
	

	/**
	 * The commented out code shows you the contents of the array,
	 *   but the call to HeapToStrings.toTree(this) makes a much nicer
	 *   output.
	 */
	public String toString() {
//		String ans = "";
//		for (int i=1; i <= size; ++i) {
//			ans = ans + i + " " + array[i] + "\n";
//		}
//		return ans;
		return HeapToStrings.toTree(this);
	}

	/**
	 * This is not the unit test, but you can run this as a Java Application
	 * and it will insert and extract 100 elements into the heap, printing
	 * the heap each time it inserts.
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*JOptionPane.showMessageDialog(null, "You are welcome to run this, but be sure also to run the TestMinHeap JUnit test");
		MinHeap<Integer> h = new MinHeap<Integer>(500, new Ticker());
		MinHeapValidator<Integer> v = new MinHeapValidator<Integer>(h);
		Random r = new Random();
		for (int i=0; i < 100; ++i) {
			v.check();
			h.insert(r.nextInt(1000));
			v.check();
			System.out.println(HeapToStrings.toTree(h));
			//System.out.println("heap is " + h);
		}
		while (!h.isEmpty()) {
			int next = h.extractMin();
			System.out.println("Got " + next);
		}*/
	}


}
