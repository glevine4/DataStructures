/**
 * An unordered unbounded container of objects that allows for adding, removing,
 * searching.
 * @author joanne
 */
public interface Bag {

    /**
     * Add an object to the bag, duplicates ok, no limit on bag size.
     * @param o the object to add
     */
    void add(Object o);

    /**
     * Get some (any) thing from the bag.
     * @return the object removed
     */
    Object remove();

    /**
     * Get a specific thing from the bag, if there.
     * @param o the object to remove (1 instance)
     * @return the object removed, or null if not found
     */
    Object remove(Object o);

    /**
     * Find out if an object is in the bag.
     * @param o the object to be matched
     * @return true if found, false otherwise
     */
    boolean contains(Object o);

    /**
     * Get the number of items in the bag.
     * @return how many
     */
    int size();

    /**
     * Empty the bag of all items.
     */
    void clear();

    /**
     * Check whether the bag has any items.
     * @return true if it is empty, false otherwise
     */
    boolean isEmpty();

}
