/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.utility.applications;

/**
 *
 * @author mallory
 */
public class FRCUtilityApplications {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            USB_FileWriter.setDefaultDir("/media/mallory");
            USB_FileWriter.setDefaultMediaDevice("/sdb1");
            String[] defaultPaths = {""};
            USB_FileWriter fw = new USB_FileWriter("Y.txt");
            fw.write("Hello World");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
