
package DataViews;

import Models.Persona;
import Models.Persona_Juridica;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Persona_JuridicaDAO extends PersonaDAO implements Serializable {

    Persona_Juridica person_Juridica;
    List<Persona_Juridica> lista_ClientesJuridicos;

    public Persona_JuridicaDAO(Persona_Juridica person_Juridica, List<Persona_Juridica> lista_ClientesJuridicos, Conexion conex, Persona persona, ResultSet result) {
        super(conex, persona, result);
        this.person_Juridica = person_Juridica;
        this.lista_ClientesJuridicos = lista_ClientesJuridicos;
    }

    public Persona_JuridicaDAO() {
        conex = new Conexion();
    }

    public Persona_JuridicaDAO(Persona_Juridica person_Juridica) {
        conex = new Conexion();
        this.person_Juridica = person_Juridica;
    }
    
    //--Ejecutando Funcion (Ingresando Persona Juridica)
    public int insertar_Cliente_Juridico() {
        String sentenciaSQL = "Select Ingresar_Cliente_Juridico"
                + "(" + person_Juridica.getId_Tipo_Idenficacion() + ",'"
                + person_Juridica.getIdentificacion() + "','"
                + person_Juridica.getDireccion() + "','"
                + person_Juridica.getTlf1() + "','"
                + person_Juridica.getTlf2() + "','"
                + person_Juridica.getCorreo() + "','"
                + person_Juridica.getRazon_Social() +"',"
                + person_Juridica.getId_tipoCliente()+")";
        //Verificamos la conexion
        if (conex.isEstado()) {
            //Una vez se asegura que la conexion este correcta.
            //Se ejecuta la sentencia ingresada.
            return conex.ejecutarProcedimiento(sentenciaSQL);
        }
        //Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;
    }

    public int actualizar_Cliente_Juridico() {
        String sentenciaSQL = "Select actualizar_persona_juridica(" 
                + person_Juridica.getId_Cliente() + ","
                + person_Juridica.getId_Tipo_Idenficacion() + ",'"
                + person_Juridica.getIdentificacion() + "','"
                + person_Juridica.getDireccion() + "','"
                + person_Juridica.getTlf1() + "','"
                + person_Juridica.getTlf2() + "','"
                + person_Juridica.getCorreo() + "','"
                + person_Juridica.getRazon_Social() + "',"
                + person_Juridica.getId_tipoCliente() + ")";

        if (conex.isEstado()) {
            //Una vez se asegura que la conexion este correcta.
            //Se ejecuta la sentencia ingresada.
            return conex.ejecutarProcedimiento(sentenciaSQL);
        }
        //Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;
    }
    
    public Persona_Juridica obtener_Cliente_Juridico() {
        Persona_Juridica p_juridica=new Persona_Juridica();
        if (conex.isEstado()) {
            try {
                String sentencia = "select*from obtener_cliente_juridico("
                        +person_Juridica.getId_Cliente()+")";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    
                     //Almacenamos en un objeto los datos personales de un 
                    //Cliente Juridico.
                     p_juridica= new Persona_Juridica(
                                                result.getString("razon_social_r"),
                                                result.getInt("idtipoidentificacion_r"),
                                                result.getString("direccion_r"),
                                                result.getString("identificacion_r"),
                                                result.getBoolean("estado_r"),
                                                result.getString("telefono1_r"),
                                                result.getString("telefono2_r"),
                                                result.getString("correo1_r"),
                                                result.getInt("idtipocliente_r"));
                     
                }
            } catch (SQLException ex) {
                p_juridica=new Persona_Juridica("",-1,"","",false,"","","",-1);
            } finally {
                conex.cerrarConexion();
            }
        }
        return p_juridica;
    }
}
