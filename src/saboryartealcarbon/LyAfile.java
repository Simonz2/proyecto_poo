/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saboryartealcarbon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;

/**
 *Funcion que lee un archivo y actualiza una variable en este archivo
 * Entradas txt que es el path al cual se quiere cambiar el path de la variable "Datos"
 * @author simon
 */
public class LyAfile {
    String line, name, link,txt,colname;//variables para la linea del archivo, y las partes de cada linea y el path que pide la funcion
    File f,//para el archivo nuevo si existe
            Links,//para el archivo donde se guardan los links o paths importantes
            tempf;
            String[] linesplit;//para separar cada linea
    
    public LyAfile(String txt,String colname){//se define el constructor de la clase
        
        try{
            f=new File(txt);//se lee el nuevo archivo
            
            if (f.exists() && (f.isFile() || f.isDirectory())){//se verifica si el nuevo archivo existe
                Links =new File("data\\LINKS.txt");//se lee el archivo links para actualizarlo
                tempf=new File("data\\tempfile.txt");//se crean un nuevo archivo temporal
                
                RandomAccessFile raf=new RandomAccessFile(Links,"rw");//se accede al archivo links
                RandomAccessFile raft=new RandomAccessFile(tempf,"rw");//se accede al archivo temporal
                
                //se lee linea a linea
                while(raf.getFilePointer()<raf.length()){
                    line=raf.readLine();//se lee la linea actual
                    
                    //se separa la linea en nombre y link
                    linesplit=line.split(";");
                    name=linesplit[0];
                    link=linesplit[1];
                    
                    //se verifica si el nombre es "DATOS"
                    if (name.equals(colname)){
                        //se crea la linea actualizada
                        line=colname+";"+txt;
                        raft.writeBytes(line);
                        raft.writeBytes(System.lineSeparator());
                        
                        
                    }else{
                        raft.writeBytes(line);
                        raft.writeBytes(System.lineSeparator());
                    }
                    
                    
                }
                raf.close();
                raft.close();
                Path l=tempf.toPath();
                Path ll=Links.toPath();
                Files.copy(l,ll,StandardCopyOption.REPLACE_EXISTING);
                tempf.delete();
               
                
                JOptionPane.showMessageDialog(new JFrame(),"Se Actualizaron los datos a "+txt);
                
                
                
            }else{
                if(colname.equals("DATOS")){
                    throw new FileNotFoundException("Archivo no encontrado");
                }else{
                throw new FileNotFoundException("Carpeta no encontrada");
            }
                   
                
            }
            
            
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(),
                        ioe);
       
        }

    }
}
