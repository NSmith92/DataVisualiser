/**
 *
 * @author Herberts Markuns
 */
/**
 * @file -Colour.java
 * @author -Herberts Markuns
 * @class  -Colour
 * @date -10 February 2013
 * 
 * @brief This class provides user with a choice of some colours to be applied 
 * on objects from different graphs/charts/diagrams. 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.*;
 
public class Colour extends AbstractColorChooserPanel
                         implements ActionListener {
    

    public Colour( ) {
        
    }
    
    @Override
    public void updateChooser( ) {
       
    }
    
    /**
     * This method creates a colour JButton, and returns it.  
     * @param name contains the name of the colour, given by the programmer.
     * @return Returns a JButton "colour".  
     */
    private JButton createColour( String name ) {

        /* Creates a Colour Button. */
        JButton colour = new JButton( );
        colour.setActionCommand( name );
        colour.addActionListener( this );
        colour.setHorizontalAlignment( JButton.HORIZONTAL );
        colour.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        
        return colour;
    }
 
    @Override
    protected void buildChooser( ) {
        /* Colour Buttons Group Row Count */
        final int CBG_ROW_COUNT = 3;
        /* Colour Button Group Column Count */
        final int CBG_COLUMN_COUNT = 4;
        /* Colour Button Height */
        final int CB_HEIGHT = 20;
        /* Colour Button Width */
        final int CB_WIDTH = 20;
        
        setLayout( new GridLayout( CBG_ROW_COUNT, CBG_COLUMN_COUNT ) );
        /* Groups default colours into specific row and column count */
        
        ButtonGroup colourButtons = new ButtonGroup( );
        colourButtons.getButtonCount( );
        
        /* White Colour Button */
        whiteButton = createColour("white");
        whiteButton.setBackground(Color.WHITE);
        whiteButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		whiteButton.setOpaque(true);
        colourButtons.add(whiteButton);
        add(whiteButton);
        
        /* Light Gray Colour Button */
        lGrayButton = createColour("lGray");
        lGrayButton.setBackground(Color.LIGHT_GRAY);
        lGrayButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		lGrayButton.setOpaque(true);
        colourButtons.add(lGrayButton);
        add(lGrayButton);
        
        /* Gray Colour Button */
        grayButton = createColour("gray");
        grayButton.setBackground(Color.GRAY);
        grayButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		grayButton.setOpaque(true);
        colourButtons.add(grayButton);
        add(grayButton);
        
        /* Black Colou Button */
        blackButton = createColour("black");
        blackButton.setBackground(Color.BLACK);
        blackButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		blackButton.setOpaque(true);
        colourButtons.add(blackButton);
        add(blackButton);       
        
        /* Yellow Colour Button */
        yellowButton = createColour("yellow");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		yellowButton.setOpaque(true);
        colourButtons.add(yellowButton);
        add(yellowButton);
        
        /* Green Button Colour */
        greenButton = createColour("green");
        greenButton.setBackground(Color.GREEN);
        greenButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		greenButton.setOpaque(true);
        colourButtons.add(greenButton);
        add(greenButton);
        
        /* Red Button Colour */
        redButton = createColour("red");
        redButton.setBackground(Color.RED);
        redButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		redButton.setOpaque(true);
        colourButtons.add(redButton);
        add(redButton);
        
        /* Cyan Colour Button */
        cyanButton = createColour("cyan");
        cyanButton.setBackground(Color.CYAN);
        cyanButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		cyanButton.setOpaque(true);
        colourButtons.add(cyanButton);
        add(cyanButton);
        
        /* Pink Colour Button */
        pinkButton = createColour("pink");
        pinkButton.setBackground(Color.PINK);
        pinkButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		pinkButton.setOpaque(true);
        colourButtons.add(pinkButton);
        add(pinkButton);
        
        /* Orange Colour Button */
        orangeButton = createColour("orange");
        orangeButton.setBackground(Color.ORANGE);
        orangeButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		orangeButton.setOpaque(true);
        colourButtons.add(orangeButton);
        add(orangeButton);
        
        /* Magenta Colour Button */
        magentaButton = createColour("magenta");
        magentaButton.setBackground(Color.MAGENTA);
        magentaButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		magentaButton.setOpaque(true);
        colourButtons.add(magentaButton);
        add(magentaButton);
        
        /* Blue Colour Button */
        blueButton = createColour("blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.setPreferredSize(new Dimension(CB_WIDTH, CB_HEIGHT));
		blueButton.setOpaque(true);
        colourButtons.add(blueButton);
        add(blueButton);
                      
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        Color newColor = null;
        String command = ( ( JButton )e.getSource( ) ).getActionCommand( );
        
        /* Switch assigns a new colour, depending on what string "command"
         * ( which contains the name of the colour ) contains. 
         */       
        switch ( command ) {
            case "green":
                newColor = Color.GREEN;
                break;
            case "red":
                newColor = Color.RED;
                break;
            case "yellow":
                newColor = Color.YELLOW;
                break;
            case "blue":
                newColor = Color.BLUE;
                break;
            case "black":
                newColor = Color.BLACK;
                break;
            case "cyan":
                newColor = Color.CYAN;
                break;
            case "gray":
                newColor = Color.GRAY;
                break;
            case "magenta":
                newColor = Color.MAGENTA;
                break;
            case "orange":
                newColor = Color.ORANGE;
                break;
            case "pink":
                newColor = Color.PINK;
                break;
            case "white":
                newColor = Color.WHITE;
                break;
            case "lGray":
                newColor = Color.LIGHT_GRAY;
                break;
        }
        
        /* Changes the colour of the object to the new colour. */
        getColorSelectionModel( ).setSelectedColor( newColor );
    }
 
    @Override
    public String getDisplayName( ) {
        return "Colours";
    }
 
    @Override
    public Icon getSmallDisplayIcon( ) {
        return null;
    }
 
    @Override
    public Icon getLargeDisplayIcon( ) {
        return null;
    }
    
	/* make new buttons to select different colours */
	/** Red Colour Button. */
    private JButton redButton;
    /** Yellow Colour Button. */
    private JButton yellowButton;
    /** Green Colour Button. */
    private JButton greenButton;
    /** Blue Colour Button. */
    private JButton blueButton;
    /** Black Colour Button. */
    private JButton blackButton;
    /** White Colour Button. */
    private JButton whiteButton;
    /** Cyan Colour Button. */
    private JButton cyanButton;
    /** Gray Colour Button. */
    private JButton grayButton;
    /** Magenta Colour Button. */
    private JButton magentaButton;
    /** Orange Colour Button. */
    private JButton orangeButton;
    /** Pink Colour Button. */
    private JButton pinkButton;
    /** Light Gray Colour Button. */
    private JButton lGrayButton;
    
}
