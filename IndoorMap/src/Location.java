import java.util.HashMap;
import java.util.Map;

public class Location {
    public String name;
    public int level;
    private Map<Location, Integer> weights;
    private Map<Location, String> directions;
    public boolean isElevator;
    public boolean isEscalator;

    public Location(String name, int level) {
        this.name = name;
        this.level = level;
        this.isElevator = false;
        this.isEscalator = false;
        this.weights = new HashMap<>();
        this.directions = new HashMap<>();
    }

    public void addAdjacentLocation(Location l, int weight, String direction) {
        this.weights.put(l, weight);
        this.directions.put(l, direction);
    }

    public Map<Location, Integer> getAdjacentEdges() {
        return this.weights;
    }

}
