 
/**
 *
 * @author Bradley Coles-Perkins - A4, Nathan Smith - A6
 */
/**
* @file    LineChart.java
* @author  Bradley Coles-Perkins
* @class   LineChart
* @date    04/04/13
* @updated 01/05/2013
* @see     
* @brief    A class that creates a Line Chart visualisation from 
* a 2D Array of data
*/

import java.awt.BorderLayout;
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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;

import java.awt.Color;

public class LineChart implements ActionListener, Graph {
	
    /**
    * creates a constructor taking in the following parameters:
    * 
    * @param m_data - the data to be passed from users to the line chart
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @param boolean first - test which graph appears first.
    */
    public LineChart ( String[][] data,
					   Dataset dataset, 
					   DataAttribute setting, boolean first ) {
    	
    	m_Data = data;
        m_ChartTitle = setting.GetTitle();
        m_Setting = setting;
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_Col = dataset.GetNoOfCols();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
        
        showChart( createDataset(m_Data), first );
    }  
    
    /**Gets the setting labels */
    public LineChart(DataAttribute setting) {
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
    /** allows user to select the colour of the points on the line chart
    */
    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == colChangeButton ) {
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
    * @param String graphTitle - the title passed from users to the graph 
    * @return graphTitle - returns the user's title value
    */
    @Override
    public String SetTitle( String graphTitle ) {
        String m_title = graphTitle;
        return m_title;
    } 
  	
    /**
    * creates an XY data set for the two selected columns
    * 
    * @param m_Data - pass the 2d array through
    * @return dataset - returns the new data set with new values
    */
    private XYDataset createDataset(String[][] data) {
        m_Dataset = new XYSeriesCollection();
        XYSeries dataSeries = new XYSeries( Key ); 
        
            for( int r = 0; r < m_Row; r++ ) {
    		
                dataSeries.add( Double.parseDouble( data[m_C1][r] ), 
                          Double.parseDouble( data[m_C2][r] ));  		
            }
    
	    
        m_Dataset.addSeries( dataSeries );     
        return m_Dataset;        
    }
    
    /**
    * creates a Line Chart using the XY data set 
    * and sets the appearance and plot orientation
    * 
    * @param dataset - passes the XY data set through
    * 
    * @return graph - returns the graph with selected title, x and y labels,
    * and data to be displayed
    */
    private JFreeChart createGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYLineChart(
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

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
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
    *  visualises the line chart using JFreeChart and sets the 
    * default size and appearance of the window and graph
    * 
    * @param dataset - passes the XY data set through
    *  
    * @return true - returns test results
    */
    private boolean showChart( XYDataset dataset, boolean first) {
    	
        JFreeChart chart = createGraph( dataset );
    	ChartPanel chartPanel = new ChartPanel( chart );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	colChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( colChangeButton,BorderLayout.SOUTH );
    	colChangeButton.addActionListener( this );

    	JFrame LineChartTest = new JFrame();
    	LineChartTest.setLayout( new BorderLayout() );
    	LineChartTest.setSize( WIDTH_FRAME, HEIGHT_FRAME );
    	LineChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	LineChartTest.add( chartPanel, BorderLayout.NORTH );
    	LineChartTest.add(new InformationJPanel(m_Setting));
    	LineChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	LineChartTest.setVisible( true );
    	
    	/*Test which graph panel appears first*/
    	if(first == true){
      		 LineChartTest.setLocation(100, 0);
      		 } else {
      		 LineChartTest.setLocation(600, 0);
      		 }
      		 LineChartTest.setVisible( true );

    	return true;
    }
    /**
     * creates a test XYDataset
     *  
     * @return XYDataset dataset - returns a test dataset
     */
    private static XYDataset createDataset() {	
    	final XYSeries series1 = new XYSeries("Test data");
    	series1.add(TestDataW, TestDataX);
    	series1.add(TestDataY, TestDataZ);
    	
    	final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
    	return dataset;
    }
    
    /**
    * main method to carry out JUnit Tests
    */
    public static void main( String[] args ) {

    	DataAttribute test = DataAttribute.GetTestDataAttribute();
    	LineChart lineChartTest = new LineChart(test);
    	
        final XYDataset datasetTest = createDataset();
    	lineChartTest.showChart(datasetTest, true);
    }
    
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_Dataset;
    
    
    /** renders the visual representation of the (x,y) items */
    private XYItemRenderer m_Renderer;
    
    /** displays a button that can select different colour options */
    private JButton colChangeButton;
    
    /** plots data in the form (x,y) */
    private XYPlot m_Plot; 
	
    /** labels the key of the graph */
    private final String Key = "Data";
    
    /** sets the frame width */
    private final int WIDTH_FRAME = 800;
    
    /** sets the frame height */
    private final int HEIGHT_FRAME = 570;
    
    private String[][] m_Data;
    
    private DataAttribute m_Setting;
    
    /**Defining variables for the dataset */
    private int m_Col;
    private int m_Row;;
    private String m_ChartTitle;
    private String m_XLabel;
    private String m_YLabel;
    private int m_C1;
    private int m_C2;
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_XScale;
    private double m_YScale;
    
    /** sets the chart panel width*/
    private final int CHART_WIDTH = 600;
    
    /** sets the chart panel height*/
    private final int CHART_HEIGHT = 400;
    
    /** magic numbers for the test method */
    private final static Double TestDataW = 1.0;
    
    /** magic numbers for the test method */
    private final static Double TestDataX = 1.0;
    
    /** magic numbers for the test method */
    private final static Double TestDataY = 2.0;
    
    /** magic numbers for the test method */
    private final static Double TestDataZ = 7.0;
}
