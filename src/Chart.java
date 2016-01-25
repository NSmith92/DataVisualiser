/**
 *
 * @author Phil Davies
 */
/**
* @file Chart.java
* @author Phill Davies
* @class  Chart
* @date 27/02/13
* @see ColumnChart.java
* 
* @brief An interface for all chart classes
*/

public interface Chart {
	
    /**
    * any class that implements Chart is forced to use the xLabel variable
    * 
    * @param xLabel the x axis label passed to the chart by the user
    */
    public String SetLabelX( String xLabel );
    
    /**	
    * any class that implements Chart is forced to use the chartTitle
    * variable
    * 
    * @param chartTitle the title passed to the chart by the user
    */
    public String SetTitle( String chartTitle );
    
}