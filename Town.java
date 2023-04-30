import java.util.HashSet;
import java.util.Set;

/*
 * Refers a town as a node on the graph. The class holds the name, list of adjacent towns,
 * weight (distance), previousTowns. The class also has traditional methods, and implements
 * the comparable interface.
 * @author Kaleb Eshetu
 */
public class Town implements Comparable<Town>{
	private String name;
	private int weight;
	private Town previousTown;
	private Set<Town> towns;
	
	/*
	 * Constructor
	 * default constructor to set values to max and null;
	 * @param name name of town
	 */
	public Town(String name)
	{
		this.name= name;
		weight =Integer.MAX_VALUE;
		previousTown=null;
		towns= new HashSet<>();
	}
	
	/*
	 * Constructor
	 * @param templeTown the town to create a copy of
	 */
	public Town(Town templeTown)
	{
		name= templeTown.getName();
		weight= templeTown.getWeight();
		previousTown= templeTown.getPreviousTown();
		towns = templeTown.getTowns();
	}

	@Override
	/*
	 * compareTo method 
	 */
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.getName());
	}
	
	//getters
	
	//@return town name
	public String getName()
	{
		return name;
	}
	
	//@return town weight (distance between towns)
	public int getWeight()
	{
		return weight;
	}
	
	//@return the Town object previous Town
	public Town getPreviousTown()
	{
		return previousTown;
	}
	
	//@return towns representing the set of towns on the graph
	public Set<Town> getTowns()
	{
		return towns;
	}
	
	//setters
	
	/*
	 * Sets the name of the town to a given parameter name
	 * @param name sets a new town name
	 */
	public void setName(String name)
	{
		this.name=name;
	}
	
	/*
	 * Sets a new weight representing distance between towns
	 * @param weight sets the towns weight.
	 */
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	/*
	 * Sets a new previousTown value
	 * @param previousTown will be set to the previousTown
	 */
	public void setPreviousTown(Town previousTown)
	{
		this.previousTown=previousTown;
	}
	
	/*
	 * Sets town on the graph
	 * @param towns will set the towns on the graph
	 */
	public void setTowns(Set<Town> towns)
	{
		this.towns=towns;
	}
	
	/*
	 * Returns true if the town names are the same, false otherwise
	 */
	public boolean equals(Object o)
	{
		if(o==null)
			return false;
		if(this ==o)
			return true;
		if(o instanceof Town)
			return true;
		
		Town temp = (Town) o;
		return name.equals(temp.getName());
	}
	
	//creates a hashcode for the name of the town and returns it.
	//@return name hashcode
	public int hashCode()
	{
		return name.hashCode();
	}
	
	/*
	 * set things to default value in case it is a town in the end
	 */
	public void restart()
	{
		this.previousTown=null;
		this.weight= Integer.MAX_VALUE;
	}
	
	/*
	 * toString method returing the town name
	 * @return format "This is town: TOWN"
	 */
	public String toString()
	{
		return "This is town: "+name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
