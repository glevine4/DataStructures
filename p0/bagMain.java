import java.math.BigInteger;

/**
   Main program for using the Bag interface and BagArray implementation.
   @author Joanne Selinski
   January 2016
*/
public final class BagMain {
    /** Constant for 3. */
    private static final int THREE = 3;
    /** Constant for 4. */
    private static final int FOUR = 4;
    /** Constant for five. */
    private static final int FIVE = 5;
    /** Constant for six. */
    private static final int SIX = 6;
    /** Constant for ten. */
    private static final int TEN = 10;
    /** Just some random double. */
    private static final double RAND = 4.4234;

    /**
     * Overrides the default constructor.
     */
    private BagMain() {
    }

    /**
     * The main function.
     * @param args the command line args.
     */
    public static void main(String[] args) {

        Bag b1, b2, b3;    // make Bag interface reference variables

        // instantiate them with BagArray implementations
        b1 = new BagArray();
        b2 = new BagArray();

        //Tests add() and toString()
        b2.add(THREE);
        assert "(3)".equals(b2.toString());

        //Tests isEmpty()
        assert (b1.isEmpty());

        //Further tests for add() on different classes of object
        b1.add(1);
        b1.add(FIVE);
        b1.add("ten");
        b1.add(true);
        b1.add(RAND);
        BigInteger in = new BigInteger("4");
        b1.add(in);

        //Tests toString()
        assert b1.toString().equals("(1, 5, ten, true, 4.4234, 4)");

        //Tests size()
        assert b1.size() == SIX;
        //Tests contains()
        assert b1.contains(1);
        assert b1.contains("ten");
        assert !b1.contains(TEN);

        //Tests remove(Object)
        Object item = b1.remove(1);
        assert b1.size() == FIVE;
        assert !b1.contains(1);

        //Tests remove()
        item = b1.remove();
        assert b1.size() == FOUR;
        assert !b1.contains(item);

        //Tests clear()
        b1.clear();
        assert b1.isEmpty();

        //Tests remove() and contains() when there are no items
        assert null == b1.remove("ten");
        assert null == b1.remove();
        assert !b1.contains("ten");
    }
}
