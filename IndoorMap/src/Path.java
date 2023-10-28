import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Path {
    int startIndex;
    int finishIndex;
    private Map<Location, Boolean> visited;
    private Map<Location, Integer> distance;
    static int MAX_VAL = Integer.MAX_VALUE;
    public List<Location> locations;
    
    
    public Path(Location start, Location finish, IndoorMap im)
    {
    	this.locations = im.locations;
     	for (int i = 0; i < this.locations.size(); i++)
     	{
     		visited.put(this.locations.get(i), false);
     		if(this.locations.get(i).name.equals(start))
     		{
     			distance.put(this.locations.get(i), 0);
     			startIndex = i;
     		}
     		else 
     			distance.put(this.locations.get(i), MAX_VAL);
     		if(this.locations.get(i).name.equals(finish))
     		{
     			finishIndex = i;
     		}
     		
     	}
    	
    }
    
    //traverses the whole graph starting from 0 to update all the distances
    public void buildPath() {
    	//PriorityQueue<Location> q = new PriorityQueue<>(); //min priority queue makes start and next easier
    	//q.offer(locations.get(startIndex));
    	
    	while(visited.containsValue(false))
    	{
    		 int weight;
    		 Location key;
    		 
    		 Location l = locations.get(startIndex); //get start
    		 for(Map.Entry<Location, Integer> entry : l.getAdjacentEdges().entrySet()) //traverse neighbours
    		 {
    			 key = entry.getKey();
    			 weight = entry.getValue();
    			 if(weight < distance.getValue(key))
    			 {
    				 
    			 }
    			 
    		 }
    		 
    	}
    	
    	
    	/*
    	for (int i = 0; i < locations.size(); i++)
    	{
    		
    	}
    	*/
    
    	//for((Map.Entry<Location, Integer> entry : map.entrySet())
    	
    }

    public static void start() {

    }

    public static void next() {

    }
}
