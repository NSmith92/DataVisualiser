/**
 *
 * @author PDM1
 */
/**
 * @author PDM1
 * @class MoreSettingJPanel
 * @date 14/04/2013
 * @brief A class designed to deal with the 'More Settings' panel
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;


public class MoreSettingJPanel extends JFrame {
	
	public final int FRAME_WIDTH = 500;
	public final int FRAME_HEIGHT = 200;
	/** the length of the text field */
	private final int TEXT_FIELD_LEN = 8;
	private final int NUMBER_FIELD_LEN = 4;
	private final int GAP_1 = 18;
	private final int GAP_2 = 27;
	private final int SIZE = 80;
	
	
	private DataAttribute m_ChartSetting;
	
	private JPanel m_MainPanel;
	
	private JLabel m_XAxisMinLabel;
    private JLabel m_XAxisMaxLabel;
    private JLabel m_YAxisMinLabel;
    private JLabel m_YAxisMaxLabel;
    private JLabel m_XAxisScaleLabel;
    private JLabel m_YAxisScaleLabel;
    private JLabel m_AuthorLabel;
    private JLabel m_DesciptionLabel;
    
    private JTextField m_XAxisMinField;
    private JTextField m_XAxisMaxField;
    private JTextField m_YAxisMinField;
    private JTextField m_YAxisMaxField;
    private JTextField m_XAxisScaleField;
    private JTextField m_YAxisScaleField;
    private JTextField m_AuthorField;
    private JTextArea m_DescriptionField;
    
    private JButton m_ApplyButton;
    private JToggleButton m_DefaultButton;
    
    /**
     * Gets X Axis Min Field
     *  
     * @return X Axis Min Field
     */
    public JTextField GetXAxisMinField() {
    	return m_XAxisMinField;
    }
    /**
     * Gets X Axis Max Field
     *  
     * @return X Axis Max Field
     */
    public JTextField GetXAxisMaxField() {
    	return m_XAxisMaxField;
    }
    /**
     * Gets Y Axis Min Field
     *  
     * @return Y Axis Min Field
     */
    public JTextField GetYAxisMinField() {
    	return m_YAxisMinField;
    }
    /**
     * Gets Y Axis Max Field
     *  
     * @return X Axis Max Field
     */
    public JTextField GetYAxisMaxField() {
    	return m_YAxisMaxField;
    }
    /**
     * Gets X Axis Scale Field
     *  
     * @return X Axis Scale Field
     */
    public JTextField GetXAxisScaleField() {
    	return m_XAxisScaleField;
    }
    /**
     * Gets Y Axis Scale Field
     *  
     * @return Y Axis Scale Field
     */
    public JTextField GetYAxisScaleField() {
    	return m_YAxisScaleField;
    }
    /**
     * Gets Default Button
     *  
     * @return Default Button
     */
    public JToggleButton GetDefaultButton() {
    	return m_DefaultButton;
    }
    
     /**
     * Gets X Axis Min value 
     *  
     * @return X Axis Min value
     */
    public double GetXAxisMin() {
    	try {
    		return Double.parseDouble(m_XAxisMinField.getText());
    	} catch (Exception e) {
    		error();
    	}
    	return 0;
    }
    /**
     * Gets X Axis Max value
     *  
     * @return X Axis Max value
     */
    public double GetXAxisMax() {
    	try {
    		return Double.parseDouble(m_XAxisMaxField.getText());
    	} catch (Exception e) {
    		error();
    	}
    	return 0;
    }
    /**
     * Gets Y Axis Min value
     *  
     * @return Y Axis Min value
     */
    public double GetYAxisMin() {
    	try {
    		return Double.parseDouble(m_YAxisMinField.getText());
    	} catch (Exception e) {
    		error();
    	}
    	return 0;
    }
    /**
     * Gets Y Axis Max value
     *  
     * @return Y Axis Max value
     */
    public double GetYAxisMax() {
    	try {
    		return Double.parseDouble(m_YAxisMaxField.getText());
    	} catch (Exception e) {
    		error();
    	}
    	return 0;
    }
    /**
     * Gets X Axis Scale value
     *  
     * @return X Axis Scale value
     */
    public double GetXAxisScale() {
    	try {
    		return Double.parseDouble(m_XAxisScaleField.getText());
    	} catch (Exception e) {
    		error();
    	}
    	return 0;
    }
    /**
     * Gets Y Axis Scale value
     *  
     * @return Y Axis Scale value
     */
    public double GetYAxisScale() {

    	try {
    		return Double.parseDouble(m_YAxisScaleField.getText());
    	} catch (Exception e) {
    		error();
    	}
    	return 0;
    }
    /**
     * Gets Authour Text
     *  
     * @return Author Field
     */
    public String GetAuthor() {
    	return m_AuthorField.getText();
    }
    /**
     * Gets Description Text
     *  
     * @return Description Text Field
     */
    public String GetDescription() {
    	return m_DescriptionField.getText();
    }
    
    private void error() {
    	
    	JOptionPane.showMessageDialog(new JFrame("Invalid entry error"),
    		    "Invalid Entry!",
    		    "Error", JOptionPane.WARNING_MESSAGE);
    }
    public MoreSettingJPanel(DataAttribute chartSetting) {
    	super("Additional settings");
    	m_ChartSetting = chartSetting;
    	
    	setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
		
		m_MainPanel = new JPanel();
		
		m_XAxisMinLabel = new JLabel();
		m_XAxisMaxLabel = new JLabel();
		m_XAxisMinField = new JTextField(NUMBER_FIELD_LEN);
		m_XAxisMaxField = new JTextField(NUMBER_FIELD_LEN);
		m_XAxisMinField.setText(String.valueOf(m_ChartSetting.GetXAxisMin()));
		m_XAxisMaxField.setText(String.valueOf(m_ChartSetting.GetXAxisMax()));
		
		m_YAxisMinLabel = new JLabel();
		m_YAxisMaxLabel = new JLabel();
		m_YAxisMinField = new JTextField(NUMBER_FIELD_LEN);
		m_YAxisMaxField = new JTextField(NUMBER_FIELD_LEN);
		m_YAxisMinField.setText(String.valueOf(m_ChartSetting.GetYAxisMin()));
		m_YAxisMaxField.setText(String.valueOf(m_ChartSetting.GetYAxisMax()));
		
		m_XAxisScaleLabel = new JLabel();
		m_YAxisScaleLabel = new JLabel();
		m_XAxisScaleField = new JTextField(NUMBER_FIELD_LEN);
		m_YAxisScaleField = new JTextField(NUMBER_FIELD_LEN);
		m_XAxisScaleField.setText(
				String.valueOf(m_ChartSetting.GetXAxisScale()));
		m_YAxisScaleField.setText(
				String.valueOf(m_ChartSetting.GetYAxisScale()));
		
		m_AuthorLabel = new JLabel();
		m_AuthorField = new JTextField(TEXT_FIELD_LEN);
		m_DesciptionLabel = new JLabel();
		
		m_DescriptionField = new JTextArea();
		m_DescriptionField.setLineWrap(true);
		m_DescriptionField.setWrapStyleWord(true);
		
		m_ApplyButton = new JButton();
		m_DefaultButton = new JToggleButton();
		m_DefaultButton.setSelected(true);

        m_XAxisMinLabel.setText("X min: ");
        m_XAxisMaxLabel.setText("X max:");
        m_YAxisMinLabel.setText("Y min: ");
        m_YAxisMaxLabel.setText("Y max:");

        m_XAxisScaleLabel.setText("X scale:");
        m_YAxisScaleLabel.setText("Y scale:");
        m_AuthorLabel.setText("Author:");
        m_DesciptionLabel.setText("Description:");

        m_ApplyButton.setText("Apply");
        m_DefaultButton.setText("Default");
        
        SettingJPanelEventHandler handler = new SettingJPanelEventHandler();
        m_ApplyButton.addActionListener(handler);
        m_DefaultButton.addActionListener(handler);
        

        javax.swing.GroupLayout layout = new GroupLayout(m_MainPanel);
        m_MainPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                		GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                        		GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m_XAxisScaleLabel)
                                .addPreferredGap(LayoutStyle.
                                		ComponentPlacement.UNRELATED)
                                .addComponent(m_XAxisScaleField, 
                                		GroupLayout.PREFERRED_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.PREFERRED_SIZE)
                                .addGap(GAP_1, GAP_1, GAP_1)
                                .addComponent(m_AuthorLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m_YAxisScaleLabel)
                                .addPreferredGap(LayoutStyle
                                		.ComponentPlacement.UNRELATED)
                                .addComponent(m_YAxisScaleField, 
                                		GroupLayout.PREFERRED_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle
                        		.ComponentPlacement.RELATED,
                        		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m_AuthorField, 
                        		GroupLayout.PREFERRED_SIZE, 
                        		GroupLayout.DEFAULT_SIZE, 
                        		GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                        		GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m_XAxisMinLabel)
                                .addPreferredGap(LayoutStyle
                                		.ComponentPlacement.UNRELATED)
                                .addComponent(m_XAxisMinField,
                                		GroupLayout.PREFERRED_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.PREFERRED_SIZE)
                                .addGap(GAP_1, GAP_1, GAP_1)
                                .addComponent(m_YAxisMinLabel)
                                .addPreferredGap(LayoutStyle
                                		.ComponentPlacement.UNRELATED)
                                .addComponent(m_YAxisMinField, 
                                		GroupLayout.PREFERRED_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(m_XAxisMaxLabel)
                                .addPreferredGap(LayoutStyle
                                		.ComponentPlacement.UNRELATED)
                                .addComponent(m_XAxisMaxField, 
                                		GroupLayout.PREFERRED_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.PREFERRED_SIZE)
                                .addGap(GAP_1, GAP_1, GAP_1)
                                .addComponent(m_YAxisMaxLabel)
                                .addPreferredGap(LayoutStyle
                                		.ComponentPlacement.UNRELATED)
                                .addComponent(m_YAxisMaxField, 
                                		GroupLayout.PREFERRED_SIZE,
                                		GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(m_DesciptionLabel)
                        .addPreferredGap(LayoutStyle
                        		.ComponentPlacement.UNRELATED)
                        .addComponent(m_DescriptionField))
                    .addGroup(GroupLayout.Alignment.TRAILING, 
                    		  layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle
                        		.ComponentPlacement.RELATED)
                        .addComponent(m_DefaultButton)
                        .addComponent(m_ApplyButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                		GroupLayout.Alignment.BASELINE)
                    .addComponent(m_XAxisMinLabel)
                    .addComponent(m_XAxisMinField, 
                    		GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, 
                    		GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_YAxisMinLabel)
                    .addComponent(m_YAxisMinField, 
                    		GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, 
                    		GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(
                		GroupLayout.Alignment.BASELINE)
                    .addComponent(m_XAxisMaxLabel)
                    .addComponent(m_XAxisMaxField, 
                    		GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, 
                    		GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_YAxisMaxLabel)
                    .addComponent(m_YAxisMaxField, 
                    		GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE,
                    		GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(
                		GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(GAP_1, GAP_1, GAP_1)
                        .addGroup(layout.createParallelGroup(
                        		GroupLayout.Alignment.BASELINE)
                            .addComponent(m_XAxisScaleLabel)
                            .addComponent(m_XAxisScaleField, 
                            		GroupLayout.PREFERRED_SIZE, 
                            		GroupLayout.DEFAULT_SIZE, 
                            		GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle
                        		.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(
                        		GroupLayout.Alignment.BASELINE)
                            .addComponent(m_YAxisScaleLabel)
                            .addComponent(m_YAxisScaleField, 
                            		GroupLayout.PREFERRED_SIZE, 
                            		GroupLayout.DEFAULT_SIZE, 
                            		GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(GAP_2, GAP_2, GAP_2)
                        .addGroup(layout.createParallelGroup(
                        		GroupLayout.Alignment.BASELINE)
                            .addComponent(m_AuthorLabel)
                            .addComponent(m_AuthorField, 
                            		GroupLayout.PREFERRED_SIZE, 
                            		GroupLayout.DEFAULT_SIZE, 
                            		GroupLayout.PREFERRED_SIZE))))
                .addGap(GAP_1, GAP_1, GAP_1)
                .addGroup(layout.createParallelGroup(
                		GroupLayout.Alignment.BASELINE)
                    .addComponent(m_DesciptionLabel)
                    .addComponent(m_DescriptionField, 
                    		GroupLayout.PREFERRED_SIZE, SIZE,
                    		GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(
                		GroupLayout.Alignment.BASELINE)
                	.addComponent(m_ApplyButton)
                    .addComponent(m_DefaultButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
		
		add(m_MainPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
    }
	
	private class SettingJPanelEventHandler implements ActionListener {
		
		
        public void actionPerformed( ActionEvent e ) {
            if( e.getSource() == m_ApplyButton ) {
            	try {
            		
            		String tempAuthor = (String) m_AuthorField.getText();
            		String tempDesc = (String) m_DescriptionField.getText();
            		double tempXMin = Double.parseDouble(m_XAxisMinField.getText());
            		double tempXMax = Double.parseDouble(m_XAxisMaxField.getText());
            		double tempYMin = Double.parseDouble(m_YAxisMinField.getText());
            		double tempYMax = Double.parseDouble(m_YAxisMaxField.getText());
            		double tempXScale = Double.parseDouble(m_XAxisScaleField.getText());
            		double tempYScale = Double.parseDouble(m_YAxisScaleField.getText());
            		
            		
            		m_ChartSetting.SetXAxisMin(tempXMin);
                    m_ChartSetting.SetXAxisMax(tempXMax);
                    m_ChartSetting.SetYAxisMin(tempYMin);
                    m_ChartSetting.SetYAxisMax(tempYMax);
                    m_ChartSetting.SetXAxisScale(tempXScale);
                    m_ChartSetting.SetYAxisScale(tempYScale);
                    m_ChartSetting.SetChartAuthor(tempAuthor);
                    m_ChartSetting.SetChartDesciption(tempDesc);
                    m_ChartSetting.SetUseDefault(false);
                    
                    setVisible(false);
                    dispose();
            	} catch (Exception err)  {
            		error();
            		err.printStackTrace();
            	}
            	
            } else if(e.getSource() == m_DefaultButton) {
            	
            	String tempAuthor = (String) m_AuthorField.getText();
        		String tempDesc = (String) m_DescriptionField.getText();
            	m_ChartSetting.SetChartAuthor(tempAuthor);
                m_ChartSetting.SetChartDesciption(tempDesc);
            	m_ChartSetting.SetUseDefault(true);
            }
        }
    }
	
	public static void main(String[] args) {
		new MoreSettingJPanel(null);
		
	}
}
