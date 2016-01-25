/**
 *
 * @author Rhys Owen - A4
 */
/**
 * @file -ImportJPanel.java
 * @author -Rhys Owen
 * @class  -ImportJPanel
 * @date -01/03/2013
 * @see Dataset.java
 * 
 * @brief This class provides a import (CSV file) procedure.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImportJPanel extends JPanel {
    
    public ImportJPanel() {
        /* create dimension for ImportJPanel */
        Dimension size = getPreferredSize();
        size.width = WIDTH;
        size.height = HEIGHT;
        setPreferredSize( size );
        
        setBorder( BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder( Color.GRAY ), IMPORT_CSV
                + "..." ) );
        
        /* create new import text field for ImportJPanel */
        m_ImportTField = new JTextField( TEXT_FIELD_LEN );
        /* create new browse button for ImportJPanel */
        m_BrowseJBut = new JButton( BROWSE );
        
        m_ImportTField.setEnabled( false );
        
        /* add import field and browse button to ImportJPanel */
        add( m_ImportTField );
        add( m_BrowseJBut );
        
        /* set listeners */
        ImportJPanelEventHandler iJEventHandler = new 
                ImportJPanelEventHandler();
        m_BrowseJBut.addActionListener( iJEventHandler );
    }
    
   /**
    * The method for importing a CSV file. This method will
    * validate and pass the CSV file from the GUI to the
    * dataset class.
    * @return TRUE if successful
    */
    public boolean SetImportCSV() {
        boolean test = true;
        if( test == true ) {
            updateStatus( WAIT_FOR_FILE );
            /* set file location */
            m_BV.GetDataset().SetFileLocationGUI();
            /* set file location in Dataset */
            m_ImportTField.setText( m_BV.GetDataset().GetFileLocation() );
            /* check for correct file extension */
            updateStatus( CHECK_FILE_TYPE );
            /* check if CSV file */
            System.out.println(m_BV.GetDataset().CheckFileType());
            if( m_BV.GetDataset().CheckFileType() ) {
                updateStatus( ATEMPT_TO_READ );
                /* if a CSV file, attempt to read */
                if( m_BV.GetDataset().ReadFile() ) {
                    if(m_BV.GetDataset().GetErrors() >0){
                        Error err = new Error();
                        if(err.ReadError( m_BV.GetDataset().GetErrors()) == 
                                true ){
                            m_BV.GetDataset().ReadFileBasic( m_BV.GetDataset().
                                    GetFileLocation() );
                            
                        }
                            
                    }
                    updateStatus( FILE_READ_OK );
                    m_BV.GetSettingJPan().SetVizSysType( m_BV.GetDataset().
                            GetColHeaders() );
                    /* enable setting panel components */
                    m_BV.GetSettingJPan().SetSettingsEnabled( true );
                    m_BV.GetGenerateVizJPan().SetVizJButEnabled( true );
                    m_BV.GetImportSecondJPan().SetBrowseJButEnabled(true);
                /* if file cannot be read */
                } else {
                    
                }
            } else {
                /* inform user of wrong file extension */
                JOptionPane.showMessageDialog( null, INVALID_FILE );
                updateStatus( INVALID_FILE );
                m_BV.GetGenerateVizJPan().SetVizJButEnabled(false);
                m_BV.GetSettingJPan().SetSettingsEnabled(false);
            }
        }
        return true;
    }
    
   private void SetBrowseJButEnabled(boolean b) {
	// TODO Auto-generated method stub
	
}

/**
    * This method will update the status. This method updates the
    * StatusJPanel.
    * @return TRUE if success
    */
    private boolean updateStatus( String status ) {
        m_BV.GetStatusJPan().SetStatus( status );
        return true;
    }
    
    /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if((test == true) && (bv == null)) {
            System.err.println("ImportJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv);
        } else if (test == true) {
            System.out.println("ImportJPanel::SetBV() Object is valid. Value"
                    + "sent: " + bv);
        }
        m_BV = bv;
        return true;
    }
    
    /* height of ImportJPanel */
    private final int WIDTH = 500;
    /* width of ImportJPanel */
    private final int HEIGHT = 60;
    
    private final String WAIT_FOR_FILE = "Waiting for file...";
    private final String CHECK_FILE_TYPE = "Checking file type...";
    private final String FAILED_TO_READ = "Failed to read file";
    private final String ATEMPT_TO_READ = "Attempting to read file...";
    private final String FILE_READ_OK = "File read successfully";
    private final String INVALID_FILE = "Invalid file extension.";
    
    private final String BROWSE = "Browse...";
    private final String IMPORT_CSV = "Import CSV File";
   
    /* size of import text field */
    private final int TEXT_FIELD_LEN = 30;
    
    /* create new browse button object */
    private JButton m_BrowseJBut;
    /* create new browse text field object */
    private JTextField m_ImportTField;
    
   
    
    /* create new BobViz object */
    private BobViz m_BV;
    
    private class ImportJPanelEventHandler implements ActionListener {
    
        @Override
        public void actionPerformed( ActionEvent e ) {
            if( e.getSource() == m_BrowseJBut ) {
            	try {
            		SetImportCSV();
            		m_BV.GetTableJPan();
            	} catch (Exception err) {
            		updateStatus(FAILED_TO_READ);
            		m_BV.GetSettingJPan().SetSettingsEnabled(false);
            	}
            }
        }
    }
    
}