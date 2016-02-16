import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * @author Gregory Levine and Matthew Saltzman
 * Due: February 17, 2016
 * 600.226 Data Structures
 * Section 01
 * Assignment P1
 * glevine4@jhu.edu
 * msaltzm5@jhu.edu
 *
 * Simulates the Cutthroat Kitchen game.
 */
public final class p1 {

    /** A list to hold the stations. */
    private static CList<CookingStation> stations;
    /** A scanner to read the input file. */
    private static Scanner in = null;

    /**
     * Hides Constructor.
     */
    private p1() {
    }

    /**
     * The main function.
     * @param args contains the command line arguments
     */
    public static void main(String[] args) {
        //checks that there is eactly 1 input argument from the command line
        if (args.length != 1) {
            System.err.println("Invalid Number of Input Arguments");
            System.exit(0);
        }
        //sets up the scanner object to read from the file, and creates
        //the initial datastructure
        setupScanner(args[0]);
        readFile();
        //declares PrintStreams for the output files
        PrintStream sim0 = null, sim1 = null, sim2 = null, simP = null;
        //initializes the printstreams
        try {
            sim0 = new PrintStream(new File("sim0.txt"));
            sim1 = new PrintStream(new File("sim1.txt"));
            sim2 = new PrintStream(new File("sim2.txt"));
            simP = new PrintStream(new File("simP.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("There was an error creating the output files.");
        }
        // Run simulation with 0 as the removeThreshold
        runSim(sim0, 0, 0);
        // Run simulation with 1 as the removeThreshold
        setupScanner(args[0]);
        readFile();
        runSim(sim1, 1, 0);
        // Run simulation with 2 as the removeThreshold
        setupScanner(args[0]);
        readFile();
        runSim(sim2, 2, 0);
        // Run simulation with our own parameters to utilize our algorithm
        // for using the penaltyThreshold
        setupScanner(args[0]);
        readFile();
        runSim(simP, 2, 2);
        sim0.close();
        sim1.close();
        sim2.close();
        simP.close();
    }

    /**
     * Reads the input file.
     */
    private static void readFile() {
        try {
            stations = new CList<CookingStation>();
            while (in.hasNextLine()) {
                stations.append(new CookingStation(in.nextLine()));
                stations.moveToEnd();
                while (in.hasNextLine()) {
                    String str = in.nextLine();
                    Scanner line = new Scanner(str);
                    if (str.trim().equals("")) {
                        break;
                    }
                    String name = line.next();
                    int time = 0, under = 0, over = 0;
                    try {
                        time = line.nextInt();
                        under = line.nextInt();
                        over = line.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Invalid input file formatting.");
                        System.exit(0);
                    }
                    stations.getValue().addItem(new CookingItem(name,
                        time, under, over));
                }
            }
        } catch (NoSuchElementException f) {
            System.err.println("Invalid input file formatting.");
            System.exit(0);
        }
    }

    /**
     * Runs the simulation of the game.
     */
    private static void runSim(PrintStream out, int removeThreshold,
        int penaltyThreshold) {
        int totalPenalty = 0;
        stations.moveToStart();
        out.println(getString());
        while (!getString().equals("[ ]")) {
            tick();
            totalPenalty += tend(removeThreshold, penaltyThreshold);
            out.println(getString());
        }
        out.println("Final penalty was: " + totalPenalty);
    }

    /**
     * Displays the current state of the kitchen.
     */
    private static String getString() {
        int index = stations.currPos();
        String output = "[ ";
        stations.moveToStart();
        for (int i = 0; i < stations.length(); i++) {
            if (stations.getValue().length() != 0) {
                output += stations.getValue().toString() + " ";
            }
            stations.next();
        }
        stations.moveToPos(index);
        return output + "]";
    }

    /**
     * Decrements time on all items by 1 minute.
     */
    private static void tick() {
        for (int i = 0; i < stations.length(); i++) {
            stations.getValue().tick();
            stations.cNext();
        }
    }

    private static int tend(int removeThreshold, int penaltyThreshold) {
        CookingStation station = stations.getValue();
        int total = 0;
        station.moveToStart();
        for (int i = 0; i < station.length(); i++) {
            CookingItem item = station.tend(removeThreshold, penaltyThreshold);
            if (item != null) {
                total += item.penalty();
                break;
            } else {
                station.next();
            }
        }
        if (station.length() == 0) {
            stations.remove();
        } else {
            stations.cNext();
        }
        return total;
    }

    private static void setupScanner(String file) {
        if (in != null) {
            in.close();
        }
        in = null;
        try {
            in = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            System.err.println("Invalid File");
            System.exit(0);
        }
    }
}
