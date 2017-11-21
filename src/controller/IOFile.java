/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class IOFile {
     public static void outFile(List ob, String s){
        ObjectOutputStream outf;
         try {
             outf = new ObjectOutputStream(new FileOutputStream(s));
             for(Object o1 : ob){
            outf.writeObject(o1);
        }
             outf.close();
         } catch (IOException ex) {             
         }        
    }
    public static void inFile(List ob,String s ){
       ob.clear();
        ObjectInputStream inf;
         try {
             inf = new ObjectInputStream(new FileInputStream(s));
             Object obj = null;
           try {
               while ((obj = inf.readObject())!= null){
                   Object o1 = (Object)obj;
                   ob.add(o1);
               }  
               inf.close();
           } catch (ClassNotFoundException ex) {               
           }
         } catch (IOException ex) {             
         }        
    }
}
