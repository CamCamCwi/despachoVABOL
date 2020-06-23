package Mail;

import Negocio.NCategoriaDoc;
import Negocio.NComentario;
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
    
    private String servidor = "mail.tecnoweb.org.bo";
    private String emisor = "grupo02sa@tecnoweb.org.bo";
    private String usuario = "grupo02sa";
    private String contrasena = "grupo02grupo02";

    public Mmail() {
        this.ncategoriadoc = new NCategoriaDoc();
    }

    private String subject = "reg_categoriadoc[Contrato, Documento que compromete a ambas partes de cumplir con el "
            + "contenido del mismo]";

    public String help() {
        String help =
                  "CU2. Gestionar categoria documento  \n"
                + "     Registrar categoria documento:  \n"
                + "             reg_categoriadoc[String nombreCategoriaDocumento, String descripcionCategoriaDocumento] \n"
                + "     Modificar categoria documento:  \n"
                + "             mod_categoriadoc[int idCategoriaDocumento, String nombreCategoriaDocumento, String descripcionCategoriaDocumento]  \n"
                + "     Eliminar categoria documento:   \n"
                + "             del_categoriadoc[int idCategoriaDocumento]    \n"
                + "     Listar categorias documento:    \n "
                + "             list_categotiadoc[]  \n "
                + "CU3. Gestionar Cliente  \n"
                + "     Registrar Cliente:  \n"
                + "             reg_cliente[int Nit,String Ciudad,String Descripcion,String Direccion,String Numero del Representante,String Pagina Web,String Pais,String Razon social,String Rubro,int Telefono,String Usuario,String Contraseña] \n"
                + "     Modificar Cliente:  \n"
                + "             mod_cliente[int Nit,String Ciudad,String Descripcion,String Direccion,String Numero del Representante,String Pagina Web,String Pais,String Razon social,String Rubro,int Telefono]  \n"
                + "     Eliminar Cliente:   \n"
                + "             del_cliente[int Nit]    \n"
                + "     Listar Clientes:    \n "
                + "             list_cliente[]  \n "
                + "     Buscar Cliente: \n"
                + "             find_cliente[int ci]    \n"
                + "     Modificar Contraseña de Cliente:    \n"
                + "             mod_contraseña_cliente[String Usuario, String Anterior contraseña , String nueva contraseña]    \n"
                + "CU4. Gestionar abogado   \n"
                + "     Registrar abogado:  \n"
                + "             reg_abogado[int ci, String nombre,String apellido Paterno ,String apellido Materno,String especialidad,int celular, String fecha de nacimiento,String genero,int numero en colegio de abogados, int numero en ministerio de justicia, int numero de registro en Corte,String usuario, int contraseña]   \n"
                + "     Modificar abogado:  \n"
                + "             mod_abogado[int ci, String nombre,String apellido Paterno ,String apellido Materno,String especialidad,int celular, String fecha de nacimiento,String genero,int numero en colegio de abogados, int numero en ministerio de justicia, int numero de registro en Corte]  \n"
                + "     Eliminar abogado:   \n"
                + "             del_abogado[int ci] \n"
                + "     Listar abogado: \n "
                + "             list_abogado[]  \n"
                + "     Buscar abogado: \n"
                + "             find_abogado[int ci] \n"
                + "     Modificar Contraseña de Abogado:    \n"
                + "             mod_contraseña_abogado[String Usuario, String Anterior contraseña , String nueva contraseña]    \n"
                + "CU5. Gestionar comentario    \n"
                + "     Registrar comentario: \n"
                + "             reg_comentario[String contenidoComentario, int idDocumento, int idUsuario, int idComentario](El comentario puede ser nulo)  \n"
                + "     Modificar comentario:    \n"
                + "             mod_comentario[int idComentario, String contenidoComentario]    \n"
                + "     Eliminar comentario: \n"
                + "             del_comentario[int idComentario]    \n"
                + "     Listar comentario:  \n"
                + "             list_comentario[]   \n"
                + "CU6. Gestionar Anuncio \n"
                + "	Registrar anuncio:   \n"
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
                + "		list_catanuncio[]   \n"
                + "CU8. Registrar solicitud de contacto \n"
                + "	Registrar solicitud de contacto:  \n"
                + "		reg_solicitudcontacto[String nombreSolicitante, String apellidoSolicitante, int celularSolicitante, String estadoSolicitudContacto(revisado/pendiente), String emailSolicitante, String contenidoSolicitudContacto, int ciAbogado(Puede ser nulo)] \n"
                + "	Modificar solicitud de contacto: 	\n"
                + "		mod_solicitudcontacto[int idSolicitudContacto, String estadoSolicitudContacto(revisado/pendiente), int ciAbogado(Puede ser nulo)] \n"
                + "	Listar solicitud de contacto:     \n"
                + "		list_solicitudcontacto[]    \n";

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
        String linea = "";
        int puerto = 110;
        String subject = "";
        String respuesta = "";
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
                this.subject = getSubject(entrada);
                System.out.println("Estoy imprimiendoooooooooooooooooooooooooooooooooooooooooooooooooo" + this.subject);

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
            }

            salida.close();
            entrada.close();
            socket.close();
            VerificarSubject(this.subject, "danielrobles1190@gmail.com");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            if (cadenaDondeBuscar.contains(loQueQuieroBuscar) || flag ) {

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
            System.out.println(line);
        }

        number = anteriorLine.substring(0, anteriorLine.indexOf(" "));
        number = number.trim();
        System.out.println(number);
        return number;
    }

    public void VerificarSubject(String sub, String receptor) {
        String errorFormato = "";
        // Solo lo uso para verificar el help
        String subjecthelp = sub.toLowerCase();
        subjecthelp = subjecthelp.trim();
        //String trimear = subjecthelp.trim();
        if (subjecthelp.equals("help")) {
            String help = this.help();
            this.sendMail(receptor, help);
        } else {
            int index1 = sub.indexOf("[");
            int index2 = sub.indexOf("]");
            if (index1 != -1 && index2 != -1) {
                if (index1 < index2) {
                    if (index1 > 0) {
                        this.OpcionesCase(sub, receptor);
                    } else {
                        errorFormato = "No se reconoce el formato indicado. Verifique que está enviando los datos dentro de un encabezado.";
                        this.sendMail(receptor, errorFormato);
                        System.out.println(errorFormato);
                    }
                } else {
                    errorFormato = "No se reconoce el formato indicado. Verifique que está utilizando los corchetes([]) de forma ordenada.";
                    this.sendMail(receptor, errorFormato);
                    System.out.println(errorFormato);
                }
            } else {
                errorFormato = "No se reconoce el formato indicado. Verifique que está utilizando los corchetes([]) para realizar la petición.";
                this.sendMail(receptor, errorFormato);
                System.out.println(errorFormato);
            }
        }
    }

    public void OpcionesCase(String sub, String receptor) {
        sub = sub.trim();
        String[] partesSubject = sub.split("\\[");
        String encabezado = partesSubject[0];
        String cuerpo[] = partesSubject[1].split("\\]");
        String datos[] = cuerpo[0].split("\\,");
        for (int i = 0; i < datos.length; i++) {
            datos[i] = datos[i].trim();
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
            case "list_categotiadoc":
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
                this.ListarComentarios();
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
                this.sendMail("danielrobles1190@gmail.com", s);
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
        this.sendMail("danielrobles1190@gmail.com", respuesta);
    }
    // ModificarCategoriaDoc

    public void ModificarCategoriaDoc(String[] datos) {
        String respuesta = "";
        if (datos.length == 3) {
            if(this.isNumericEntero(datos[0])){
               respuesta = this.ncategoriadoc.ModificarCategoriaDoc(Integer.parseInt(datos[0]), datos[1], datos[2]); 
            }else{
                respuesta = "El identificador de la categoría documento que se quiere modificar, debe ser un entero y no: " + datos[0];
            }
            
        } else if (datos.length < 3) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail("danielrobles1190@gmail.com", respuesta);
    }
    // EliminarCategoriaDoc

    public void EliminarCategoriaDoc(String[] datos) {
        String respuesta = "";
        if (datos.length == 1) {
            if(this.isNumericEntero(datos[0])){
               respuesta = this.ncategoriadoc.EliminarCategoriaDoc(Integer.parseInt(datos[0])); 
            }else{
                respuesta = "El identificador de la categoría documento que se quiere eliminar, debe ser un entero y no: " + datos[0];
            }
            
        } else if (datos.length < 1) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail("danielrobles1190@gmail.com", respuesta);
    }
    // ListarCategoriaDoc

    public void ListarCategoriaDoc() {
        String respuesta = this.ncategoriadoc.ListarCategoriaDoc();
        this.sendMail("danielrobles1190@gmail.com", respuesta);
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
            if(this.isNumericEntero(datos[1])){
                if(this.isNumericEntero(datos[2])){
                    if(this.isNumericEntero(datos[3])){
                        java.util.Date fechaHoy = new Date();
                        long d = fechaHoy.getTime();
                        java.sql.Time horaAhora = new java.sql.Time(d);
                        java.sql.Date fechaAhora = new java.sql.Date(d);
                        respuesta = this.ncomentario.RegistrarComentario(fechaAhora, horaAhora, datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[2]));
                    }else{
                        respuesta = "El identificador del comentario del comentario a registrar, debe ser un entero y no: " + datos[2];
                    }
                }else{
                    respuesta = "El identificador del usuario del comentario a registrar, debe ser un entero y no: " + datos[2];
                }
            }else{
                respuesta = "El identificador del documento del comentario a registrar, debe ser un entero y no: " + datos[1];
            }
            
        } else if (datos.length < 4) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail("danielrobles1190@gmail.com", respuesta);
    }
    // ModificarComentario

    public void ModificarComentario(String[] datos) {

    }
    // EliminarComentario

    public void EliminarComentario(String[] datos) {
        String respuesta = "";
        if (datos.length == 1) {
            if(this.isNumericEntero(datos[0])){
                respuesta = this.ncomentario.EliminarComentario(Integer.parseInt(datos[0]));
            }else{
                respuesta = "El identificador del comentario a modificar, debe ser un entero y no: " + datos[0];
            }
            
        } else if (datos.length < 1) {
            respuesta = "Los parámetros no son correctos, faltan datos para realizar la operación. Vuelva a intentarlo";
        } else {
            respuesta = "Los parámetros no son correctos, usted envió parametros de más. Vuelva a intentarlo";
        }
        System.out.println(respuesta);
        this.sendMail("danielrobles1190@gmail.com", respuesta);
    }
    // ListarComentario

    public void ListarComentarios() {
        String respuesta = this.ncomentario.ListarComentarios();
        this.sendMail("danielrobles1190@gmail.com", respuesta);
    }
    // CU6: Gestionar Anuncio
    // RegistrarAnuncio
    public void RegistrarAnuncio(String[] datos) {
        System.out.println("Realizo el registrar anuncio");
    }
    // ModificarAnuncio

    public void ModificarAnuncio(String[] datos) {
        System.out.println("Realizo el modificar anuncio");
    }
    // EliminarAnuncio

    public void EliminarAnuncio(String[] datos) {
        System.out.println("Realizo el eliminar anuncio");
    }
    // ListarAnuncio

    public void ListarAnuncios() {
        System.out.println("Realizo el listar anuncios");
    }
    // CU7: Gestionar Categoria Anuncio
    // RegistrarCategoriaAnuncio
    public void RegistrarCategoriaAnuncio(String[] datos) {
        System.out.println("Realizo el registrar CategoriaAnuncio");
    }
    // ModificarCategoriaAnuncio

    public void ModificarCategoriaAnuncio(String[] datos) {
        System.out.println("Realizo el modificar CategoriaAnuncio");
    }
    // EliminarCategoriaAnuncio

    public void EliminarCategoriaAnuncio(String[] datos) {
        System.out.println("Realizo el eliminar CategoriaAnuncio");
    }
    // ListarCategoriaAnuncio

    public void ListarCategoriaAnuncios() {
        System.out.println("Realizo el listar CategoriaAnuncio");
    }
    // CU8: Gestionar solicitud de contacto
    // RegistrarSolicitudContacto
    public void RegistrarSolicitudContacto(String[] datos) {
        System.out.println("Realizo el registrar SolicitudContacto");
    }
    // ModificarSolicitudContacto

    public void ModificarSolicitudContacto(String[] datos) {
        System.out.println("Realizo el modificar SolicitudContacto");
    }
    // ListarSolicitudContacto

    public void ListarSolicitudContacto() {
        System.out.println("Realizo el listar SolicitudContacto");
    }
    
    public boolean isNumericEntero(String cadena){
	try {
		Long.parseLong(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
}   

