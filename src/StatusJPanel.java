/**
 *
 * @author Rhys Owen - A4
 */
/**
 * @file -StatusJPanel.java
 * @author -Rhys Owen
 * @class  -StatusJPanel
 * @date -02/03/2013
 * @see BobViz.java
 * 
 * @brief This class will display the status of the application.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusJPanel extends JPanel {
    
    /**
    * This method updates the status of the program. Other classes will
    * call this method to update the status which will inform the
    * user.
    * @param status - the current status.
    * @return boolen true if successful
    */
    public boolean SetStatus( String status ) {
        m_Status = status;
        statusJLab.setText( "Status: " + GetStatus() );
        return true;
    }
    
   /**
    * @return String m_Status -a variable which stores the current status
    * of the program.
    */
    public String GetStatus() {
        return m_Status;
    }
   
    public StatusJPanel() {
        /* set dimensions of StatusJPanel */
        Dimension size = getPreferredSize();
        size.height = PAN_HEIGHT;
        /* set new layout for FlowLayout */
        setLayout( new FlowLayout( FlowLayout.LEFT ) );
        /* set new size */
        setPreferredSize( size );
        /* set new background colour */
        setBackground( new Color( BACK_COL, BACK_COL, BACK_COL ) );
        setBorder( BorderFactory.createMatteBorder( BORD_TOP, BORD_BOT, 
                BORD_LEFT, BORD_RIGHT, Color.LIGHT_GRAY ) );
        /* add status label to new StatusJPanel */
        add( statusJLab );
    }
    
    /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if( ( test == true ) && ( bv == null ) ) {
            System.err.println( "StatusJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "StatusJPanel::SetBV() Object is valid. Value"
                    + "sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
    /** set panel height for StatusJPanel */
    private final int PAN_HEIGHT = 28;
    
    /** set dimensions for the border */
    private final int BORD_TOP = 1;
    private final int BORD_BOT = 0;
    private final int BORD_LEFT = 0;
    private final int BORD_RIGHT = 0;
    
    private final int BACK_COL = 220;
    
    private BobViz m_BV;
    
    private String m_Status = "Ready";
    private JLabel statusJLab = new JLabel( "Status: " + GetStatus() );
    
}