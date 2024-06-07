package ar.com.codo24101.log.service;

import ar.com.codo24101.log.DefaultLogImpl;
import ar.com.codo24101.log.EmailLogImpl;
import ar.com.codo24101.log.ILog;
import ar.com.codo24101.log.SMSLogImpl;

public class LogService {

    private ILog log;

    public LogService(int servicioActivo) {
        switch (servicioActivo) {
            case 1:
                this.log = new SMSLogImpl();
                break;
            case 2:
                this.log = new EmailLogImpl();
                break;

            default:
                this.log = new DefaultLogImpl();
                break;
        }
    }

    public void enviar(String mensaje) {
        log.loguear(mensaje);
    }

}
