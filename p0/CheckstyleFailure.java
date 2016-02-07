/**
 * A class that demonstrates some java stuff.
 */
public final class CheckstyleFailure {
    /** Constant for three. */
    private static final int THREE = 3;
    /** Constant for three. */
    private static final int FOUR = 4;
    /** Constant for six. */
    private static final int SIX = 6;

    /**
     * Overrides the default constructor.
     */
    private CheckstyleFailure() {
    }

    /**
     * An internal Class.
     */
    public class InternalClass {
        /** A private constant of the internal class. */
        private static final int CONSTANT = 12;
        /** A private variable of the internal class. */
        private int number;

        /** A constructor for the internal class. */
        InternalClass() {
            this.number = CONSTANT;
        }

        /**
         * Sets number.
         * @param n the number
         */
        public void setNumber(int n) {
            if (n > 0) {
                this.number = n;
            } else {
                this.number = 0;
            }
        }

        /**
         * Gets the number.
         * @return the number
         */
        public int getNumber() {
            return this.number;
        }
    }

    /**
     * The main function.
     * @param args command line args.
     */
    public static void main(String[] args) {
        int six = SIX;
        if (six * 1 < six + 2) {
            System.out.println("That makes sense!");
        } else {
            System.out.println("Uhhhhh....");
        }
        // an example of using command-line arguments

        System.out.println("command line args: " + args.length);
        // these are Strings
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();
        if (args.length >= 2) {
            messAround(args);
        }
    }
    /**
     * MessAround function.
     * @param ra a string array.
     */
    public static void messAround(String[] ra) {
        if (ra.length >= 2) {
            int i = 0;
            try {
                i = Integer.parseInt(ra[1]);
                System.out.println(i);
                if (i == 0) {
                    System.out.println("zero");
                } else if (i == 1) {
                    System.out.println("one");
                } else if (i == 2) {
                    System.out.println("two");
                } else if (i == THREE) {
                    System.out.println("three");
                } else if (i == FOUR) {
                    System.out.println("four");
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

    }

}
