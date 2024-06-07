
package ar.com.codo24101.log.main;

import ar.com.codo24101.log.service.LogService;

public class MainLog {

    public static void main(String[] args) {

        String mensajeAEnviar = "Plan 10gb a $25k.";

        LogService logger = new LogService(1);

        logger.enviar(mensajeAEnviar);
        System.out.println("----------------");
        logger = new LogService(2);

        logger.enviar(mensajeAEnviar);
        System.out.println("----------------");
        logger = new LogService(3);

        logger.enviar(mensajeAEnviar);

    }

}
