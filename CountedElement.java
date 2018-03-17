public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int c){
		element = e;
		count = c;
	}
	
	public CountedElement(E e){
		element = e;
	}

	//add getters and setters
	
	public E getElement() {
		return element;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setElement(E elem) {
		element = elem;
	}
	
	public void setCount(int c) {
		count = c;
	}
	
	//add toString() method
	
	public String toString() {
		return element.toString()+", "+count;
	}
	
	public int compareTo(CountedElement<E> sC1) {
		/*
		 *  As we are assuming the CountedElement has a toString() method
		 *  we can use String object's built in compareTo method
		 */
		return this.toString().compareTo(sC1.toString());
		//to complete
	}

}
