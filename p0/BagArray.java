/** Array based implementation of the Bag interface.
 */

public class BagArray implements Bag {

    /* Data members of my class.
     */
    private Object[] array;  // holds the items
    private int size;   // actual number of items

    public static final int SIZE = 10;   // default starting size

    /** Make an empty bag of a standard capacity.
     */
    public BagArray() {
        clear();
    }

    /**
     * Empty the bag of all items.
     */
    public void clear() {
        this.array = new Object[SIZE];
        this.size = 0; 
    }


    /**
     * Add an object to the bag, duplicates ok, no limit on bag size.
     * @param o the object to add
     */
    public void add(Object o) {
        // check if array is full
        if (size == this.array.length) {
            // resize: double the current size
            Object[] temp = new Object[size*2];
            // lookup System.arraycopy(...)
            for (int i=0; i < size; i++)
                temp[i] = this.array[i];
            array = temp;
        }
        // actual adding of the new item
        this.array[size++] = o;
    }

    /**
     * Get some (any) thing from the bag.
     * @return the object removed
     */
    public Object remove() {
        if (!this.isEmpty()) {
            Object o = this.array[--this.size];
            this.array[this.size] = null;
            return o;
        }
        return null;
    }

    /**
     */
    public Object remove(Object o) {
        int where = find(o);
        if (where == -1) {
            return null;
        }
        else {
            Object temp = this.array[where];
            this.size--;
            this.array[where] = this.array[this.size];
            this.array[this.size] = null;
            return temp;
        }
    }

    /**
       Get the location of an object in the Bag, if it's there.
       @param o the object to find
       @return the index of the first occurrence, or -1 if it's not there.
    */
    private int find(Object o) {
        for (int i=0; i < this.size; i++) {
            if (this.array[i].equals(o))
                return i;
        }
        return -1;
    }

    /**
     */
    public boolean contains(Object o) {
        return find(o) != -1;
    }

    /**
     * Get the number of items in the bag.
     * @return how many
     */
    public int size() {
        return this.size;
    }

    /**
     * Check whether the bag has any items.
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        String result = "(";
        for (int i=0; i < this.size; i++) {
            result += this.array[i];   // calls toString implicitly
            if (i < this.size-1) {
                result += ", ";
            }
        }
        result += ")";
        return result;
    }
}
