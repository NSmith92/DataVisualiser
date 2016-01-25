/**
 *
 * @author Mark Leyshon - A5, Nathan Smith - A6
 */
/**
* @file    PolarChart.java
* @author  Mark Leyshon
* @class   PolarChart
* @date    14/04/13
* @updated 01/05/2013
* @see     
* @brief    A class that creates a Polar Chart visualisation from 
* a 2D Array of data
* \ see Graph.java
* 
* This class is responsible for making the Polar chart
* It was made using JFreeChart and following examples
* on how to set up the Polar chart
* It uses the Graph.java to return a JFreeChart
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class PolarChart implements ActionListener, Graph {
	
	/**
	    * creates a constructor taking in the following parameters:
	    * 
	    * @param String[][] data - data used to create the visualisation.
	    * @param Dataset dataset - the dataset used (rows and columns)
	    * @param DataAttribute setting - Data attributes
	    * @param boolean first - Test which graph panel appears first
	    */
	    public PolarChart ( String[][] data,
	    				   Dataset dataset, 
	    				   DataAttribute setting,
	    				   boolean first) {
	        
	    	m_Data = data;
	        m_ChartTitle = setting.GetTitle();
	        m_Setting = setting;
	        m_XLabel = setting.GetAxisLabelX();
	        m_YLabel = setting.GetAxisLabelY();
	        m_Col = dataset.GetNoOfCols();
	        m_Row = dataset.GetNoOfRows();
	        m_C1 = setting.GetSelectedXIndex();
	        m_C2 = setting.GetSelectedYIndex();
	        
	        CreateDataset(m_Data);
	        ShowChart(m_Dataset, first);
	    }  
	    
	    /**
	    * second constructor for JUnit Tests
	    */
	    public PolarChart (DataAttribute setting) {
	    	 m_ChartTitle = setting.GetTitle();
	         m_Setting = setting;
	         m_XLabel = setting.GetAxisLabelX();
	         m_YLabel = setting.GetAxisLabelY();
	         m_C1 = setting.GetSelectedXIndex();
	         m_C2 = setting.GetSelectedYIndex();
	        
	    }
		
	    /** allows user to select the colour of the points on the Polar chart
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
	        return m_title;
	    } 
	  	
	    /**
	    * creates an XY data set for the two selected columns
	    * 
	    * @param String[][] data - pass the 2d array through
	    * @return XYDataset dataset - returns the new data set with new values
	    */
	    private XYDataset CreateDataset(String[][] data) {
	    	XYSeriesCollection collection = new XYSeriesCollection();
	        
	        
            for( int r = 0; r < m_Row; r++ ) {
            	XYSeries dataSeries = new XYSeries( Key + r); 
                dataSeries.add( Double.parseDouble( data[m_C1][r] ), 
                				Double.parseDouble( data[m_C2][r] ));  		
                collection.addSeries( dataSeries );   
	        }
	    
            
            
            m_Dataset = collection;
            
	        return collection;
	    }
	    
	    /**
	    *  visualises the Polar chart using JFreeChart and sets the 
	    * default size and appearance of the window and graph
	    * 
	    * @param XYDataset dataset - passes the XY data set through 
	    * @return Boolean true - returns test results
	    */
	    private boolean ShowChart( XYDataset dataset, boolean first ) {
	    	
	    	JFreeChart chart = ChartFactory.createPolarChart(
	    			m_ChartTitle,
	    			dataset, 
	    			false, 
	    			false,
	    			false);
	        
	    	chart.setBackgroundPaint(Color.white);
	        
	    	ChartPanel chartPanel = new ChartPanel( chart );
	    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

	    	m_ColChangeButton = new JButton( "Change Colours" );
	    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
	    	m_ColChangeButton.addActionListener( this );
	    	
	    	final PolarPlot plot = (PolarPlot) chart.getPlot();
	        final DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
	        renderer.setSeriesFilled(2, true);
	        
	        m_Plot = plot;
	        m_Renderer = renderer;
	        
	        
	    	JFrame test = new JFrame();
	        test.setLayout( new BorderLayout());
	        test.setSize( FRAME_HEIGHT, FRAME_WIDTH );
	        test.setTitle( "Polar Chart" );
	        test.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	        test.add( chartPanel, BorderLayout.NORTH );
	        test.add(new InformationJPanel(m_Setting), BorderLayout.CENTER);
	        test.add( m_ColChangeButton, BorderLayout.SOUTH );
	        test.setVisible( true );
	        
	        /*Tests which graph panel appears first */
	    	if(first == true){
	      		 test.setLocation(100, 0);
	      		 } else {
	      		 test.setLocation(600, 0);
	      		 }
	      		 test.setVisible( true );
	         
	    	return true;
	    }
	    
	    /**
	    * main method to carry out JUnit Tests
	    */
	    public static void main( String[] args ) {
			
			Random generator = new Random();
			
			XYSeriesCollection testCollection = new XYSeriesCollection();
			XYSeries testDataset = new XYSeries("testSeries");
			
			DataAttribute test = DataAttribute.GetTestDataAttribute();
			PolarChart polarChartTest = new PolarChart(test);
	    	
	        for(int i = 0;i< LOOP;i++) {
	        	int temp1 = generator.nextInt(LIMIT);
	        	int temp2 = generator.nextInt(LIMIT);
	        	
	        	testDataset.add(temp1,temp2);
	        }
	        testCollection.addSeries(testDataset);
	        
	    	polarChartTest.ShowChart(testCollection, true);
	    }
	    
	    
	    private final static int LOOP =10;
	    private final static int LIMIT = 360;
	    /** represents a series of vertices to be used as a data set */
	    private XYSeriesCollection m_Dataset;
	    
	    /** renders the visual representation of the (x,y) items */
	    private DefaultPolarItemRenderer m_Renderer;
	    
	    /** displays a button that can select different colour options */
	    private JButton m_ColChangeButton;
	    
	    /** plots data in the form (x,y) */
	    private PolarPlot m_Plot; 
		
	    /** labels the key of the graph */
	    private final String Key = "Data";
	    
	    /** data used to create the visualisation */
	    private String[][] m_Data;
	    
	    //private DataAttribute m_Setting;
	    
	    /**Defining variables for the dataset */
	    private int m_Col;
	    private int m_Row;;
	    private String m_ChartTitle;
	    private String m_XLabel;
	    private String m_YLabel;
	    private int m_C1;
	    private int m_C2;
	 
	    
	    /** sets the frame width */
	    private final int FRAME_WIDTH = 570;
	    private final int FRAME_HEIGHT = 800;
	
	    private DataAttribute m_Setting;
    
        private final int CHART_HEIGHT = 400;
    
        private final int CHART_WIDTH = 600;
}
