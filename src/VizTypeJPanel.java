/**
 *
 * @author Rhys Owen - A4, Leopold Stiegler - A5
 */
/**
 * @file VizTypeJPanel.java
 * @author Rhys Owen & Leopold Stiegler
 * @class  VizTypeJPanel
 * @date 01/03/2013
 * @see Dataset.java
 * 
 * @brief This class provides the user with a list of different
 * visualisation types to choose from.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VizTypeJPanel extends JPanel {
    
    public VizTypeJPanel() {
        /* set new dimension for VizTypeJPanel */
        Dimension size = getPreferredSize();
        
        /* set height and size for VizTypeJPanel */
        size.width = PAN_WIDTH;
        size.height = PAN_HEIGHT;
        setPreferredSize( size );
        
        setBorder( BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder( Color.GRAY ), "Select visualisation") );
        
        /* set new layout */
        setLayout( new FlowLayout( FlowLayout.CENTER ) );
        
        /* create new JPanel */
        JPanel centerJPan = new JPanel();
        /* set new layout for centerJPan */
        centerJPan.setLayout( new GridLayout( GRID_ROW, GRID_COL, GRID_VGAP, 
                GRID_HGAP ) );
       
        /**
         * Adding them to a button group will allow only a single button
         * to be toggled at any time
         */
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(m_ColumnChartButton);
        buttonGroup.add(m_PieChartButton);
        buttonGroup.add(m_LineChartButton);
        buttonGroup.add(m_BubbleChartButton);
        buttonGroup.add(m_ScatterPlotButton);
        buttonGroup.add(m_AreaChartButton);
        buttonGroup.add(m_PolarChartButton);
        
        centerJPan.add(m_ColumnChartButton);
        m_ColumnChartButton.setSelected(true);
        centerJPan.add(m_PieChartButton);
        centerJPan.add(m_LineChartButton);
        centerJPan.add(m_BubbleChartButton);
        centerJPan.add(m_ScatterPlotButton);
        centerJPan.add(m_AreaChartButton);
        centerJPan.add(m_PolarChartButton);
        centerJPan.add(m_TableViewButton);
        
         /* add centerJPan to VizTypeJPanel */
        add( centerJPan );
        
        /* set listeners */
        VizTypeJPanelEventHandler vTEventHandler = new 
                VizTypeJPanelEventHandler();
        m_BubbleChartButton.addItemListener(vTEventHandler);
    }
    
    public VizTypeJPanel(boolean b) {}
    
    /**
	 * Column Chart Button
	 * @return Column Chart m_ColumnChartButton
	 */
    public JToggleButton GetColumnChartButton() {
    	return m_ColumnChartButton;
    }
    /**
	 * Pie Chart Button
	 * @return Pie Chart m_PieChartButton
	 */
    public JToggleButton GetPieChartButton() {
    	return m_PieChartButton;
    }
    /**
	 * Line Chart Button
	 * @return Line Chart m_LineChartButton
	 */
    public JToggleButton GetLineChartButton() {
    	return m_LineChartButton;
    }
    /**
	 * Bubble Chart Button
	 * @return Bubble Chart m_BubbleChartButton
	 */
    public JToggleButton GetBubbleChartButton() {
    	return m_BubbleChartButton;
    }
    /**
	 * Scatter Plot Button
	 * @return Scatter Plot Chart m_ScatterPlotButton
	 */
    public JToggleButton GetScatterChartButton() {
    	return m_ScatterPlotButton;
    }
    /**
	 * Area Chart Button
	 * @return Are Chart m_AreaChartButton
	 */
    public JToggleButton GetAreaChartButton() {
    	return m_AreaChartButton;
    }
    /**
	 * Polar Chart Button
	 * @return Polar Chart m_PolarChartButton
	 */
    public JToggleButton GetPolarChartButton() {
    	return m_PolarChartButton;
    }
    /**
	 * Table View Button
	 * @return Table View m_TableViewButton
	 */
    public JToggleButton GetTableViewButton() {
    	return m_TableViewButton;
    }
    
    /* event handler for VizTypeJPanel */
    private class VizTypeJPanelEventHandler implements ItemListener {
    	public void itemStateChanged(ItemEvent e) {
 		   if(e.getSource() == m_BubbleChartButton) {
 			   if(m_BubbleChartButton.isSelected()) {
 				  m_BV.GetSettingJPan().GetZAxisComboBox().setEnabled(true);
 			   } else {
 				  m_BV.GetSettingJPan().GetZAxisComboBox().setEnabled(false);
 			   }
 		   }
 	   	}
    }
    
   /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if( ( test == true ) && ( bv == null ) ) {
            System.err.println( "VizTypeJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "VizTypeJPanel::SetBV() Object is valid. Value"
                    + "sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    /**
	 * Gets Panel Width
	 * @return PAN_WIDTH
	 */
    
    public int GetPanelWidth() {
    	return PAN_WIDTH;
    }
    /**
	 * Gets Panel Height
	 * @return PAN_Height
	 */
    public int GetPanelHeight() {
    	return PAN_HEIGHT;
    }
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
        VizTypeJPanel vTypeJPan = new VizTypeJPanel();
        JPanel panel = new JPanel();
        panel.add(vTypeJPan);
        frame.setSize(new Dimension(vTypeJPan.GetPanelHeight(),vTypeJPan.GetPanelWidth()));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void SetTableViewButton(JToggleButton m_TableViewButton) {
		this.m_TableViewButton = m_TableViewButton;
	}

	/** width of VizTypeJPanel */
    private final int PAN_WIDTH = 500;
    /** height of VizTypeJPanel */
    private final int PAN_HEIGHT = 150;
    
    /** grid row size for GridLayout */
    private final int GRID_ROW = 4;
    /** grid col size for GridLayout */
    private final int GRID_COL = 2;
    /** grid vgap size for GridLayout */
    private final int GRID_VGAP = 50;
    /** grid hgap size for GridLayout */
    private final int GRID_HGAP = 0;
    
    private JToggleButton m_ColumnChartButton = new JToggleButton("Column Chart");
    private JToggleButton m_PieChartButton = new JToggleButton("Pie Chart");
    private JToggleButton m_LineChartButton = new JToggleButton("Line Chart");
    private JToggleButton m_BubbleChartButton = new JToggleButton("Bubble Chart");
    private JToggleButton m_ScatterPlotButton = new JToggleButton("Scatter Plot");
    private JToggleButton m_AreaChartButton = new JToggleButton("Area Chart");
    private JToggleButton m_PolarChartButton = new JToggleButton("Polar Chart");
    private JToggleButton m_TableViewButton = new JToggleButton("Table View");
   
    /** the BobViz instance **/
    private BobViz m_BV;
}