import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Path {

    int startIndex = 0;
    int finishIndex = 0;
    private Map<Location, Boolean> visited;
    private Map<Location, Integer> distance;
    static int MAX_VAL = Integer.MAX_VALUE;
    public List<Location> locations;
    IndoorMap im;

    public class Pair {
        private Location l;
        private int x;

        public Pair(Location l, int x) {
            this.l = l;
            this.x = x;
        }

        public Location getLocation() {
            return this.l;

        }

        public int getDistance() {
            return this.x;
        }

    }

    @SuppressWarnings("hiding")
    public class PairCompare implements Comparator<Pair> {

        @Override
        public int compare(Path.Pair o1, Path.Pair o2) {
            // TODO Auto-generated method stub
            return o1.getDistance() - o2.getDistance();
        }

    }

    public Path(Location start, Location finish, IndoorMap im) {
        this.im = im;
        this.visited = new HashMap<>();
        this.distance = new HashMap<>();
        this.locations = im.locations;
        for (int i = 0; i < this.locations.size(); i++) {
            this.visited.put(this.locations.get(i), false);
            if (this.locations.get(i).name.equals(start.name)) {
                this.distance.put(this.locations.get(i), 0);
                this.startIndex = i;
            } else {
                this.distance.put(this.locations.get(i), MAX_VAL);
            }
            if (this.locations.get(i).name.equals(finish.name)) {
                this.finishIndex = i;
            }

        }

    }

    //traverses the whole graph starting from 0 to update all the distances
    public void buildPath() {
        //PriorityQueue<Location> q = new PriorityQueue<>(); //min priority queue makes start and next easier
        //q.offer(locations.get(startIndex));

        PriorityQueue<Pair> q = new PriorityQueue<>();

        q.add(new Pair(this.locations.get(this.startIndex), 0));

        while (!q.isEmpty()) {
            Pair p = q.poll(); //get start
            Location l = p.getLocation();
            int sourceWeight = p.getDistance(); //0 for start
            int edgeWeight;
            Location key;
            this.visited.replace(l, true);
            for (Map.Entry<Location, Integer> entry : l.getAdjacentEdges()
                    .entrySet()) //traverse neighbours
            {
                key = entry.getKey();

                if (!this.visited.get(key)) {
                    edgeWeight = entry.getValue();
                    if (edgeWeight < this.distance.get(key)) {
                        this.distance.replace(key, edgeWeight + sourceWeight); //weight + source distance
                        q.add(p);
                    }
                }
            }

        }

        /*
         * for (int i = 0; i < locations.size(); i++) {
         *
         * }
         */

        //for((Map.Entry<Location, Integer> entry : map.entrySet())

    }

    public static void start() {

    }

    public static void next() {

    }
}
