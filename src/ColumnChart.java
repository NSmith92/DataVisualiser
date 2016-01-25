/**
 *
 * @author Gavin Driscoll -A4, Mark Leyshon - A5, Nathan Smith - A6.
 */
/**
* @file ColumnChart.java
* @class ColumnChart
* @date 01 March 2013
* @updated 01/05/2013
* @see Dataset.java, BobViz.java
*
* @brief A class that displays specific data in a Column Chart visualiser.
*
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

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


public class ColumnChart implements ActionListener {

    /**
    * Create a constructor taking in the following parameters:
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @param boolean first - test which graph opens first
    * Create the column chart dataset and instantiate it. 
    * JFreeChart.
    */
    public ColumnChart( String[][] data, 
    					Dataset dataset, 
    					DataAttribute setting,
    					boolean first) {
    	
        m_Data = data;
        m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();

        CreateDataset( m_Data );
        ShowGraph( m_Dataset );
    }	
    
    /**Getting the setting labels */
    public ColumnChart(DataAttribute setting) {
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
    * a test taking a String argument.
    * @param String - ChartTitle a String argument.
    * @return boolean - The test results.
    */
    public boolean SetChartTitle( String ChartTitle ){
        m_ChartTitle = ChartTitle;
        return true;
    }
    
    /**
	 * Method to make a dataset usable by the BarChart
	 * @return XYSeriesCollection dataset
	 */
    /**
     * creates an XY data set for the two selected columns
     * 
     * @param String[][] data - pass the 2d array through
     * @return XYDataset dataset - returns the new data set with new values
     */
     private XYDataset CreateDataset( String[][] data ) {
         m_Dataset = new XYSeriesCollection();
         XYSeries dataset = new XYSeries( "Series" ); 
         int r = 0;
         for(r = 0; r < m_Row; r++ ) {
         	
         	dataset.add( Double.parseDouble( data[m_C1][r] ), 
                       	Double.parseDouble( data[m_C2][r] ));
         	m_IndexCount++;
         }
         m_Dataset.addSeries( dataset ); 
         return m_Dataset;
     }

	/**
	 * @param XYSeriesCollection dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
    private JFreeChart CreateChart(XYSeriesCollection dataset) {
		
    	final JFreeChart chart = ChartFactory.createXYBarChart(
				m_ChartTitle, //Chart Title
				m_XLabel, //X Axis Label
				false,  //Legend
				m_YLabel, // Y Axis Label
				dataset, 
				PlotOrientation.VERTICAL, 
				false, //tooltips
				true, //Legend 
				false);

		XYPlot plot = chart.getXYPlot();
        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
       

        m_Renderer = plot.getRenderer();
		m_Renderer.setSeriesItemLabelsVisible( 0, Boolean.TRUE );
		
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setTickMarkPaint(Color.black);
            
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        
        
        return chart;    
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == m_ColChangeButton ) {
            ColourMap cM = new ColourMap();
            cM.SetupData( m_IndexCount, m_Renderer );
            cM.setVisible( false );
        }
    }
    /**
     *  visualises the scatter graph using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * 
     * @param dataset - passes the XY data set through
     *  
     * @return true - returns test results
     */
     private boolean ShowGraph(XYSeriesCollection dataset ) {
     	
         JFreeChart graph = CreateChart( dataset );
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

     	return true;
     }
     
    /**
     * main method to carry out JUnit Tests
     */
     public static void main( String[] args ) {
 		
     	DataAttribute test = DataAttribute.GetTestDataAttribute();
     	ColumnChart columnTest = new ColumnChart(test);
     	
     	Random generator = new Random();
     	
     	XYSeriesCollection dataSeries = new XYSeriesCollection();
     	XYSeries dataset = new XYSeries("testSeries"); 
     	for(int i = 0;i < LOOP;i++) {
     		int temp1 = generator.nextInt(LIMIT);
     		int temp2 = generator.nextInt(LIMIT);
         	dataset.add(temp1,temp2);
     	}
     	dataSeries.addSeries(dataset);
     	
         columnTest.ShowGraph(dataSeries);
     }
     
     private static final int LOOP = 10;
     private static final int LIMIT = 100;
     private XYItemRenderer m_Renderer;
     private XYPlot m_Plot;
     private JButton m_ColChangeButton;
     private int m_IndexCount;
     private String m_ChartTitle;
    
     /** an array of data for visualisation */
     private String m_Data[][];
     /** represents a series of vertices to be used as a data set */
     private XYSeriesCollection m_Dataset;
    
     private DataAttribute m_Setting;
     
     /** x and y label axis */
     private String m_XLabel;
     private String m_YLabel;
    
     /* amount of rows */
     private int m_Row;
    
     /** columns */
     private int m_C1;
     private int m_C2;
    
     private double m_XMin;
     private double m_XMax;
     private double m_YMin;
     private double m_YMax;
     private double m_XScale;
     private double m_YScale;
    
     /** sets the frame width */
     private final int FRAME_WIDTH = 800;
     private final int FRAME_HEIGHT = 570;
     private final int CHART_WIDTH = 600;
     private final int CHART_HEIGHT = 400;

}