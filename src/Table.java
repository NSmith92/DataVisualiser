/**
 *
 * @author Connor McFadden - A4, Nathan Smith - A6
 */
/**
 * @file   Table.java
 * @author Connor McFadden
 * @class  Table
 * @date   28 February 2013
 * @updated 01/05/2013
 *
 * @brief A simple class that displays data in a table visualisation
 *
 */

import java.awt.*;
import javax.swing.*;

public class Table extends JPanel {
    /**
    * @param m_data - the data to be passed from users to the table
    * @param m_headers - the headers to be passed to the table
    * @param boolean first - test to place the tables in the appropriate places
    */
	 public Table( String[][] m_data, String[] m_headers, String tableTitle, boolean first ) {
		 /**Instantiate a table taking in specific data from users*/
		 JTable table = new JTable( m_data, m_headers );
		 table.setBorder( BorderFactory.createLineBorder( Color.gray ) );
		 JLabel title = new JLabel( tableTitle );

		 JPanel panel = new JPanel();
		 panel.add( title );
		 /** Create a JScrollPane which adds the JTable*/
		 JScrollPane scrollPane = new JScrollPane( table );
		 scrollPane.setBorder( BorderFactory.createEmptyBorder() );
		 JPanel tableJPan = new JPanel();
		 JButton disabledBut = new JButton( "Change colour" );
		 disabledBut.setEnabled( false );
		 tableJPan.add( scrollPane );

		 JFrame test = new JFrame();
		 test.setLayout( new BorderLayout() );
		 test.setSize( WIDTH_FRAME, HEIGHT_FRAME );
		 test.setTitle( tableTitle );
		 test.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		 test.add( tableJPan, BorderLayout.CENTER );
		 test.add( panel, BorderLayout.NORTH );
		 
		 /*Tests if it is the first or second tables*/
		 if(first == true){
		 test.setLocation(400, 0);
		 } else {
		 test.setLocation(900, 0);
		 }
		 test.setVisible( true );


		 }

    /**Main method for Testing*/
    public static void main(String[] args) {
    	String array[][] = {{"1","2","3","4"},{"4","3","2","1"}};
    	String array1[] = {"header1","header2","header3","header4"};
    	Table Tabletest = new Table(array, array1, "Test Table",false);
    	String array2[][] = {{"5","6","7","8"}, {"8","7","6","5"}};
    	String array3[] = {"header1", "header2", "header3", "header4"};
    	Table Tabletest2 = new Table(array2,array3,"Test Table 2",false);
    	
    }
    
    private final int WIDTH_FRAME = 500;
    private final int HEIGHT_FRAME = 600;
}