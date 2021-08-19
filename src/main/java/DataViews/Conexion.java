
package DataViews;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;


public class Conexion implements Serializable{
    String consulta = "nada";
    private Connection conex;
    private java.sql.Statement st;
    private ResultSet lector;
    private boolean estado;
    private String mensaje;
    private FacesMessage.Severity tipoMensaje;

    private String url = "jdbc:postgresql://localhost:5432/erpglobal";
    private String usuario = "postgres";
    private String clave = "123456";
    private String classForName = "org.postgresql.Driver";
    
     public Conexion() {
        estado = true;
    }

    public Conexion(String user, String pass, String url) {
        usuario = user;
        clave = pass;
        this.url = url;
        estado = true;
    }

    public boolean abrirConexion() {
        try {
            if (conex == null || !(conex.isClosed())) {
                Class.forName(classForName);
                conex = DriverManager.getConnection(url, usuario, clave);
                st = conex.createStatement();
                estado = true;
            }
        } catch (ClassNotFoundException | SQLException exSQL) {
            mensaje = exSQL.getMessage();
            System.out.println(mensaje);
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            return false;
        }
        return true;
    }

    public void cerrarConexion() {
        try {
            if (conex != null && !conex.isClosed()) {
                conex.close();
                conex = null;
            }
            if (st != null && !st.isClosed()) {
                st.close();
                st = null;
            }
            if (lector != null && !lector.isClosed()) {
                lector.close();
                lector = null;
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println("ERROR: " + mensaje);
        }
    }
    
   
    
    public ResultSet ejecutarConsulta(String sql) {
        try {
            if (abrirConexion()) {
                lector = st.executeQuery(sql);
            }
        } catch (SQLException exc) {
            System.out.println(sql);
            mensaje = exc.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println(mensaje);
            cerrarConexion();
        }
        return lector;
    }
    
    public int ejecutarProcedimiento(String sql){
        int retorno = -1;
        try{
            if (abrirConexion()) {
                st.executeQuery(sql);
                mensaje = "El procedimiento se ejecutó correctamente";
                retorno=1;
                tipoMensaje = FacesMessage.SEVERITY_INFO;
            }
        } catch (SQLException exc){
             System.out.println(sql);
            mensaje = exc.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println(mensaje);
        }finally {
            cerrarConexion();
        }
        return retorno;
    }

    public int insertar(String sql) {
        int retorno = -1;
        try {
            if (abrirConexion()) {
                retorno = st.executeUpdate(sql);
                mensaje = "Se insertó correctamente";
                tipoMensaje = FacesMessage.SEVERITY_INFO;
            }
        } catch (SQLException exc) {
            System.out.println(sql);
            mensaje = exc.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println(mensaje);
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
   
     public int modificar(String sql) {
        int retorno = -1;
        try {
            if (abrirConexion()) {
                retorno = st.executeUpdate(sql);
                cerrarConexion();
                mensaje = "Se modificó correctamente : ";
                tipoMensaje = FacesMessage.SEVERITY_INFO;
            }
        } catch (SQLException exc) {
            System.out.println(sql);
            mensaje = exc.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println(mensaje);
        }
        return retorno;
    }
     public boolean isEstado() {
        return estado;
    }
}
