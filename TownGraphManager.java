import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class TownGraphManager implements TownGraphManagerInterface{

	private Graph graph = new Graph();

	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName)
	{
		 // validate input parameters
		if (town1==null || town2==null) {
			return false;
		}
		if (graph.addEdge(getTown(town1),getTown(town2),weight,roadName) != null) {
			return true;
		}
		else
		return false;
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2)
	{
		Set<Town> theTowns = graph.vertexSet();
        Town townA = null;
        Town townB = null;
        for (Town t : theTowns) {
            if (t.getName().equals(town1)) {
                townA = t;
            }
            if (t.getName().equals(town2)) {
                townB = t;
            }
            if (townA!=null && townB!=null) {
                break;
            }
        }
        Road r = graph.getEdge(townA, townB);
        if (r == null)
            return null;
        else
        return r.getRoadName();
	}
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v)
	{
		return graph.addVertex(new Town(v));
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name)
	{
		Set<Town> towns = graph.vertexSet();
		Town town = null;
		for (Town t: towns) {
			if (t.getName().equals(name)) {
				town = t;
			}
		}
		return town;
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v)
	{
		return graph.containsVertex(new Town(v));
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2)
	{
		return graph.containsEdge(getTown(town1), getTown(town2));
	}
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads()
	{
		return graph.edgeSet().stream().map(Road::getRoadName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road)
	{
		if (!(graph.removeEdge(new Town(town1), new Town(town2), 0, road)== null)) 
			return true;
		else
		return false;
	}
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v)
	{
		return graph.removeVertex(getTown(v));
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns()
	{
		return graph.vertexSet().stream().map(Town::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2)
	{
		return graph.shortestPath(getTown(town1), getTown(town2));
	}
	
	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		InputStream stream = new FileInputStream(selectedFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		reader.lines().map(str -> str.split(";|\\,")).forEach(array -> 
		{
		addTown(array[2]); 
		addTown(array[3]); 
		addRoad(array[2], array[3], Integer.parseInt(array[1]),array[0]);});
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
