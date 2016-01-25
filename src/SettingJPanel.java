/**
 *
 * @author Rhys Owen - A4, Leopold Stiegler- A5, Nathan Smith -A6
 */
/**
 * @file -SettingJPanel.java
 * @author -Rhys Owen & Leopold Stiegler
 * @class  -SettingJPanel
 * @date -01/03/2013
 * @updated 31/04/2013
 * @see BobViz.java
 * 
 * @brief This class provides the settings for each visualisation.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingJPanel extends JPanel {
    
    public SettingJPanel() {
        /* set new size dimensions */
        Dimension size = getPreferredSize();
        size.width = PAN_WIDTH;
        size.height = PAN_HEIGHT;
        setPreferredSize( size );
        
        setBorder( BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder( Color.GRAY  ), SELECT_VIZ_PREF
                 ) );
        setLayout( new FlowLayout( FlowLayout.LEFT ) );
        
        /* create new JPanel */
        JPanel centerJPan = new JPanel();
        centerJPan.setLayout( new GridLayout( GRID_ROW, GRID_COL, GRID_VGAP, 
                GRID_HGAP ) );
        
        /* set default x and y axis */
        String[] xAxis = {""};
        String[] yAxis = {""};
        String[] zAxis = {""};
        
        /* set default x and y axis */
        String[] xAxisSecond = {""};
        String[] yAxisSecond = {""};
        String[] zAxisSecond = {""};
        
        /* create new jlabels */
        JLabel chartTitJLab = new JLabel( "Chart title:" );
        JLabel xAxisJLab = new JLabel( "X axis:" );
        JLabel yAxisJLab = new JLabel( "Y axis:" );
        JLabel zAxisJLab = new JLabel( "Z axis:" );
        
        /* create new jlabels */
        JLabel chartSecondTitJLab = new JLabel( "Chart title:" );
        JLabel xAxisSecondJLab = new JLabel( "X axis:" );
        JLabel yAxisSecondJLab = new JLabel( "Y axis:" );
        JLabel zAxisSecondJLab = new JLabel( "Z axis:" );
        
        /* create new input fields */
        m_ChartTitJTextF = new JTextField( TEXT_FIELD_LEN );
        m_xAxisJCom = new JComboBox( xAxis );
        m_yAxisJCom = new JComboBox( yAxis );
        m_zAxisJCom = new JComboBox( zAxis );
        m_zAxisJCom.setEnabled(false);
        
        /* create new input fields */
        m_ChartSecondTitJTextF = new JTextField( TEXT_FIELD_LEN );
        m_xAxisSecondJCom = new JComboBox( xAxis );
        m_yAxisSecondJCom = new JComboBox( yAxis );
        m_zAxisSecondJCom = new JComboBox( zAxis );
        m_zAxisSecondJCom.setEnabled(false);
        
        
        /* set all input fields disabled by default */
        m_ChartTitJTextF.setEnabled( false );
        m_ChartSecondTitJTextF.setEnabled(false);
        m_xAxisJCom.setEnabled( false );
        m_yAxisJCom.setEnabled( false );
        m_zAxisJCom.setEnabled( false );
        
        m_xAxisSecondJCom.setEnabled( false );
        m_yAxisSecondJCom.setEnabled( false );
        m_zAxisSecondJCom.setEnabled( false );
        
        m_AdvancedButton.setEnabled( false );
        
        /* add all components to centerJPan */
        centerJPan.add( chartTitJLab );
        centerJPan.add( m_ChartTitJTextF );
        
        centerJPan.add( m_ChartSecondTitJTextF );
        
        centerJPan.add( xAxisJLab );
        centerJPan.add( m_xAxisJCom );

        centerJPan.add(m_xAxisSecondJCom);
        
        centerJPan.add( yAxisJLab );
        centerJPan.add( m_yAxisJCom );
        
        centerJPan.add(m_yAxisSecondJCom);
        
        centerJPan.add( zAxisJLab );
        centerJPan.add( m_zAxisJCom );

        centerJPan.add( m_zAxisSecondJCom );
        
        centerJPan.add( m_AdvancedButton );
        
        /* add centerJPan to SettingJPanel */
        add( centerJPan );
        
        /* set listeners */
        SettingJPanelEventHandler iJEventHandler = new 
                SettingJPanelEventHandler();
        m_xAxisJCom.addActionListener( iJEventHandler );
        m_yAxisJCom.addActionListener( iJEventHandler );
        m_zAxisJCom.addActionListener( iJEventHandler );
        
        m_xAxisSecondJCom.addActionListener( iJEventHandler );
        m_yAxisSecondJCom.addActionListener( iJEventHandler );
        m_zAxisSecondJCom.addActionListener( iJEventHandler );
        
        m_AdvancedButton.addActionListener( iJEventHandler ); 
        
    }
    /**
     * Gets X Axis Min Field
     *  
     * @return X Axis Min Field
     */
    public double GetXAxisMin() {
    	return m_SettingPanel.GetXAxisMin();
    }
    /**
     * Gets X Axis Max Field
     *  
     * @return X Axis Max Field
     */
    public double GetXAxisMax() {
    	return m_SettingPanel.GetXAxisMax();
    }
    /**
     * Gets Y Axis Min Field
     *  
     * @return Y Axis Min Field
     */
    public double GetYAxisMin() {
    	return m_SettingPanel.GetYAxisMin();
    }
    /**
     * Gets Y Axis Max Field
     *  
     * @return X Axis Max Field
     */
    public double GetYAxisMax() {
    	return m_SettingPanel.GetYAxisMax();
    }
    /**
     * Gets X Axis Scale Field
     *  
     * @return X Axis Scale Field
     */
    public double GetXAxisScale() {
    	return m_SettingPanel.GetXAxisScale();
    }
    /**
     * Gets Y Axis Scale Field
     *  
     * @return Y Axis Scale Field
     */
    public double GetYAxisScale() {
    	return m_SettingPanel.GetYAxisScale();
    }
    /**
     * Gets Authour Text
     *  
     * @return Author Field
     */
    public String GetAuthor() {
    	return m_SettingPanel.GetAuthor();
    }
    /**
     * Gets Description Text
     *  
     * @return Description Text Field
     */
    public String GetDescription() {
    	return m_SettingPanel.GetDescription();
    }
    /**
     * Gets Default Button
     *  
     * @return Default Button
     */
    public JToggleButton GetDefaultButton() {
    	return m_SettingPanel.GetDefaultButton();
    }
    /**
     * Gets ZAxis Combo Box
     *  
     * @return JComboBox ZAxis value
     */
    public JComboBox GetZAxisComboBox() {
    	return m_zAxisJCom;
    }
    
    
    /**
     * Gets ZAxis Combo Box
     *  
     * @return JComboBox ZAxis value
     */
    public JComboBox GetZAxisComboBox2() {
    	return m_zAxisSecondJCom;
    }
    
    
   /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV(BobViz bv) {
        boolean test = true;
        if((test == true) && (bv == null)) {
            System.err.println( "SettingJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv );
        } else if (test == true) {
            System.out.println( "SettingJPanel::SetBV() Object is valid. Value"
                    + "sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
   /**
    * This method sets the setting panel enabled or disabled. This
    * will prevent the user selecting x and y axis' values
    * before they have imported a file.
    * @param b - a boolean object
    * @return TRUE on success
    */
    public boolean SetSettingsEnabled(boolean b) {
       m_ChartTitJTextF.setEnabled(b);
       m_xAxisJCom.setEnabled(b);
       m_yAxisJCom.setEnabled(b);
       
       m_AdvancedButton.setEnabled(b);
       
       return true;
    }
    
    /**
     * This method sets the setting panel enabled or disabled. This
     * will prevent the user selecting x and y axis' values
     * before they have imported a file.
     * @param b - a boolean object
     * @return TRUE on success
     */
    
    public boolean SetSettingsEnabled2(boolean b) {

        m_ChartSecondTitJTextF.setEnabled(b);
        
        m_xAxisSecondJCom.setEnabled(b);
        m_yAxisSecondJCom.setEnabled(b);
        
        m_AdvancedButton.setEnabled(b);
        
        return true;
     }
    
   /**
    * This method sets the correct visualisation system type.
    * @param headings - an array list of current listed headings from the
    * CSV file.
    * @return TRUE on success
    */
    public boolean SetVizSysType(String[] headings) {
        m_xAxisJCom.removeAllItems();
        m_yAxisJCom.removeAllItems();
        m_zAxisJCom.removeAllItems();
        for(String s: headings){
            m_xAxisJCom.addItem(s);
            m_yAxisJCom.addItem(s);
            m_zAxisJCom.addItem(s);
        }
        return true;
    }
    
    /**
     * This method sets the correct visualisation system type.
     * @param headings - an array list of current listed headings from the
     * CSV file.
     * @return TRUE on success
     */
    public boolean SetVizSysType2(String[] headings) {
        m_xAxisSecondJCom.removeAllItems();
        m_yAxisSecondJCom.removeAllItems();
        m_zAxisSecondJCom.removeAllItems();
        for(String s: headings){
            m_xAxisSecondJCom.addItem(s);
            m_yAxisSecondJCom.addItem(s);
            m_zAxisSecondJCom.addItem(s);
        }
        return true;
    }
    
   /**
    * This method will return the title of the visualisation.
    * @return String - the current title.
    */
    public String GetTitle() {
        m_Title = m_ChartTitJTextF.getText();
        return m_Title;
    }
    
    
    
   /**
    * This method will return the title of the visualisation.
    * @return String - the current title.
    */
    public String GetTitle2() {
        m_Title = m_ChartSecondTitJTextF.getText();
        return m_Title;
    }
    
   /**
    * This method will return the x axis label.
    * @return String - the current x axis label.
    */
    public String GetAxisLabelX() {
        return (String) m_xAxisJCom.getSelectedItem();
    }
    
    /**
     * This method will return the x axis label.
     * @return String - the current x axis label.
     */
    public String GetAxisLabelX2() {
        return (String) m_xAxisSecondJCom.getSelectedItem();
    }
    
   /**
    * This method will return the y axis label.
    * @return String - the current y axis label.
    */
    public String GetAxisLabelY() {
        return (String) m_yAxisJCom.getSelectedItem();
    }
    
    /**
     * This method will return the y axis label.
     * @return String - the current y axis label.
     */
    public String GetAxisLabelY2() {
        return (String) m_yAxisSecondJCom.getSelectedItem();
    }
    
    /**
     * This method will return the z axis label.
     * @return String - the current z axis label.
     */
     public String GetAxisLabelZ() {
         return (String) m_zAxisJCom.getSelectedItem();
     }
     
     /**
      * This method will return the z axis label.
      * @return String - the current z axis label.
      */
     public String GetAxisLabelZ2() {
         return (String) m_zAxisSecondJCom.getSelectedItem();
     }
   /**
    * This method will return the current x index.
    * @return int - the current selected x index.
    */
    public int GetSelectedXIndex() {
        return m_xAxisJCom.getSelectedIndex();
    }
    
     /**
     * This method will return the current x index.
     * @return int - the current selected x index.
     */
    public int GetSelectedXIndex2() {
        return m_xAxisSecondJCom.getSelectedIndex();
    }
    
   /**
    * This method will return the current y index.
    * @return int - the current selected y index.
    */
    public int GetSelectedYIndex() {
        return m_yAxisJCom.getSelectedIndex();
    }
    
   /**
    * This method will return the current y index.
    * @return int - the current selected y index.
    */
    public int GetSelectedYIndex2() {
        return m_yAxisSecondJCom.getSelectedIndex();
    }
    
    /**
     * This method will return the current z index.
     * @return int - the current selected z index.
     */
     public int GetSelectedZIndex() {
         return m_zAxisJCom.getSelectedIndex();
     }
     
     /**
      * This method will return the current z index.
      * @return int - the current selected z index.
      */
     public int GetSelectedZIndex2() {
         return m_zAxisSecondJCom.getSelectedIndex();
     }
    
   
   /**
    * This method will check if all settings are enabled.
    * @return boolean - the result of all setting objects (to
    * check if they're enabled).
    */
    public boolean GetSettingsEnabled() {
       return m_ChartTitJTextF.isEnabled()
               && m_xAxisJCom.isEnabled()
               && m_yAxisJCom.isEnabled()
               && m_AdvancedButton.isEnabled();
   }
    
    
    /**
     * This method will check if all settings are enabled.
     * @return boolean - the result of all setting objects (to
     * check if they're enabled).
     */
    public boolean GetSettingsEnabled2() {
        return m_ChartSecondTitJTextF.isEnabled()
                && m_xAxisSecondJCom.isEnabled()
                && m_yAxisSecondJCom.isEnabled()
                && m_AdvancedButton.isEnabled();
    }
    /**
     * Gets Panel Width
     *  
     * @return Panel Width
     */
    public int GetPanelWidth() {
    	return PAN_WIDTH;
    }
    /**
     * Gets Panel Height
     *  
     * @return Panel Height
     */
    public int GetPanelHeight() {
    	return PAN_HEIGHT;
    }
   /** width of SettingJPanel */
   private final int PAN_WIDTH = 500;
   /** height of SettingJPanel */
   private final int PAN_HEIGHT = 180;
    
   private final String SELECT_VIZ_PREF = "Select Visualization Preferences";
    
   /** the values of the grid */
   private final int GRID_ROW = 5;
   private final int GRID_COL = 3;
   private final int GRID_VGAP = 1;
   private final int GRID_HGAP = 5;
        
   /** the length of the text field */
   private final int TEXT_FIELD_LEN = 14;
   private JComboBox m_xAxisJCom;
   private JComboBox m_yAxisJCom;
   private JComboBox m_zAxisJCom;
   
   private JComboBox m_xAxisSecondJCom;
   private JComboBox m_yAxisSecondJCom;
   private JComboBox m_zAxisSecondJCom;
   
   
   private JButton m_AdvancedButton = new JButton("More");
   private JTextField m_ChartTitJTextF = new JTextField(TEXT_FIELD_LEN);
   private JTextField m_ChartSecondTitJTextF = new JTextField(TEXT_FIELD_LEN);
    
   private String m_Title;
    
   /** create new BobViz object */
   private BobViz m_BV;
   
   private DataAttribute m_ChartSetting = new DataAttribute();
   private MoreSettingJPanel m_SettingPanel = new MoreSettingJPanel(m_ChartSetting);
    
   /* event handler for setting jpanel */
   private class SettingJPanelEventHandler implements ActionListener {
	   Boolean autoGen = true;
        @Override
        public void actionPerformed( ActionEvent e ) {
            String xLabel;
            String yLabel;
            String zLabel;
            
            String xSecondLabel;
            String ySecondLabel;
            String zSecondLabel;
            
        	try {
            	xLabel = (String) m_xAxisJCom.getSelectedItem();
            	yLabel = (String) m_yAxisJCom.getSelectedItem();
            	zLabel = (String) m_zAxisJCom.getSelectedItem();
            	
            } catch (Exception er) {
            	xLabel = "";
            	yLabel = "";
            	zLabel = "";
            	
            }
        	
        	if( e.getSource() == m_xAxisJCom ) {
                m_BV.GetDataAttribute()
                	.SetSelectedXIndex( m_xAxisJCom.getSelectedIndex() );
                
                if(autoGen) {
                	m_ChartTitJTextF
                		.setText(((String) m_xAxisJCom.getSelectedItem())
                				+ " against " + ((String) m_yAxisJCom.getSelectedItem()));
                }
            } else if( e.getSource() == m_yAxisJCom ) {
                m_BV.GetDataAttribute()
                	.SetSelectedYIndex( m_yAxisJCom.getSelectedIndex() );
          
                if(autoGen) {
                	m_ChartTitJTextF
                		.setText(((String) m_xAxisJCom.getSelectedItem())
                				+ " against " + ((String) m_yAxisJCom.getSelectedItem()));
                }
            } else if( e.getSource() == m_zAxisJCom ) {
                m_BV.GetDataAttribute()
            	.SetSelectedZIndex( m_zAxisJCom.getSelectedIndex() );
	            
	            if(autoGen && m_BV.GetVizTypeJPan().GetBubbleChartButton().isSelected()) {
	            	m_ChartTitJTextF
	            		.setText(((String) m_xAxisJCom.getSelectedItem())
	            				+ " against " + ((String) m_yAxisJCom.getSelectedItem()
	            				+ " and "	  +	(String) m_zAxisJCom.getSelectedItem()));
	            }
            } else if( e.getSource() == m_AdvancedButton ) {
            	m_SettingPanel.setVisible(true);
            } else if ( e.getSource() == m_ChartTitJTextF) {
            	autoGen = false;
            }
        	
        	try {
            	xSecondLabel = (String) m_xAxisSecondJCom.getSelectedItem();
            	ySecondLabel = (String) m_yAxisSecondJCom.getSelectedItem();
            	zSecondLabel = (String) m_zAxisSecondJCom.getSelectedItem();
            	
            } catch (Exception er) {
            	
            	xSecondLabel = "";
            	ySecondLabel = "";
            	zSecondLabel = "";
            }
        
        	if( e.getSource() == m_xAxisSecondJCom ) {
        		m_BV.GetDataAttribute()
        		.SetSelectedXIndex( m_xAxisSecondJCom.getSelectedIndex() );
       
        		if(autoGen) {
        			m_ChartSecondTitJTextF
        			.setText(((String) m_xAxisSecondJCom.getSelectedItem())
       				+ " against " + ((String) m_yAxisSecondJCom.getSelectedItem()));
        		}
        	} else if( e.getSource() == m_yAxisSecondJCom ) {
        	m_BV.GetDataAttribute()
        	.SetSelectedYIndex( m_yAxisSecondJCom.getSelectedIndex() );
 
        	if(autoGen) {
        		m_ChartSecondTitJTextF
        		.setText(((String) m_xAxisSecondJCom.getSelectedItem())
       			+ " against " + ((String) m_yAxisSecondJCom.getSelectedItem()));
        	}
        } else if( e.getSource() == m_zAxisSecondJCom ) {
        	m_BV.GetDataAttribute()
        	.SetSelectedZIndex( m_zAxisSecondJCom.getSelectedIndex() );
       
        	if(autoGen && m_BV.GetVizTypeJPan().GetBubbleChartButton().isSelected()) {
        		m_ChartSecondTitJTextF
       			.setText(((String) m_xAxisSecondJCom.getSelectedItem())
       			+ " against " + ((String) m_yAxisSecondJCom.getSelectedItem()
       			+ " and "	  +	(String) m_zAxisSecondJCom.getSelectedItem()));
        	}
        } else if( e.getSource() == m_AdvancedButton ) {
        	m_SettingPanel.setVisible(true);
        } else if ( e.getSource() == m_ChartSecondTitJTextF) {
        	autoGen = false;
        }
     }
  }
  
  /**Main method for testing*/
   public static void main(String[] args) {
   		JFrame frame = new JFrame();
	    SettingJPanel settingJPan = new SettingJPanel();
	    JPanel panel = new JPanel();
	    panel.add(settingJPan);
	    frame.setSize(new Dimension(settingJPan.GetPanelWidth(),settingJPan.GetPanelHeight()));
	    frame.add(panel);
	    frame.setVisible(true);
   }
}