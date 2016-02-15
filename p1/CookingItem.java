/**
 * @author Gregory Levine and Matthew Saltzman
 * 600.226 Data Structures
 * Section 01
 * Assignment P1
 *
 * Represents a Cooking Item.
 */
public class CookingItem {
    /** Number of minutes remaining until the item is fully cooked. */
    private int minutes;
    /** Name of item. */
    private String name;
    /** Underdone Penalty. */
    private int underPenalty;
    /** Overdone Penalty. */
    private int overPenalty;

    /**
     * A constructor.
     * @param n the name of the item.
     * @param time the time for the item to cook in minutes.
     * @param under the undercooked penalty
     * @param over the overcooked penalty
     */
    public CookingItem(String n, int time, int under, int over) {
        this.name = n;
        this.minutes = time;
        this.underPenalty = under;
        this.overPenalty = over;
    }

    /**
     * Implements a simulation of one minute of time for this item by
     * decrementing cooking time by one minute.
     */
    public void tick() {
        this.minutes--;
    }

    /**
     * Returns the time remaining until fully cooked.
     * @return the time remaining.
     */
    public int timeRemaining() {
        return this.minutes;
    }

    /**
     * Penalty if this dish were removed now.
     * @return Penalty if this dish is removed now.
     */
    public int penalty() {
        if (this.minutes > 0) {
            return this.minutes * this.underPenalty;
        } else {
            return -1 * this.minutes * this.overPenalty;
        }
    }

    /**
     * Returns a string representation of the item.
     * @return a string representation of the item.
     */
    public String toString() {
        return "(" + this.name + " " + this.minutes + ")";
    }
}
