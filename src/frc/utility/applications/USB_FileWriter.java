/**
 * Note : USB_FileWriter is a java class which is to be used for writing files
 * to a USB flashdrive attached to either a Linux Computer or to the FRC
 * Robo-Rio.
 */
package frc.utility.applications;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mallory
 */
public class USB_FileWriter {
    
    private static String defaultDir = "/media";
    private static String defaultMediaDevice = "/sda1";
//    private ArrayList<String> defaultMediaDevices = new ArrayList<>(0);
    private File m_file = null;
    private FileWriter m_fw = null;
    
    /**
     * Sets the default media path to <i>defaultDir</i>
     * @param defaultDir The default media path for the system
     */
    public static void setDefaultDir(String defaultDir){
        USB_FileWriter.defaultDir = defaultDir;
    }
    
    public static void setDefaultMediaDevice(String defaultDevice){
        USB_FileWriter.defaultMediaDevice = defaultDevice;
    }
    
    /**
     * The default constructor to be used for the USB FileWriter.
     * @param dir The absolute file path that points to the flash drive on the machine (This does not include "/media" in the path)
     * @param fileName The name of the file to be written to. This includes the file format such as .csv or .txt.
     * @throws IOException 
     */
    public USB_FileWriter(String dir, String fileName) throws IOException{
        m_file = new File(dir + "/" + fileName);
        if(!m_file.exists()){
            m_file.createNewFile();
        }
        m_fw = new FileWriter(m_file);
    }
    
    /**
     * The advanced constructor for the USB FileWriter used to find a media device which is not one of the default media devices.
     * @param fileName The name of the file to be written to. This includes the file format such as .csv or .txt.
     * @param defaultMediaPaths A list of default Media Paths in the "/media" directory which are always there or are not valid directories to use. Directories included in this variable will be ignored. Note : These directories are relative to the "/media" folder.
     * @throws IOException 
     */
    public USB_FileWriter(String fileName, String[] defaultMediaPaths) throws IOException{
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
        if(!m_file.exists()){
            m_file.createNewFile();
        }
        m_fw = new FileWriter(m_file);
        
    }
    
    /**
     * A very basic constructor used to try to create a file at the default media device, If the default device is not set, the address will be "/media/sda1"
     * @param fileName The name of the file to be written to. This includes the file format such as .csv or .txt.
     * @throws IOException 
     */
    public USB_FileWriter(String fileName) throws IOException{
        m_file = new File(defaultDir + defaultMediaDevice + "/" + fileName);
        if(!m_file.exists()){
            m_file.createNewFile();
        }
        m_fw = new FileWriter(m_file);        
    }
    
    /**
     * Writes a string to the file.
     * @param value The string to write to the file
     * @throws IOException 
     */
    public void write(String value) throws IOException{
        m_fw.write(value);
        m_fw.flush();
        
    }
    
    /**
     * Closes the file.
     * @throws IOException 
     */
    public void close() throws IOException{
        m_fw.close();
    }
    
    
}
