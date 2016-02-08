
/** Circular Linked List implementation of the List interface.
 * There is no designated Head or Tail, only the current element.
 *
 * @author Gregory Levine
 * @param <T> the type of the List
 */
public class MyCList<T> implements List<T> {

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

    /**
     * Create an empty list with sentinels.
     */
    public MyCList() {
        this.clear();  // code reuse!
    }

    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.curr = new Node(null, null, null);
        this.curr.next = this.curr; //Node links back to itself, data is null
        this.curr.prev = this.curr;
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        if (this.size == 0) {
            this.curr.data = t;
            this.size++;
        } else {
            Node n = new Node(t, this.curr, this.curr.next);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.size++;
        }
        return true;
    }

    /**
     * Append a value before current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        if (this.size == 0) {
            this.curr = new Node(t, null, null);
            this.curr.next = this.curr;
            this.curr.prev = this.curr;
            this.size++;
        } else {
            Node n = new Node(t, this.curr.prev, this.curr);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.size++;
        }
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor).
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        T val = this.curr.next.data;
        this.curr.next = this.curr.next.next;  // bypass node being deleted
        this.curr.next.prev = this.curr;       // bypass it in other direction
        this.size--;
        return val;
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
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
     * Set the current position to the start of the list. Does nothing in a
     * circular list.
     */
    public void moveToStart() {
    }

    /**
     * Set the current position to the end of the list. Just calls prev
     * because "end" doesnt mean anything in a circular list
     */
    public void moveToEnd() {
        this.prev();
    }

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    public void prev() {
        this.curr = this.curr.prev;
    }

    /**
     * Move the current position one step right.
     */
    public void next() {
        this.curr = this.curr.next;
    }

    /**
     * Return the position of the current element. In a circular list, the
     * current position is taken to be 0.
     * @return the current position in the list
     */
    public int currPos() {
        // dummy implementation
        return 0;
    }

    /**
     * Set the current position. Positions are numbered with the current
     * position 0 and the index increasing to the right in a circular list.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        while (pos > 0) {
            this.curr = this.curr.next;
            pos--;
        }
        return false;
    }

    /**
     * Return true if current position is at end of the list. Always false in a
     * circular list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        // dummy implementation
        return false;
    }

}
