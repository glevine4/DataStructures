/**
 * @author Gregory Levine and Matthew Saltzman
 * 600.226 Data Structures
 * Section 01
 * Assignment P1
 *
 * Circular Linked List implementation of the List interface.
 * @param <T> the type that will be held in the list.
 */
public class CList<T> implements List<T> {

    /**
     * Inner doubly linked Node class for convenience.
     * Note that the generic type is implied since we are within MyCList<T>.
     */
    public class Node {

        /** The data in the element. */
        private T data;
        /** The left neighbor node. */
        private Node prev;
        /** The right neighbor node. */
        private Node next;

        /**
         * Make a node.
         * @param item the data to put in it
         * @param p the link to the previous node
         * @param n the link to the next node
         */
        public Node(T item, Node p, Node n) {
            this.data = item;
            this.prev = p;
            this.next = n;
        }
    }

    /** Number of actual data nodes in list. */
    private int size;
    /** Current node (think of as a cursor between nodes). */
    private Node curr;
    /** Keeps track of the head of the circular list. */
    private Node head;
    /** Keeps track of the position in the circular list. */
    private int position;

    /**
     * Create an empty list with sentinels.
     */
    public CList() {
        this.clear(); // code reuse!
    }

    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.curr = null;
        this.head = null;
        this.position = 0;
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        if (this.size <= 0) {
            this.curr = new Node(t, null, null);
            this.curr.prev = this.curr;
            this.curr.next = this.curr;
            this.head = this.curr;
            this.position = 0;
            this.size = 1;
        } else {
            Node n = new Node(t, this.curr, this.curr.next);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.size++;
        }
        if (this.curr == this.head.prev.prev) {
            this.head = this.curr.next;
        }
        return true;
    }

    /**
     * Append a value between head and tail.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        if (this.size == 0) {
            this.insert(t);
        } else {
            Node n = new Node(t, this.head.prev, this.head);
            n.next.prev = n;  //connect right neighbor
            this.curr = this.curr.next.prev;
            n.prev.next = n;   // connect left neighbor
            this.size++;
        }
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor).
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.curr == null) {
            return null;
        }
        if (this.curr.next == this.head) {
            this.head = this.head.next;
        }
        if (this.isAtEnd()) {
            this.position = 0;
        }
        if (this.size > 1) {
            T val = this.curr.next.data;
            this.curr.next = this.curr.next.next;  // bypass node being deleted
            this.curr.next.prev = this.curr;     // bypass it in other direction
            this.size--;
            return val;
        } else if (this.size == 1) {
            T val = this.curr.data;
            this.clear();
            return val;
        } else {
            return null;
        }
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
        if (this.curr == null) {
            return null;
        }
        return this.curr.next.data;
    }

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }

    /**
     * Set the current position to the start of the list.
     */
    public void moveToStart() {
        if (this.curr != null) {
            this.curr = this.head.prev;
            this.position = 0;
        }
    }

    /**
     * Set the current position to the last node.
     */
    public void moveToEnd() {
        if (this.curr != null) {
            this.curr = this.head.prev.prev;
            this.position = this.size - 1;
        }
    }

    /**
     * Move the current position one step left.
     */
    public void prev() {
        if ((this.curr != null) && (this.curr.next != this.head)) {
            this.position--;
            this.curr = this.curr.prev;
        }
    }

    /**
     * Move the current position one step right.
     */
    public void next() {
        if ((this.curr != null) && (this.curr.next != this.head.prev)) {
            this.position++;
            this.curr = this.curr.next;
        }
    }

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        return this.position;
    }

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        if (pos >= this.size || pos < 0) {
            return false;
        }
        while (pos > this.position) {
            this.next();
        }
        while (pos < this.position) {
            this.prev();
        }
        return true;
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        if (this.curr == null) {
            return false;
        }
        return this.curr == this.head.prev.prev;
    }

    /**
     * Outputs a string representation of the list.
     * @return a string representation of the list.
     */
    public String toString() {
        if (this.curr == null) {
            return "[]";
        }
        Node val = this.head;
        String output = "";
        output += "[ ";
        for (int i = 0; i < this.size; i++) {
            output += val.data.toString() + " ";
            val = val.next;
        }
        output += "]";
        return output;
    }

    /* Some Bonus Methods that are not part of the List interface */

    /**
     * Same as next, but circles around instead of stopping at the tail node.
     */
    public void cNext() {
        if (this.curr != null) {
            this.curr = this.curr.next;
            this.position++;
        }
        if (this.position == this.size) {
            this.position = 0;
        }
    }

    /**
     * Same as prev, but circles around instead of stopping at the head node.
     */
    public void cPrev() {
        if (this.curr != null) {
            this.curr = this.curr.prev;
            this.position--;
        }
        if (this.position == -1) {
            this.position = this.size - 1;
        }
    }
}
