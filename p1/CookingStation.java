/**
 * @author Gregory Levine and Matthew Saltzman
 * 600.226 Data Structures
 * Section 01
 * Assignment P1
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
     * @param it the dish to add
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
     * Tend the current item.
     * @param removeThreshold the number of minutes that may be used to
     * determine if an item should be removed from the station.
     * @param penaltyThreshold the limit on the penalty value that may be
     * used to determine if an item should be removed from the station.
     * @return the item if you decide to remove it, or null otherwise.
     */
    public CookingItem tend(int removeThreshold, int penaltyThreshold) {
        if (penaltyThreshold == 0) {
            if (this.getValue().timeRemaining() <= removeThreshold) {
                return this.remove();
            } else {
                return null;
            }
        //The following is the technique I came up with to reduce penalty
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
