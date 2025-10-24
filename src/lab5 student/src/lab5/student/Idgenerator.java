
package lab5.student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Idgenerator {
    private String filename;
    private int genID;

   public Idgenerator(String filename) throws FileNotFoundException{
       this.filename = filename;
       this.genID = createID(filename);
}

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getGenID() {
        return genID;
    }

    public void setGenID(int genID) {
        this.genID = genID;
    }
   
   private int createID(String filename){
       int maxID = 0;
       
       try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
           String line;
           while((line = reader.readLine()) != null){
           String[] parts = line.split(",");
           int id = Integer.parseInt(parts[0]);
           if( id > maxID ) maxID = id;
           }
              
       }catch(FileNotFoundException e){
           maxID = 0;
       }
       catch (IOException | NumberFormatException e){
           System.out.println("Error reading Student file: " + e.getMessage());
       }
       return maxID+1;
   }



   
}
