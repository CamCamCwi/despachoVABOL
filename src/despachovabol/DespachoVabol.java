
package despachovabol;

import Mail.Mmail;
import Negocio.NCategoriaDoc;


public class DespachoVabol {

    public static void main(String[] args) {
        

        String subject = "reg_categoriadoc[Contrato, Documento que compromete a ambas partes de cumplir con el "
            + "contenido del mismo]";
        Mmail mail = new Mmail();
        mail.VerificarSubject(subject, "danielrobles1190@gmail.com");
        
        
       
    }
    
}
