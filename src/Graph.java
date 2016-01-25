/**
 *
 * @author Phil Davies - A4
 */
/**
* @file    Graph.java
* @author  Phill Davies
* @class   Graph
* @date    27/02/13
* @see  ScatterPlotGraph.java
* @brief An interface for all graph classes
*/

public interface Graph {
		
    /**
    * any class that implements Graph is forced to use the xLabel 
    * variable
    * 
    * @param xLabel the x axis label passed to the graph by the user
    */
    public String SetLabelX( String xLabel );
	
    /**
    * any class that implements Graph is forced to use the yLabel 
    * variable
    * 
    * @param yLabel the y axis label passed to the graph by the user
    */
    public String SetLabelY( String yLabel );
	
    /**
    * any class that implements Graph is forced to use the graphTitle 
    * variable
    * 
    * @param graphTitle the title passed to the graph by the user
    */
    public String SetTitle( String graphTitle );
}
