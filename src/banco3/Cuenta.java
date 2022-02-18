
package banco3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/* Clase contenedora formada entre otros argumentos por un objeto de la clase 
   Cliente y una colección de objetos de la clase Movimiento */

public class Cuenta {
    
    private long numero;
    Cliente titular; // Objeto contenido
    private float saldo;
    float interesAnual;
    LinkedList <Movimiento>  movimientos;  //Colección contenida, puesto que el 
                                           //tamaño puede ser muy dispar seha 
                                           //optado por LinkedList
    
    int nmovimientos;  // movimientos de esta cuenta
    static int nOp = 0;// movimientos de todas las cuentas
    
    public Cuenta(long aNumero, Cliente aTitular, float aInteresAnual) {    
        numero = aNumero;    
        titular = aTitular;    
        saldo = 0;      
        interesAnual = aInteresAnual; //constructor con paramentros que crea una nueva cuenta sin movimientos 
        movimientos = new LinkedList ();
        nmovimientos=0;
    

    }
    
     public Cuenta(long aNumero) throws FileNotFoundException, IOException,ClassNotFoundException 
     {   /* Constructor que crea el objeto  tomando los datos de un fichero cuyo
             nombre es el número de cuenta recibido como parámentro con la extensión
             cnt. Necesita por tanto que previamente se haya trabajado con esa cuenta 
             y esta se haya salvado. Ver método salvar. */
         
         FileInputStream fis = new FileInputStream(aNumero + ".cnt");
         ObjectInputStream ois = new ObjectInputStream(fis); 
         /*Apertura de fichero binario del que se leeran los atributos 
           incluidos el objeto cliente y la colección de movimientos.
           Deben ser leídos teniendo en cuenta el orden en el que fueron escritos.*/
         
         numero = aNumero;  // Éste no se lee del fichero sino que toma el valor del parámetro  
         
         titular = (Cliente) ois.readObject();   //Lectura del objeto contenido,
                                                 // se lee como object por lo que 
                                                 //debe realizarse el downcasting   
         saldo = ois.readFloat();     
         interesAnual = ois.readFloat();   // Lectura de atributos de tipos básicos
         
         movimientos = (LinkedList <Movimiento>) ois.readObject();// Lectura de la colección
                        // se lee como se escribió, como un único objeto
                        // también debe realizar el downcasting
         
         nmovimientos = ois.readInt(); // Lectura de atributo de tipo básico  
     }

     /* En los dos métodos que siguen, es en el único lugar de la aplicación donde
     pueden crearse movimientos, por eso éstos siempre están ligados a una cuenta
     yaque como hemos comentado fuera de las cuentas no tienen sentido.*/
    
    public void ingreso (float cantidad){
        nmovimientos++;
        // Crea un movimiento del tipo ingreso, registrando el momento en que se
        // hace y actualizando el saldo que ademas se pasa ya actualizado como 
        // parámetro al nuevo movimiento
        movimientos.add(new Movimiento(new Date(), 'I', cantidad, saldo += cantidad)); 
        nOp++;
    }
    public void reintegro (float cantidad){
        nmovimientos++;
        // Crea un movimiento del tipo reintegro, registrando el momento en que se
        // hace y actualizando el saldo que ademas se pasa ya actualizado como 
        // parámetro al nuevo movimiento
        movimientos.add(new Movimiento(new Date(), 'R', cantidad, saldo -= cantidad)); 
        nOp++;
        
    }
    boolean enRojos() { return saldo < 0; }    
    float leerSaldo() { return saldo; } 
    
   
    static int leerNOperaciones() { return nOp; } 
    
    public void verCuenta(){
        
        System.out.println("Numero: " + numero);
        System.out.println("Titular: " + titular.nombreCompleto() );
        for(Movimiento movimiento : movimientos){//recorre la LinkedList y muestra todos sus movimientos
            movimiento.verMovimiento();                   
        }
        System.out.println("Saldo: " + saldo);
        
        
    }   
    
    public float verSaldo (){
        return saldo ;
        
    }
    
   
    
      public void salvar() throws FileNotFoundException, IOException{
          /*Método para grabar en un fichero binario los datos de este objeto Cuenta (this).
            Recordad que en este tipo de ficheros los datos se graban tal y como están en memoria.
            El número de cuenta se utiliza como nombre del fichero, por lo que no se graba.
            El resto de atributos se graban según su naturaleza.
          */
          
          
          FileOutputStream fos = new FileOutputStream(numero + ".cnt");  
          ObjectOutputStream oos = new ObjectOutputStream(fos); 
          /*Apertura de fichero binario en el que se escribiran los atributos 
           incluidos el objeto cliente y la colección de movimientos.
          */
          oos.writeObject(titular);  // Escritura del objeto contenido de la clase Cliente
          
          oos.writeFloat(saldo);     
          oos.writeFloat(interesAnual);  //Escritura de atributos de tipos básicos
          
          oos.writeObject(movimientos);// Escritura de la colección de como un único objeto
          
          oos.writeInt(nmovimientos);//Escritura de atributo de tipos básico
          
          oos.close(); //Cierre del fichero
      
      }
}
