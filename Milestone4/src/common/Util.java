package common;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

/**
 *
 * created by Osama 
 */
public class Util {
    
    public static boolean saveToFile(Scanner sc, String s){
        try{
            System.out.println("Enter your file path:");
            String path = sc.next();
            PrintWriter writer = new PrintWriter(path);
            writer.write(s);
            writer.close();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean saveToFile(String path){
        return false;
    }
    public static String loadStringFromFile(String path) throws IOException{
        
        Path p = Paths.get(path); 
        List<String> lines = Files.readAllLines(p, null);
        if(lines.isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        for(String l: lines)
            builder.append(l);
        return builder.toString();
        
    }
    
    public static Object toObject (String className) {
        try{
            Class c= Class.forName(className);
            return c.newInstance();
        }catch(Exception ex){
            return null;
        }
    }
    
    /**
     * 
     * @param sc
     * @param o
     * @return
     */
    public static boolean serializeToFile(Scanner sc, Object o){
        try{
            System.out.println("Enter your file path:");
            String path = sc.next();
            FileOutputStream fileOut =
            new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     *  to load the game 
     * @param file
     * @return
     */
    public static Object deserializeFromFile(String file){
        try
        {
         FileInputStream fileIn = new FileInputStream(file);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         return   in.readObject();
        
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
