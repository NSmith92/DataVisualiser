
/**
 *
 * @author PDM1
 */
/**
 * @author PDM1
 * @class InformationJPanel
 * @date 14/04/2013
 * @brief A class designed to deal with the Information panel
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class InformationJPanel extends JPanel {
	
	private final int WIDTH = 15;
	private final int COLUMN = 5;
	private final int ROW = 40;
	private final int HGAP = 2;
	private final int VGAP = 2;
	
	private JLabel m_AuthorLabel = new JLabel("Author:");
	private JTextField m_AuthorField = new JTextField(WIDTH);
	private JLabel m_DescriptionLabel = new JLabel("Description:");
	private JTextArea m_DescriptionField = new JTextArea(COLUMN,ROW);
	private DataAttribute m_DataAttribute;
	
	private String m_TimeStamp;
	
	/**
	 * @param JLabel authorLabel
	 * @return Boolean true - if success
	 */
	public Boolean SetAuthorLabel(JLabel label) { 
		m_AuthorLabel = label;
		return true; 
	}
	/**
	 * @param String authorField
	 * @return Boolean true - if success
	 */
	public Boolean SetAuthorField(String field) { 
		m_AuthorField.setText(field);
	  	return true; 
	}
	/**
	 * @param JLabel descriptionLabel
	 * @return Boolean true - if success
	 */
	public Boolean SetDescriptionLabel(JLabel label) { 
		m_DescriptionLabel = label;
		return true; 
	}
	
	
	/**
	 * @param String descriptionArea
	 * @return Boolean true - if success
	 */
	public Boolean SetDescriptionArea(String area) { 
		m_DescriptionField.setText(area);
	  	return true; 
	}
	/**
	 * @param DataAttribute dataAttribute
	 * @return Boolean true - if success
	 */
	public Boolean SetDataAttribute(DataAttribute data) {
		m_DataAttribute = data;
		return true;
	}
	/**
	 * @return JLabel authorLabel
	 */
	public JLabel GetAuthorLabel() {
		return m_AuthorLabel;
	}
	/**
	 * @return JTextField authorField
	 */
	public JTextField GetAuthorField() {
		return m_AuthorField;
	}
	/**
	 * @return JLabel descriptionLabel
	 */
	public JLabel GetDescriptionLabel() {
		return m_DescriptionLabel;
	}
	/**
	 * @return JTextArea descriptionArea
	 */
	public JTextArea GetDescriptionArea() {
		return m_DescriptionField;
	}
	/**
	 * @return DataAttribute dataAttribute
	 */
	public DataAttribute GetDataAttribute() {
		return m_DataAttribute;
	}
	
	public InformationJPanel(DataAttribute setting) {
		
        setLayout(new BorderLayout(HGAP,VGAP));
        TimeStamp ts = new TimeStamp();
        SetDataAttribute(setting);
		SetAuthorField(GetDataAttribute().GetChartAuthor());
		SetDescriptionArea(GetDataAttribute().GetChartDescription());
		GetDescriptionArea().append("\n" + ts.GetTimeStamp());
		JScrollPane scrollPane = new JScrollPane(GetDescriptionArea());
		GetAuthorField().setEditable(false);
		GetDescriptionArea().setEditable(false);
		GetDescriptionArea().setLineWrap(true);
		GetDescriptionArea().setWrapStyleWord(true);
		
		JPanel top = new JPanel();
		JPanel bot = new JPanel();
		
		top.add(GetAuthorLabel());
		top.add(GetAuthorField());
		bot.add(GetDescriptionLabel());
		bot.add(scrollPane);
		
		add(top, BorderLayout.CENTER);
		add(bot, BorderLayout.LINE_END);
	}
	
	/**Main method to test the class */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Information Window");
		DataAttribute data = new DataAttribute();
		TimeStamp timeStamp = new TimeStamp();
		data.SetChartAuthor("Author");
		data.SetChartDesciption(
				"This is a description of something describable\n");
		frame.add(new InformationJPanel(data));
		frame.pack();
		frame.setVisible(true);
	}
}
