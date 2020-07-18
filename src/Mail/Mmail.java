package Mail;

import Negocio.NAnuncio;
import Negocio.NCategoriaAnuncio;
import Negocio.NCategoriaDoc;
import Negocio.NComentario;
import Negocio.NSolicitudContacto;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.regex.Pattern;

public class Mmail {

    private NCategoriaDoc ncategoriadoc;
    private NComentario ncomentario;
    //CU6,CU7,CU8
    private NCategoriaAnuncio ncategoriaanuncio;
    private NAnuncio nanuncio;
    private NSolicitudContacto nsolicitudcontacto;

    private String servidor = "mail.tecnoweb.org.bo";
    private String mailLocal = "grupo02sa@tecnoweb.org.bo";
    private String usuario = "grupo02sa";
    private String contrasena = "grupo02grupo02";
    private String mailExterno;

    public Mmail() {
        this.ncategoriadoc = new NCategoriaDoc();
        this.ncomentario = new NComentario();

        //CU6,CU7,CU8
        this.ncategoriaanuncio = new NCategoriaAnuncio();
        this.nanuncio = new NAnuncio();
        this.nsolicitudcontacto = new NSolicitudContacto();
    }

    public String help() {        
        String help =  "Content-Type: text/html; charset=\"UTF-8\"\n" +
                        "\n" +
                        "<h1>HELP:  </h1>"+
                        "<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n" +
                        "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Caso de uso</th>\n" +
                        "\n" +
                        "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Método</th>\n" +
                        "\n" +
                        "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Comando</th>\n" +
                        "\n" +
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_documento[String doc_titulo,String doc_descripcion,String doc_cliente,int doc_abogado,int doc_categoriadoc]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_documento[int doc_id,String doc_titulo,String doc_descripcion,String doc_cliente,int doc_abogado,int doc_categoriadoc]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_documento[int doc_id]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar documentos</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_documento[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" + 
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_categoriadoc[String nombreCategoriaDocumento, String descripcionCategoriaDocumento]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_categoriadoc[int idCategoriaDocumento, String nombreCategoriaDocumento, String descripcionCategoriaDocumento]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_categoriadoc[int idCategoriaDocumento]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar categorias documento</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_categoriadoc[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_cliente[int Nit,String Ciudad,String Descripcion,String Direccion,String Numero del Representante,String Pagina Web,String Pais,String Razon social,String Rubro,int Telefono,String Usuario,String Contraseña]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_cliente[int Nit,String Ciudad,String Descripcion,String Direccion,String Numero del Representante,String Pagina Web,String Pais,String Razon social,String Rubro,int Telefono]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_cliente[int Nit]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar Clientes</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_cliente[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Buscar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">find_cliente[int ci]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\"> Modificar Contraseña de Cliente</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_contraseña_cliente[String Usuario, String Anterior contraseña , String nueva contraseña]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_abogado[int ci, String nombre,String apellido Paterno ,String apellido Materno,String especialidad,int celular, String fecha de nacimiento,String genero,int numero en colegio de abogados, int numero en ministerio de justicia, int numero de registro en Corte,String usuario, int contraseña]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_abogado[int ci, String nombre,String apellido Paterno ,String apellido Materno,String especialidad,int celular, String fecha de nacimiento,String genero,int numero en colegio de abogados, int numero en ministerio de justicia, int numero de registro en Corte]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_abogado[int ci]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\"> list_abogado[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Buscar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">find_abogado[int ci]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\"> Modificar Contraseña de Abogado</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_contraseña_abogado[String Usuario, String Anterior contraseña , String nueva contraseña]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_comentario[String contenidoComentario, int idDocumento, int idUsuario, int idComentario](El idComentario puede ser nulo, si lo es, ingrese un '-')</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_comentario[int idComentario, String contenidoComentario]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_comentario[int idComentario]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar comentario</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_comentario[int doc_id]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_anuncio[String tituloAnuncio, String contenidoAnuncio, int estadoAnuncio (0-1), int ciAbogado, int idCategoria]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_anuncio[int idAnuncio, String tituloAnuncio, String contenidoAnuncio, int estadoAnuncio (0-1), int ciAbogado, int idCategoria]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_anuncio[int idAnuncio]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_anuncio[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar categoria anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_catanuncio[String nombreCategoriaAnuncio, String descripcionCategoriaAnuncio]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar categoria anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_catanuncio[int idCategoriaAnuncio, String nombreCategoriaAnuncio, String descripcionCategoriaAnuncio]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar categoria anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_catanuncio[int idCategoriaAnuncio]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar categoria anuncio</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_catanuncio[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU8. Registrar solicitud de contacto</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar solicitud de contacto</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_solicitudcontacto[String nombreSolicitante, String apellidoSolicitante, int celularSolicitante, String estadoSolicitudContacto(revisado/pendiente), String emailSolicitante, String contenidoSolicitudContacto]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n"+
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU8. Registrar solicitud de contacto</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar solicitud de contacto</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_solicitudcontacto[int idSolicitudContacto, String estadoSolicitudContacto(revisado/pendiente), int ciAbogado]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "  <tr>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU8. Registrar solicitud de contacto</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar solicitud de contacto</td>\n" +
                        "\n"+
                        "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_solicitudcontacto[]</td>\n" +
                        "\n"+
                        "  </tr>\n" +
                        "\n" +
                        "\n" +
                        "</table>";

        return help;
    }

    public void sendMail(String body) {
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

                comando = "EHLO " + this.servidor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultilineSMTP(entrada));

                comando = "MAIL FROM: " + this.mailLocal + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "RCPT TO: " + this.mailExterno + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "DATA\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultilineSMTP(entrada));

                comando = "Subject: Respuesta"
                        + "\r\n" + body + "\r\n.\r\n";
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

    public void getMail() {

        String comando = "";
        int puerto = 110;
        String subject = "";
        String number = "";

        try {
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "USER " + usuario + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "PASS " + contrasena + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "LIST \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                number = getLastMail(entrada);

                comando = "RETR " + number + "\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                this.mailExterno = getEmisorMail(entrada);
                subject = getSubject(entrada);

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
            }

            salida.close();
            entrada.close();
            socket.close();
            VerificarSubject(subject);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCantidadMails() {

        String comando = "";
        int puerto = 110;
        String subject = "";
        String number = "";

        try {
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            if (socket != null && entrada != null && salida != null) {
                //System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "USER " + usuario + "\r\n";
                //System.out.print("C : " + comando);
                salida.writeBytes(comando);
                //System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "PASS " + contrasena + "\r\n";
                //System.out.print("C : " + comando);
                salida.writeBytes(comando);
                //System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "LIST \r\n";
                //System.out.print("C : " + comando);
                salida.writeBytes(comando);
                number = getLastMail(entrada);

                comando = "RETR " + number + "\n";
                //System.out.print("C : " + comando);
                salida.writeBytes(comando);
                //this.subject = getSubject(entrada);                

                comando = "QUIT\r\n";
                //System.out.print("C : " + comando);
                salida.writeBytes(comando);
                //System.out.println("S : " + entrada.readLine() + "\r\n");
            }

            salida.close();
            entrada.close();
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(number);
    }

    public String getSubject(BufferedReader in) throws IOException {
        String subject = "";
        boolean flag = false;
        while (true) {

            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                break;
            }
            String cadenaDondeBuscar = line;
            String loQueQuieroBuscar = "Subject:";
            loQueQuieroBuscar = loQueQuieroBuscar.trim();

            if (cadenaDondeBuscar.contains(loQueQuieroBuscar) || flag) {

                if (cadenaDondeBuscar.contains("To:")) {
                    flag = false;
                } else {
                    System.out.println("Encontrado");
                    subject = subject + cadenaDondeBuscar;
                    subject = subject.trim();
                    flag = true;
                }
            }

            System.out.println(line);
        }
        subject = subject.replaceAll("Subject:", "");
        subject = subject.trim();
        return subject;
    }

    public String getEmisorMail(BufferedReader in) throws IOException {
        String emisor = "";
        while (true) {

            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                break;
            }
            String cadenaDondeBuscar = line;
            String loQueQuieroBuscar = "From:";
            loQueQuieroBuscar = loQueQuieroBuscar.trim();

            if (cadenaDondeBuscar.contains(loQueQuieroBuscar)) {
                emisor = cadenaDondeBuscar.substring((cadenaDondeBuscar.indexOf("<")) + 1, (cadenaDondeBuscar.indexOf(">")));
                break;
            }
        }
        return emisor;
    }

    public String getLastMail(BufferedReader in) throws IOException {
        String number = "";
        String line = "";
        String anteriorLine = "";
        while (true) {
            anteriorLine = line;
            line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                break;
            }
            //System.out.println(line);
        }

        number = anteriorLine.substring(0, anteriorLine.indexOf(" "));
        number = number.trim();
        //System.out.println(number);
        return number;
    }

    public void VerificarSubject(String sub) {
        String errorFormato = "";
        // Solo lo uso para verificar el help
        String subjecthelp = sub.toLowerCase();
        subjecthelp = subjecthelp.trim();
        //String trimear = subjecthelp.trim();
        if (subjecthelp.equals("help")) {
            String help = this.help();
            this.sendMail(help);
        } else {
            int index1 = sub.indexOf("[");
            int index2 = sub.indexOf("]");
            if (index1 != -1 && index2 != -1) {
                if (index1 < index2) {
                    if (index1 > 0) {
                        this.OpcionesCase(sub);
                    } else {
                        errorFormato = "No se reconoce el formato indicado. Verifique que está enviando los datos dentro de un encabezado.";
                        this.sendMail(errorFormato);
                        System.out.println(errorFormato);
                    }
                } else {
                    errorFormato = "No se reconoce el formato indicado. Verifique que está utilizando los corchetes([]) de forma ordenada.";
                    this.sendMail(errorFormato);
                    System.out.println(errorFormato);
                }
            } else {
                errorFormato = "No se reconoce el formato indicado. Verifique que está utilizando los corchetes([]) para realizar la petición.";
                this.sendMail(errorFormato);
                System.out.println(errorFormato);
            }
        }
    }

    public void OpcionesCase(String sub) {
        sub = sub.trim();
        String[] partesSubject = sub.split("\\[");
        String encabezado = partesSubject[0];
        String cuerpo[] = partesSubject[1].split("\\]");
        String datos[] = null;
        if (cuerpo.length != 0) {
            datos = cuerpo[0].split("\\,");
            for (int i = 0; i < datos.length; i++) {
                datos[i] = datos[i].trim();
            }
        }
        switch (encabezado) {
            

            //CU2: Gestionar Categoria Documento
            case "reg_categoriadoc":
                this.RegistrarCategoriaDoc(datos);
                break;
            case "mod_categoriadoc":
                this.ModificarCategoriaDoc(datos);
                break;
            case "del_categoriadoc":
                this.EliminarCategoriaDoc(datos);
                break;
            case "list_categoriadoc":
                this.ListarCategoriaDoc();
                break;
            // CU3: Gestionar Cliente
            case "reg_cliente":
                this.RegistrarCliente(datos);
                break;
            case "mod_cliente":
                this.ModificarCliente(datos);
                break;
            case "del_cliente":
                this.EliminarCliente(datos);
                break;
            case "list_cliente":
                this.ListarClientes();
                break;
            case "find_cliente":
                this.BuscarCliente(datos);
                break;
            case "mod_contraseña_cliente":
                this.ModificarContraseñaCliente(datos);
                break;
            // CU4: Gestionar Abogado
            case "reg_abogado":
                this.RegistrarAbogado(datos);
                break;
            case "mod_abogado":
                this.ModificarAbogado(datos);
                break;
            case "del_abogado":
                this.EliminarAbogado(datos);
                break;
            case "list_abogado":
                this.ListarAbogados();
                break;
            case "find_abogado":
                this.BuscarCliente(datos);
                break;
            case "mod_contraseña_abogado":
                this.ModificarContraseñaCliente(datos);
                break;
            // CU5: Gestionar comentario
            case "reg_comentario":
                this.RegistrarComentario(datos);
                break;
            case "mod_comentario":
                this.ModificarComentario(datos);
                break;
            case "del_comentario":
                this.EliminarComentario(datos);
                break;
            case "list_comentario":
                this.ListarComentarios(datos);
                break;
            // CU6: Gestionar anuncio
            case "reg_anuncio":
                this.RegistrarAnuncio(datos);
                break;
            case "mod_anuncio":
                this.ModificarAnuncio(datos);
                break;
            case "del_anuncio":
                this.EliminarAnuncio(datos);
                break;
            case "list_anuncio":
                this.ListarAnuncios();
                break;
            // CU7. Gestionar Categoria de Anuncio
            case "reg_catanuncio":
                this.RegistrarCategoriaAnuncio(datos);
                break;
            case "mod_catanuncio":
                this.ModificarCategoriaAnuncio(datos);
                break;
            case "del_catanuncio":
                this.EliminarCategoriaAnuncio(datos);
                break;
            case "list_catanuncio":
                this.ListarCategoriaAnuncios();
                break;
            // CU8. Registrar solicitud de contacto 
            case "reg_solicitudcontacto":
                this.RegistrarSolicitudContacto(datos);
                break;
            case "mod_solicitudcontacto":
                this.ModificarSolicitudContacto(datos);
                break;
            case "list_solicitudcontacto":
                this.ListarSolicitudContacto();
                break;
            default:
                String s = "La petición '" + encabezado + "' es incorrecta.";
                this.sendMail(s);
                break;
        }
    }

    // Métodos de cada clase. 
    // CU2: CategoriaDoc
    // RegistrarCategoriaDoc
    public void RegistrarCategoriaDoc(String[] datos) {
        String respuesta = "";
        if (datos.length == 2) {
            respuesta = this.ncategoriadoc.RegistrarCategoriaDoc(datos[0], datos[1]);
        } else if (datos.length < 2) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ModificarCategoriaDoc

    public void ModificarCategoriaDoc(String[] datos) {
        String respuesta = "";
        if (datos.length == 3) {
            if (this.isNumericEntero(datos[0])) {
                respuesta = this.ncategoriadoc.ModificarCategoriaDoc(Integer.parseInt(datos[0]), datos[1], datos[2]);
            } else {
                respuesta = "El identificador de la categoría documento que se quiere modificar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 3) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // EliminarCategoriaDoc

    public void EliminarCategoriaDoc(String[] datos) {
        String respuesta = "";
        if (datos.length == 1) {
            if (this.isNumericEntero(datos[0])) {
                respuesta = this.ncategoriadoc.EliminarCategoriaDoc(Integer.parseInt(datos[0]));
            } else {
                respuesta = "El identificador de la categoría documento que se quiere eliminar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 1) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ListarCategoriaDoc

    public void ListarCategoriaDoc() {
        String respuesta = this.ncategoriadoc.ListarCategoriaDoc();
        this.sendMail(respuesta);
    }

    // CU3: Gestionar Cliente
    // RegistrarCliente
    public void RegistrarCliente(String[] datos) {
        System.out.println("Realizo el registrar cliente");
    }
    // ModificarCliente

    public void ModificarCliente(String[] datos) {
        System.out.println("Realizo el modificar cliente");
    }
    // EliminarCliente

    public void EliminarCliente(String[] datos) {
        System.out.println("Realizo el eliminar cliente");
    }
    // ListarClientes

    public void ListarClientes() {
        System.out.println("Realizo el listar cliente");
    }
    // BuscarCliente

    public void BuscarCliente(String[] datos) {
        System.out.println("Realizo el listar cliente");
    }
    // ModificarContraseñaCliente

    public void ModificarContraseñaCliente(String[] datos) {
        System.out.println("Realizo el modificar contraseña cliente");
    }

    // CU4: Gestionar Abogado
    // RegistrarAbogado
    public void RegistrarAbogado(String[] datos) {
        System.out.println("Realizo el registrar abogado");
    }
    // ModificarAbogado

    public void ModificarAbogado(String[] datos) {
        System.out.println("Realizo el modificar abogado");
    }
    // EliminarAbogado

    public void EliminarAbogado(String[] datos) {
        System.out.println("Realizo el eliminar abogado");
    }
    // ListarAbogados

    public void ListarAbogados() {
        System.out.println("Realizo el listar abogado");
    }
    // BuscarAbogado

    public void BuscarAbogado(String[] datos) {
        System.out.println("Realizo el buscar abogado");
    }
    // ModificarContraseñaAbogado

    public void ModificarContraseñaAbogado(String[] datos) {
        System.out.println("Realizo el modificar contraseña abogado");
    }

    // CU5: Gestionar Comentario
    // RegistrarComentario
    public void RegistrarComentario(String[] datos) {
        String respuesta = "";
        if (datos.length == 4) {
            if (this.isNumericEntero(datos[1])) {
                if (this.isNumericEntero(datos[2])) {
                    if (datos[3].equals("-") || this.isNumericEntero(datos[3])) {
                        java.util.Date fechaHoy = new Date();
                        long d = fechaHoy.getTime();
                        java.sql.Time horaAhora = new java.sql.Time(d);
                        java.sql.Date fechaAhora = new java.sql.Date(d);
                        respuesta = this.ncomentario.RegistrarComentario(fechaAhora, horaAhora, datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), datos[3]);
                    } else {
                        respuesta = "El identificador del comentario del comentario a registrar, debe ser un entero o un '-' y no: " + datos[3];
                    }
                } else {
                    respuesta = "El identificador del usuario del comentario a registrar, debe ser un entero y no: " + datos[2];
                }
            } else {
                respuesta = "El identificador del documento del comentario a registrar, debe ser un entero y no: " + datos[1];
            }

        } else if (datos.length < 4) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ModificarComentario

    public void ModificarComentario(String[] datos) {
        String respuesta = "";
        if (datos.length == 2) {
            if (this.isNumericEntero(datos[0])) {
                java.util.Date fechaHoy = new Date();
                long d = fechaHoy.getTime();
                java.sql.Time horaAhora = new java.sql.Time(d);
                java.sql.Date fechaAhora = new java.sql.Date(d);
                respuesta = this.ncomentario.ModificarComentario(Integer.parseInt(datos[0]), fechaAhora, horaAhora, datos[1]);
            } else {
                respuesta = "El identificador del comentario a modificar, debe ser un entero y no: " + datos[0];
            }
        } else if (datos.length < 2) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // EliminarComentario

    public void EliminarComentario(String[] datos) {
        String respuesta = "";
        if (datos.length == 1) {
            if (this.isNumericEntero(datos[0])) {
                respuesta = this.ncomentario.EliminarComentario(Integer.parseInt(datos[0]));
            } else {
                respuesta = "El identificador del comentario a modificar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 1) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ListarComentario

    public void ListarComentarios(String[] datos) {
        String respuesta = this.ncomentario.ListarComentarios(Integer.parseInt(datos[0]));
        this.sendMail(respuesta);
    }

    // CU6: Gestionar Anuncio
    // RegistrarAnuncio
    public void RegistrarAnuncio(String[] datos) {
        String respuesta = "";
        if (datos.length == 5) {
            if (this.isNumericEntero(datos[2])) {
                if (this.isNumericEntero(datos[3])) {
                    if (this.isNumericEntero(datos[4])) {
                        java.util.Date fechaHoy = new Date();
                        long d = fechaHoy.getTime();
                        java.sql.Time horaAhora = new java.sql.Time(d);
                        java.sql.Date fechaAhora = new java.sql.Date(d);
                        respuesta = this.nanuncio.RegistrarAnuncio(datos[0], datos[1], Integer.parseInt(datos[2]), fechaAhora, horaAhora, Integer.parseInt(datos[3]), Integer.parseInt(datos[4]));
                    } else {
                        respuesta = "El identificador del anuncio a registrar, debe ser un entero y no: " + datos[4];
                    }
                } else {
                    respuesta = "El identificador del anuncio a registrar, debe ser un entero y no: " + datos[3];
                }
            } else {
                respuesta = "El identificador del anuncio a registrar, debe ser un entero y no: " + datos[2];
            }

        } else if (datos.length < 5) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ModificarAnuncio

    public void ModificarAnuncio(String[] datos) {
        String respuesta = "";
        if (datos.length == 6) {
            if (this.isNumericEntero(datos[0])) {
                if (this.isNumericEntero(datos[3])) {
                    if (this.isNumericEntero(datos[4])) {
                        if (this.isNumericEntero(datos[5])) {
                            java.util.Date fechaHoy = new Date();
                            long d = fechaHoy.getTime();
                            java.sql.Time horaAhora = new java.sql.Time(d);
                            java.sql.Date fechaAhora = new java.sql.Date(d);
                            respuesta = this.nanuncio.ModificarAnuncio(Integer.parseInt(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3]), fechaAhora, horaAhora, Integer.parseInt(datos[4]), Integer.parseInt(datos[5]));
                        } else {
                            respuesta = "El identificador del anuncio a modificar, debe ser un entero y no: " + datos[5];
                        }
                    } else {
                        respuesta = "El identificador del anuncio a modificar, debe ser un entero y no: " + datos[4];
                    }
                } else {
                    respuesta = "El identificador del anuncio a modificar, debe ser un entero y no: " + datos[3];
                }
            } else {
                respuesta = "El identificador del anuncio a modificar, debe ser un entero y no: " + datos[0];
            }
        } else if (datos.length < 6) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // EliminarAnuncio

    public void EliminarAnuncio(String[] datos) {
        String respuesta = "";
        if (datos.length == 1) {
            if (this.isNumericEntero(datos[0])) {
                respuesta = this.nanuncio.EliminarAnuncio(Integer.parseInt(datos[0]));
            } else {
                respuesta = "El identificador del anuncio a eliminar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 1) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ListarAnuncio

    public void ListarAnuncios() {
        String respuesta = this.nanuncio.ListarAnuncio();
        this.sendMail(respuesta);
    }

    // CU7: Gestionar Categoria Anuncio
    // RegistrarCategoriaAnuncio
    public void RegistrarCategoriaAnuncio(String[] datos) {
        String respuesta = "";
        if (datos.length == 2) {
            respuesta = this.ncategoriaanuncio.RegistrarCategoriaAnuncio(datos[0], datos[1]);
        } else if (datos.length < 2) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo.";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo.";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ModificarCategoriaAnuncio

    public void ModificarCategoriaAnuncio(String[] datos) {
        String respuesta = "";
        if (datos.length == 3) {
            if (this.isNumericEntero(datos[0])) {
                respuesta = this.ncategoriaanuncio.ModificarCategoriaAnuncio(Integer.parseInt(datos[0]), datos[1], datos[2]);
            } else {
                respuesta = "El identificador de la categoría anuncio que se quiere modificar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 3) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // EliminarCategoriaAnuncio

    public void EliminarCategoriaAnuncio(String[] datos) {
        String respuesta = "";
        if (datos.length == 1) {
            if (this.isNumericEntero(datos[0])) {
                respuesta = this.ncategoriaanuncio.EliminarCategoriaAnuncio(Integer.parseInt(datos[0]));
            } else {
                respuesta = "El identificador de la categoría anuncio que se quiere eliminar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 1) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ListarCategoriaAnuncio

    public void ListarCategoriaAnuncios() {
        String respuesta = this.ncategoriaanuncio.ListarCategoriaAnuncio();
        this.sendMail(respuesta);
    }

    // CU8: Gestionar solicitud de contacto
    // RegistrarSolicitudContacto
    public void RegistrarSolicitudContacto(String[] datos) {
        String respuesta = "";
        if (datos.length == 6) {
            if (this.isNumericEntero(datos[2])) {
                java.util.Date fechaHoy = new Date();
                long d = fechaHoy.getTime();
                java.sql.Date fechaAhora = new java.sql.Date(d);
                respuesta = this.nsolicitudcontacto.RegistrarSolicitudContacto(datos[0], datos[1], fechaAhora, Integer.parseInt(datos[2]), datos[3], datos[4], datos[5]);

            } else {
                respuesta = "El identificador del anuncio a registrar, debe ser un entero y no: " + datos[2];
            }

        } else if (datos.length < 6) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ModificarSolicitudContacto

    public void ModificarSolicitudContacto(String[] datos) {
        String respuesta = "";
        if (datos.length == 3) {
            if (this.isNumericEntero(datos[0])) {
                if (this.isNumericEntero(datos[2])) {
                    respuesta = this.nsolicitudcontacto.ModificarSolicitudContacto(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]));

                } else {
                    respuesta = "El identificador del anuncio a registrar, debe ser un entero y no: " + datos[2];
                }
            } else {
                respuesta = "El identificador del anuncio a registrar, debe ser un entero y no: " + datos[0];
            }

        } else if (datos.length < 3) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail(respuesta);
    }
    // ListarSolicitudContacto

    public void ListarSolicitudContacto() {
        String respuesta = this.nsolicitudcontacto.ListarSolicitudContacto();
        this.sendMail(respuesta);
    }

    public boolean isNumericEntero(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
