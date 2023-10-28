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
    IndoorMap im;
    
    public Path(Location start, Location finish, IndoorMap im)
    {
    	this.im = im;
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
    	
    	Queue<Location> q = new LinkedList<>();
    	
    	q.offer(locations.get(startIndex));
    	
    	while(!q.isEmpty())
    	{
    		 Location l = q.poll(); //get start
    		 int sourceWeight = distance.get(l); //0 for start
    		 int edgeWeight;
    		 Location key;
    		 for(Map.Entry<Location, Integer> entry : l.getAdjacentEdges().entrySet()) //traverse neighbours
    		 {
    			 key = entry.getKey();
    			 edgeWeight = entry.getValue();
    			 if(edgeWeight < distance.get(key))
    			 {
    				 distance.replace(key, edgeWeight + sourceWeight); //weight + source distance
    			 }
    			 if(visited.get(key) == false)
    			 q.offer(im.getLocation(key.name));
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
