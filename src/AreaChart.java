/**
 *
 * @author Nathan Smith- A4, Nathan Smith - A6
 */
/**
* @file    AreaChart.java
* @author  Nathan Smith
* @class   AreaChart
* @date    03/04/13
* @updated 01/05/2013
* @see     
* @brief    A class that creates an Area Chart visualisation from 
* a 2D Array of data
* \ see Graph.java
* 
* This class is responsible for making the Area chart
* It was made using JFreeChart and following examples
* on how to set up the Area chart
* It uses the Graph.java to return a JFreeChart
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;

public class AreaChart implements ActionListener, Graph {
	
    /**
    * creates a constructor taking in the following parameters:
     * @param secondDataset 
    * 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @param boolean first - Used for testing which chart panel appears first
    */
    public AreaChart ( String[][] data,
    				   Dataset dataset,
    				   DataAttribute setting, 
    				   boolean first) {
        
    	m_Data = data;
    	m_Data2 = data;
        m_ChartTitle = setting.GetTitle();
        m_Setting = setting;
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_XLabel2 = setting.GetAxisLabelX();
        m_YLabel2 = setting.GetAxisLabelY();
        m_Col = dataset.GetNoOfCols();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        
        m_XScale = setting.GetXAxisScale();
        setting.GetYAxisScale();
        
        createDataset(m_Data);
        showChart( m_Dataset, first );
        

    }  
    
    /**
    * second empty constructor for JUnit Tests
    */
    public AreaChart (DataAttribute setting){       
   	 m_ChartTitle = setting.GetTitle();
     m_Setting = setting;
     m_XLabel = setting.GetAxisLabelX();
     m_YLabel = setting.GetAxisLabelY();
     m_XLabel2 = setting.GetAxisLabelX2();
     m_YLabel2 = setting.GetAxisLabelY2();
     m_C1 = setting.GetSelectedXIndex();
     m_C2 = setting.GetSelectedYIndex();
    }
	
    /** allows user to select the colour of the points on the area chart
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
        if ( ( ( m_xLabel.isEmpty() )||( m_xLabel.length() > MAX_LABEL_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetLabelX() " + 
                                m_xLabel );
        }
        return m_labelX;
    }
    
    
    /** 
    * @param m_xLabel2 - the x axis label passed from users to the graph 
    * @return  m_labelX2 - returns the user's x label value
    */
    
    public String SetLabelX2( String m_xLabel2 ) {
    String m_labelX2 = m_xLabel2;
		
        boolean test = true;
        if ( ( ( m_xLabel2.isEmpty() )||( m_xLabel2.length() > MAX_LABEL_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetLabelX() " + 
                                m_xLabel2 );
        }
        return m_labelX2;
    }
	
    /** 
    * @param m_yLabel - the y axis label passed from users to the graph
    * @return m_labelY - returns the user's y label value
    */
    @Override
    public String SetLabelY( String m_yLabel ) {
        String m_labelY = m_yLabel;
		
        boolean test = true;
        if ( ( ( m_yLabel.isEmpty() )||( m_yLabel.length() > MAX_LABEL_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetLabelY() " + 
                                m_yLabel );
        }
        return m_labelY;
    }
    
    /** 
    * @param m_yLabel2 - the y axis label passed from users to the graph
    * @return m_labelY2 - returns the user's y label value
    */
    
    public String SetLabelY2( String m_yLabel2 ) {
        String m_labelY2 = m_yLabel2;
		
        boolean test = true;
        if ( ( ( m_yLabel2.isEmpty() )||( m_yLabel2.length() > MAX_LABEL_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetLabelY() " + 
                                m_yLabel2 );
        }
        return m_labelY2;
    }
    
    /** 
    * @param m_graphTitle - the title passed from users to the graph 
    * @return m_labelY - returns the user's title value
    */
    @Override
    public String SetTitle( String m_graphTitle ) {
        String m_title = m_graphTitle;
		
        boolean test = true;
        if ( ( ( m_graphTitle.isEmpty() )||( m_graphTitle.length() > MAX_TITLE_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetTitle() " + 
                                m_graphTitle );
        }	
        return m_title;
    } 
  	
    /**
    * creates an XY data set for the two selected columns
    * 
    * @param m_data - pass the 2D array through
    * 
    * @return dataset - returns the new data set with new values
    */
    private XYDataset createDataset(String[][] data) {
        m_Dataset = new XYSeriesCollection();
        XYSeries dataSeries = new XYSeries( Key ); 
        
        /* goes through each row and each column in the table */
            for( int c = 0; c < m_Col; c++ ) {
                for( int r = 0; r < m_Row; r++ ) {
	    		
                    /* takes every row value for the two selected columns,
                    * parses them as doubles, then adds them to the xy 
                    * data series 
                    */
                    dataSeries.add( Double.parseDouble( data[m_C1][r] ), 
                              Double.parseDouble( data[m_C2][r] ));  		
	        }
	    }
	    
        m_Dataset.addSeries( dataSeries );     
        return m_Dataset;
    }
       
    
    /**
    * creates an Area Chart using the XY data set 
    * and sets the appearance and plot orientation
    * 
    * @param dataset - passes the XY data set through
    * @return graph - returns the Area graph with selected title, x and y labels,
    * and data to be displayed
    */
    private JFreeChart createGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYAreaChart(
            SetTitle( m_ChartTitle ),			
            SetLabelX( m_XLabel ),          
            SetLabelY( m_YLabel ),
            dataset,                  		
            /* sets the range (y) axis to vertical */
            PlotOrientation.VERTICAL,
            
            /* include legend */
            true,
            /* tool tips */
            true,
            /* URLs*/
            false);
     
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setForegroundAlpha(m_ForegroundAlpha);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setTickMarkPaint(Color.black);
            domain.setLowerMargin(m_LowerMargin);
            domain.setUpperMargin(m_UpperMargin);
            
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        
        m_Plot = chart.getXYPlot();
        
        m_Renderer = m_Plot.getRenderer();
        



   		 
        return chart;
    }
    
    /**
    *  visualises the Area chart using JFreeChart and sets the 
    * default size and appearance of the window and graph
    * 
    * @param dataset - passes the XY data set through
    *  
    * @return true - returns test results
    */
    private boolean showChart( XYDataset dataset, boolean first ) {
    	
        JFreeChart chart = createGraph( dataset );
    	ChartPanel chartPanel = new ChartPanel( chart );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	m_ColChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
    	m_ColChangeButton.addActionListener( this );

    	JFrame AreaChartTest = new JFrame();
    	AreaChartTest.setLayout( new BorderLayout() );
    	AreaChartTest.setSize( HEIGHT_FRAME, WIDTH_FRAME );
    	AreaChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	AreaChartTest.add( chartPanel, BorderLayout.NORTH );
    	AreaChartTest.add(new InformationJPanel(m_Setting));
    	AreaChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	AreaChartTest.setVisible( true );
    	
    	/*Tests whether the first panel appears or not */
    	if(first == true){
   		 AreaChartTest.setLocation(100, 0);
   		 } else {
   		 AreaChartTest.setLocation(600, 0);
   		 }
   		 AreaChartTest.setVisible( true );

    	return true;
    }
    
    /**
     *  Creates test data for the charts.
     *  
     * @return dataset - the test dataset
     */
    
    private static XYDataset createDataset() {	
    	final XYSeries series1 = new XYSeries("Test data");
    	series1.add(TestA, TestB);
    	series1.add(TestC, TestD);
    	series1.add(TestE, TestF);
    	series1.add(TestG, TestH);
    	series1.add(TestI, TestJ);
    	series1.add(TestK, TestL);
    	series1.add(TestM, TestN);
    	series1.add(TestO, TestP);
    	
    	final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
    	return dataset;
    }
    
    /**
    * main method to carry out JUnit Tests
    */
    public static void main( String[] args ) {
    
    	DataAttribute test = DataAttribute.GetTestDataAttribute();
    	AreaChart areaChartTest = new AreaChart(test);
    	
        final XYDataset testDataset= createDataset();
    	areaChartTest.showChart(testDataset, true);
    }
    
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_Dataset;
    
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_SecondDataset;
    
    /** renders the visual representation of the (x,y) items */
    private XYItemRenderer m_Renderer;
    
    /** displays a button that can select different colour options */
    private JButton m_ColChangeButton;
    
    /** plots data in the form (x,y) */
    private XYPlot m_Plot; 
	
    /** labels the key of the graph */
    private final String Key = "Data";
    
    /** data used to create the visualisation */
    private String[][] m_Data;
    
    /** data used to create the visualisation */
    private String[][] m_Data2;
    
    /**data to use the Setting */
    private DataAttribute m_Setting;
    
    /**Defining variables for the dataset */
    private int m_Col;
    private int m_Row;;
    private String m_ChartTitle;
    private String m_XLabel;
    private String m_YLabel;
    private String m_XLabel2;
    private String m_YLabel2;
    private int m_C1;
    private int m_C2;
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_XScale;
    /** sets the frame width */
    private final int WIDTH_FRAME = 570;
    
     /** sets the frame width */
    private final int HEIGHT_FRAME = 800;
    
    /** sets the chart panel width*/
    private final int CHART_WIDTH = 600;
    
    /** sets the chart panel height*/
    private final int CHART_HEIGHT = 400;
    
    /** sets the max length of label*/
    private final int MAX_LABEL_LENGTH = 25;
    
    /** sets the max length of label*/
    private final int MAX_TITLE_LENGTH = 35;
    
    /**sets the chart Foreground Alpha */
    private float m_ForegroundAlpha = 0.65f;
    
    /**set the initial lower margin number*/
    private double m_LowerMargin = 0.0;
    
    /**set the initial upper margin number*/
    private double m_UpperMargin = 0.0;
	
    /** Data for the test Coordinate A */
    private final static int TestA = 1862 ;
        
    /** Data for the test Coordinate B */
    private final static int TestB = 34;
    
    /** Data for the test Coordinate C */
    private final static int TestC = 1863;
    
    /** Data for the test Coordinate D */
    private final static int TestD = 49;
    
    /** Data for the test Coordinate E */
    private final static int TestE = 1861;
    
    /** Data for the test Coordinate F */
    private final static int TestF = 60;
    
    /** Data for the test Coordinate G */
    private final static int TestG = 1900;
    
    /** Data for the test Coordinate H */
    private final static int TestH = 83;
    
    /** Data for the test Coordinate I */
    private final static int TestI = 1910;
    
    /** Data for the test Coordinate J */
    private final static int TestJ = 25;
    
    /** Data for the test Coordinate K */
    private final static int TestK = 1925;
    
    /** Data for the test Coordinate L */
    private final static int TestL = 114;
    
    /** Data for the test Coordinate M */
    private final static int TestM = 1929;
    
    /** Data for the test Coordinate N */
    private final static int TestN = 73;
    
    /** Data for the test Coordinate O */
    private final static int TestO = 1934 ;
    
    /** Data for the test Coordinate P */
    private final static int TestP = 15;
    
    
}