
/**
 *
 * @author Joshua Taylor - A4
 */
/**
 * @author Josh Taylor
 * @class Error
 * @date 1st March
 * @brief A class designed to deal with errors and alerts the user
 */
import javax.swing.*;

    public class Error {
        /** «
        * File Error method to display a message box to alert the user the
        * file cannot be read
        * @return boolean if completed successfully
        */
        
        
        public boolean FileError(){
            JOptionPane.showMessageDialog(null, "Failed to open file,"
                    + "File is too currupted");
            return true;
        }
        /**
         * 
         * @param i integer representing how many errors exist
         * @return 
         */
        public boolean ReadError(int i){
            int reply = JOptionPane.showConfirmDialog(null,
i+" errors have been detected, would you like to remove the corrupted data?", "choose one", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION){
                return true;
            } else {
                return false;
            }
            
        }
        
    }