/**
 *
 * @author Rhys Owen - A4, Nathan Smith - A6
 */
/**
 * @file SelectionVizJPanel.java
 * @author Rhys Owen
 * @class  SelectionVizJPanel
 * @date 02/03/2013
 * @updated 31/04/2013
 * @see Table.java, ColumnChart.java, ScatterGraph.java, PieChart.java
 * 
 * @brief This class passes the data from the GUI to the constructors of the
 * visualisation types.
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectionVizJPanel extends JPanel {
    
    public SelectionVizJPanel() {
        
        /* create new visualisation button */
        m_VisualizeJBut = new JButton( "Generate Visualization!" );
        /* set visualisation font */
        m_VisualizeJBut.setFont( new Font( "Courier", Font.BOLD, FONT_SIZE ) );
        m_VisualizeJBut.setEnabled( false );
        add( m_VisualizeJBut );
        
        /* set listeners */
        GenerateVizJPanelEventHandler iJEventHandler = new 
                GenerateVizJPanelEventHandler();
        m_VisualizeJBut.addActionListener( iJEventHandler );
    }
    
   /**
    * This method will cast the BobViz instance from
    * BobVizDemo.
    * @param bv -a BobViz object
    * @return boolean if true
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if( ( test == true ) && ( bv == null ) ) {
            System.err.println( "GenerateVizJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "GenerateVizJPanel::SetBV() Object is valid. "
                    + "Value sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
   /**
    * This method changes the enabled state of the visualisation button.
    * @param b -a boolean object
    * @return boolean true if successful
    */
    public boolean SetVizJButEnabled( boolean b ) {
        boolean test = true;
        if ( test == true ) {
            System.out.println( "GenerateVizJPanel::SetVizJButEnabled() m_"
                    + "VisualizeJBut enabled status: " + b );
        }
        m_VisualizeJBut.setEnabled( b );
        return true;
    }
    
    /**
     * @return the state of visualise button
     */
    public boolean GetVizJButEnabled() {
        return m_VisualizeJBut.isEnabled();
    }
    /**
     * Sets X & Y axis labels in DataAttribute
     * @return boolean true if successful
     */
    public boolean SetDataAttribute() {
    	m_BV.GetDataAttribute().SetTitle( m_BV.GetSettingJPan().
                GetTitle() );
        /* store axis label x and y in DataAttribute */
      	m_BV.GetDataAttribute().SetAxisLabelX( m_BV.
                GetSettingJPan().GetAxisLabelX());        
      	m_BV.GetDataAttribute().SetAxisLabelY( m_BV.
                GetSettingJPan().GetAxisLabelY());
      	m_BV.GetDataAttribute().SetAxisLabelZ( m_BV.
                GetSettingJPan().GetAxisLabelZ());
      	
      	m_BV.GetDataAttribute().SetXAxisMin( m_BV.
      			GetSettingJPan().GetXAxisMin());
      	m_BV.GetDataAttribute().SetXAxisMax( m_BV.
      			GetSettingJPan().GetXAxisMax());
      	m_BV.GetDataAttribute().SetYAxisMin( m_BV.
      			GetSettingJPan().GetYAxisMin());
      	m_BV.GetDataAttribute().SetYAxisMax( m_BV.
      			GetSettingJPan().GetYAxisMax());
      	
      	m_BV.GetDataAttribute().SetXAxisScale( m_BV.
      			GetSettingJPan().GetXAxisScale());
      	m_BV.GetDataAttribute().SetYAxisScale( m_BV.
      			GetSettingJPan().GetYAxisScale());
      	
      	m_BV.GetDataAttribute().SetChartAuthor( m_BV.
      			GetSettingJPan().GetAuthor());
      	m_BV.GetDataAttribute().SetChartDesciption( m_BV.
      			GetSettingJPan().GetDescription());
      	m_BV.GetDataAttribute().DisplayAll();
      	
      	m_BV.GetDataAttribute().SetUseDefault(
      			m_BV.GetSettingJPan().GetDefaultButton().isSelected());
      	
    	return true;
    }
 
    /* event handler for GenerateVizJPanel */
    private class GenerateVizJPanelEventHandler implements ActionListener {
    	public void actionPerformed( ActionEvent e ) {
    		if (e.getSource() == m_VisualizeJBut ) {
    			if(m_BV.GetVizTypeJPan().GetColumnChartButton().isSelected()) {
    				
    				SetDataAttribute();
                	m_BV.GetViz().SetColChart(
                          m_BV.GetDataset().GetArray(),
                          m_BV.GetDataset(),
                          m_BV.GetDataAttribute(), true);
    				
                	m_BV.GetViz().SetColChart(
    						m_BV.GetSecondDataset().GetArray(),
    						m_BV.GetSecondDataset(),
    						m_BV.GetDataAttribute(), false);
                	
    			} else if(m_BV.GetVizTypeJPan().GetPieChartButton().isSelected()) {
    				
    				SetDataAttribute();
    				m_BV.GetViz().SetVizPieChart( 
    						m_BV.GetDataset().GetArray()
                          , m_BV.GetDataset()
                          , m_BV.GetDataAttribute(),true);
    				
    				m_BV.GetViz().SetVizPieChart(
    						m_BV.GetSecondDataset().GetArray(),
    						m_BV.GetSecondDataset(),
    						m_BV.GetDataAttribute(), false);
    			} else if(m_BV.GetVizTypeJPan().GetScatterChartButton().isSelected()) {
    				
    				SetDataAttribute();
    				m_BV.GetViz().SetScatterPlotGraph(
                            m_BV.GetDataset().GetArray(),
                            m_BV.GetDataset(),
                            m_BV.GetDataAttribute(), true);
    				
    				m_BV.GetViz().SetScatterPlotGraph(
    						m_BV.GetSecondDataset().GetArray(),
    						m_BV.GetSecondDataset(),
    						m_BV.GetDataAttribute(), false);
    			} else if(m_BV.GetVizTypeJPan().GetAreaChartButton().isSelected()) {
    				
    				SetDataAttribute();
    				m_BV.GetViz().SetAreaChart(
                            m_BV.GetDataset().GetArray(),
                            m_BV.GetDataset(),
                            m_BV.GetDataAttribute(), true);
    				
    				m_BV.GetViz().SetAreaChart(
                            m_BV.GetSecondDataset().GetArray(),
                            m_BV.GetSecondDataset(),
                            m_BV.GetDataAttribute(), false);
    			} else if(m_BV.GetVizTypeJPan().GetBubbleChartButton().isSelected()) {
    				
    				SetDataAttribute();
    				m_BV.GetViz().SetBubbleChart(
                            m_BV.GetDataset().GetArray(),
                            m_BV.GetDataset(),
                            m_BV.GetDataAttribute(), true);
    				
    				m_BV.GetViz().SetBubbleChart(
    						m_BV.GetSecondDataset().GetArray(),
    						m_BV.GetSecondDataset(),
    						m_BV.GetDataAttribute(), false);
    			} else if(m_BV.GetVizTypeJPan().GetLineChartButton().isSelected()) {
    				
    				SetDataAttribute();
    				m_BV.GetViz().SetLineChart(
                            m_BV.GetDataset().GetArray(),
                            m_BV.GetDataset(),
                            m_BV.GetDataAttribute(),true);
    				
    				m_BV.GetViz().SetLineChart(
    						m_BV.GetSecondDataset().GetArray(),
    						m_BV.GetSecondDataset(),
    						m_BV.GetDataAttribute(), false);
    			} else if(m_BV.GetVizTypeJPan().GetPolarChartButton().isSelected()) {
    				
    				SetDataAttribute();
    				m_BV.GetViz().SetPolarChart(
                            m_BV.GetDataset().GetArray(),
                            m_BV.GetDataset(),
                            m_BV.GetDataAttribute(),true);
    				
    				m_BV.GetViz().SetPolarChart(
                            m_BV.GetSecondDataset().GetArray(),
                            m_BV.GetSecondDataset(),
                            m_BV.GetDataAttribute(),false);
    			} 
    			
    			if(m_BV.GetVizTypeJPan().GetTableViewButton().isSelected()) {
    				
    				m_BV.GetTableJPan();
    			}  
    			
    		}
    	}
    }
    
    /** create visualisation JButton */
    private JButton m_VisualizeJBut;
    /** font size of visualisation button JLabel */
    private final int FONT_SIZE = 13;
    
    /** create BobViz object */
    private BobViz m_BV;
    
}