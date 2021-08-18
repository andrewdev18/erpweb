
package DataViews;

import Models.Plan_Pago;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plan_PagoDAO implements Serializable {

    List<Plan_Pago> lista_facturaspendientes;
    Conexion conex;
    Plan_Pago plan_pago;
    ResultSet result;

    //Constructor sin parametros, para iniciar una conexion.
    public Plan_PagoDAO() {
        conex = new Conexion();
    }

    //Constructor que recibe el objeto Plan_Pago y abre una nueva conexion.
    public Plan_PagoDAO(Plan_Pago planPago) {
        conex = new Conexion();
        this.plan_pago = planPago;
    }

    public Plan_PagoDAO(Conexion conex, Plan_Pago planPago, ResultSet result) {
        this.conex = conex;
        this.plan_pago = planPago;
        this.result = result;
    }

    //Método para en enlistar las facturas pendientes.
    public List<Plan_Pago> obtenerFacturasPendientes() {
        lista_facturaspendientes = new ArrayList<>();
        
        //Verificamos la conexion
        if (conex.isEstado()) {
            try {
                /* Se obtiene una TABLA con todas las facturas que se pagaron a
                credito, con sus respectivo datos calculados como la 
                fecha de vencimiento =fecha actual+diascredito */
                String sentencia = "Select*from Obtener_Facturas_Pendientes()";
                result = conex.ejecutarConsulta(sentencia);

                //Recorremos la TABLA retornada y la almacenamos en la lista.
                while (result.next()) {

                    lista_facturaspendientes.add(
                            new Plan_Pago(result.getObject("fechacredito_r", LocalDate.class),
                                    result.getInt("diasdecredito_r"),
                                    result.getObject("fechavencimiento_r", LocalDate.class),
                                    result.getString("nombre_clientes_r"),
                                    result.getInt("id_venta_r"),
                                    result.getDouble("valortotalfactura_r"),
                                    result.getDouble("valorpendiente_r"),
                                    result.getObject("fechapagofinal_r", LocalDate.class),
                                    result.getString("descripcionestado_r"),
                                    result.getInt("diasmora_r")));

                }
            } catch (SQLException ex) {
                /*Enviamos su respectivo mensaje de error a su ves una lista 
                    con valores incorrectos.*/
                System.out.println(ex.getMessage());
                lista_facturaspendientes.add(
                            new Plan_Pago(null,-1,null,null,-1,-1,-1,null,"",-1));
            } finally {

                conex.cerrarConexion();

            }
        }
        return lista_facturaspendientes;
    }

    //Procedimiento para insertar un nuevo Plan de Pago.
    public int insertarPlanDePago() {
        /*--Se ubica en el siguiente orden 
        (idVenta,dias de credito,fecha de credito,
        valor total de la factura,intereses)*/
        String sentenciaSQL = "Select ingresar_plan_de_pago(" + plan_pago.getIdFactura()
                + "," + plan_pago.getDiasCredito()
                + ",'" + plan_pago.getFechaFacturacion()
                + "'," + plan_pago.getValorTotalFactura() + "," + plan_pago.getIntereses() + ")";

        //Verificamos la conexion
        if (conex.isEstado()) {

            /*Una vez se asegura que la conexion este correcta.
            Se ejecuta la sentencia ingresada.*/
            return conex.ejecutarProcedimiento(sentenciaSQL);

        }

        return -1;
    }

    /*Funcion para obtener el total de Venta de la empresa, así como el total de
    lo que debe cobrar a los clientes, todo esto se lo devuelve en un arreglo
    donde la pos[0] es la venta y la pos[1] la cartera pendiente*/
    public double[] obtenerTotalVentayCarteraPendiente() {
        double[] tVentaCarteraP = {0, 0}; //[0] Total Venta, [1] Cartera P.
        
        //Verificamos la conexion
        if (conex.isEstado()) {
            try {
                //Se obtiene una TABLA con 1 fila y 2 columnas.
                String sentencia = "Select*From obtener_ventas_y_cartera_pendiente()";
                result = conex.ejecutarConsulta(sentencia);
                
                result.next();
                
                //Almacenamos en sus respectiva posicion los resultados.
                tVentaCarteraP[0] = result.getDouble("total_venta_r");
                tVentaCarteraP[1] = result.getDouble("cartera_pendiente_r");
                
            } catch (SQLException ex) {
                /*Si hay algun error retornamos 0,0 para cada valor
                    y su respectivo mensaje de error.*/
                System.out.println(ex.getMessage());
                return tVentaCarteraP;
                
            } finally {
                
                conex.cerrarConexion();
                
            }
        }
        
        return tVentaCarteraP;
    }

    /*Funcion para devolver el valor pendiente de cobro de un determinado plan*/
    public double obtenerValorPendiente() {
        double valorPendiente = 0;

        try {
            /*El valor pendiente de un plan se lo obtiene sumando todos los 
            los montos de los abonos menos el total de la factura.
            */
            String sentencia = "Select * from "
                    + "Obtener_Valor_Pendiente_de_un_plan"
                    + "(" + plan_pago.getIdFactura() + ")";
            result = conex.ejecutarConsulta(sentencia);
            
            result.next();
            
            //Almacenamos el valor obtenido en la variable
            valorPendiente = result.getDouble(1);
            
        } catch (SQLException ex) {
            //Si hay algun error o el valor es nulo, se retorna -1.
            return -1;
            
        } finally {
            
            conex.cerrarConexion();
            
        }

        return valorPendiente;
    }

}
