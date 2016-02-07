/** Array based implementation of the Bag interface.
 */

public class BagArray implements Bag {

    /** Default starting size. */
    public static final int SIZE = 10;

    /* Data members of my class. */
    /** Holds the items. */
    private Object[] array;
    /** Actual number of items. */
    private int size;

    /** Make an empty bag of a standard capacity.
     */
    public BagArray() {
        this.clear();
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
        if (this.size == this.array.length) {
            // resize: double the current size
            Object[] temp = new Object[this.size * 2];
            // lookup System.arraycopy(...)
            for (int i = 0; i < this.size; i++) {
                temp[i] = this.array[i];
            }
            this.array = temp;
        }
        // actual adding of the new item
        this.array[this.size++] = o;
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
     * The remove function.
     * @param o an object to remove.
     * @return the object removed.
     */
    public Object remove(Object o) {
        int where = this.find(o);
        if (where == -1) {
            return null;
        } else {
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
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * The contains function.
     * @param o an object.
     * @return a boolean.
     */
    public boolean contains(Object o) {
        return this.find(o) != -1;
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

    /**
     * The toString method.
     * @return string.
     */
    public String toString() {
        String result = "(";
        for (int i = 0; i < this.size; i++) {
            result += this.array[i];   // calls toString implicitly
            if (i < this.size - 1) {
                result += ", ";
            }
        }
        result += ")";
        return result;
    }
}
