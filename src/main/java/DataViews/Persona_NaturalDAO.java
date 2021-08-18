
package DataViews;

import Models.Persona;
import Models.Persona_Natural;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class Persona_NaturalDAO extends PersonaDAO implements Serializable {
    
    Persona_Natural person_Natural;
    List<Persona_Natural> lista_ClientesNaturales;

    public Persona_NaturalDAO(Persona_Natural person_Natural, List<Persona_Natural> lista_ClientesNaturales, Conexion conex, Persona persona, ResultSet result) {
        super(conex, persona, result);
        this.person_Natural = person_Natural;
        this.lista_ClientesNaturales = lista_ClientesNaturales;
    }

    public Persona_NaturalDAO() {
        conex = new Conexion();
    }

    public Persona_NaturalDAO(Persona_Natural person_Natural) {
        this.person_Natural = person_Natural;
        conex = new Conexion();
    }

    

    
    public int insertar_Cliente_Natural(){ 
        String sentenciaSQL="Select Ingresar_Cliente_Natural " //Es un Procedimiento Almacenado
                + "("+person_Natural.getId_Tipo_Idenficacion()+",'"
                +person_Natural.getIdentificacion()+"','"
                +person_Natural.getNombre1()+"','"
                +person_Natural.getNombre2()+"','"
                +person_Natural.getApellido1()+"','"
                +person_Natural.getApellidos2()+"','"
                +person_Natural.getDireccion()+"','"
                +person_Natural.getTlf1()+"','"
                +person_Natural.getTlf2()+"','"
                +person_Natural.getCorreo()+"','"
                +person_Natural.getSexo()+"','"
                +person_Natural.getGenero()+"','"
                +person_Natural.getFecha_nacimiento()+"',"
                +person_Natural.getId_tipoCliente()+")";
        

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
    
    public int actualizar_Cliente_Natural() {
        
        String sentenciaSQL = "Select actualizar_persona_natural(" + person_Natural.getId_Cliente() + ","
                + person_Natural.getId_Tipo_Idenficacion() + ",'"
                + person_Natural.getIdentificacion() + "','"
                + person_Natural.getDireccion() + "','"
                + person_Natural.getTlf1() + "','"
                + person_Natural.getTlf2() + "','"
                + person_Natural.getCorreo() + "','"
                + person_Natural.getNombre1()+ "','"
                + person_Natural.getNombre2()+ "','"
                + person_Natural.getApellido1()+ "','"
                + person_Natural.getApellidos2()+ "','"
                + person_Natural.getSexo()+ "','"
                + person_Natural.getGenero()+ "','"
                + person_Natural.getFecha_nacimiento()+ "',"
                + person_Natural.getId_tipoCliente() + ")";
    
//--Ejecutando Funcion (Actualizar Persona Natural)
//Select actualizar_persona_natural(2,1,'1250599436','Parr. Nicolas Infante Días',
//'0988152065',null,'andy2000-09@hotmail.com','Andy','Joel','Ninasunta','Rodríguez','M','Hetero','10/09/2000',1)
        
        if (conex.isEstado()) {
            //Una vez se asegura que la conexion este correcta.
            //Se ejecuta la sentencia ingresada.
            return conex.ejecutarProcedimiento(sentenciaSQL);
        }
        //Caso contrario: Se retorna -1 indicando que la conexión está
        //en estado Falso
        return -1;
        
    }
    
    public Persona_Natural obtener_Cliente_Natural() {
        Persona_Natural p_natural=new Persona_Natural();
        
        if (conex.isEstado()) {
            try {
                
                String sentencia = "select*from obtener_cliente_natural("
                        +person_Natural.getId_Cliente()+")";
                
                result = conex.ejecutarConsulta(sentencia);
                
                while (result.next()) {
                     
                    //Almacenamos en un objeto los datos personales de un 
                    //Cliente Natural.
                     p_natural= new Persona_Natural(
                        result.getString("sexo_r"),
                        result.getString("genero_r"),
                        result.getString("nombre1_r"),
                        result.getString("nombre2_r"),
                        result.getString("apellido1_r"),
                        result.getString("apellido2_r"),
                        result.getObject("fecha_nacimiento_r", LocalDate.class),
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
                
                System.out.println("Error al Obtener los datos del cliente: " 
                        + ex.getMessage());
            
            } finally {
                
                conex.cerrarConexion();
            
            }
        }
        return p_natural;
    }
    
}
