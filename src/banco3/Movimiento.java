

package banco3;

import java.io.Serializable;
import java.util.Date;

/* La interface Serializable no tiene métodos, pero es necesario implementarla pues el método writeObject de 
   la clase ObjetOutputStream recibe como  argumento un objeto "Serializable"
*/
public class Movimiento implements Serializable {  // los objetos de esta clase, no tienen sentido fuera los objetos de la clase contenedora Cuenta
    //Atributos de los objetos de la clase Movimiento
    private    Date fecha;   // momento de la operación 
    private char tipo;    //Podrá ser I (ingreso)o R (reintegro)        
    private float importe;    
    private float saldo;  /// saldo de la cuenta después de la operación
    
    public Movimiento(Date aFecha, char aTipo, float aImporte, float aSaldo) {  
        
        fecha = aFecha; 
        tipo = aTipo;          //Constructor que recibe como parámetro los datos de los argumentos
        importe = aImporte;       
        saldo = aSaldo;     
    } 
    
    public void verMovimiento(){ // para mostrar los datos del movimiento por pantalla
        if (tipo =='I')
            System.out.println(fecha + " Ingreso de " + importe + " saldo: " + saldo);
        else
            System.out.println(fecha + " Reintegro de " + importe + " saldo: " + saldo);
    }
    
    //Como vemos aquí, los movimientos nunca pueden modificarse ya que sólo se crean se muestran y se graban
}


