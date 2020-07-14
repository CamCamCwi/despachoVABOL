
package despachovabol;

import Mail.Mmail;
import Negocio.NCategoriaDoc;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DespachoVabol {

    public static void main(String[] args) {
      /*
        Mmail mail = new Mmail();
        mail.getMail(); */
        
        Mmail mail = new Mmail();
        
        int cantMails = mail.getCantidadMails();
        
        while (true) { 
            int cantMailsNew = mail.getCantidadMails();
            if (cantMails != cantMailsNew){
                cantMails = cantMailsNew;
                mail.getMail();  
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DespachoVabol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
