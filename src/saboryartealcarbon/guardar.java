/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saboryartealcarbon;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *funcion para guardar la factura
 * DefaultTableModel table y un integer contador
 * se guardara en la carpeta que se destino para guardar en el archivo LINKS.txt
 * y se guardara como venta+contador+.txt
 * @author simon
 */
public class guardar {
    //se inician las variables a utilizar
    String path; //variable para el path de donde se guarda el txt
    File f;//archivo donde se va a guardar los datos, solo se va a guardar los datos que en unidades sean diferentes de 0
    //variables para lectura de la tabla
    String productos,
    precio,
    un,
    tp,
    line;
    DefaultTableModel table;
    public guardar(String contador){
           //se lee el path de donde guardar
        leerdatos ld1=new leerdatos("FOLDERSAVE","data\\LINKS.txt");
        path=ld1.pathdatos+"\\venta"+contador+".txt";
       
       }
        
        
        
    
    public void savef(DefaultTableModel table){
        try{
         
        
        //se inicia el nuevo archivo, si existe se borra y se crea uno nuevo 
        //y si no excite se crea uno nuevo
       f=new File(path);
       if (f.createNewFile()==false){
           f.delete();
           f=new File(path);
       } 
       //se crea el acceso al archivo
       RandomAccessFile raf=new RandomAccessFile(f,"rw");
       
       //se lee la tabla linea a linea y se pega al archivo
       for (int i=0;i<table.getRowCount();i++){
           un=table.getValueAt(i, 2).toString();
           //verificar que las unidades pedidas sean diferentes de 0
           //if (un.isEmpty()){
             //  un="0";
           //}if (Integer.parseInt(un)!=0){
               productos=table.getValueAt(i, 0).toString();
               precio=table.getValueAt(i, 1).toString();
               tp=table.getValueAt(i, 3).toString();
               line=productos+";"+precio+";"+un+";"+tp;
               raf.writeBytes(line);
               raf.writeBytes("\r\n");
           //}
       }
       }catch(IOException ioe){
         
    }
    
}
}
