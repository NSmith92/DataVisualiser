/**
 *
 * @author Phil Davies - A4, Leopold Stiegler - A5, Nathan Smith -A6
 */
/**
* @file    ScatterPlotGraph.java
* @author  Phill Davies & Leopold Stiegler
* @class   ScatterPlotGraph
* @date    27/02/13
* @updated 01/05/2013
* @see     
* @brief    A class that creates a Scatter Plot Graph visualisation from 
* a 2D Array of data
*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlotGraph implements ActionListener, Graph {
	
    /**
    * creates a constructor taking in the following parameters:
    * 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @param boolean first - Test which graph panel appears first.
    * 
    */
    public ScatterPlotGraph ( String[][] data, 
    						  Dataset dataset, 
    						  DataAttribute setting,
    						  boolean first) {
        
    	m_Data = data;
    	m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_Col = dataset.GetNoOfCols();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
    	
        createDataset( m_Data );
        showGraph( m_Dataset, first );
    }  
    
    /*Gets the setting labels */
    public ScatterPlotGraph(DataAttribute setting) {
        m_ChartTitle = setting.GetTitle();
        m_Setting = setting;
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
    }
    
    /**
    * second empty constructor for JUnit Tests
    */
    public ScatterPlotGraph () {}
	
    /** allows user to select the colour of the points on the 
    * scatter graph
    */
    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == m_ColChangeButton ) {
            ColourMap cM = new ColourMap();
            cM.SetupData( m_Plot.getSeriesCount(), 
                          m_Renderer );
            cM.setVisible( false );
        }
    }
	
    /** 
    * @param m_xLabel - the x axis label passed from users to the graph 
    * @return  m_labelX - returns the user's x label value
    */
    @Override
    public String SetLabelX( String m_xLabel ) {
    String m_labelX = m_xLabel;
		
        boolean test = true;                   
        
        return m_labelX;
    }
	
    /** 
    * @param m_yLabel - the y axis label passed from users to the graph
    * @return m_labelY - returns the user's y label value
    */
    @Override
    public String SetLabelY( String m_yLabel ) {
        String m_labelY = m_yLabel;
        return m_labelY;
    }
    
    /** 
    * @param m_graphTitle - the title passed from users to the graph 
    * @return m_title - returns the user's title value
    */
    @Override
    public String SetTitle( String m_graphTitle ) {
        String m_title = m_graphTitle;
		
        boolean test = true;
        return m_title;
    } 
  	
    /**
    * creates an XY data set for the two selected columns
    * 
    * @param data - pass the 2d array through
    * 
    * @return m_Dataset - returns the new data set with new values
    */
    private XYDataset createDataset( String[][] data ) {
        m_Dataset = new XYSeriesCollection();
        //XYSeries dataset = null;
        
        /* goes through each row and each column in the table */
            
        for( int r = 0; r < m_Row; r++ ) {
        	XYSeries dataset = new XYSeries( Key + r ); 
    		
            /* takes every row value for the two selected columns,
            * parses them as doubles, then adds them to the xy 
            * data series 
            */
        	dataset.add( Double.parseDouble( data[m_C1][r] ), 
                      Double.parseDouble( data[m_C2][r] ));
        	
        	m_Dataset.addSeries( dataset ); 
        }
        
	    
          
        return m_Dataset;
    }
    
    /**
    * creates a Scatter Plot Graph using the XY data set 
    * and sets the appearance and plot orientation
    * 
    * @param dataset - passes the XY data set through
    * 
    * @return graph - returns the graph with selected title, x and y labels,
    * and data to be displayed
    */
    private JFreeChart createGraph( XYDataset dataset ) {
        JFreeChart graph = ChartFactory.createScatterPlot(
            SetTitle( m_ChartTitle ),			
            SetLabelX( m_XLabel ),          
            SetLabelY( m_YLabel ),          
            dataset,                  		
            /* sets the range (y) axis to vertical */
            PlotOrientation.VERTICAL,
            /* include legend */
            false,
            /* tool tips */
            true,
            /* URLs*/
            false);
        
        m_Plot = graph.getXYPlot();
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) m_Plot.getDomainAxis();
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) m_Plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
            
        m_Renderer = m_Plot.getRenderer();
        
        return graph;
    }
    
    /**
    *  visualises the scatter graph using JFreeChart and sets the 
    * default size and appearance of the window and graph
    * 
    * @param dataset - passes the XY data set through
    *  
    * @return true - returns test results
    */
    private boolean showGraph( XYDataset dataset, boolean first ) {
    	
        JFreeChart graph = createGraph( dataset );
    	ChartPanel chartPanel = new ChartPanel( graph );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	m_ColChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
    	m_ColChangeButton.addActionListener( this );
    	
    	JFrame scatterChart = new JFrame();
    	scatterChart.setLayout( new BorderLayout() );
    	scatterChart.setSize( FRAME_WIDTH, FRAME_HEIGHT );
    	scatterChart.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	scatterChart.add( chartPanel, BorderLayout.NORTH );
    	scatterChart.add(new InformationJPanel(m_Setting));
    	scatterChart.add( colourButtonPanel,BorderLayout.SOUTH );
    	scatterChart.setVisible( true );
    	
    	/*Test which graph panel appears first */
    	if(first == true){
      		 scatterChart.setLocation(100, 0);
      		 } else {
      		 scatterChart.setLocation(600, 0);
      		 }
      		 scatterChart.setVisible( true );

    	return true;
    }
    
    /**
    * main method to carry out JUnit Tests
    */
    public static void main( String[] args ) {
		
    	DataAttribute test = DataAttribute.GetTestDataAttribute();
    	ScatterPlotGraph scatterTest = new ScatterPlotGraph(test);
    	
    	Random generator = new Random();
    	
    	XYSeriesCollection dataSeries = new XYSeriesCollection();
    	XYSeries dataset = new XYSeries("testSeries"); 
    	for(int i = 0;i < LOOP;i++) {
    		int temp1 = generator.nextInt();
    		int temp2 = generator.nextInt();
        	dataset.add(temp1,temp2);
    	}
    	dataSeries.addSeries(dataset);
    	
        scatterTest.showGraph(dataSeries, true);
    }
    
    private static final int LOOP = 30;
    private int m_IndexCount;
    private String m_ChartTitle;
    
    /** an array of data for visualisation */
    private String m_Data[][];
    
    /** x and y label axis */
    private String m_XLabel;
    private String m_YLabel;
    
    /* amount of rows */
    private int m_Row;
    private int m_Col;
    
    /** columns */
    private int m_C1;
    private int m_C2;
    
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_XScale;
    private double m_YScale;
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_Dataset;
    
    /** renders the visual representation of the (x,y) items */
    private XYItemRenderer m_Renderer;
    
    private DataAttribute m_Setting;
    
    /** displays a button that can select different colour options */
    private JButton m_ColChangeButton;
    
    /** plots data in the form (x,y) */
    private XYPlot m_Plot; 
	
    /** labels the key of the graph */
    private final String Key = "Data";
    
    /** sets the frame width */
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 570;
    private final int CHART_WIDTH = 600;
    private final int CHART_HEIGHT = 400;
    
    
}