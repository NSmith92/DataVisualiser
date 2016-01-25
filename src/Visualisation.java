/**
 *
 * @author Gavin Driscoll - A4, Nathan Smith -A6
 */
/**
* @file -Visualisation.java
* @author -Gavin Driscoll
* @class  -Visualisation
* @date -01 March 2013
* @updated 01/05/2013
* @see -BobsViz.java
*
* @brief A class that displays specific data in a Column Chart visualiser.
*
*/
import javax.swing.JPanel;

public class Visualisation extends JPanel {
    
    public ColumnChart GetColChart() {
        return m_ColumnChart;
    }
    /**
    * creates a column chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean first- if successful
    */
    public boolean SetColChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting,
    							boolean first) {
    	
        m_ColumnChart = new ColumnChart( data, dataset, setting, true );
         
        return true;
    }
    /**
     * Gets Pie Chart
     *  
     * @return PieChart m_PieChart
     */
    
    public PieChart GetVizPieChart(){
        return m_PieChart;
    }
    /**
    * creates a pie chart.
    * @param String[] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetVizPieChart( String[][] data, 
								   Dataset dataset,
								   DataAttribute setting, boolean first ) {
          
    	m_PieChart = new PieChart( data, dataset, setting, true);
        
        return true;
    }
    /**
     * Gets Area Chart
     *  
     *  
     * @return AreaChart m_AreaChart
     */
    public AreaChart GetAreaChart() {
        return m_AreaChart;
    }
    /**
    * creates a area chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetAreaChart( String[][] data, 
    							Dataset dataset,
    							
    							DataAttribute setting, boolean first) {
    	
        m_AreaChart = new AreaChart( data, dataset, setting, first);
         
        return true;
    }
    
    /**0
    * creates a table visualisation.
    * @param data - data used to create a visualisations. 
    * @param headers - gets the headers for the table visualisation.
    * @return - boolen true if success
    */
    public boolean SetTable( String[][] data, String[] headers, String title, boolean first) {
        m_Table = new Table( data, headers, title, first );
        boolean test = true;
        
        if(( test == true ) && ( data == null )) {
        System.err.println( "SetTable::SetTable() ***Warning, object"
                    + "data is null. Value sent: " + data );
        }
           else if(( test == true ) && ( headers == null )) {
        System.err.println( "SetTable::SetTable() ***Warning, object"
                    + "headers is null. Value sent: " + headers );
        }
        
        return true;
    }
    /**
     * Gets Table
     *  
     * @return Table m_Table
     */
    public Table GetTable(){
        //Returns a Table
        return m_Table;
    }
    
    /**
    * creates a scatter plot graph visualisation.
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetScatterPlotGraph( String[][] data, 
										Dataset dataset,
										DataAttribute setting,
										boolean first) {
        m_ScatterPlotGraph = new ScatterPlotGraph(data, dataset, setting, true);
        
        return true;
    }
    /**
     * Gets ScatterPlotGraph
     *  
     * @return ScatterPlotGraph m_ScatterPlotGraph
     */
    public ScatterPlotGraph GetScatterPlotGraph(){
        //Returns a Table
        return m_ScatterPlotGraph;
    }
    /**
     * Gets BubbleChart
     *  
     * @return BubbleChart m_BubbleChart
     */
    public BubbleChart GetBubbleChart() {
        return m_BubbleChart;
    }
    /**
    * creates a bubble chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetBubbleChart(String[][] data, 
	Dataset dataset, DataAttribute setting, boolean first) {

		m_BubbleChart = new BubbleChart( data, dataset, setting, first);

        return true;
    }
    /**
     * Gets Line Chart
     *  
     * @return LineChart m_LineChart
     */
    public LineChart GetLineChart() {
        return m_LineChart;
    }
    /**
    * creates a line chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetLineChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting,
    							boolean first) {
    	
    	m_LineChart = new LineChart( data, dataset, setting, true );
         
        return true;
    }
    /**
     * Gets Polar Chart
     *  
     * @return PolarChart m_PolarChart
     */
    public PolarChart GetPolarChart() {
        return m_PolarChart;
    }
    /**
    * creates a polar chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetPolarChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting,
    							boolean first) {
    	
    	m_PolarChart = new PolarChart( data, dataset, setting, true );
         
        return true;
    }
    
      /**
    * creates test instances. 
    */
    public static void main(String[][] args) {
        Visualisation v = new Visualisation();
        
        v.SetScatterPlotGraph( null, null, null, false );
        v.SetTable( null, null, null,false );
        v.SetVizPieChart( null, null, null, false );
        v.SetColChart( null, null, null, false);
    }
 
            
           
    /**
    * @return - returns the visualisation. boolean true
    * 			if successful 
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if(( test == true ) && ( bv == null) ) {
            System.err.println( "Visualisation::SetBV() ***Warning, GroupPDM2"
                    + "application is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "Visualisation::SetBV() GroupPDM2"
                    + "application. Value sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    

    private PieChart m_PieChart;
    private ScatterPlotGraph m_ScatterPlotGraph;
    private ColumnChart m_ColumnChart;
    private AreaChart m_AreaChart;
    private BubbleChart m_BubbleChart;
    private LineChart m_LineChart;
    private PolarChart m_PolarChart;
    private Table m_Table;
    private BobViz m_BV;
    

}