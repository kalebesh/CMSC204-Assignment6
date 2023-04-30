import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Graph implements GraphInterface<Town, Road> {

	private Set<Town> towns;
	private Set<Road> roads;
	private ArrayList<String> edges = new ArrayList<String>();
	private Set<Town> uncheckedPathsList, checkedPathsList;
	
	/*
	 * Constructor
	 * creates HashSets for the created variables
	 */
	public Graph()
	{
		towns= new HashSet<>();
		roads = new HashSet<>();
		uncheckedPathsList = new HashSet<>();
		checkedPathsList = new HashSet<>();
	}
	
	
	//~ Methods ----------------------------------------------------------------

    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    public Road getEdge(Town sourceVertex, Town destinationVertex)
    {
    	if (destinationVertex == null || sourceVertex == null) {
			throw new NullPointerException("One or both vertices are null");
		}
    	
    	if(!(containsVertex(destinationVertex)))
    		throw new IllegalArgumentException("the vertice is not in the graph");
    	
    	if(!(containsVertex(sourceVertex)))
    		throw new IllegalArgumentException("the vertice is not in the graph");

		Road theRoad = null;
		for (Road r: roads) {
			if (r.contains(sourceVertex) && r.contains(destinationVertex)) {
				theRoad = r;
			}
		}
		return theRoad;
    }


    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException, NullPointerException
    {
    	if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException("One or both vertices are null");
			
    		if(!(containsVertex(destinationVertex)))
    			throw new IllegalArgumentException("vertice is not in graph");
    		
    		if (!(containsVertex(sourceVertex))) 
    			throw new IllegalArgumentException("vertices is not in the graph");
    		
    		sourceVertex.getTowns().add(destinationVertex); 
    		destinationVertex.getTowns().add(sourceVertex); 
    		Road r = new Road(sourceVertex, destinationVertex, weight, description);
    		roads.add(r);	
    		return r;
    }

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    public boolean addVertex(Town v) throws NullPointerException
    {
    	if (v == null) {
			throw new NullPointerException("The vertex is null");
		}
    	
		if (!(towns.contains(v))) {
			return towns.add(v);
		}
		else
		return false;
    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    public boolean containsEdge(Town sourceVertex, Town destinationVertex)
    {
    	if(getEdge(sourceVertex, destinationVertex) != null)
    		return true;
    	else
    		return false;
    }
    

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    public boolean containsVertex(Town v)
    {
    	return towns.contains(v);
    }
    

    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    public Set<Road> edgeSet()
    {
    	return roads;
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    public Set<Road> edgesOf(Town vertex)
    {
		Set<Road> listRoad = new HashSet<>();

    	if (vertex == null) {
			throw new NullPointerException("Town does not exist");
		}
    	
		for (Road r: roads) {
			if (r.contains(vertex)) {
				listRoad.add(r);
			}
		}
		return listRoad;
    }


    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {
    	Road currentRoad = null;
		for (Road r: roads) {
			if (r.contains(destinationVertex) && r.contains(sourceVertex))
			{
				if(description != null&& weight > -1)
				 {
				currentRoad = r;
				 }
			}
		}
		
		destinationVertex.getTowns().remove(sourceVertex);
		sourceVertex.getTowns().remove(destinationVertex); 
		roads.remove(currentRoad);
		return currentRoad;
    }


    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    public boolean removeVertex(Town v)
    {
    	if (v == null) {
			return false;
		}
		return towns.remove(v);
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    public Set<Town> vertexSet()
    {
    	return towns;
    }
    
    /*
	 * Represents String path
	 * @param edges arraylist of paths
	 * @param sourceVertex town of path beginning
	 * @param destinationVertex town of path ending
	 */
    private void pathsPattern(ArrayList<String> edges, Town sourceVertex, Town destinationVertex) {
		try {		
			StringBuilder StrEdge = new StringBuilder();
			Road r = getEdge(destinationVertex.getPreviousTown(), destinationVertex);
			StrEdge.append(r.toString());
			edges.add(StrEdge.toString());

			while (destinationVertex.getPreviousTown().equals(sourceVertex) == false) {
				pathsPattern(edges, sourceVertex, destinationVertex.getPreviousTown());
			}
		} 
		catch(Exception e) {
			System.out.println("Cleaning paths... ");
			edges.clear();
		}
	}
    
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
    {
    	checkedPathsList = new HashSet<Town>();
		uncheckedPathsList = new HashSet<Town>(towns);
		checkedPathsList.add(sourceVertex); // Add it to the checked towns list
		uncheckedPathsList.remove(sourceVertex); // Remove it from the unchecked towns list

		for (Town town: towns) {
			town.setWeight(Integer.MAX_VALUE);
			town.setPreviousTown(null);
		}

		sourceVertex.setWeight(0); // Set source vertex (town) to 0
		dijkstraShortestPath(sourceVertex);
		StringBuilder shortPaths = new StringBuilder();
		Road r = getEdge(destinationVertex.getPreviousTown(), destinationVertex);
		shortPaths.append(r.toString());
		edges.add(shortPaths.toString());
		if (destinationVertex.getPreviousTown().equals(sourceVertex) == false) {
			pathsPattern(edges, sourceVertex, destinationVertex.getPreviousTown());
		}
		Collections.reverse(edges);
		return edges;
    }
    
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
    public void dijkstraShortestPath(Town sourceVertex)
    {
    	Boolean found = false;
		while (found == false && uncheckedPathsList.isEmpty() == false) {
			
			found = true;
			Town next = null;
			int max = Integer.MAX_VALUE;
			int weight =0;

			for (Town town: checkedPathsList) {
				Set<Town> checked = town.getTowns();
				Set<Town> unchecked = new HashSet<>();
				for (Town t: checked) {
					if (uncheckedPathsList.contains(t) == false) {
						continue;
					}
					unchecked.add(t);
				}
				for (Town t: unchecked) {
					if(t.equals(sourceVertex))
						weight=town.getWeight() + getEdge(town, t).getWeight();
						
						max = weight;
						next = t;
						t.setPreviousTown(town);
				}
			}

			if (next != null) {
				found = false;
				next.setWeight(max);
				checkedPathsList.add(next);
				edges.add(next.getName());
				uncheckedPathsList.remove(next);
			}
		}
    }
    
}
