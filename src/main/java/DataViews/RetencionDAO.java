package DataViews;

import Models.Retencion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RetencionDAO implements Serializable {

    Conexion conex;
    Retencion retencion;
    ResultSet result;
    List<Retencion> lista_Retencion;

    public RetencionDAO(Conexion conex, ResultSet result, List<Retencion> lista_Retencion) {
        this.conex = conex;
        this.result = result;
        this.lista_Retencion = lista_Retencion;
    }

    public RetencionDAO() {
        conex = new Conexion();
    }

    public RetencionDAO(Retencion retencion) {
        this.retencion = retencion;
    }

    public List<Retencion> obtener_retenciones(int idCliente) {
        lista_Retencion = new ArrayList<>();
        if (conex.isEstado()) {
            try {
                String sentencia = "Select *from Obtener_Retenciones(" + idCliente + ")";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    lista_Retencion.add(new Retencion(result.getInt("idretencion_r"),
                            result.getInt("idventa_r"),
                            result.getInt("porcentaje_r"),
                            result.getObject("fechaemision_r", LocalDate.class),
                            result.getDouble("baseimponible_r"),
                            result.getString("descripcion_r"),
                            result.getString("ejerciciofiscal_r"),
                            result.getDouble("total_r")));
                }
            } catch (SQLException ex) {
                lista_Retencion.add(new Retencion(-1, -1, 0, null, 0.0, "", "", 0.0));
            } finally {
                conex.cerrarConexion();
            }
        }
        return lista_Retencion;
    }

    public List<Retencion> obtener_Ventas(int idCliente) {
        lista_Retencion = new ArrayList<>();
        if (conex.isEstado()) {
            try {
                String sentencia ="select*from obtener_idfacturas_de_Cliente("+idCliente+")";
                result = conex.ejecutarConsulta(sentencia);
                while (result.next()) {
                    lista_Retencion.add(new Retencion(result.getInt("idventa_r")));
                }
            }catch (SQLException ex) {
                lista_Retencion.add(new Retencion(-1));
            } finally {
                conex.cerrarConexion();
            }
        }
        return lista_Retencion;
    }
    
    public int insertar_Retencion(int idVenta) {
        String sentenciaSQL = "Select Ingresar_Retencion(" + idVenta + ","
                + retencion.getPorcen_Retencion() + ",'"
                + retencion.getFecha_Emision() + "',"
                + retencion.getBaseImponible() + ",'"
                + retencion.getDescImpuesto() + "')";

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

    //Modificar/Actualizar una retencion
    public int actualizar_Retencion() {
        String sentenciaSQL = "Select actualizar_retencion(" + retencion.getId_Retencion() + ","
                + retencion.getPorcen_Retencion() + ",'"
                + retencion.getFecha_Emision() + "',"
                + retencion.getBaseImponible() + ",'"
                + retencion.getDescImpuesto() + "')";
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
}
