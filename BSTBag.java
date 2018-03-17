import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {
	private BSTBag.BSTNode<E> root;
	
	/**
	 * Constructor for Bag, sets root node to null.
	 */
	public BSTBag() { root = null; }
	
	/**
	 * Inner Node class
	 * This class stores an CountedElement and the nodes which are connected
	 * to this node. 
	 */
	private static class BSTNode<E extends Comparable<E>> {
		protected CountedElement<E> element;
		protected BSTNode<E> left, right;
		
		/**
		 * When creating a new node, a new counted element s produced,
		 * which will always start with a count of 1.
		 * @param elem the element to be store in the node
		 */
		protected BSTNode(E elem) {
			this.element = new CountedElement<E>(elem,1);
			this.left = null;
			this.right = null;
		}
		
		/**
		 * method to remove an node from the BST. If the node has only
		 * one child, this is returned to the remove method to replace,
		 * the node being deleted. If there are 2 child nodes it will return
		 * the left most child of the right hand subtree
		 * @return the node to replace the topMost node
		 */
		public BSTNode<E> deleteTopMost() {
			if (this.left == null) {
				return this.right;
			} else if (this.right == null) {
				return this.left;
			} else {
				this.element = this.right.getLeftMost();
				this.right = this.right.deleteLeftMost();
				return this;
			}
		}
		
		/**
		 * Method to find the left most node of a subtree.
		 * Keeps going to the left child until the left child
		 * is null
		 * @return the left most node in the subtree
		 */
		private CountedElement<E> getLeftMost() {
			BSTNode<E> curr = this;
			while (curr.left!=null) {
				curr = curr.left;
			}
			return curr.element;
		}
		
		/**
		 * Method to remove the left most node of a subtree and replace
		 * the root of the subtree with the child of the new leftmost element.
		 * @return the leftmost 
		 */
		private BSTNode<E> deleteLeftMost() {
			if (this.left == null) {
				return this.right;
			} else {
				return this;
			}
		}
	}
		
	/**
	 * Inner Iterator class
	 * returns an iterator objects that implements the methods required for an
	 * iterator class
	 */
	private class IteratorObject implements Iterator<E> {
		private Stack<BSTBag.BSTNode<E>> track;
		
		/**
		 * Constructor for IteratorObject. creates a trace of all nodes in the 
		 * bag and stores the left most nodes of the BST such that the right sub-trees
		 * can be iterated through
		 */
		private IteratorObject() {
			track = new LinkedStack<BSTBag.BSTNode<E>>();
			for (BSTBag.BSTNode<E> curr = root ; curr != null ; curr = curr.left) {
				track.push(curr);
			}
		}
		
		/**
		 * Method to test if there are any more nodes that have not been traversed
		 */
		@Override
		public boolean hasNext() {
			return (!track.empty());
		}

		/**
		 * Method to return the next element in the iterator.
		 * Returns the leftmost node from the BST and also adds all the leftmost
		 * nodes from the right hand subtree of the element being returned.
		 */
		@Override
		public E next() {
			if (track.empty()) {
				throw new NoSuchElementException();
			} else {
				// remove leftmost node
				BSTBag.BSTNode<E> place = track.pop();
				for (BSTBag.BSTNode<E> curr = place.right ; curr != null ; curr = curr.left) {
					// add all leftmost nodes from right sub-tree
					track.push(curr);
				}
				
				// Return the element from the BST, not the element and it's count value
				return place.element.getElement();
			}
		}
		
	}
	
	/**
	 * Method to validate whether or not there are any values stored in the BST
	 */
	@Override
	public boolean isEmpty() {
		// if the root is null, there is no data in the tree
		return (root == null ? true : false);
	}

	/*
	 * Use a recursive algorithm to measure the size of a single node, then
	 * find the size of child nodes
	 */
	@Override
	public int size() {
		if (root == null) {
			return 0;
		} else {
			return 1 + subTreeSize(root.left) + subTreeSize(root.right);
		}
	}
	private int subTreeSize(BSTBag.BSTNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subTreeSize(node.left) + subTreeSize(node.right);
		}
	}

	/**
	 * Method to find the occurrence of an element and return true if it 
	 * occurs in the BST
	 */
	@Override
	public boolean contains(E element) {
		BSTBag.BSTNode<E> curr = root;
		while(curr != null) {
			if (element.compareTo(curr.element.getElement()) == 0) {
				return true;
			} else if (element.compareTo(curr.element.getElement()) > 0) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
		return false;
	}

	/**
	 * Method to test of this instance of a binary search tree is the same as
	 * another BST - regardless of the ordering of nodes.
	 * 
	 * Iterates through all elements of other BST and tests if this instance
	 * contains an element with the same value
	 */
	@Override
	public boolean equals(Bag<E> that) {
		Iterator<E> it = that.iterator();
		while (it.hasNext()) {
			if (!this.contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * method to delete all elements in the BST by removing the root node
	 * (making all other nodes inaccessible)
	 */
	@Override
	public void clear() {
		root = null;
	}

	/**
	 * Method to add a node to the BST. Searches the BST for the location it belongs to
	 * and then if that element already exists it increments it's count (assuming it
	 * is a CountedElement), otherwise it creates a new node in the location and creates a
	 * link.
	 */
	@Override
	public void add(E element) {
		BSTBag.BSTNode<E> curr = root;
		// if the root node is null, this element is now the root node.
		if (curr == null) {
			System.out.println("First node is: "+element.toString());
			root = new BSTBag.BSTNode<E>(element);
			return;
		}
		
		// Find the location the node belongs in
		while (true) {
			BSTBag.BSTNode<E> parent = curr;
			if(element.compareTo(curr.element.getElement()) == 0) {
				// If the element already exists increment the count value and terminate
				System.out.println(element.toString()+" is already in the tree - incrementing");
				curr.element.setCount(curr.element.getCount()+1);
				return;
			} else if (element.compareTo(curr.element.getElement()) > 0) {
				// if the new node is bigger than the current node, move right down the tree
				System.out.println(element.toString()+" is right of "+curr.element.toString());
				 curr = curr.right;
				 if (curr == null) {
					 // if there is no value to the right, create a new node here.
					 parent.right = new BSTBag.BSTNode<E>(element);
					 return;
				 }
			} else {
				// If the element is smaller than the current node, move left down the tree.
				System.out.println(element.toString()+" is left of "+curr.element.toString());
				curr = curr.left;
				if (curr == null) {
					// if there is no node here, create a new node
					parent.left = new BSTBag.BSTNode<E>(element);
					return;
				}
			}
		}
	}

	/**
	 * Method to remove the specified node from the BST if it exists in the BST.
	 */
	@Override
	public void remove(E element) {
		BSTBag.BSTNode<E> parent, curr;
		parent = null;
		curr = root;
		// if the root node is null, terminate
		if (curr == null) { return; };
		
		// Search the array for the value to be removed
		while (true) {
			if (curr == null) {
				// if the bottom of the BST has been reached, terminate
				return;
			} else if (element.compareTo(curr.element.getElement()) == 0) {
				// We have found the element to be removed.
				if (curr.element.getCount() > 1) {
					// if the element a count of more than 1, do not remove the node, but decrement it's value
					System.out.println("There are "+curr.element.getCount()+" instances of "+curr.element.getElement()+" - decrementing");
					curr.element.setCount(curr.element.getCount()-1);
					return;
				} else {
					// if there is only a count of 1. find the replacement node of the deleted node
					// this is implemented in the Node inner class
					BSTBag.BSTNode<E> replacement = curr.deleteTopMost();
					if (curr == root) {
						// if the current node is the root node, replace the root with its replacement
						root = replacement;
					} else if (curr == parent.left) {
						// if the deleted node is a left child node, update the parent node
						parent.left = replacement;
					} else if (curr == parent.right) {
						// if the deleted node is a right child node, update the parent node
						parent.right = replacement;
					}
					return;
				}
			} else {
				// if the node has not been found, traverse the BST until it is found
				parent = curr;
				curr = ((element.compareTo(curr.element.getElement()) > 0) ? parent.right : parent.left);
			}
		}
	}

	/**
	 * Method to return the IteratorObject inner class.
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorObject();
	}
	
}