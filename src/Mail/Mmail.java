package Mail;

import Negocio.NAbogado;
import Negocio.NAnuncio;
import Negocio.NCategoriaAnuncio;
import Negocio.NCategoriaDoc;
import Negocio.NCliente;
import Negocio.NComentario;
import Negocio.NDocumento;
import Negocio.NSolicitudContacto;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.Date;
import java.util.regex.Pattern;

public class Mmail {

    private NAbogado nabogado;
    private NCliente ncliente;
    private NDocumento ndocumento;
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
        this.ndocumento = new NDocumento();
        //CU6,CU7,CU8
        this.ncategoriaanuncio = new NCategoriaAnuncio();
        this.nanuncio = new NAnuncio();
        this.nsolicitudcontacto = new NSolicitudContacto();
        this.nabogado = new NAbogado();
        this.ncliente = new NCliente();
    }

    public String help() {
        String help = "Content-Type: text/html; charset=\"UTF-8\"\n"
                + "\n"
                + "<h1>HELP:  </h1>"
                + "<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Caso de uso</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Método</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">Comando</th>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_documento[String doc_descripcion ;; String doc_cliente ;; int doc_abogado ;; int doc_categoriadoc];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_documento[int doc_id ;; String doc_descripcion ;; int doc_categoriadoc];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_documento[String doc_titulo];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar documentos</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_documento[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU1. Gestionar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Buscar documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">find_documento[String doc_titulo];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_categoriadoc[String nombreCategoriaDocumento ;; String descripcionCategoriaDocumento];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_categoriadoc[int idCategoriaDocumento ;; String nombreCategoriaDocumento ;; String descripcionCategoriaDocumento];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_categoriadoc[int idCategoriaDocumento];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU2. Gestionar categoria documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar categorias documento</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_categoriadoc[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_cliente[String Nit ;; String Ciudad ;; String Descripcion ;; String Direccion ;; String Numero del Representante ;; String Pagina Web ;; String Pais ;; String Razon social ;; String Rubro ;; int Telefono ;; String Usuario ;; String Contraseña];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_cliente[String Nit ;; String Ciudad ;; String Descripcion ;; String Direccion ;; String Numero del Representante ;; String Pagina Web ;; String Pais ;; String Razon social ;; String Rubro ;; int Telefono];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_cliente[String Nit];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar Clientes</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_cliente[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Buscar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">find_cliente[String ci];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU3. Gestionar Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\"> Modificar Contraseña de Cliente</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_contrasena_cliente[String Usuario ;; String Anterior contrasena ;; String nueva contrasena];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_abogado[int ci ;; String nombre ;; String apellido Paterno ;; String apellido Materno ;; String especialidad ;; int celular ;; String fecha de nacimiento ;; String genero ;; int numero en colegio de abogados ;; int numero en ministerio de justicia ;; int numero de registro en Corte ;; String usuario ;; int contraseña];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_abogado[int ci ;; String nombre ;; String apellido Paterno ;; String apellido Materno ;; String especialidad ;; int celular ;; String fecha de nacimiento ;; String genero ;; int numero en colegio de abogados ;; int numero en ministerio de justicia ;; int numero de registro en Corte];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_abogado[int ci];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\"> list_abogado[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Buscar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">find_abogado[int ci];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU4. Gestionar abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\"> Modificar Contraseña de Abogado</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_contrasena_abogado[String Usuario ;; String Anterior contrasena ;; String nueva contrasena];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_comentario[String contenidoComentario ;; int idDocumento ;; int idUsuario ;; int idComentario];(El idComentario puede ser nulo, si lo es, ingrese un '-')</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_comentario[int idComentario ;; String contenidoComentario];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_comentario[int idComentario];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU5. Gestionar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar comentario</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_comentario[int doc_id];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_anuncio[String tituloAnuncio ;; String contenidoAnuncio ;; int estadoAnuncio (0-1) ;; int ciAbogado ;; int idCategoria];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_anuncio[int idAnuncio ;; String tituloAnuncio ;; String contenidoAnuncio ;; int estadoAnuncio (0-1) ;; int ciAbogado ;; int idCategoria];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_anuncio[int idAnuncio];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU6. Gestionar Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_anuncio[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar categoria anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_catanuncio[String nombreCategoriaAnuncio ;; String descripcionCategoriaAnuncio];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar categoria anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_catanuncio[int idCategoriaAnuncio ;; String nombreCategoriaAnuncio ;; String descripcionCategoriaAnuncio];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Eliminar categoria anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">del_catanuncio[int idCategoriaAnuncio];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU7. Gestionar Categoria de Anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar categoria anuncio</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_catanuncio[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU8. Registrar solicitud de contacto</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Registrar solicitud de contacto</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">reg_solicitudcontacto[String nombreSolicitante ;; String apellidoSolicitante ;; int celularSolicitante ;; String estadoSolicitudContacto(revisado/pendiente) ;; String emailSolicitante ;; String contenidoSolicitudContacto];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU8. Registrar solicitud de contacto</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Modificar solicitud de contacto</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">mod_solicitudcontacto[int idSolicitudContacto ;; String estadoSolicitudContacto(revisado/pendiente) ;; int ciAbogado];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">CU8. Registrar solicitud de contacto</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">Listar solicitud de contacto</td>\n"
                + "\n"
                + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">list_solicitudcontacto[];</td>\n"
                + "\n"
                + "  </tr>\n"
                + "\n"
                + "\n"
                + "</table>";

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
            cadenaDondeBuscar = cadenaDondeBuscar.trim();
            String loQueQuieroBuscar = "Subject:";
            loQueQuieroBuscar = loQueQuieroBuscar.trim();

            if (cadenaDondeBuscar.contains(loQueQuieroBuscar) || flag) {
               
                if(cadenaDondeBuscar.equalsIgnoreCase("Subject: help")){
                    flag = false;
                    subject = cadenaDondeBuscar;
                    subject = subject.trim();
                }else{
                    if (cadenaDondeBuscar.contains("];")) {
                        flag = false;
                        subject = subject + cadenaDondeBuscar.substring(0, cadenaDondeBuscar.length()-1);
                        subject = subject.trim();

                    } else {
                        System.out.println("Encontrado");
                        subject = subject + cadenaDondeBuscar;
                        subject = subject.trim();
                        flag = true;
                    }
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
            datos = cuerpo[0].split("\\;;");
            for (int i = 0; i < datos.length; i++) {
                datos[i] = datos[i].trim();
            }
        }
        switch (encabezado) {
            // CU1: Gestionar documentos
            case "reg_documento":
                this.RegistrarDocumento(datos);
                break;
            case "mod_documento":
                this.ModificarDocumento(datos);
                break;
            case "del_documento":
                this.EliminarDocumento(datos);
                break;
            case "list_documento":
                this.ListarDocumentos();
                break;
            case "find_documento":
                this.BuscarDocumento(datos);
                break;
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
            case "mod_contrasena_cliente":
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
                this.BuscarAbogado(datos);
                break;
            case "mod_contrasena_abogado":
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
            // CU8. Gestionar solicitud de contacto 
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
    // CU1: Gestionar Documento
    //Registrar Documento 
    public void RegistrarDocumento(String[] datos) {
        if (datos.length<4 || datos.length>4) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        String respuesta = "";
        respuesta += datos[0].length() < 1 ? "Descripcion de documento no valida" : "";
        respuesta += datos[1].length() < 1 ? "NIT de cliente no valido" : "";
        respuesta += !isNumericEntero(datos[2])  ? "CI de abogado no valido" : "";
        respuesta += !isNumericEntero(datos[3]) ? "Categoria de documento no valido" : "";
        if (respuesta.length() == 0) {
            GuardarMail(datos);
        } else {
            sendMail(respuesta);
        }

    }

    //Modificar Documento 
    public void ModificarDocumento(String[] datos) {
        if (datos.length<3 || datos.length>3) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        String respuesta = "";
        respuesta += !isNumericEntero(datos[0])? "El id es de formato no valido" : "" ;
        respuesta += datos[1].length() < 1 ? "Descripcion en formato no valido \n" : "";
        respuesta += !isNumericEntero(datos[2])? "Categorida de documento en formato no valido \n" : "";
        if (respuesta.length() == 0) {
            java.util.Date fechaHoy = new Date();
            long d = fechaHoy.getTime();
            java.sql.Time horaAhora = new java.sql.Time(d);
            java.sql.Date fechaAhora = new java.sql.Date(d);
            respuesta = this.ndocumento.ModificarDocumento(Integer.parseInt(datos[0]),datos[1],fechaAhora,horaAhora,Integer.parseInt(datos[2]));
        }
        sendMail(respuesta);
    }

    //Eliminar Documento 
    public void EliminarDocumento(String[] datos) {
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        String respuesta = "";
        respuesta += datos[0].length() < 1 ? "Titulo de formato no valido" : "";
        if (respuesta.length() == 0) {
            respuesta = this.ndocumento.EliminarPorTitulo(datos[0]);
        }
        sendMail(respuesta);
    }

    //Listar Documento 
    public void ListarDocumentos() {
        String respuesta = this.ndocumento.ListarDocumento();
        sendMail(respuesta);

    }

    //Buscar Documento 
    public void BuscarDocumento(String[] datos) {
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        String respuesta = "";
        respuesta += datos[0].length() < 1 ? "Titulo del documento no valido" : "";
        if (respuesta.length() == 0) {
            this.getDocFromMail(datos);
        } else {
            sendMail(respuesta);
        }

    }

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
        String respuesta = "";
        if (datos.length<12 || datos.length>12) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos[0].length() < 1 ? "NIT de formato no valid \n" : "";
        respuesta += datos[1].length() < 1 ? "Ciudad de formato no valido \n" : "";
        respuesta += datos[2].length() < 1 ? "Descripcion de formato no valido \n" : "";
        respuesta += datos[3].length() < 1 ? "Direccion de formato no valido \n" : "";
        respuesta += datos[4].length() < 1 ? "Nombre de representante no valido \n" : "";
        respuesta += datos[6].length() < 1 ? "Pais formato no valido \n" : "";
        respuesta += datos[7].length() < 1 ? "Razon social de formato no valido \n" : "";
        respuesta += datos[8].length() < 1 ? "Rubro de formato no valido \n" : "";
        respuesta += !isNumericEntero(datos[9]) ? "Telefono de formato no valido \n" : "";
        respuesta += datos[10].length() < 1 ? "Mail de formato no valido \n" : "";
        respuesta += datos[11].length() <= 8 ? "Contraseña de longitud no valida \n" : "";
        if (respuesta.length() == 0) {
            respuesta = this.ncliente.RegistrarCliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8], Integer.parseInt(datos[9]), datos[10], datos[11]);
        }
        System.out.println(respuesta);
        sendMail(respuesta);
    }
    // ModificarCliente

    public void ModificarCliente(String[] datos) {
        String respuesta = "";
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos[0].length() < 1 ? "NIT de formato no valid \n" : "";
        respuesta += datos[1].length() < 1 ? "Ciudad de formato no valido \n" : "";
        respuesta += datos[2].length() < 1 ? "Descripcion de formato no valido \n" : "";
        respuesta += datos[3].length() < 1 ? "Direccion de formato no valido \n" : "";
        respuesta += datos[4].length() < 1 ? "Nombre de representante no valido \n" : "";
        respuesta += datos[6].length() < 1 ? "Pais formato no valido \n" : "";
        respuesta += datos[7].length() < 1 ? "Razon social de formato no valido \n" : "";
        respuesta += datos[8].length() < 1 ? "Rubro de formato no valido \n" : "";
        respuesta += !isNumericEntero(datos[9]) ? "Telefono de formato no valido \n" : "";
        if (respuesta.length() == 0) {
            respuesta = this.ncliente.ModificarCliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8], Integer.parseInt(datos[9]));
        }
        System.out.println(respuesta);
        sendMail(respuesta);
    }
    // EliminarCliente

    public void EliminarCliente(String[] datos) {
        String respuesta = "";
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos[0].length() < 1 ? "NIT de formato no valid \n" : "";
        if (respuesta.length() == 0) {
            respuesta = this.ncliente.EliminarCliente(datos[0]);
        }
        System.out.println(respuesta);
        sendMail(respuesta);
    }
    // ListarClientes

    public void ListarClientes() {
        String respuesta = this.ncliente.ListarCliente();
        sendMail(respuesta);
    }
    // BuscarCliente

    public void BuscarCliente(String[] datos) {
        String respuesta = "";
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos[0].length() < 1 ? "NIT de formato no valid \n" : "";
        if (respuesta.length() == 0) {
            respuesta = this.ncliente.FindCliente(datos[0]);
        }
        System.out.println(respuesta);
        sendMail(respuesta);
    }
    // ModificarContraseñaCliente

    public void ModificarContraseñaCliente(String[] datos) {
        String respuesta = "";
        if (datos.length<3 || datos.length>3) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos[0].length() == 0 ? "Email no puede ser nulo \n" : "";
        respuesta += datos[1].length() < 8 ? "Contraseña de longitud no valida \n" : "";
        respuesta += datos[2].length() < 8 ? "Contraseña de longitud no valida " : "";

        if (respuesta.length() == 0) {
            respuesta = this.ncliente.ModificarContraseñaCliente(datos[0], datos[1], datos[2]);
        }
        sendMail(respuesta);
    }

    // CU4: Gestionar Abogado
    // RegistrarAbogado
    public void RegistrarAbogado(String[] datos) {
        String respuesta = "";
        if (datos.length<13 || datos.length>13) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += !isNumericEntero(datos[0]) ? "CI no valido \n " : "";
        respuesta += datos[1].length() <= 0 ? "El nombre no es valido \n" : "";
        respuesta += datos[2].length() <= 0 ? "El apellido paterno no es valido valido \n" : "";
        respuesta += datos[3].length() <= 0 ? "El apellido materno no es valido \n" : "";
        respuesta += datos[4].length() <= 0 ? "La especialidad no es un valor valido \n" : "";
        respuesta += !isNumericEntero(datos[5])? "El numero de celular no es valido \n" : "";
        respuesta += datos[6].length() < 10 ? "La fecha de nacimiento no es valido \n" : "";
        respuesta += datos[7].length() < 1 ? "El genero del abogado  no es valido \n" : "";
        respuesta += !isNumericEntero(datos[8])? "Nro de Colegio de abogados no valido \n" : "";
        respuesta += !isNumericEntero(datos[9]) ? "Nro de ministerio de justicia no valido \n" : "";
        respuesta += !isNumericEntero(datos[10])? "Nro de registro en corte no valido \n" : "";
        respuesta += datos[11].length() <= 0 ? "Mail no valido \n" : "";
        respuesta += datos[12].length() <= 8 ? "Contraseña de longitud no valida \n" : "";

        if (respuesta.length() == 0) {
            respuesta = this.nabogado.RegistrarAbogado(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], Integer.parseInt(datos[5]), datos[6], datos[7], Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(datos[10]), datos[11], datos[12]);
        }
        sendMail(respuesta);
    }
    // ModificarAbogado

    public void ModificarAbogado(String[] datos) {
        String respuesta = "";
        if (datos.length<11 || datos.length>11) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += !isNumericEntero(datos[0])  ? "CI no valido \n " : "";
        respuesta += datos[1].length() <= 0 ? "El nombre no es valido \n" : "";
        respuesta += datos[2].length() <= 0 ? "El apellido paterno no es valido valido \n" : "";
        respuesta += datos[3].length() <= 0 ? "El apellido materno no es valido \n" : "";
        respuesta += datos[4].length() <= 0 ? "La especialidad no es un valor valido \n" : "";
        respuesta += !isNumericEntero(datos[5]) ? "El numero de celular no es valido \n" : "";
        respuesta += datos[6].length() < 10 ? "La fecha de nacimiento no es valido \n" : "";
        respuesta += datos[7].length() < 1 ? "El genero del abogado  no es valido \n" : "";
        respuesta += !isNumericEntero(datos[8]) ? "Nro de Colegio de abogados no valido \n" : "";
        respuesta += !isNumericEntero(datos[9])? "Nro de ministerio de justicia no valido \n" : "";
        respuesta += !isNumericEntero(datos[10]) ? "Nro de registro en corte no valido \n" : "";

        if (respuesta.length() == 0) {
            respuesta = this.nabogado.ModificaAbogado(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], Integer.parseInt(datos[5]), datos[6], datos[7], Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(datos[10]));
        }

        sendMail(respuesta);
    }
    // EliminarAbogado

    public void EliminarAbogado(String[] datos) {
        String respuesta = "";
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += !isNumericEntero(datos[0])? "CI no valido \n " : "";

        if (respuesta.length() == 0) {
            respuesta = this.nabogado.EliminarAbogado(Integer.parseInt(datos[0]));
        }
        sendMail(respuesta);
    }
    // ListarAbogados

    public void ListarAbogados() {        
        String respuesta = this.nabogado.ListarAbogado();
        sendMail(respuesta);
    }
    // BuscarAbogado

    public void BuscarAbogado(String[] datos) {
        String respuesta = "";
        if (datos.length<1 || datos.length>1) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos.length<1 || datos.length>1 ? "Error cantidad de parametros incorrecta" :"" ;
        respuesta += !isNumericEntero(datos[0]) ? "El ci no es un formato valido" : "";

        if (respuesta.length() == 0) {
            respuesta = this.nabogado.FindAbogado(Integer.parseInt(datos[0]));
        }
        sendMail(respuesta);
    }
    // ModificarContraseñaAbogado

    public void ModificarContraseñaAbogado(String[] datos) {
        String respuesta = "";
        if (datos.length<3 || datos.length>3) {
            sendMail("Cantidad de parametros incorrecta");
            return;
        }
        respuesta += datos[0].length() == 0 ? "Email no puede ser nulo \n" : "";
        respuesta += datos[1].length() < 8 ? "Contraseña de longitud no valida \n" : "";
        respuesta += datos[2].length() < 8 ? "Contraseña de longitud no valida " : "";

        if (respuesta.length() == 0) {
            respuesta = this.nabogado.ModificarContraseñaAbogado(datos[0], datos[1], datos[2]);
        }
        sendMail(respuesta);
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

    public void GuardarMail(String[] datos) {
        String comando = "";
        int puerto = 110;
        String nombre = "";
        String number = "";
        String respuesta = "";
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
                nombre = getNameFromMime(entrada);

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
            }

            salida.close();
            entrada.close();
            socket.close();
            java.util.Date fechaHoy = new Date();
            long d = fechaHoy.getTime();
            java.sql.Time horaAhora = new java.sql.Time(d);
            java.sql.Date fechaAhora = new java.sql.Date(d);
            if (nombre=="") {
                sendMail("No existe un Documento");
                return;
            }
            respuesta = ndocumento.RegistrarDocumento(nombre, datos[0], fechaAhora, horaAhora, "/docs", datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Integer.parseInt(number));
            sendMail(respuesta);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDocFromMail(String[] datos) {
        String comando = "";
        int puerto = 110;
        String encodeDoc = "";
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

                number = String.valueOf(ndocumento.getIDMailPorTitulo(datos[0]));
                if ("-1".equals(number)) {
                    sendMail("No se ha encontrado el Documento");
                    return;
                }
                //verificar si existe el mail
                comando = "RETR " + number + "\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                this.mailExterno = getEmisorMail(entrada);
                encodeDoc = getEncodeMime(entrada);
                System.out.println(encodeDoc);

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
            }

            salida.close();
            entrada.close();
            socket.close();
            sendMail(encodeDoc);
            //   VerificarSubject(subject);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEncodeMime(BufferedReader in) throws IOException {
        String respuesta = "";
        String linea_anterior = "";
        String linea_actual = "";
        boolean flag = false;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                break;
            }
            linea_anterior = linea_actual;
            linea_actual = line;
            if (linea_anterior.contains("Content-Type:") && linea_actual.contains("name=") && flag == false) {
                flag = true;
            }
            if (linea_actual.contains("--") && flag) {
                respuesta += linea_anterior;
                flag = false;
            }
            if (flag) {
                respuesta += linea_anterior + "\n";
            }
        }
        return respuesta;
    }

    public String getNameFromMime(BufferedReader in) throws IOException {
        String respuesta = "";
        String linea_anterior = "";
        String linea_actual = "";
        boolean flag = false;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                break;
            }
            linea_anterior = linea_actual;
            linea_actual = line;
            if (linea_anterior.contains("Content-Type:") && linea_actual.contains("name=") && flag == false) {
                respuesta = linea_actual;
                respuesta = respuesta.replaceAll("name=", "");
                respuesta = respuesta.replaceAll("Content-Disposition: attachment; file", "");
                respuesta = respuesta.replaceAll("\"" , "");
                respuesta = respuesta.trim();
            }

        }
        return respuesta;
    }
}
