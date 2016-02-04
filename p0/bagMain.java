/**
   Main program for using the Bag interface and BagArray implementation.
   @author Joanne Selinski
   @date January 2016
*/

public class bagMain {

    public static void main(String [] args) {

        Bag b1, b2, b3;    // make Bag interface reference variables

        // instantiate them with BagArray implementations
        b1 = new BagArray();
        b2 = new BagArray();

        b2.add(3);
        assert "(3)".equals(b2.toString());

        assert (b1.isEmpty());
        b1.add(1);
        b1.add(5);
        b1.add("ten");
        assert b1.toString().equals("(1, 5, ten)");
        assert b1.size() == 3;
        assert b1.contains(1);
        assert b1.contains("ten");
        assert false == b1.contains(10);
    }
}
