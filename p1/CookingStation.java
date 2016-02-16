/**
 * @author Gregory Levine and Matthew Saltzman
 * Due: February 17, 2016
 * 600.226 Data Structures
 * Section 01
 * Assignment P1
 * glevine4@jhu.edu
 * msaltzm5@jhu.edu
 *
 * A class representing a cooking station.
 */
public class CookingStation extends CList<CookingItem>
    implements CookingStationInterface {
    /** The name of the station. */
    private String name;

    /**
     * A constructor.
     * @param n the name of the station.
     */
    public CookingStation(String n) {
        super();
        this.name = n;
    }

    /**
     * Put a new dish at the end of the station.
     * @param it the dish to add.
     */
    public void addItem(CookingItem it) {
        this.append(it);
    }

    /**
     * Simulate one minute time passing for this station.
     */
    public void tick() {
        this.moveToStart();
        for (int i = 0; i < this.length(); i++) {
            this.getValue().tick();
            this.next();
        }
    }

    /**
     * Tend the current item. The following is the method that I came up with
     * to improve the performance of the simulation:
     * 1. If removeThreshold is less than of equal to 0, then it is assumed to
     * be unused, and the item is simply removed if the removeThreshold has
     * been reached.
     * 2. If the item is already perfectly cooked or overdone, it is removed, as
     * this is always the best decision.
     * 3. If a positive penalty threshold is given, then a number of conditions
     * are used to determine when to remove the items before they are fully
     * cooked. First, it will only be removed if the time remaining is less than
     * the removeThreshold. Second, the penalty/timeRemaining is used to
     * determine the penalty per minute early the item is removed, and it is
     * only removed if this ratio is less than the penalty threshold. The reason
     * this improves performance is that it distinguishes between items that are
     * costly to remove early and those which are not as costly.
     * @param removeThreshold the number of minutes that may be used to
     * determine if an item should be removed from the station.
     * @param penaltyThreshold the limit on the penalty value that may be
     * used to determine if an item should be removed from the station.
     * @return the item if you decide to remove it, or null otherwise.
     */
    public CookingItem tend(int removeThreshold, int penaltyThreshold) {
        if (penaltyThreshold <= 0) {
            if (this.getValue().timeRemaining() <= removeThreshold) {
                return this.remove();
            } else {
                return null;
            }
        } else {
            if (this.getValue().timeRemaining() > 0 && this.getValue().penalty()
                / this.getValue().timeRemaining() < penaltyThreshold
                && this.getValue().timeRemaining() < removeThreshold) {
                return this.remove();
            } else if (this.getValue().timeRemaining() <= 0) {
                return this.remove();
            } else {
                return null;
            }
        }
    }

    /**
     * Returns a string representation of the cooking station.
     * @return a string representation of the cooking station.
     */
    public String toString() {
        String output = this.name + " [ ";
        this.moveToStart();
        for (int i = 0; i < this.length(); i++) {
            output += this.getValue().toString() + " ";
            this.next();
        }
        return output + "]";
    }
}
