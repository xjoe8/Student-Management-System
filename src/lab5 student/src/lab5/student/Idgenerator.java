
package lab5.student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Idgenerator {
    private String filename;
    private int currentMaxID;
    private int nextID;

    
   public Idgenerator(String filename) throws FileNotFoundException{
       this.filename = filename;
       this.currentMaxID = createID(filename);
       this.nextID = currentMaxID + 1;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCurrentMaxID() {
        return currentMaxID;
    }

  
    
    public synchronized int getNextID(){
        return nextID++;
    }
    
    public void updateFromFile(){
        this.currentMaxID = createID(filename);
        this.nextID = currentMaxID + 1;
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
           reader.close();  
           
       }catch(FileNotFoundException e){
           try{
           new java.io.File(filename).createNewFile();
           System.out.println("Created new file: " + filename);
           maxID = 0;
       }catch (IOException ioException){
           System.out.println("error creating file: " + ioException.getMessage());
       }}
       catch (IOException | NumberFormatException e){
           System.out.println("Error reading Student file: " + e.getMessage());
       }
       return maxID;
   }
}


   
