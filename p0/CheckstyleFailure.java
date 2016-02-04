
public class CheckstyleFailure {
    
    public class InternalClass {
        private int number;
        private static final int CONSTANT = 12;
        InternalClass() {
            number = CONSTANT;
        }
        
        /**
         * Incomplete!
         */
        public void setNumber(int n)
        {
            this.number = n > 0 ? n : 0;
        }
        
        /**
         * Also incomplete!
         */
        public int getNumber(){
            return number;
            }
    }

    public static void main(String[] args)
    {
        int six = 6;
        if (six*1 < six+2)
        {
            System.out.println("That makes sense!");
        }
        else
        {
            System.out.println("Uhhhhh....");
        }
        // an example of using command-line arguments
        
        System.out.println("command line args: " + args.length);
        // these are Strings
        for (int i=0; i < args.length; i++)
            System.out.print(args[i] + " ");
        System.out.println();
        if (args.length >= 2)             
            MessAround(args);
    }

public static void MessAround(String[] RA) {
if (RA.length >= 2) {
    int i = 0;
        try {
            i = Integer.parseInt(RA[1]);
            System.out.println(i);
            if (i == 0)
                System.out.println("zero");
            else if (i == 1)
                System.out.println("one");
            else if (i == 2)
                System.out.println("two");
            else if (i == 3)
                System.out.println("three");
            else if (i == 4)
                System.out.println("four");
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

    }

}
