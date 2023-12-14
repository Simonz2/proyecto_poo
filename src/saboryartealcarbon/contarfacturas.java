/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saboryartealcarbon;
import java.io.File;
import java.io.FileFilter;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author simon
 */
public class contarfacturas {
    int contador=0;//contador de las ventas del dia
    String path,//path del folder donde guarda las ventas
           pathD,//path donde se guardan los datos
           pathvt;//path para guardar archivo ventatotal;
    File[] files;
    String line;
    String[] linesplit;
    int i;//donde agregar las unidades y el total parcial
    
    public contarfacturas(){
        //se lee el path a la carpeta donde estan guardados los archivos
        leerdatos ld1=new leerdatos("FOLDERSAVE","data\\LINKS.txt");
        path=ld1.pathdatos;
        
        //se accede a la carpeta
        FileFilter txtff=new FileFilter(){
            public boolean accept(File file){
                if (file.getName().endsWith(".txt") &&
                        !file.getName().endsWith("total.txt") && 
                        !file.getName().endsWith("LINKS.txt") && 
                         file.getName().contains("venta")){
                    return true;
                }
                return false;
            }   
        };
        File folder=new File(path);
        files=folder.listFiles(txtff);
        contador=files.length;
    }
    //metodo para leer todas las facturas y 
    public void cerrardia(int len){
        float [] und=new float[len];
        float [] tp=new float[len];
        try{
            //se lee archivo por archivo de las ventas del dia
            for (File f:files ){
               i=0;
                File fr=new File(f.getAbsolutePath());//se lee el archivo
                RandomAccessFile raf=new RandomAccessFile(fr,"rw");//se accede al archivo
                //se lee linea a linea
                while(raf.getFilePointer()<raf.length()){
                    //se lee la linea
                    line=raf.readLine();
                    //se separa la linea en partes
                    linesplit=line.split(";");
                    
                    
                    
                    if (linesplit.length==4){
                        und[i]=und[i]+Float.parseFloat(linesplit[2]);//se añade al cotador de las unidades del producto
                       
                        tp[i]=tp[i]+Float.parseFloat(linesplit[3]);//se añade al cotador de las unidades del producto
                    }else{
                        und[i]=und[i]+0;//se añade al cotador de las unidades del producto
                        
                        tp[i]=tp[i]+0;//se añade al cotador de las unidades del producto
                    }
                    i++;
                }
            }
            //se lee el archvio de datos, y se accede a este archvio
            leerdatos lddatos=new leerdatos("DATOS","data\\LINKS.txt");
            
            pathD=lddatos.pathdatos;
            File fdatos=new File(pathD);
            RandomAccessFile rbfdatos=new RandomAccessFile(fdatos,"rw");
            
            //se hace un nuevo archivo de ventatotal.txt y se accede a este archivo
            pathvt=path+"\\ventatotal.txt";
            File ventatotal=new File(pathvt);
            //verificar si existe y si existe eliminarlo
            if (ventatotal.exists()){
                ventatotal.delete();
                ventatotal=new File(pathvt);
            }
            RandomAccessFile rbfvt=new RandomAccessFile(ventatotal,"rw");
            //se lee linea a linea el archivo Datos
            i=0;
            while(rbfdatos.getFilePointer()<rbfdatos.length()){
                //se lee la linea
                line=rbfdatos.readLine();
                
                //se arma la nueva linea para escribir en el archivo ventas
                line=line+";"+und[i]+";"+tp[i];
                rbfvt.writeBytes(line);
                rbfvt.writeBytes(System.lineSeparator());
                i++;
            }
        
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(),
                        ioe);
        }
    }
    
}
