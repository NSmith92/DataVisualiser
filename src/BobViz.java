/**
 *
 * @author Rhys Owen - A4, Nathan Smith -A6
 */
/**
 * @file -BobViz.java
 * @author -Rhys Owen
 * @class  -BobViz
 * @date -01/03/2013
 * @updated - 01/05/2013
 * 
 * @brief This class provides the user with a simple GUI system. It initiates
 * all instances used throughout the application.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BobViz extends JFrame {
    
    public BobViz() {
        /* create new JPanel which will hold all GUI components */
        JPanel top = new JPanel();
        /* create a new JPanel which will hold the current status of program */
        m_StatusJPan = new StatusJPanel();
        
        /* set frame title for BobViz */
        setTitle( FRAME_TITLE );
        /* set frame height and width for BobViz */
		setSize( FRAME_WIDTH, FRAME_HEIGHT );
		setResizable( false );
	    setLayout( new BorderLayout() );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        /* create new menu bar for BobViz */
        m_MenuBar = new JMenuBar();

        /* create new menu */
        m_FileMenu = new JMenu( FILE_MENU );

        /* create new menu items */
        m_ImportCSVItem = new JMenuItem( FILE_IMPORTCSV );
        m_ExitItem = new JMenuItem( FILE_EXIT );
        m_HelpConItem = new JMenuItem( FILE_HELPCON );
        
        /* add menu items to file menu */
        m_FileMenu.add( m_ImportCSVItem );
        m_FileMenu.add( m_ExitItem );

        /* make new help menu */
        m_HelpMenu = new JMenu( FILE_HELP );
        
        /* add menu bars to menu */
        m_MenuBar.add( m_FileMenu );
        m_MenuBar.add( m_HelpMenu );
        
        /* make new help content menu item */
        /* add helpConItem to menu */
        m_HelpMenu.add( m_HelpConItem );
        
        /* add menu bar to BobViz */
        setJMenuBar( m_MenuBar );
        
        /* add the mouse listener for menu */
        MouseListener popupListener = new PopupListener();
        m_ImportCSVItem.addMouseListener( popupListener );
        m_ExitItem.addMouseListener( popupListener );
        
        /* create new logo for BobViz */
        ImageIcon bvIcon = new ImageIcon( LOGO_DIR );
        /* add icon to JLabel */
        JLabel bobTitleJPan = new JLabel( bvIcon );
        
        /* make new instances of all panels */
        m_AboutJPan = new AboutJPanel();
        m_ImportJPan = new ImportJPanel();
        m_ImportSecondJPan = new ImportSecondJPanel();
        m_VizTypeJPan = new VizTypeJPanel();
        m_SettingJPan = new SettingJPanel();
        
        m_SelectionVizJPan = new SelectionVizJPanel();
        
        /* set top panel with FlowLayout layout */
        top.setLayout( new FlowLayout( FlowLayout.CENTER ) );
        /* add all panels and titles to top JPanel */
        top.add( bobTitleJPan );
        top.add( m_AboutJPan );
        top.add( m_ImportJPan );
        top.add(m_ImportSecondJPan);
        top.add( m_VizTypeJPan );
        top.add( m_SettingJPan );
       
        top.add( m_SelectionVizJPan );
        
        
        /* add top and m_StatusJPan to BobViz */
        add( top, BorderLayout.CENTER );
        add( m_StatusJPan, BorderLayout.SOUTH );
        
        setVisible( true );
  
    }
    
    /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if( ( test == true ) && ( bv == null) ) {
            System.err.println( "AboutJPanel::SetBV() ***Warning, GroupPDM2"
                    + "application is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "AboutJPanel::SetBV() GroupPDM2"
                    + "application. Value sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
    /**
     * @return the AboutJPanel instance
     */
    public AboutJPanel GetAboutJPan() {
        return m_AboutJPan;
    }
    
    /**
     * @return the DataAttribute instance
     */
    public DataAttribute GetDataAttribute() {
        return m_DataAttribute;
    }
    
    /**
     * @return the Dataset instance
     */
    public Dataset GetDataset() {
        return m_Dataset;
    }
    
    /**
     * @return the Second Dataset instance
     */
    public Dataset GetSecondDataset() {
        return m_SecondDataset;
    }
    
    /**
     * @return the ImportJPanel instance
     */
    public ImportJPanel GetImportJPan() {
    	
        return m_ImportJPan;
    }
    
    /**
     * @return the Second ImportJPanel instance
     */
    
    public ImportSecondJPanel GetImportSecondJPan() {
        return m_ImportSecondJPan;
    }
    
    /**
     * @return m_TableJPan
     */
    public Table GetTableJPan() {
    	m_TableJPan = new Table(m_BV.m_Dataset.GetArrayBackwards(), 
    			m_BV.m_Dataset.GetColHeaders(), "", true);
    	
        return m_TableJPan;
    }
    
    /**
     * @return m_SecondTableJPan
     */
    
    public Table GetSecondTableJPan(){
    	m_SecondTableJPan = new Table(m_BV.m_SecondDataset.GetArrayBackwards(),
    	m_BV.m_SecondDataset.GetColHeaders(), "", false);
    	
    	return m_SecondTableJPan;
    	}  
    /**
     * @return the SettingJPanel instance
     */
    public SettingJPanel GetSettingJPan() {
        return m_SettingJPan;
    }
    

    /**
     * @return the MoreSettingJPanel instance
     */
    public MoreSettingJPanel GetMoreSettingJPan() {
    	return m_MoreSettingJPan;
    }
    
    /**
     * @return the VizTypeJPanel instance
     */
    public VizTypeJPanel GetVizTypeJPan() {
        return m_VizTypeJPan;
    }
    
    /**
     * @return the StatusJPanel instance
     */
    public StatusJPanel GetStatusJPan() {
        return m_StatusJPan;
    }
    
    /**
     * @return the GenerateVizJPanel instance
     */
    public SelectionVizJPanel GetGenerateVizJPan() {
        return m_SelectionVizJPan;
    }
    
    /**
     * @return the Visualisation instance
     */
    public Visualisation GetViz() {
        return m_Visualisation;
    }
   
    
    /* listener for menu bar */
    private class PopupListener extends MouseAdapter {
        @Override
        public void mousePressed( MouseEvent e ) {
            //
        }

        @Override
        public void mouseReleased( MouseEvent e ) {
            if( e.getSource() == m_ImportCSVItem ) {
                m_BV.GetImportJPan().SetImportCSV();
            } else if( e.getSource() == m_ExitItem ) {
                System.exit( 0 );
            }
        }
    }
    
    
    /** AboutJPanel type object */
    private AboutJPanel m_AboutJPan;
    /** ImportJPanel type object */
    private ImportJPanel m_ImportJPan;
    /** ImportJPanel type object */
    private ImportSecondJPanel m_ImportSecondJPan;
    /**TableJPanel Object */
    private Table m_TableJPan;
    /**TableJPanel Object */
    private Table m_SecondTableJPan;
    /** VizTypeJPanel type object */
    private VizTypeJPanel m_VizTypeJPan;
    /** SettingJPanel type object */
    private SettingJPanel m_SettingJPan;
    /** StatusJPanel type object */
  
    
    private StatusJPanel m_StatusJPan;
    /** SelectionJPanel type object */
    private SelectionVizJPanel m_SelectionVizJPan;
    /** MoreSettingJPanel type object */
    private MoreSettingJPanel m_MoreSettingJPan;
    /** Visualisation new instance */
    private Visualisation m_Visualisation = new Visualisation();
    /** DataAttribute new instance */
    private DataAttribute m_DataAttribute = new DataAttribute();
    /** Dataset new instance */
    private Dataset m_Dataset = new Dataset();
    /** Second Dataset new instance */
    private Dataset m_SecondDataset = new Dataset();
    
    /** add new file menu */
    private final String FILE_MENU = "File";
    /** add new exit menu */
    private final String FILE_EXIT = "Exit";
    /** add new help menu */
    private final String FILE_HELP = "Help";
    
    /** add new import csv menu item */
    private final String FILE_IMPORTCSV = "Import CSV...";
    /** add new help content menu item */
    private final String FILE_HELPCON = "Help Contents";
    
    /** set logo directory */
    private final String LOGO_DIR = "images/png/bv_logo.png";
    
    /** title frame of BobViz */
    private final String FRAME_TITLE = "BobViz v1.0";
    /** width size of BobBiz */
    private final int FRAME_WIDTH = 556;
    /** height size of BobViz */
    private final int FRAME_HEIGHT = 680;
    
    /** create new BobViz object **/
    private BobViz m_BV;
    
    /** create new menu objects */
    private JMenuBar m_MenuBar;
    private JMenu m_FileMenu;
    private JMenu m_HelpMenu;
    private JMenuItem m_ImportCSVItem;
    private JMenuItem m_ExitItem;
    private JMenuItem m_HelpConItem;
    
}