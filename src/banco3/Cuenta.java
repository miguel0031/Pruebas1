/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author César
 */
public class Cuenta {
    
    private long numero;
    Cliente titular;
    private float saldo;
    float interesAnual;
    Movimiento [] movimientos;
    int nmovimientos;
    
    public Cuenta(long aNumero, Cliente aTitular, float aInteresAnual) {    
        numero = aNumero;    
        titular = aTitular;    
        saldo = 0;      
        interesAnual = aInteresAnual; 
        movimientos = new Movimiento[20];
        nmovimientos=0;
    

    }
    
     public Cuenta(long aNumero) throws FileNotFoundException, IOException,ClassNotFoundException 
     {        
         FileInputStream fis = new FileInputStream(aNumero + ".cnt");
         ObjectInputStream ois = new ObjectInputStream(fis);
         numero = aNumero;       
         titular = (Cliente) ois.readObject();     
         saldo = ois.readFloat();     
         interesAnual = ois.readFloat();   
         movimientos = (Movimiento[]) ois.readObject();
         nmovimientos = ois.readInt();   
     }

     
    
    public void ingreso (float cantidad){
        
        movimientos[nmovimientos++]=new Movimiento(new Date(), 'I', cantidad, saldo += cantidad); 
        nOp++;
    }
    public void reintegro (float cantidad){
        
        movimientos[nmovimientos++]=new Movimiento(new Date(), 'R', cantidad, saldo -= cantidad); 
        nOp++;
        
    }
    boolean enRojos() { return saldo < 0; }    
    float leerSaldo() { return saldo; } 
    static int nOp = 0;
    // Operación estática auxiliar de conversión   
    static float eurosAPesetas(float euros) { return euros * 166.386f; }
    static int leerNOperaciones() { return nOp; } 
    
    public void verCuenta(){
        
        System.out.println("Numero: " + numero);
        System.out.println("Titular: " + titular.nombreCompleto() );
        for(int i=0;i<nmovimientos;i++){
            movimientos[i].verMovimiento();                   
        }
        System.out.println("Saldo: " + saldo);
        
        
    }   
    
    public float verSaldo (){
        return saldo ;
        
    }
    
    @Override    
      public void finalize() throws IOException, Throwable {    
        super.finalize();
        System.out.println("adios");
        salvar();
          
      }

      public void salvar() throws FileNotFoundException, IOException{
          FileOutputStream fos = new FileOutputStream(numero + ".cnt");  
          ObjectOutputStream oos = new ObjectOutputStream(fos);    
          oos.writeObject(titular);    
          oos.writeFloat(saldo);     
          oos.writeFloat(interesAnual);   
          oos.writeObject(movimientos);
          oos.writeInt(nmovimientos);
          oos.close(); 
      
      }
}
