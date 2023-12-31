import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IndoorMap {

    public List<Location> locations;

    public IndoorMap(String file) {
        this.locations = new LinkedList<>();
        try {
            Scanner fileIn = new Scanner(new File(file));
            this.initializeMap(fileIn);
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(0);

        }

    }

    private void initializeMap(Scanner input) {
        if (input.nextLine().equals("Locations")) {
            this.parseLocations(input);
        } else {
            System.out.println("File is not properly formatted.");
            System.exit(0);
        }
        if (input.nextLine().equals("Connections")) {
            this.parseConnections(input);
        } else {
            System.out.println("File is not properly formatted.");
            System.exit(0);
        }

    }

    private void parseLocations(Scanner input) {
        String str = input.nextLine();
        while (str.length() > 0) {
            String[] info = str.split(",");
            Location l = new Location(info[0], Integer.parseInt(info[1]));
            if (info.length > 2) {
                if (info[2].equals("Ev")) {
                    l.isElevator = true;
                } else if (info[2].equals("Esc")) {
                    l.isEscalator = true;
                }
            }
            this.locations.add(l);
            str = input.nextLine();
        }
    }

    private void parseConnections(Scanner input) {
        String str = input.nextLine();
        while (str.length() > 0) {
            String[] info = str.split(",");
            Location L1 = this.getLocation(info[0]);
            Location L2 = this.getLocation(info[1]);
            if (L1 == (null) || L2 == (null)) {
                System.out.println("One of the stores was not found(" + info[0]
                        + "," + info[1] + ")");
                System.exit(0);
            }
            L1.addAdjacentLocation(L2, Integer.parseInt(info[2]), info[3]);
            L2.addAdjacentLocation(L1, Integer.parseInt(info[2]),
                    getOppositeDirection(info[3]));

            str = input.nextLine();
        }
    }

    private static String getOppositeDirection(String direction) {
        String str;
        switch (direction) {
            case "up":
                str = "down";
                break;
            case "down":
                str = "up";
                break;
            case "east":
                str = "west";
                break;
            case "west":
                str = "east";
                break;
            case "north":
                str = "south";
                break;
            case "south":
                str = "north";
                break;
            case "southwest":
                str = "northeast";
                break;
            case "southeast":
                str = "northwest";
                break;
            case "northwest":
                str = "southeast";
                break;
            case "northeast":
                str = "southwest";
                break;
            default:
                str = direction;
                break;
        }
        return str;
    }

    public Location getLocation(String name) {
        Location L = null;
        for (int i = 0; i < this.locations.size(); i++) {
            if (this.locations.get(i).name.equals(name)) {
                L = this.locations.get(i);
            }
        }
        return L;
    }

    public boolean hasLocation(String name) {
        for (int i = 0; i < this.locations.size(); i++) {
            if (this.locations.get(i).name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a file");
        String file = in.nextLine();

        IndoorMap im = new IndoorMap(file);
        System.out.println("Enter your starting point");
        String start = in.nextLine();
        while (!im.hasLocation(start)) {
            System.out
                    .println("We don't have that store, please enter another.");
            start = in.nextLine();
        }
        System.out.println("Enter your ending point");

        String finish = in.nextLine();
        while (!im.hasLocation(finish)) {
            System.out
                    .println("We don't have that store, please enter another.");
            finish = in.nextLine();
        }
        if (im.getLocation(start).level != im.getLocation(finish).level) {
            System.out.println("This route requires moving up/down levels.");
            System.out.println("Do you require the elevator(yes/no)");
            if (in.nextLine().equals("yes")) {
                for (Location l : im.locations) {
                    if (l.isEscalator) {
                        l.changeWeights(100000);
                    }
                }
            }
        }

        Path p = new Path(im.getLocation(start), im.getLocation(finish), im);

        List<Location> path = p.buildPath();
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i).name);
        }

        in.close();
    }
}
