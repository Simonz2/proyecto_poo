/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saboryartealcarbon;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author simon
 */
public class leerdatos {
    //se inicializan las variables
    //Object[][] data;//variable objeto para 
    //recopilar todos los datos de los producots
    List<Object[]> datalist = new ArrayList<>();
    
    String txt,//path al archivo link que guarda el path 
    //al archivo DATOS.csv
            name,//nombre de lo que se busca en la linea
            pathdatos,//link o path de lo que se busca
            line,//para leer linea a linea los archivos
            paths,
            names=null;//el archivo que se quiere leer
    
    String [] linesplit; //para dividir la linea del archivo en varias partes
    File Links,//archivo que lee los links
            datos;//archivo que lee los datos de los productos
    int precio;
    public leerdatos(String names,String paths){//se declara el constructor de la clase
        try{
            if (paths.equals("data\\LINKS.txt")){
               Links =new File("data\\LINKS.txt");//se lee el archivo links para ver donde se encuentran 
        RandomAccessFile raflinks=new RandomAccessFile(Links,"rw");//se accede al archivo Links
        //se lee linea a linea el archivo Links, se separa la linea en name y path si el name es "DATOS"
        //se le da valor al path y se sale del ciclo
        while(raflinks.getFilePointer()<raflinks.length()){
            line=raflinks.readLine();
            linesplit=line.split(";");
            name=linesplit[0];
            if (name.equals(names)){//se busca el nombre del path que se requiere
                pathdatos=linesplit[1];
                break;
            }
        //raflinks.close();
        }
            }else{
                pathdatos=paths;
            }
           
        
        }catch(FileNotFoundException ioe){
            JOptionPane.showMessageDialog(new JFrame(),
                        "Archivo Links no encontrado");
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(),
                        ioe+"hay datos");
        }
           
    }
    
    public Object[][] productos(){
        Object[][] data=null;
        
        //if ("DATOS".equals(this.names)){
          
        try{
            datos=new File(pathdatos);//se lee el archivo datos
            if (!datos.exists()){
                var ld=new leerdatos("DATOS","data\\LINKS.txt");
                //System.out.println(ld.pathdatos);
                pathdatos=ld.pathdatos;
                datos=new File(pathdatos);//se lee el archivo datos
            }
                    
            BufferedReader brdatos=new BufferedReader(new FileReader(datos));
            
            //se lee linea a linea el archivo datos
            while((line=brdatos.readLine())!=null){
                
                linesplit=line.split(";");
                name=linesplit[0];
                precio=Integer.parseInt(linesplit[1]);
                //se verifica si el largo de los datos son 2 columnas o 4
                if(linesplit.length==2){//si el largo de linesplit es 2
                   Object[] data1={name,precio,String.valueOf(""),String.valueOf("")};
                    datalist.add(data1); 
                }else{//si el largo de linesplit es 4
                    Object[] data1={name,precio,linesplit[2],linesplit[3]};
                    datalist.add(data1);
                }
                
                //System.out.println(Arrays.deepToString(data1));
            }
            brdatos.close();
            
        }catch(FileNotFoundException ioe){
            JOptionPane.showMessageDialog(new JFrame(),
                        "Archivo Datos no encontrado")
                    ;
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(),
                        ioe+"no datossss");
            }
        data=new Object[datalist.size()][];
        datalist.toArray(data);
        //return data;  
        //}
        return data;  
    }
    public String[] colnames(){
        
        String[] col={"Productos","Precio","Unidades","Total parcial"};
        return col;
    }
}

