/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.codo24101.log;

/**
 *
 * @author Asterisco Sublimados
 */
public class SMSLogImpl implements ILog{
    
    private String numero;
    
    public SMSLogImpl(String numero){
        this.numero=numero;
    };
   
    public SMSLogImpl(){
    };
    
    @Override
    public void loguear(String msj) {
        System.out.println("Enviando mensaje al n�mero: " + numero);
        System.out.println("Mensaje a enviar: " + msj);
        System.out.println("Mensaje enviado.");
    }

    public void setNumero(String numero){
        this.numero = numero;
    }
    
    
}
