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
            USB_FileReader.setDefaultDir("/media/mallory");
            USB_FileReader.setDefaultMediaDevice("/sdb1");
            USB_FileReader.setDefaultDelimiter(":");
            USB_FileWriter fw = new USB_FileWriter("Y.txt");
            USB_FileReader fr = new USB_FileReader("Y.txt");
            fw.write("x:y:z:a:b:c\n1:2:3:4:5:6");
            String[][] fileContents = fr.read();
            for(int i = 0; i < fileContents.length;i++){
                for(int j = 0; j < fileContents[i].length; j++){
                    System.out.println(fileContents[i][j]);
                }
            }
            System.out.println("[1][2] => " + fileContents[1][2]);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
