package Mail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Mmail {

    private String servidor = "mail.tecnoweb.org.bo";
    private String emisor = "grupo02sa@tecnoweb.org.bo";
    private String usuario="grupo02sa";
    private String contrasena="grupo02grupo02";

    private String subject = "reg_categoriadoc[Contrato, Documento que compromete a ambas partes de cumplir con el "
            + "contenido del mismo]";

    public String help() {
        String help = "CU2: Gestionar categoría de documenton \n"
                + "   RegistrarCategoriaDoc\n"
                + "     reg_categoriadoc[String nombreCategoriaDocumento, String descripcionCategoriaDocumento]\n"
                + "   ModificarCategoriaDoc\n"
                + "     mod_categoriadoc[int idCategoriaDocumento, String nombreCategoriaDocumento, String descripcionCategoriaDocumento]\n"
                + "   EliminarCategoriaDoc\n"
                + "     del_categoriadoc[int idCategoriaDocumento]\n"
                + "   ListarCategoriaDoc\n"
                + "     list_categotiadoc[]\n"
                + "CU3: Gestionar Cliente  \n"
                + "   Registrar Cliente : \n"
                + "	reg_cliente[int Nit,String Ciudad,String Descripcion,String Direccion,String Numero del Representante,String Pagina Web,String Pais,String Razon social,String Rubro,int Telefono,String Usuario,String Contraseña] \n"
                + "   Modificar Cliente \n"
                + "	mod_cliente[int Nit,String Ciudad,String Descripcion,String Direccion,String Numero del Representante,String Pagina Web,String Pais,String Razon social,String Rubro,int Telefono] \n"
                + "   Eliminar Cliente : \n"
                + "	del_cliente[int Nit] \n"
                + "   Listar Clientes: \n "
                + "	list_cliente[] \n "
                + "   Buscar Cliente : \n"
                + " 	find_cliente[int ci] \n"
                + "   Modificar Contraseña de Cliente : \n"
                + "	mod_contraseña_cliente[String Usuario, String Anterior contraseña , String nueva contraseña] \n"
                + "CU4:Gestionar Abogado \n"
                + "   Registrar Abogado : \n"
                + " 	reg_abogado[int ci, String nombre,String apellido Paterno ,String apellido Materno,String especialidad,int celular, String fecha de nacimiento,String genero,int numero en colegio de abogados, int numero en ministerio de justicia, int numero de registro en Corte,String usuario, int contraseña] \n"
                + "   Modificar Abogado \n"
                + "	mod_abogado[int ci, String nombre,String apellido Paterno ,String apellido Materno,String especialidad,int celular, String fecha de nacimiento,String genero,int numero en colegio de abogados, int numero en ministerio de justicia, int numero de registro en Corte] \n"
                + "   Eliminar Abogado : \n"
                + " 	del_abogado[int ci] \n"
                + "   Listar Abogado: \n "
                + "	list_abogado[] \n"
                + "   Buscar Abogado : \n"
                + "	find_abogado[int ci] \n"
                + "   Modificar Contraseña de Abogado : \n"
                + "	mod_contraseña_Abogado(String Usuario, String Anterior contraseña , String nueva contraseña] \n"
                + "CU5: Gestionar comentario\n"
                + "   RegistrarComentario\n"
                + "     reg_comentario[String contenidoComentario, int idDocumento, int idUsuario, int idComentario](El comentario puede ser nulo)\n"
                + "   ModificarComentario\n"
                + "     mod_comentario[int idComentario, String contenidoComentario]\n"
                + "   EliminarComentario\n"
                + "     del_comentario[int idComentario]\n"
                + "   ListarComentario\n"
                + "     list_comentario[]\n"
                + "CU6. Gestionar Anuncio \n"
                + "	Registrar anuncio:  \n"
                + "		reg_anuncio[String tituloAnuncio, String contenidoAnuncio, int estadoAnuncio (0-1), int ciAbogado, int idCategoria] \n"
                + "	Modificar anuncio: 	\n"
                + "		mod_anuncio[int idAnuncio, String tituloAnuncio, String contenidoAnuncio, int estadoAnuncio (0-1), int ciAbogado, int idCategoria] \n"
                + "	Eliminar anuncio:   \n"
                + "		del_anuncio[int idAnuncio]\n"
                + "	Listar anuncio:     \n"
                + "		list_anuncio[]\n"
                + "CU7. Gestionar Categoria de Anuncio \n"
                + "	Registrar categoria anuncio:  \n"
                + "		reg_catanuncio[String nombreCategoriaAnuncio, String descripcionCategoriaAnuncio] \n"
                + "	Modificar categoria anuncio: 	\n"
                + "		mod_catanuncio[int idCategoriaAnuncio, String nombreCategoriaAnuncio, String descripcionCategoriaAnuncio] \n"
                + "	Eliminar categoria anuncio:   \n"
                + "		del_catanuncio[int idCategoriaAnuncio]\n"
                + "	Listar categoria anuncio:     \n"
                + "		list_catanuncio[]\n"
                + "CU8. Registrar solicitud de contacto \n"
                + "	Registrar solicitud de contacto:  \n"
                + "		reg_solicitudcontacto[String nombreSolicitante, String apellidoSolicitante, int celularSolicitante, String estadoSolicitudContacto(revisado/pendiente), String emailSolicitante, String contenidoSolicitudContacto, int ciAbogado(Puede ser nulo)] \n"
                + "	Modificar solicitud de contacto: 	\n"
                + "		mod_solicitudcontacto[int idSolicitudContacto, String estadoSolicitudContacto(revisado/pendiente), int ciAbogado(Puede ser nulo)] \n"
                + "	Listar solicitud de contacto:     \n"
                + "		list_solicitudcontacto[]\n";

        return help;
    }

    public void sendMail(String receptor, String body) {
        String line;
        String comando = "";
        int puerto = 25;

        try {
            Socket socket = new Socket(servidor, puerto);

            BufferedReader entrada
                    = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            DataOutputStream salida
                    = new DataOutputStream(socket.getOutputStream());

            if (socket != null && entrada != null && salida != null) {

                System.out.println("S : " + entrada.readLine());

                comando = "EHLO " + servidor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultilineSMTP(entrada));

                comando = "MAIL FROM: " + emisor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "RCPT TO: " + receptor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "DATA\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultilineSMTP(entrada));

                comando = "Subject: Respuesta"
                        + "\r\n" + body + ".\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

            }

            salida.close();
            entrada.close();
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : No se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMultilineSMTP(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                break;
            }
            lines = lines + "\n" + line;
        }
        return lines;
    }
    
    public void getMail(){
        
        String comando="";
        String linea="";
        int puerto=110;
        String subject = "";
        String respuesta = "";
        String number = "";
   
        try{
            Socket socket=new Socket(servidor,puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream (socket.getOutputStream());            
            if( socket != null && entrada != null && salida != null ){
                System.out.println("S : "+entrada.readLine()+"\r\n");
       
                comando="USER "+usuario+"\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );               
                System.out.println("S : "+entrada.readLine()+"\r\n");
               
                comando="PASS "+contrasena+"\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );               
                System.out.println("S : "+entrada.readLine()+"\r\n");
               
                comando="LIST \r\n";
                System.out.print("C : "+comando);   
                salida.writeBytes( comando );               
                number = getLastMail(entrada);
               
                comando="RETR "+ number +"\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando ); 
                subject = getSubject(entrada);
                
                
                comando="QUIT\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );               
                System.out.println("S : "+entrada.readLine()+"\r\n");
            }
            
            salida.close();
            entrada.close();
            socket.close();
          
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public String getSubject(BufferedReader in) throws IOException{
        String subject = "";
        
        while (true){
            String line = in.readLine();
            if (line == null){
               throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")){
                break;
            }
            String cadenaDondeBuscar = line;
            String loQueQuieroBuscar = "Subject:";
            String[] palabras = loQueQuieroBuscar.split("\\s+");
            for (String palabra : palabras) {
                  if (cadenaDondeBuscar.contains(palabra)) {
                      System.out.println("Encontrado");
                      subject = line.substring(8);
                      subject = subject.trim();
                  }
            }
            
            System.out.println(line);
        }       
        return subject;
    }
    
    public String getLastMail(BufferedReader in) throws IOException{ 
        String number = "";
        String line = "";
        String anteriorLine = "";
        while (true){
            anteriorLine = line;
            line = in.readLine();
            if (line == null){
               throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")){
                break;
            }
            System.out.println(line);
        }       
        
        number = anteriorLine.substring(0, anteriorLine.indexOf(" "));
        number = number.trim();
        System.out.println(number);
        return number;  
    }

    // Métodos de cada clase. 
    // CU1: Gestionar Comentario /n
    // RegistrarComentario
    // ModificarComentario
    // EliminarComentario
    // ListarComentarios
    // Anuncio
    // RegistrarAnuncio
    // ModificarAnuncio
    // EliminarAnuncio
    // ListarAnuncios
    // CategoriaAnuncio
    // RegistrarCategoriaAnuncio
    // ModificarCategoriaAnuncio
    // EliminarCategoriaAnuncio
    // ListarCategoriaAnuncio
    // CategoriaDoc
    // RegistrarCategoriaDoc
    // ModificarCategoriaDoc
    // EliminarCategoriaDoc
    // ListarCategoriaDoc
    // SolicitudContacto
    // RegistrarSolicitudContacto
    // ModificarSolicitudContacto
    // ListarSolicitudContacto
    //  Abogado
    // RegistrarAbogado
    // ModificarAbogado
    // EliminarAbogado
    // ListarAbogados
    // BuscarAbogado
    // Cliente
    // RegistrarCliente
    // ModificarCliente
    // EliminarCliente
    // ListarClientes
    // BuscarCliente
    // Documento
    // RegistrarDocumento
    // ModificarDocumento
    // EliminarDocumento
    // ListarDocumentos
    // BuscarDocumento
}
