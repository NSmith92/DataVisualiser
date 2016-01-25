/**
 * @author Herberts Markuns -A4, Christopher Brennen - A5
 */
/**
 * @file ColourMap.java
 * @class ColourMap
 * @date 10 February 2013
 *
 * @brief This class provides user with a choice of colours to be applied
 * on objects from different graphs/charts/diagrams, and does all calculations
 * needed in order to get it all working correctly.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.PolarItemRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.ui.RefineryUtilities;
import java.awt.Color;

public class ColourMap extends JPanel
implements ActionListener, ChangeListener {
    
    /** This method handles all the actions performed by the user.  */
    @Override
    public void actionPerformed( ActionEvent e ) {
        
        if( e.getSource(  ) == m_MoreColoursButton ) {
            // Show advanced colour menu
            
            Color newColor = JColorChooser.showDialog( 
                                ColourMap.this, "Choose Object Colour",
                                m_ObjectColour.getBackground(  ) );
            if(  newColor != null ) {
                m_ObjectColour.setBackground( newColor );
                
            }
        }
        
        else if(  ( e.getSource(  ) == m_ConfirmOK ) ||
                  (  e.getSource(  ) == m_ConfirmApply ) ) {
            if( m_SchemeSelected ) {
                
            } else {
                /* This will check for the correct object, will change its colour
                 * and then will close the window */
                if( getObjectType(  ).equals( m_CategoryItemRendererName ) ) {
                    // Calls SetColour method for the CategoryItemRenderer type.
                    setColour( getIndexToChange(  ),getCIRToChange(  ),
                              m_ObjectColour.getBackground(  ) );
                } else if( getObjectType(  ).equals( m_PiePlot3DName ) ) {
                    // Calls SetColour method for the PiePlot3D type.
                    setColour( getIndexToChange(  ),getPP3DToChange(  ),
                              m_ObjectColour.getBackground(  ) );
                } else if( getObjectType(  ).equals( m_XYItemRendererName ) ) {
                    // Calls SetColour method for the XYItemRenderer type.
                    setColour( getIndexToChange(  ),getXYIRToChange(  ),
                              m_ObjectColour.getBackground(  ) );
                } else if( getObjectType(  ).equals( m_PolarItemRendererName ) ) {
                    // Calls SetColour method for the XYItemRenderer type.
                    setColour( getIndexToChange(  ),getPolarIRToChange(  ),
                              m_ObjectColour.getBackground(  ) );
                } else {
                    /* Displays error message, if the renderer/object has not been
                     * recognized and/or it is missing an ActionListener condition.
                     */
                    System.out.println( m_ObjectType );
                    System.out.println( "Renderer Type not recognized,"
                                       + " Missing ActionListener." );
                }
            }
            if( e.getSource(  ) == m_ConfirmOK ) {
                /* Closes the window if changes confirmed. */
                CloseWindow(  );
            }
        } else if(  e.getSource(  ) == m_ConfirmCancel ) {
            /* Doesn't update the actual object, just closes the window. */
            CloseWindow(  );
        } else if(  e.getSource(  ) == m_IndexSelection ) {
            /* Sets the indexToChange value and updates the colour of
             * colour preview(  objectColour ) to match the current colour of
             * object on that index.
             */
             setIndexToChange( m_IndexSelection.getSelectedIndex(  ) );
            m_ObjectColour.setBackground( applyPreviousColour(  ) );
        } else if(  e.getSource(  ) == m_ColourSchemesBox ) {
            m_SchemeSelected = true;
            m_ColourSchemes[0][0]= Color.RED;
            m_ColourSchemes[0][1]= Color.GREEN;
            m_ColourSchemes[0][2]= Color.BLUE;
            m_ColourSchemes[0][3]= Color.YELLOW;
            m_ColourSchemes[0][4]= Color.CYAN;
            m_ColourSchemes[1][0]= Color.ORANGE;
            m_ColourSchemes[1][1]= Color.YELLOW;
            m_ColourSchemes[1][2]= Color.RED;
            m_ColourSchemes[1][3]= Color.PINK;
            m_ColourSchemes[1][4] = Color.MAGENTA;
            m_ColourSchemes[2][0] = Color.LIGHT_GRAY;
            m_ColourSchemes[2][1] = Color.GRAY;
            m_ColourSchemes[2][2] = Color.DARK_GRAY;
            m_ColourSchemes[2][3] = Color.BLACK;
            m_ColourSchemes[2][4] = Color.WHITE;
            m_ColourSchemes[3][0] = Color.CYAN;
            m_ColourSchemes[3][1] = Color.PINK;
            m_ColourSchemes[3][2]= Color.MAGENTA;
            m_ColourSchemes[3][3]= Color.YELLOW;
            m_ColourSchemes[3][4]= Color.LIGHT_GRAY;
            m_ColourSchemes[4][0]= Color.CYAN;
            m_ColourSchemes[4][1]= Color.BLUE;
            m_ColourSchemes[4][2]= Color.PINK;
            m_ColourSchemes[4][3]= Color.MAGENTA;
            m_ColourSchemes[4][4]= Color.BLACK;
            
            int index = m_ColourSchemesBox.getSelectedIndex(  );
            
            if( getObjectType(  ).equals( m_CategoryItemRendererName ) ) {
                // Calls SetColour method for the CategoryItemRenderer type.
                int j=0;
                for( int i=0;i< getIndexCount(  ); i++ ) {
                    setColour( i,m_C_objectToChange,m_ColourSchemes[index][j] );
                    
                    if( j<m_ColourSchemes[index].length-1 ) {
                        j++;
                    } else {
                        j = 0;
                    }
                    
                }
                
            } else if( getObjectType(  ).equals( m_PiePlot3DName ) ) {
                // Calls SetColour method for the PiePlot3D type.
                int j=0;
                for( int i=0;i< getIndexCount(  ); i++ ) {
                    setColour( i,m_P_objectToChange,m_ColourSchemes[index][j] );
                    
                    if( j<m_ColourSchemes[index].length-1 ) {
                        j++;
                    } else {
                        j = 0;
                    }
                    
                }
            } else if( getObjectType(  ).equals( m_XYItemRendererName ) ) {
                // Calls SetColour method for the XYItemRenderer type.
                int j=0;
                for( int i=0;i< getIndexCount(  ); i++ ) {
                    setColour( i,m_X_objectToChange,m_ColourSchemes[index][j] );
                    
                    if( j<m_ColourSchemes[index].length-1 ) {
                        j++;
                    } else {
                        j = 0;
                    }
                    
                }
            } else if( getObjectType(  ).equals( m_PolarItemRendererName ) ) {
                // Calls SetColour method for the XYItemRenderer type.
                int j=0;
                for( int i=0;i< getIndexCount(  ); i++ ) {
                    setColour( i,m_PO_objectToChange,m_ColourSchemes[index][j] );
                    
                    if( j<m_ColourSchemes[index].length-1 ) {
                        j++;
                    } else {
                        j = 0;
                    }
                    
                }
            } else {
                /* Displays error message, if the renderer/object has not been
                 * recognized and/or it is missing an ActionListener condition.
                 */
                System.out.println( m_ObjectType );
                System.out.println( "Renderer Type not recognized,"
                                   + " Missing ActionListener." );
            }
        }
        
        
    }
    
    /**
     * This method closes/hides the window without shutting down the program.
     * It's public, just incase there is a need to close it outside this class.
     */
    public boolean CloseWindow(  ) {
        boolean errorless = true;
        
        try {
            m_MainColourFrame.dispose(  );
        } catch(  Exception e ) {
            errorless = false;
        }
        return errorless;
    }
    
    /**
     * Constructor is being called to construct a new BorderLayout
     * and to set up the GUI.
     */
    public ColourMap(  ) {
        super( new BorderLayout(  ) );
        setupGUI(  ); // Sets up the GUI
    }
    
    /**
     * This method sets up all the needed data to perform colour changes with
     * the CategoryItemRenderer type object.
     * @param indexCount contains number of indexes the object has.
     * @param renderer contains the object which needs colour adjustments.
     * @return Returns true if it executed without errors, false otherwise.
     */
    public boolean SetupData( int indexCount, CategoryItemRenderer renderer ) {
        boolean errorless = true;
        
        if( indexCount < 0 ) {
            /* Checks if the index count is a negative number.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Trying to set indexCount to a negative number. "
                               + "Index count can't be negative." );
            errorless = false;
        }
        
        if(  renderer == null ) {
            /* Checks if the renderer is null.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Renderer passed in is of a null value. "
                               + "Renderer can't be null." );
            errorless = false;
        }
        
        if( errorless ) {
            /* If there are no errors detected in the passed in data,
             * Initialize all of it and create the GUI. */
            setObjectToChange( renderer );
            setIndexCount( indexCount );
            setObjectType( m_CategoryItemRendererName );
            
            createAndShowGUI(  );
        }
        
        return errorless;
    }
    
    /**
     * This method sets up all the needed data to perform colour changes with
     * the PiePlot3D type object.
     * @param indexCount contains number of indexes the object has.
     * @param renderer contains the object which needs colour adjustments.
     * @return Returns true if it executed without errors, false otherwise.
     */
    public boolean SetupData( int indexCount, PiePlot3D renderer ) {
        boolean errorless = true;
        
        if( indexCount < 0 ) {
            /* Checks if the index count is a negative number.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Trying to set indexCount to a negative number. "
                               + "Index count can't be negative." );
            errorless = false;
        }
        
        if( renderer == null ) {
            /* Checks if the renderer is null.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Renderer passed in is of a null value. "
                               + "Renderer can't be null." );
            errorless = false;
        }
        
        if( errorless ){
            /* If there are no errors detected in the passed in data,
             * Initialize all of it and create the GUI. */
            setObjectToChange( renderer );
            setIndexCount( indexCount );
            setObjectType( m_PiePlot3DName );
            
            createAndShowGUI(  );
        }
        
        return errorless;
    }
    
    /**
     * This method sets up all the needed data to perform colour changes with
     * the XYItemRenderer type object.
     * @param indexCount contains number of indexes the object has.
     * @param renderer contains the object which needs colour adjustments.
     * @return Returns true if it executed without errors, false otherwise.
     */
    public boolean SetupData( int indexCount,
                             XYItemRenderer renderer ) {
        Boolean errorless = true;
        if( indexCount < 0 ) {
            /* Checks if the index count is a negative number.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Trying to set indexCount to a negative number. "
                    + "Index count can't be negative." );
            errorless = false;
        }
        
        if(  renderer == null ) {
            /* Checks if the renderer is null.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Renderer passed in is of a null value. "
                               + "Renderer can't be null." );
            errorless = false;
        }
        
        if( errorless ) {
            /* If there are no errors detected in the passed in data,
             * Initialize all of it and create the GUI. */
            setObjectToChange( renderer );
            setIndexCount( indexCount );
            setObjectType( m_XYItemRendererName );
            
            createAndShowGUI(  );
        }
        
        return errorless;
    }
    
    /**
     * This method sets up all the needed data to perform colour changes with
     * the XYItemRenderer type object.
     * @param indexCount contains number of indexes the object has.
     * @param renderer contains the object which needs colour adjustments.
     * @return Returns true if it executed without errors, false otherwise.
     */
    public boolean SetupData( int indexCount,
    		DefaultPolarItemRenderer renderer ) {
        Boolean errorless = true;
        if( indexCount < 0 ) {
            /* Checks if the index count is a negative number.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Trying to set indexCount to a negative number. "
                    + "Index count can't be negative." );
            errorless = false;
        }
        
        if(  renderer == null ) {
            /* Checks if the renderer is null.
             * If true, returns an error message. */
            System.out.println( "SetupData ERROR:" );
            System.out.println( "Renderer passed in is of a null value. "
                               + "Renderer can't be null." );
            errorless = false;
        }
        
        if( errorless ) {
            /* If there are no errors detected in the passed in data,
             * Initialize all of it and create the GUI. */
            setObjectToChange( renderer );
            setIndexCount( indexCount );
            setObjectType( m_PolarItemRendererName );
            
            createAndShowGUI(  );
        }
        
        return errorless;
    }
    
    /**
     * This method is responsible for detecting when the state of the button has
     * changed, thus responding to it with an action.
     * @param e is a ChangeEvent type of event.
     */
    @Override
    public void stateChanged( ChangeEvent e ) {
        //Changes colour depending on what user has clicked
        Color newColor = m_MoreColours.getColor(  );
        m_ObjectColour.setBackground( newColor );
    }
    
    /**
     * This method retrieves the previous colour of the object in paint type,
     * converts it to Color type, and returns it.
     *
     * @return Returns the previous colour of the object.
     */
    private Color applyPreviousColour(  ) {
        /*
         * This method will get the previous colour of the object as a Paint
         * type. Because Paint type cant be simply converted into a Color type
         * it first needs to convert the Paint type into a String type
         * and then by using some the replaceAll method for string, it will
         * remove everything but the numbers for RGB. Then using a Scanner
         * it assings Strings "r", "g", and "b" it's values. Afterwards
         * it attepts to assign the new RGB values to "newishColour" Colour
         * object, by parsing Strings "r", "g", "b" into an Integer type.
         * And when that's done, it finally returns the newishColour, which is
         * the previous colour that was assigned to the object in the selected
         * index.
         */
        
        Paint newColour = m_ObjectColour.getBackground(  );
        Color newishColour = m_ObjectColour.getBackground(  );
        String r="",g="",b="";
        // Initialize incase it fails to get initialized later.
        
        if( getObjectType(  ).equals( m_PiePlot3DName ) ) {
            newColour =(  m_P_objectToChange.getSectionPaint( getIndexToChange(  ) ) );
        } else if( getObjectType(  ).equals( m_CategoryItemRendererName ) ) {
            newColour =(  m_C_objectToChange.getSeriesPaint( getIndexToChange(  ) ) );
        } else if( getObjectType(  ).equals( m_XYItemRendererName ) ){
            newColour =(  m_X_objectToChange.getSeriesPaint( getIndexToChange(  ) ) );
        } else if( getObjectType(  ).equals( m_PolarItemRendererName ) ){
            newColour =(  m_PO_objectToChange.getSeriesPaint( getIndexToChange(  ) ) );
        }
        
        String newColourString = newColour.toString(  );
        // Converts the Paint type to String
        
        /* Replacing all unwanted symbols/letters with spaces or empty spaces */
        String sanitizedText = newColourString
        .replaceAll( "[^\\w\\s\\.]", " " );
        sanitizedText = sanitizedText.replaceAll( "java.awt.Color|r|b|g", "" );
        
        
        Scanner sk = new Scanner( sanitizedText );
        //Scanner object that reads
        
        /* Assigns all the integers to corresponding colour channels */
        if(  sk.hasNext(  ) ) {
            r = sk.next(  );
            if(  sk.hasNext(  ) ) {
                g = sk.next(  );
                if(  sk.hasNext(  ) ) {
                    b = sk.next(  );
                }
            }
        }
        
        
        /* Attempt to assign new RGB values to the newishColour */
        try{
            newishColour = new Color( Integer.parseInt( r ),
                                     Integer.parseInt( g ), Integer.parseInt( b ) );
            
        } catch( Exception e ) {
            System.out.println( "applyPreviousColour(  ) method Error:" );
            System.out.println( "Couldn't create a new color." );
        }
        
        
        return newishColour;
    }
    
    
    
    /**
     * This method creates the GUI and displays it.
     */
    private boolean createAndShowGUI(  ) {
        boolean errorless = true;
        
        try{
            // Create and set up the window.
            m_MainColourFrame = new JFrame( "Colour Choose" );
            m_MainColourFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            RefineryUtilities.centerFrameOnScreen( m_MainColourFrame );
            
            
            // Create and set up the content pane.
            JComponent newContentPane = new ColourMap(  );
            newContentPane.setOpaque( true ); //content panes must be opaque
            m_MainColourFrame.setContentPane( newContentPane );
            
            // Display the window.
            m_MainColourFrame.pack(  );
            m_MainColourFrame.setVisible( true );
        } catch(  Exception e ) {
            errorless = false;
        }
        
        return errorless;
    }
    
    private boolean CreateColourSchemes(  ) {
        boolean errorless = true;
        
        m_ColourSchemes[0][0]= Color.RED;
        m_ColourSchemes[0][1]= Color.GREEN;
        m_ColourSchemes[0][2]= Color.BLUE;
        m_ColourSchemes[1][0]= Color.YELLOW;
        m_ColourSchemes[1][1]= Color.CYAN;
        m_ColourSchemes[1][2]= Color.PINK;
        m_ColourSchemes[2][0]= Color.LIGHT_GRAY;
        m_ColourSchemes[2][1] = Color.GRAY;
        m_ColourSchemes[2][2] = Color.DARK_GRAY;
        
        
        return errorless;
    }
    
    /**
     * Returns a CategoryItemRenderer object that needs to be changed.
     * @return returns a CategoryItemRendererObject object.
     */
    private CategoryItemRenderer getCIRToChange(  ) {
        
        return m_C_objectToChange;
    }
    
    /**
     * Returns index count in the object currently being manipulated
     * @return returns index count in the object
     */
    private int getIndexCount(  ) {
        
        return m_O_indexCount;
    }
    
    /**
     * Returns the index of the object that needs to be changed.
     * @return the index of the object that needs to be changed.
     */
    private int getIndexToChange(  ) {
        
        return m_O_indexToChange;
    }
    
    /**
     * Returns what kind of object is being currently manipulated
     * @return Returns type name of the object currently being manipulated.
     */
    private String getObjectType(  ) {
        return m_ObjectType;
    }
    
    /**
     * Returns a PiePlot3D object that needs to be changed.
     * @return returns a PiePlot3D object.
     */
    private PiePlot3D getPP3DToChange(  ) {
        
        return m_P_objectToChange;
    }
    
    /**
     * Returns a XYItemRenderer object that needs to be changed.
     * @return returns a XYItemRenderer object.
     */
    private XYItemRenderer getXYIRToChange(  ) {
        
        return m_X_objectToChange;
    }
    
    /**
     * Returns a DefaultPolarItemRenderer object that needs to be changed.
     * @return returns a DefaultPolarItemRenderer object.
     */
    private DefaultPolarItemRenderer getPolarIRToChange(  ) {
        
        return m_PO_objectToChange;
    }
    
    
    
    /**
     * This method applies the new colour to the CategoryItemRenderer type
     * object.
     * @param seriesIndex object seriesindex that needs changing
     * @param renderer is the object the new colour needs to be applied on
     * @param colour is colour to apply to the object
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setColour( int seriesIndex,
                              CategoryItemRenderer renderer, Color newColour ) {
        boolean errorless = true;
        
        if( newColour == null ) {
            /* Checks if the colour to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "newColour is null. Cannot set a new colour." );
            errorless = false;
        }
        
        if(  renderer == null ){
            /* Checks if the renderer to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "renderer is null. Cannot access it." );
            errorless = false;
        }
        
        if( seriesIndex < 0 ) {
            /* Checks if the index to change is a negative number.
             * Gives a warning if it is a negative number. Still attempts
             * change the colour of the object, because a negative index
             * might is possible, but unlikely. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "Trying to set index to a negative number. "
                               + "Index cannot be negative." );
            errorless = false;
        }
        
        if( errorless ){
            /* Attempt to change the colour. */
            try {
                renderer.setSeriesPaint( seriesIndex, newColour );
            } catch( Exception e ) {
                System.out.println( "Error in SetColour method:" );
                System.out.println( "Couldn't set the new colour to "
                                   + "series with index " +seriesIndex );
                errorless = false;
            }
        }
        
        return errorless;
    }
    
    
    /**
     * This method applies the new colour to the PiePlot3D type object.
     * @param seriesIndex object seriesindex that needs changing
     * @param renderer is the object the new colour needs to be applied on
     * @param colour is colour to apply to the object
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setColour( int seriesIndex,
                              PiePlot3D renderer, Color newColour ) {
        boolean errorless = true;
        
        if( newColour == null ) {
            /* Checks if the colour to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "newColour is null. Cannot set a new colour." );
            errorless = false;
        }
        
        if(  renderer == null ){
            /* Checks if the renderer to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "renderer is null. Cannot access it." );
            errorless = false;
        }
        
        if( seriesIndex < 0 ) {
            /* Checks if the index to change is a negative number.
             * Gives a warning if it is a negative number. Still attempts
             * change the colour of the object, because a negative index
             * might is possible, but unlikely. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "Trying to set index to a negative number. "
                               + "Index cannot be negative." );
            errorless = false;
        }
        
        if( errorless ){
            /* Attempt to change the colour. */
            try {
                renderer.setSectionPaint( seriesIndex,newColour );
            } catch( Exception e ) {
                System.out.println( "Error in SetColour method:" );
                System.out.println( "Couldn't set the new colour to "
                                   + "series with index " +seriesIndex );
                errorless = false;
            }
        }
        
        return errorless;
        
    }
    
    
    /**
     * This method applies the new colour to the XYItemRenderer type
     * object.
     * @param seriesIndex object seriesindex that needs changing
     * @param renderer is the object the new colour needs to be applied on
     * @param colour is colour to apply to the object
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setColour( int seriesIndex,
                              XYItemRenderer renderer, Color newColour ) {
        boolean errorless = true;
        
        if( newColour == null ) {
            /* Checks if the colour to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "newColour is null. Cannot set a new colour." );
            errorless = false;
        }
        
        if(  renderer == null ){
            /* Checks if the renderer to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "renderer is null. Cannot access it." );
            errorless = false;
        }
        
        if( seriesIndex < 0 ) {
            /* Checks if the index to change is a negative number.
             * Gives a warning if it is a negative number. Still attempts
             * change the colour of the object, because a negative index
             * might is possible, but unlikely. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "Trying to set index to a negative number. "
                               + "Index cannot be negative." );
            errorless = false;
        }
        
        if( errorless ){
            /* Attempt to change the colour. */
            try {
                renderer.setSeriesPaint( seriesIndex, newColour );
            } catch( Exception e ) {
                System.out.println( "Error in SetColour method:" );
                System.out.println( "Couldn't set the new colour to "
                                   + "series with index " +seriesIndex );
                errorless = false;
            }
        }
        
        return errorless;
    }
    
    /**
     * This method applies the new colour to the XYItemRenderer type
     * object.
     * @param seriesIndex object seriesindex that needs changing
     * @param renderer is the object the new colour needs to be applied on
     * @param colour is colour to apply to the object
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setColour( int seriesIndex,
    		DefaultPolarItemRenderer renderer, Color newColour ) {
        boolean errorless = true;
        
        if( newColour == null ) {
            /* Checks if the colour to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "newColour is null. Cannot set a new colour." );
            errorless = false;
        }
        
        if(  renderer == null ){
            /* Checks if the renderer to change to is null, returns an Error
             * if it is null. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "renderer is null. Cannot access it." );
            errorless = false;
        }
        
        if( seriesIndex < 0 ) {
            /* Checks if the index to change is a negative number.
             * Gives a warning if it is a negative number. Still attempts
             * change the colour of the object, because a negative index
             * might is possible, but unlikely. */
            System.out.println( "setColour method ERROR:" );
            System.out.println( "Trying to set index to a negative number. "
                               + "Index cannot be negative." );
            errorless = false;
        }
        
        if( errorless ){
            /* Attempt to change the colour. */
            try {
                renderer.setSeriesPaint( seriesIndex, newColour );
            } catch( Exception e ) {
                System.out.println( "Error in SetColour method:" );
                System.out.println( "Couldn't set the new colour to "
                                   + "series with index " +seriesIndex );
                errorless = false;
            }
        }
        
        return errorless;
    }
    
    /**
     * Changes the global Index Count variable(  o_indexCount ) ,
     * that is used to figure out how many indexes there are.
     * @param newIndexCount is the new passed in count of indexes in the object.
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setIndexCount( int newIndexCount ) {
        boolean errorless = true;
        
        if( newIndexCount<0 ) {
            /* If index count is below zero, gives an error, because it's
             impossible to have negative amound of indexes. */
            System.out.println( "setIndexCount ERROR:" );
            System.out.println( "Trying to set index count to a negative "
                               + "number. Index count cannot be negative." );
            errorless = false;
        }
        
        if( errorless ) {
            /* If no errors are detected in the input, set the new index count.
             * to "newIndexCount". */
            m_O_indexCount = newIndexCount;
        }
        
        return errorless;
    }
    
    /**
     * Sets the index of the object thats colour needs to be changed.
     * @param index contains the object index that needs to be changed.
     */
    private boolean setIndexToChange( int index ) {
        boolean errorless = true;
        
        if( index < 0 ) {
            /* If index is below zero, gives an error, because an object can't
             * have a negative index number. */
            System.out.println( "setIndexToChange ERROR:" );
            System.out.println( "Trying to set index to a negative number. "
                               + "Index cannot be a negative number." );
            errorless = false;
        }
        
        if( errorless ) {
            /* If no errors are detected in the input, change the index to
             * change to "index".*/
            m_O_indexToChange = index;
        }
        
        return errorless;
    }
    
    /**
     * Sets the global CategoryItemRenderer variable to one passed in.
     * @param renderer is the new CategoryItemRenderer passed in.
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setObjectToChange( CategoryItemRenderer renderer ) {
        boolean errorless = true;
        
        if( renderer == null ) {
            /* Checks if the renderer is null.
             * Returns an error if it is null. */
            System.out.println( "setObjectToChange ERROR:" );
            System.out.println( "Trying to set renderer as null. Can't set "
                               + "the used renderer to null." );
            errorless = false;
        }
        
        if( errorless ){
            /* If no errors are detected in the input, change the object to
             * change to "renderer".*/
            m_C_objectToChange = renderer;
        }
        
        return errorless;
    }
    
    /**
     * Sets the global PiePlot3D variable to one passed in.
     * @param renderer is the new PiePlot3D passed in.
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setObjectToChange( PiePlot3D renderer ) {
        boolean errorless = true;
        
        if( renderer == null ) {
            /* Checks if the renderer is null.
             * Returns an error if it is null. */
            System.out.println( "setObjectToChange ERROR:" );
            System.out.println( "Trying to set renderer as null. Can't set "
                               + "the used renderer to null." );
            errorless = false;
        }
        
        if( errorless ){
            /* If no errors are detected in the input, change the object to
             * change to "renderer".*/
            m_P_objectToChange = renderer;
        }
        
        return errorless;
    }
    /**
     * Sets the global DefaultPolarItemRenderer variable to one passed in.
     * @param renderer is the new DefaultPolarItemRenderer passed in.
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setObjectToChange( DefaultPolarItemRenderer renderer ) {
        boolean errorless = true;
        
        if( renderer == null ) {
            /* Checks if the renderer is null.
             * Returns an error if it is null. */
            System.out.println( "setObjectToChange ERROR:" );
            System.out.println( "Trying to set renderer as null. Can't set "
                               + "the used renderer to null." );
            errorless = false;
        }
        
        if( errorless ){
            /* If no errors are detected in the input, change the object to
             * change to "renderer".*/
            m_PO_objectToChange = renderer;
        }
        
        return errorless;
    }
    /**
     * Sets the global XYItemRenderer variable to one passed in.
     * @param renderer is the new XYItemRenderer passed in.
     * @return Returns true if it executed without errors, false otherwise.
     */
    private boolean setObjectToChange( XYItemRenderer renderer ) {
        boolean errorless = true;
        
        if( renderer == null ) {
            /* Checks if the renderer is null.
             * Returns an error if it is null. */
            System.out.println( "setObjectToChange ERROR:" );
            System.out.println( "Trying to set renderer as null. Can't set "
                               + "the used renderer to null." );
            errorless = false;
        }
        
        if( errorless ){
            /* If no errors are detected in the input, change the object to
             * change to "renderer".*/
            m_X_objectToChange = renderer;
        }
        
        return errorless;
    }
    
    /**
     * Sets what kind of object is currently being manipulated.
     * @param newObjectType contains a string name of the object type.
     */
    private boolean setObjectType( String newObjectType ) {
        boolean errorless = true;
        if( ( newObjectType == null ) || newObjectType.equals( "" ) ){
            /* Checks if the new passed in String is null or is an empty string.
             * Gives an error message if it is. */
            System.out.println( "setObjectType ERROR:" );
            System.out.println( "Trying to set objectType to an empty string. "
                               + "objectType cannot be empty." );
            errorless = false;
        }
        
        if( errorless ){
            /* If no errors got detected, set the object type(  objectType ) to
             * the "newObjectType". */
            m_ObjectType = newObjectType;
            
        }
        return errorless;
    }
    
    
    /**
     * Creates all buttons/panels needed, assigns actionListeners to them.
     * @param OC_HEIGHT is a constant set to define the height of objectColour
     * @param OC_WIDTH is a constant set to define the width of objectColour
     */
    private boolean setupGUI(  ) {
        boolean errorless = true;
        
        try{
            final int OC_HEIGHT = 30; // Heigth of the objectColour
            final int OC_WIDTH = 50; // Width of the objectColour
            
            /* Set up object colour banner. */
            m_ObjectColour = new JLabel(  );
            /* Default colour first time. */
            m_ObjectColour.setBackground( Color.RED );
            /* Opaque needs to be true, to display it. */
            m_ObjectColour.setOpaque( true );
            /* Prefered size, so that it's clearly visible, but not too big. */
            m_ObjectColour.setPreferredSize( new Dimension( OC_WIDTH, OC_HEIGHT ) );
            //
            
            /* Set up a panel to fill with colour to display new object Colour.
             */
            JPanel bannerPanel = new JPanel( new BorderLayout(  ) );
            bannerPanel.add( m_ObjectColour, BorderLayout.CENTER );
            bannerPanel.setBorder( BorderFactory
                                  .createTitledBorder( "Object Colour" ) );
            
            /* Set up a pannel and configure it */
            JPanel confirmPanel = new JPanel( new BorderLayout(  ) );
            /* OK button to save changes */
            m_ConfirmOK = new JButton( "OK" );
            /* Cancel button to cancel changes. */
            m_ConfirmCancel = new JButton( "Cancel" );
            /* Apply button to confirm changes, but not close the window. */
            m_ConfirmApply = new JButton( "Apply" );
            
            
            /* Assign action listeners to the confirmation buttons. */
            m_ConfirmOK.addActionListener( this );
            m_ConfirmCancel.addActionListener( this );
            m_ConfirmApply.addActionListener( this );
            
            /* Add confirmation buttons to the panel. */
            confirmPanel.add( m_ConfirmOK, BorderLayout.LINE_START );
            confirmPanel.add( m_ConfirmCancel, BorderLayout.LINE_END );
            confirmPanel.add( m_ConfirmApply, BorderLayout.CENTER );
            confirmPanel.setBorder( BorderFactory
                                   .createTitledBorder( "Confirm Colour" ) );
            
            
            /* Set up of More Colours option */
            m_MoreColours = new JColorChooser(  );
            m_MoreColours.getSelectionModel(  ).addChangeListener( this );
            m_MoreColours.setBorder( BorderFactory.createTitledBorder( "Default" ) );
            
            /* Remove the preview panel. */
            m_MoreColours.setPreviewPanel( new JPanel(  ) );
            
            /* Override the colour chooser panels. */
            AbstractColorChooserPanel panels[] = { new
                Colour(  ) };
            m_MoreColours.setChooserPanels( panels );
            m_MoreColours.setColor( m_ObjectColour.getBackground(  ) );
            
            String[] indexValues = new String[getIndexCount(  )];
            for( int l = 0;l < getIndexCount(  ); l++ ) {
                indexValues[l] = "Index "+l;
            }
            m_IndexSelection = new JComboBox( indexValues );
            m_IndexSelection.addActionListener( this );
            
            /* Setup for the "more colours" panel. */
            JPanel colourPanel = new JPanel(  ); //use FlowLayout
            
            String[] colourSchemesBoxNames = new String[5];
            colourSchemesBoxNames[0] = "Colourful";
            colourSchemesBoxNames[1] = "Sunburst";
            colourSchemesBoxNames[2] = "Dark";
            colourSchemesBoxNames[3] = "Feminine";
            colourSchemesBoxNames[4] = "Blue Evo";
            m_ColourSchemesBox = new JComboBox( colourSchemesBoxNames );
            m_ColourSchemesBox.addActionListener( this );
            
            m_MoreColoursButton = new JButton( "More Colours" );
            /* Button that brings up More Colours. */
            m_MoreColoursButton.addActionListener( this );
            colourPanel.add( m_MoreColoursButton );
            colourPanel.add( m_MoreColours );
            colourPanel.add( m_ColourSchemesBox );
            colourPanel.setBorder( BorderFactory.createTitledBorder( "More" ) );
            colourPanel.add( m_IndexSelection );
			
            /* Add everything to their corresponding panels. */
            add( bannerPanel, BorderLayout.NORTH );
            add( m_MoreColours, BorderLayout.CENTER );
            add( colourPanel, BorderLayout.EAST );
            add( confirmPanel, BorderLayout.PAGE_END );
        } catch(  Exception e ) {
            errorless = false;
        }
        
        
        
        return errorless;
        
    }
    
    
    public static void main( String[] args ) {
        /* Unit tests */
        
        ColourMap cM = new ColourMap(  );
        CategoryItemRenderer nullCIR = null;
        XYItemRenderer nullXYIR = null;
        PiePlot3D nullPP3D = null;
        
        
        System.out.println( "SetupData CIR Test" );
        System.out.println( "Both invalid." );
        cM.SetupData( -1, nullCIR ); //Both invalid inputs
        System.out.println( "Invalid Renderer." );
        cM.SetupData( 1, nullCIR ); //Second input invalid
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "SetupData XYIR Test" );
        System.out.println( "Both invalid." );
        cM.SetupData( -1, nullXYIR ); //Both invalid inputs
        System.out.println( "Invalid Renderer." );
        cM.SetupData( 1, nullXYIR ); //Second input invalid
        System.out.println( "-------------------------------------------------" );
        
        
        System.out.println( "SetupData PP3D Test" );
        System.out.println( "Both invalid." );
        cM.SetupData( -1, nullPP3D ); //Both invalid inputs
        System.out.println( "Invalid Renderer." );
        cM.SetupData( 1, nullPP3D ); //Second input invalid
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setColour PP3D Test" );
        System.out.println( "All invalid." );
        cM.setColour( -1, nullPP3D, null );
        System.out.println( "Invalid Renderer." );
        cM.setColour( 1, nullPP3D, Color.YELLOW );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setColour CIR Test" );
        System.out.println( "All invalid." );
        cM.setColour( -1, nullCIR, null );
        System.out.println( "Invalid Renderer." );
        cM.setColour( 1, nullCIR, Color.YELLOW );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setColour XYIR Test" );
        System.out.println( "All invalid." );
        cM.setColour( -1, nullXYIR, null );
        System.out.println( "Invalid Renderer." );
        cM.setColour( 1, nullXYIR, Color.YELLOW );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setIndexCount Test" );
        System.out.println( "Invalid input." );
        cM.setIndexCount( -1 );
        System.out.println( "Valid" );
        cM.setIndexCount( 3 );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setIndexToChange Test" );
        System.out.println( "Invalid input." );
        cM.setIndexToChange( -1 );
        System.out.println( "Valid" );
        cM.setIndexToChange( 3 );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setObjectToChange CIR Test" );
        System.out.println( "Invalid input." );
        cM.setObjectToChange( nullCIR );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setObjectToChange XYIR Test" );
        System.out.println( "Invalid input." );
        cM.setObjectToChange( nullXYIR );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setObjectToChange PP3D Test" );
        System.out.println( "Invalid input." );
        cM.setObjectToChange( nullPP3D );
        System.out.println( "-------------------------------------------------" );
        
        System.out.println( "setObjectType Test" );
        System.out.println( "Invalid input." );
        cM.setObjectType( "" );
        cM.setObjectType( null );
        System.out.println( "Valid" );
        cM.setObjectType( "Tram!" );
        System.out.println( "-------------------------------------------------" );
    }
    
    /** Label that shows the chosen colour. */
    private JLabel m_ObjectColour;
    /** Colour Panel that gives a lot of colour options. */
    private JColorChooser m_MoreColours;
    /** Button to confirm change of the colour. */
    private JButton m_ConfirmOK;
    /** Button to cancel change of the colour. */
    private JButton m_ConfirmCancel;
    /** Button to apply change of the colour, but not close the window. */
    private JButton m_ConfirmApply;
    /** GUI frame for this class. */
    private static JFrame m_MainColourFrame;
    /** Button to access moreColours. */
    private JButton m_MoreColoursButton;
    /** Combo box, to provide user with selection of objects index, to apply
     the new index to. */
    private JComboBox m_IndexSelection;
    /**  Provides with the number of indexes the object has.  */
    private static int m_O_indexCount;
    /** Index for series(  object ID ) that needs colour changing. */
    private static int m_O_indexToChange;
    /** Object that is of a CategoryItemRenderer type.
     * Used to change its colour. */
    private static CategoryItemRenderer m_C_objectToChange;
    /** Object that is of a PiePlot3D type. Used to change its colour. */
    private static PiePlot3D m_P_objectToChange;
    /** Object that is of a XYItemRenderer type. */
    private static XYItemRenderer m_X_objectToChange;
    
    private static DefaultPolarItemRenderer m_PO_objectToChange;
    /** Stores a string saying which object needs its colour changed. */
    private static String m_ObjectType ="";
    /** Name to be set for the objectType, if the object
     * is CategoryItemRenderer. */
    private String m_CategoryItemRendererName = "CIR";
    /** Name to be set for the objectType, if the object is PiePlot3D. */
    private String m_PiePlot3DName = "PP3D";
    /** Name to be set for the objectType, if the object is XYItemRenderer. */
    private String m_XYItemRendererName = "XY";
    private String m_PolarItemRendererName = "Polar";
    /** 2D array containing Colour Schemes. */
    private static Color[][] m_ColourSchemes = new Color[5][5];
    /** Combo box for colour selection. */
    private JComboBox m_ColourSchemesBox;
    /** Boolean value to check if scheme is selected */
    private boolean m_SchemeSelected = false;
}