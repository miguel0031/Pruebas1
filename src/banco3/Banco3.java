
package banco3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;


public class Banco3 {

    
    public static void main(String[] args) throws IOException {
     /*  El siguiente código comentado se uso para crear un par de cuentas e introducir movimientos en ellas 
       para finalmente grabarlas y poder después trabajar con ellas.
        
        
        
       Cliente unCliente = new Cliente("Jesús", "López", "Libertad", "Villamuriel",new Date(10,12,1986) ); 
       Cuenta unaCuenta = new Cuenta(654321l, unCliente, 0.25f);
       
       unaCuenta.ingreso(2345);
       unaCuenta.reintegro(321);
         
       unaCuenta.ingreso(145);
       unaCuenta.reintegro(320);
         
       unaCuenta.ingreso(1550);
       unaCuenta.reintegro(210);
       
       
       System.out.println(unCliente.nombreCompleto() + " tiene la cuenta: ");
       unaCuenta.verCuenta();
       Cliente otroCliente = new Cliente("Ana", "López", "Libertad", "Villamuriel",new Date(10,12,2001) ); 
       Cuenta otraCuenta = new Cuenta(654322l, otroCliente, 0.25f);
       
       otraCuenta.ingreso(3000);
       otraCuenta.reintegro(321);
         
       otraCuenta.ingreso(145);
      
         
       
       otraCuenta.reintegro(210);
       
       otraCuenta.verCuenta();
       
        System.out.println("operaciones de Jesús "+unaCuenta.nmovimientos);
        System.out.println("operaciones de Ana "+otraCuenta.nmovimientos);
        System.out.println("operaciones totales "+Cuenta.nOp);
       
        unaCuenta.salvar();
        otraCuenta.salvar();
       
 */   
        
        
        
        // Simulación de cajero
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//entrada estandar de java
        long nc;
        float mi; 
        try {  
            System.out.println("Introduzca núm. de cuenta: ");  
            nc = Long.parseLong(br.readLine());  //linea proveniente del teclado es convertida a entero largo
            System.out.println("Introduzca importe a retirar:");   
            mi = Float.parseFloat(br.readLine()); //linea proveniente del teclado es convertida a float
        } 
        catch(Exception e) {    
            System.out.println("Error al leer datos");   
            return;  // Si los datos no son correctos el programa termina
        }
        Cuenta c; 
        try {   
            c = new Cuenta(nc);// Constructor que lee del fichero los datos de la cuenta
        }
        catch(Exception e) {   
            System.out.println("Imposible recuperar cuenta");    
            return;   // Si no existe el fichero con los datos de la cuenta el programa termina
        }
        if (c.leerSaldo() < mi) //  No se permite sacar más dinero del que hay en la cuenta  
            System.out.println("Saldo insuficiente");
        else     
            c.reintegro(mi); 
       
        c.verCuenta();  // se nuestra la cuenta con o sin el último reintegro, según se haya realizado o no.
        
        c.salvar();   //Al final se graba de nuevo la cuenta 
        
    
    
}
        
}
