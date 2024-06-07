package ar.com.codo24101.log;

public class EmailLogImpl implements ILog{
    
    private String correo;

    public EmailLogImpl() {
    }

    public EmailLogImpl(String correo) {
        this.correo = correo;
    }

    
    @Override
    public void loguear(String msj) {

        System.out.println("Enviar Email a: " + correo);
        System.out.println("mensaje del Email: " + msj);
        System.out.println("Email enviado.");
    };

     public void setCorreo(String correo) {
         this.correo = correo;
     };
    


}
