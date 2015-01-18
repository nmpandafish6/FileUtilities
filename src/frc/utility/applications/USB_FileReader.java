/**
 * Note : USB_FileReader is a java class which is to be used for reading csv files
 * from a USB flashdrive attached to either a Linux Computer or to the FRC
 * Robo-Rio.
 */
package frc.utility.applications;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mallory
 */
public class USB_FileReader {
    
    private static String defaultDir = "/media";
    private static String defaultMediaDevice = "/sda1";
    private static String defaultDelimiter = "\t";
//    private ArrayList<String> defaultMediaDevices = new ArrayList<>(0);
    private File m_file = null;
    private FileReader m_fr = null;
    
    /**
     * Sets the default media path to <i>defaultDir</i>
     * @param defaultDir The default media path for the system
     */
    public static void setDefaultDir(String defaultDir){
        USB_FileReader.defaultDir = defaultDir;
    }
    
    /**
     * Sets the default media path to <i>defaultDevice</i>
     * @param defaultDevice The default media path for the system
     */
    public static void setDefaultMediaDevice(String defaultDevice){
        USB_FileReader.defaultMediaDevice = defaultDevice;
    }
    
    
    /**
     * Sets the default media path to <i>defaultDevice</i>
     * @param delimiter The default media path for the system
     */
    public static void setDefaultDelimiter(String delimiter){
        USB_FileReader.defaultDelimiter = delimiter;
    }
    
    /**
     * The default constructor to be used for the USB FileWriter.
     * @param dir The absolute file path that points to the flash drive on the machine (This does not include "/media" in the path)
     * @param fileName The name of the file to be read from. This includes the file format such as .csv or .txt.
     * @throws IOException 
     */
    public USB_FileReader(String dir, String fileName) throws IOException{
        m_file = new File(dir + "/" + fileName);
        m_fr = new FileReader(m_file);
    }
    
    /**
     * The advanced constructor for the USB FileWriter used to connect to a media device which is not one of the default media devices.
     * @param fileName The name of the file to be read from to. This includes the file format such as .csv or .txt.
     * @param defaultMediaPaths A list of default Media Paths in the "/media" directory which are always there or are not valid directories to use. Directories included in this variable will be ignored. Note : These directories are relative to the "/media" folder.
     * @throws IOException 
     */
    public USB_FileReader(String fileName, String[] defaultMediaPaths) throws IOException{
        File mediaPaths = new File(defaultDir);
        ArrayList<String> paths = new ArrayList(Arrays.asList(mediaPaths.list()));
        for(int i = 0; i < defaultMediaPaths.length; i++){
            for(int j = 0; j < paths.size(); j++){
                if(paths.get(j).equals(defaultMediaPaths[i])){
                    paths.remove(j);
                    j--;
                }
            }
        }
        m_file = new File(defaultDir + "/" + paths.get(0) + "/"+ fileName);
        m_fr = new FileReader(m_file);
        
    }
    
    /**
     * A very basic constructor used to try to connect to a file at the default media device, If the default device is not set, the address will be "/media/sda1"
     * @param fileName The name of the file to be read from. This includes the file format such as .csv or .txt.
     * @throws IOException 
     */
    public USB_FileReader(String fileName) throws IOException{
        m_file = new File(defaultDir + defaultMediaDevice + "/" + fileName);
        m_fr = new FileReader(m_file);        
    }
    
    /**
     * Breaks the file contents into a double array of lines and columns.
     * @return Returns the file contents in a double array of lines and columns. [0][2] would represent the first line and 3rd string following the default delimiter.
     * @throws IOException 
     */
    public String[][] read() throws IOException{
        char[] cbuf = new char[(int)m_file.length()];
        m_fr.read(cbuf);
        String fileContents = new String(cbuf);
        String[] lines = fileContents.split("\n");
        int numColumns = lines[0].split(defaultDelimiter).length;
        String[][] formattedFileContents = new String[lines.length][numColumns];
        for(int i = 0; i < lines.length; i++){
            formattedFileContents[i] = lines[i].split(defaultDelimiter, numColumns);
        }
        return formattedFileContents;
    }
    
    /**
     * Closes the file.
     * @throws IOException 
     */
    public void close() throws IOException{
        m_fr.close();
    }
    
    
}
