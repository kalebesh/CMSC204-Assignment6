/*
 * Class Road represents the edges of the graph of Towns.
 * The class has references to vertices which are the towns, and the distances between them
 * The class also has the road Name, it contains traditional methods, including compareTo.
 * Consider this to be an undirected graph allowing an edge to go from A to B and B to A
 * @author Kaleb Eshetu
 */
public class Road implements Comparable<Road>{
	private Town source;
	private Town destination;
	private String roadName;
	private int weight;
	
	/*
	 * Constructor
	 * @param source a town on the road
	 * @param destination another Town a particular distance away from the first town
	 * @param name the road name
	 */
	public Road(Town source, Town destination, String name)
	{
		this.source= source;
		this.destination = destination;
		roadName= name;
		weight =1;
	}
	/*
	 * Constructor
	 * @param source is a town on the road
	 * @param destination another Town different from first town
	 * @param degrees the weight (distance) from a town to the other
	 * @param name the roadName
	 */
	public Road(Town source, Town destination, int degrees, String name)
	{
		this.source= source;
		this.destination =destination;
		weight=degrees;
		roadName=name;
	}

	/*
	 * Comparing two roads. 0 if the same, positive or negative if different
	 * @param o road object which is to be compared.
	 */
	@Override
	public int compareTo(Road o) {
		// TODO Auto-generated method stub
		return roadName.compareTo(o.getRoadName());
	}
	
	/*
	 * Returns true if the ends of the roads are the same, considering their undirected roads
	 * @param r road object
	 */
	public boolean equals(Object r)
	{
		if(r==null)
			return false;
		if(r==this)
			return true;
		if(!(r instanceof Road))
			return false;
		Road temp = (Road) r;
		
		return ((destination.equals(temp.source)&& source.equals(temp.destination))||
				(source.equals(temp.source) && destination.equals(temp.destination)));
	}
	
	//getters
	/*
	 * returns the name of the road
	 * @return roadName the road name
	 */
	public String getRoadName()
	{
		return roadName;
	}
	
	/*
	 * returns the first town on the road
	 * @retrun source first town on road.
	 */
	public Town getSource()
	{
		return source;
	}
	
	/*
	 *returns second town on the road
	 *@return destination the second town on the road 
	 */
	public Town getDestination()
	{
		return destination;
	}
	
	/*
	 * returns the distance of the road
	 * @return weight the distance of the road
	 */
	public int getWeight()
	{
		return weight;
	}
	
	//setters
	
	/*
	 * sets the name of the road (edge)
	 * @param name the road name of the edge
	 */
	public void setRoadName(String name)
	{
		roadName=name;
	}
	
	/*
	 * Sets the source of the vertices
	 * @param source source of vertices
	 */
	public void setSource(Town source)
	{
		this.source =source;
	}
	
	/*
	 * sets destination of second road
	 * @param destination the destination of the vertices
	 */
	public void setDestination(Town destination)
	{
		this.destination = destination;
	}
	
	/*
	 * sets the weight between vertices (distance)
	 * @param weight the distance between vertices
	 */
	public void setWeight(int weight)
	{
		this.weight= weight;
	}
	
	/*
	 * returns true if the edge holds the given town (vertices)
	 * @param vert vertex at the end of the edge
	 * @return true only if the edge is connected to the certain town, false otherwise.
	 */
	public boolean contains(Town vert)
	{
		return destination.getName().equals(vert.getName())||source.getName().equals(vert.getName());
	}
	
	/*
	 * return a String representation of "town A by road R heading towards town B distance between the town miles
	 * @return "town A by road R heading towards town B distance miles"
	 */
	public String toString()
	{
		return destination.getPreviousTown().getName() + " by "+ this.getRoadName() +" heading towards "
				+destination.getName()+ " "+getWeight() + " miles";
	}
	//Baltimore by I395-Highway heading towards Silver Spring 34 miles
	
	
	
}
