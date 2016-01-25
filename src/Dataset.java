/**
 *
 * @author Joshua Taylor - A4
 */
/**
 * @file    -Dataset.java
 * @author  -Josh Taylor
 * @class   -Dataset
 * @date    -1st March 2013
 *
 *  @brief A Class that Reads a data file format 'CSV'
 *  and Creates a 2D array and has methods to extract columns of data
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Dataset {
    
    /** Constructor
     *
     */
    public Dataset() {}
    
    /** Returns Full Array
     *
     * @return
     */
    public String[][] GetArray(){
        return m_CompleteArray;
    }
    
    /** returns full array [rows][columns] for use with table Visualisation
     *
     * @return
     */
    public String[][] GetArrayBackwards(){
        return m_CompleteArrayBackwards;
    }
    
    /** returns array of columns
     *
     * @return
     */
    public String[] GetColHeaders(){
        return m_ColumnHeaders;
    }
    
    /** Returns array or 'column' of data from the 2D array
     *
     * @param index
     * @return an array of data from a single column
     */
    public String[] GetColumn( int index ){
        String[] column = new String[GetNoOfRows()];
        for(int j=0; j<GetNoOfRows(); j++){
            column[j] = GetArray()[index][j];
        }
        return column;
    }
    
    /** Returns Array full of damaged data, if method
     // "ReadFileBasic" is called
     */
    public String[][] GetDamagedArray(){
        return m_DamagedArray;
    }
    
    /**
     *
     * @return number of errors collected whilst reading the CSV file
     */
    public int GetErrors(){
        return m_Errors;
    }
    
    /** Returns String of file directory
     *
     * @return String
     */
    public String GetFileLocation(){
        return m_FileLocation;
    }
    
    /**
     *
     * @return number of columns located whilst reading the CSV file
     */
    public int GetNoOfCols(){
        return m_NoOfColumns;
    }
    /** returns how many rows are in the array
     *
     * @return
     */
    public int GetNoOfRows(){
        return m_NoOfRows;
    }
    
    /**
     * @param bv - a BobViz object
     * @return TRUE on success
     */
    public boolean SetBV( BobViz bv ) {
        m_BV = bv;
        return true;
    }
    
    public boolean setErrorsZero() {
        m_Errors = 0;
        return true;
    }
    
    /** Sets Column headers of CSV file in an array
     *
     * @param headers
     * @return boolean true if method completed successfully
     */
    private boolean setColumnHeaders( String[] headers ){
        boolean test = true;
        if ( headers.length < 1 && test==true ) {
            System.err.println( "DataStore::setColumnHeaders() ***Warning, "
                               + "set Headers length to: " + headers.length );
        }
        else if ( test == true ) {
            System.out.println( "DataStore::setColumnHeaders() number of "
                               + "Headers set to: " + headers.length );
        }
        m_ColumnHeaders = headers;
        return true;
    }
    
    /** Sets 2D array to a local variable
     *
     * @param array as a 2D array
     * @return boolean true if method completed successfully
     */
    private boolean setCompleteArray( String[][] array ){
        boolean test = true;
        if ( array[0].length < 1 && test==true ) {
            System.err.println( "DataStore::setCompleteArray() ***Warning, "
                               + "set Array length to: " + array[0].length );
        }
        if ( array[1].length < 1 && test==true ) {
            System.err.println( "DataStore::setCompleteArray() ***Warning, "
                               + "set Array Length to: " + array[0].length );
        }
        else if ( test == true ) {
            System.out.println( "DataReader::setCompleteArray() number of "
                               + "Array set to: " + array[0].length 
                               + array[1].length );
        }
        m_CompleteArray = array;
        return true;
    }
    
    /** Sets 2D array in reverse to a local variable
     *
     * @param array
     * @return boolean true if method completed successfully
     */
    private boolean setCompleteArrayBackwards( String[][] array ){
        boolean test = true;
        if ( array[0].length < 1 && test==true ) {
            System.err.println( "DataStore::setCompleteArrayBackwards() "
                               + "***Warning, set Array length to: " 
                               + array[0].length );
        }
        if ( array[1].length < 1 && test==true ) {
            System.err.println( "DataStore::setCompleteArrayBackwards() "
                               + "***Warning, set Array Length to: " 
                               + array[0].length) ;
        }
        else if ( test == true ) {
            System.out.println( "DataReader::setCompleteArrayBackwards() number"
                               + "of Array set to: " + array[0].length 
                               + array[1].length );
        }
        m_CompleteArrayBackwards = array;
        return true;
    }
    
    /** Sets 2D array full of damages data to a local variable
     *
     * @param array
     * @return boolean true if method completed successfully
     */
    private boolean setDamagedArray( String[][] array ){
        boolean test = true;
        if ( array[0].length < 1 && test==true ) {
            System.err.println( "DataStore::setDamagedArray() ***Warning, "
                               + "set Array length to: " + array[0].length );
        }
        if ( array[1].length < 1 && test==true ) {
            System.err.println( "DataStore::setDamagedArray() ***Warning, "
                               + "set Array Length to: " + array[0].length );
        }
        else if ( test == true ) {
            System.out.println("DataReader::setDamagedArray() number of columns"
                               + " set to: " + array[0].length 
                               + array[1].length);
        }
        m_DamagedArray = array;
        return true;
    }
    
    /** Sets file directory
     *
     * @param String file directory
     * @return boolean true if method completed successfully
     */
    private boolean setFileLocation( String input ){
        boolean test = true;
        
        if ( input == null && test==true ) {
            System.err.println( "FileIO::setFileLocation() ***Warning, file "
                               + "location set to: " + input );
        }
        else if ( test == true ) {
            System.out.println( "FileIO::setFileLocation() file "
                               + "location set to: " + input );
        }
        m_FileLocation = input;
        return true;
    }
    
    /** Displays a GUI for the user to select the file location
     *
     * @return boolean true if method completed successfully
     */
    public boolean SetFileLocationGUI(){
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        FileFilter ft = new FileNameExtensionFilter( "CSV Files", "CSV" );
        fc.addChoosableFileFilter( ft );
        //In response to a button click:
        int returnVal = fc.showOpenDialog(fc);
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            m_File = fc.getSelectedFile();
            setFileLocation( m_File.getPath() );
            return true;
        } else {
            return false;
        }
    }
    
    /** Sets the number of columns in the CSV File
     *
     * @param column count as integer
     * @return boolean true if method completed successfully
     */
    private boolean setNoOfColumns( int cols ){
        boolean test = true;
        
        if ( cols < 1 && test==true ) {
            System.err.println( "DataReader::setNoOfCols() ***Warning, "
                               + "set number of columns to: " + cols );
        }
        else if ( test == true ) {
            System.out.println( "DataReader::setNoOfCols() "
                               + "number of columns set to: " + cols );
        }
        m_NoOfColumns = cols;
        return true;
    }
    
    /** Adds to the current number of errors
     *
     * @param error integer
     * @return boolean true if method completed successfully
     */
    private boolean setNoOfErrors( int error ){
        boolean test = true;
        
        if ( error < 0 && test==true ) {
            System.err.println( "DataReader::setNoOfErrors() "
                               + "***Warning, increased number of errors by: " 
                               + error );
        }
        else if ( test == true ) {
            System.out.println( "DataReader::setNoOfErrors() "
                               + "number of errors increasedby: " + error );
        }
        m_Errors = m_Errors+error;
        return true;
    }
    
    /** Sets the value for number of rows in the CSV file
     *
     * @param rows as an integer
     * @return boolean true if method completed successfully
     */
    private boolean setNoOfRows( int rows ){
        boolean test = true;
        
        if ( rows < 1 && test==true ) {
            System.err.println( "DataReader::setNoOfRows() ***Warning, "
                               + "set number of rows to: " + rows );
        }
        else if ( test == true ) {
            System.out.println( "DataReader::setNoOfRows() "
                               + "number of rows set to: " + rows );
        }
        m_NoOfRows = rows;
        return true;
    }
    
    
    /** Check file type to match preset CSV format
     // If file format does not match return false
     * @return boolean true if method completed successfully
     */
    public boolean CheckFileType(){
        String filename = m_File.getName();
        String extension = filename.substring( filename.lastIndexOf( "." )
                                              + 1, filename.length() );
        if( extension.equals( "csv" ) ){
            return true;
        } else {
            return false;
        }
    }
    
    /** scans CSV file to locate the number of columns and rows
     *
     * @param location as a String
     * @return boolean true if method completed successfully and sets the 
     * number of
     * rows and columns
     */
    private boolean findNoOfColsRows( String location ){
        try {
            // buffered reader 1 to read the first line and get the column names
            BufferedReader br;
            br = new BufferedReader( new FileReader( location ) );
            String sCurrentLine = br.readLine();
            sCurrentLine = insertBlankValuesHeaders( sCurrentLine );
            
            
            String columns[] = sCurrentLine.split( "," );
            setColumnHeaders( columns );
            setNoOfColumns( columns.length );
            int rowcount=0;
            
            //  while loop to count the number of rows
            while ( ( sCurrentLine = br.readLine() ) != null ) {
                rowcount++;
            }
            setNoOfRows( rowcount );
            br.close();
        } catch ( IOException e ){
            e.printStackTrace();
        }
        return true;
    }
    
    /** Input a row from a CSV file and the method will return the string
     // back filling in missing elements with the String '000000'
     * @param String current line of file being read with potential errors
     * @return String current line with corrected errors
     */
    private String insertBlankValues( String sCurrentLine ){
        int err=0;
        ArrayList<Character> stringa  = new ArrayList<Character>();
        
        for(int i = 0; i < sCurrentLine.length(); i++ ){
            stringa.add( sCurrentLine.charAt( i ) );
        }
        
        for(int i = 0; i < stringa.size(); i++){
            if(i==stringa.size()-1 && stringa.get(i)==','){
                stringa.add( '0' );
                stringa.add( '0' );
                stringa.add( '0' );
                stringa.add( '0' );
                stringa.add( '0' );
                stringa.add( '0' );
                err++;
            } else{
                if(stringa.get( i )==',' && stringa.get( i+1 )==','){
                    stringa.add( i+1, '0' );
                    stringa.add( i+2, '0' );
                    stringa.add( i+3, '0' );
                    stringa.add( i+4, '0' );
                    stringa.add( i+5, '0' );
                    stringa.add( i+6, '0' );
                    err++;
                }
            }
        }
        String help = "";
        for ( char s : stringa ){
            help += s;
        }
        if( err>0 ){
            setNoOfErrors( err );
        }
        err = 0;
        return help;
    }
    
    /** Input a row from a CSV file and the method will return the string
     // back filling in missing elements with the String '000000'
     * @param String current line of file being read with potential errors
     * @return String current line with corrected errors
     */
    private String insertBlankValuesHeaders(String sCurrentLine){
        int err=0;
        ArrayList<Character> stringa  = new ArrayList<Character>();
        
        for(int i = 0; i < sCurrentLine.length(); i++ ){
            stringa.add( sCurrentLine.charAt( i ) );
        }
        
        for(int i = 0; i < stringa.size(); i++){
            if(i==stringa.size()-1 && stringa.get(i)==','){
                stringa.add( 'U' );
                stringa.add( 'n' );
                stringa.add( 't' );
                stringa.add( 'i' );
                stringa.add( 't' );
                stringa.add( 'l' );
                stringa.add( 'e' );
                stringa.add( 'd' );
                stringa.add( ' ' );
                stringa.add( 'C' );
                stringa.add( 'o' );
                stringa.add( 'l' );
                stringa.add( 'u' );
                stringa.add( 'm' );
                stringa.add( 'n' );
                err++;
            } else{
                if(stringa.get( i )==',' && stringa.get( i+1 )==','){
                    stringa.add( i+1, 'U' );
                    stringa.add( i+2, 'n' );
                    stringa.add( i+3, 't' );
                    stringa.add( i+4, 'i' );
                    stringa.add( i+5, 't' );
                    stringa.add( i+6, 'l' );
                    stringa.add( i+7, 'e' );
                    stringa.add( i+8, 'd' );
                    stringa.add( i+9, ' ' );
                    stringa.add( i+10, 'C' );
                    stringa.add( i+11, 'o' );
                    stringa.add( i+12, 'l' );
                    stringa.add( i+13, 'u' );
                    stringa.add( i+14, 'm' );
                    stringa.add( i+15, 'n' );
                    err++;
                }
            }
        }
        String help = "";
        for ( char s : stringa ){
            help += s;
        }
        if( err>0 ){
            setNoOfErrors( err );
        }
        return help;
    }
    
    /** Reads file populating 2D array with Data and returning Boolean
     *
     * @return boolean true if method completed successfully
     */
    public boolean ReadFile() {
        try {
            setErrorsZero();
            findNoOfColsRows( GetFileLocation() );
            BufferedReader bbrr;
            int p=0;
            
            
            
            String[][] completeArray = new String[GetNoOfCols()][GetNoOfRows()];
            String[][] completeArrayBackwards = new String[GetNoOfRows()][GetNoOfCols()];
            bbrr = new BufferedReader(new FileReader(GetFileLocation()));
            
            
            String sCurrentLine = bbrr.readLine();
            
            
            while ((sCurrentLine = bbrr.readLine()) != null) {
                sCurrentLine = insertBlankValues(sCurrentLine);
                
                String sep[] = sCurrentLine.split(",");
                for(int s = 0; s<GetNoOfCols(); s++){
                    System.out.println(sep[s]);
                    completeArray[s][p] = sep[s];
                    completeArrayBackwards[p][s] = sep[s];
                }
                p++;
            }
            setCompleteArray(completeArray);
            setCompleteArrayBackwards(completeArrayBackwards);
            bbrr.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }
    
    /** Reads File into two 2D arrays, the first with complete data, the second
     * with missing or corrupt data
     *
     * @param location of the file as a String
     * @return boolean true if method completed successfully
     */
    public boolean ReadFileBasic( String location ) {
        try {
            setErrorsZero();
            findNoOfColsRows( location );
            BufferedReader bbrr;
            int p=0;
            
            
            String[][] completeArray = new String[GetNoOfCols()][GetNoOfRows()];
            String[][] completeArrayBackwards = new String
            [GetNoOfRows()][GetNoOfCols()];
            String[][] damagedArray = new String[GetNoOfRows()][GetNoOfCols()];
            bbrr = new BufferedReader( new FileReader( location ) );
            
            
            String sCurrentLine = bbrr.readLine();
            
            
            while ((sCurrentLine = bbrr.readLine()) != null) {
                sCurrentLine = insertBlankValues( sCurrentLine );
                if( sCurrentLine.contains( "blank" ) ){
                    String sep[] = sCurrentLine.split( "," );
                    for(int s = 0; s != GetNoOfCols(); s++){
                        damagedArray[s][p] = sep[s];
                    }
                } else {
                    String sep[] = sCurrentLine.split(",");
                    for(int s = 0; s != GetNoOfCols(); s++){
                        completeArray[s][p] = sep[s];
                        completeArrayBackwards[p][s] = sep[s];
                    }
                }
                p++;
            }
            setDamagedArray( damagedArray );
            setCompleteArray( completeArray );
            setCompleteArrayBackwards( completeArrayBackwards );
            bbrr.close();
        } catch ( IOException e ){
            e.printStackTrace();
        }
        return true;
    }
    
    
    public static Dataset GetTestDataset() {
    	Dataset testData = new Dataset();
    	
    	return testData;
    }
    
    public double GetMax(String[] data) {
    	double max = 0;
    	try {
    		max = Double.parseDouble(data[0]);
    		for(String element : data) {
    			double temp = Double.parseDouble(element);
    			if(temp > max) {
    				max = temp;
    			}
    		}
    	} catch (Exception e) {
    		System.err.println("Couldn't get maximum.");
    	}
    	
    	return max;
    }
    /** Main Method used for Unit testing
     *
     * @param args
     */
    public static void main(String[] args){
        Dataset data = new Dataset();
        System.out.println( " " );
        System.out.println( "Unit test 1: null data" );
        
        data.setColumnHeaders( null );
        data.setCompleteArray( null );
        data.setCompleteArrayBackwards( null );
        data.setDamagedArray( null );
        data.setFileLocation( null );
        data.setNoOfColumns( 0 );
        data.setNoOfErrors( 0 );
        data.setNoOfRows( 0 );
        
        
        System.out.println( " " );
        System.out.println( "Unit test data 2: Large Data" );
        data.setFileLocation( "Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail Fail Fail Fail "
                             + "Fail Fail Fail Fail Fail Fail Fail" );
        // data.setColumnHeaders();
        data.setCompleteArray( null );
        data.setCompleteArrayBackwards( null );
        data.setDamagedArray( null );
        
        data.setNoOfColumns( UNIT_TEST_LARGE );
        data.setNoOfErrors( UNIT_TEST_LARGE );
        data.setNoOfRows( UNIT_TEST_LARGE );
        
        System.out.println( " " );
        System.out.println( "Unit test 3: Standard data" );
        data.setFileLocation( "C://Dekstop" );
        data.setColumnHeaders( null );
        
        data.setCompleteArrayBackwards( null );
        data.setDamagedArray( null );
        
        data.setNoOfColumns( UNIT_TEST_STANDARD );
        data.setNoOfErrors( UNIT_TEST_STANDARD );
        data.setNoOfRows( UNIT_TEST_STANDARD );
    }
    
    /** the BobViz instance **/
    private BobViz m_BV;
    private String[][] m_CompleteArray;
    private String[][] m_CompleteArrayBackwards;
    private String[][] m_DamagedArray;
    private String[] m_ColumnHeaders;
    private String m_FileLocation;
    private int m_NoOfRows;
    private int m_NoOfColumns;
    private int m_Errors;
    private File m_File;
    private final int MAXLENGTH = 150;
    private final static int UNIT_TEST_LARGE = 999999999;
    private final static int UNIT_TEST_STANDARD = 10;
    
}