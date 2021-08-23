/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataViews;

import java.io.Serializable;
import DataViews.Conexion;
import java.sql.ResultSet;
import java.util.HashSet;
import Models.Proforma;
import java.util.List;
import Models.Producto;

public class ProformaDAO {
    Conexion con;
    Proforma proforma = new Proforma();

    public ProformaDAO() {
        con = new Conexion();
    }
    
    public void IngresarProforma(Proforma ProformaDetalle, Producto prod){
        String procedimiento;
        int estado;
        con.abrirConexion();
        try{
           procedimiento = "call ingresarproforma("+ ProformaDetalle.getId_proforma()
                   +","+ ProformaDetalle.getId_cliente() +","+ ProformaDetalle.getId_empleado()
                   +",'"+ ProformaDetalle.getFecha_creacion() +"','"+ ProformaDetalle.getFecha_actualizacion()
                   +"','"+ ProformaDetalle.getFecha_expiracion() +"',"+ ProformaDetalle.isProforma_terminada()
                   +","+ ProformaDetalle.isAceptacion_cliente() +",'"+ ProformaDetalle.getEstado()
                   +"','"+ ProformaDetalle.getFecha_autorizacion() +"',"+ ProformaDetalle.getBase12()
                   +","+ ProformaDetalle.getBase0() +","+ ProformaDetalle.getBase_excento_iva()
                   +","+ ProformaDetalle.getIva12() +","+ ProformaDetalle.getIce()
                   +","+ ProformaDetalle.getTotalproforma() +")";
           estado = con.ejecutarProcedimiento(procedimiento);
           if(estado>0){
               System.out.println("Proforma correctamente ingresada");
               estado=0;
               procedimiento = "call insertarproductoproforma("+ ProformaDetalle.getDetalleproformacodigo()
                       +","+ProformaDetalle.getId_proforma()+","+prod.getCodigo()
                       +","+prod.getStock()+","+prod.getDescuento()+","+prod.getPrecioUnitario()+")";
               estado = con.ejecutarProcedimiento(procedimiento);
               if(estado>0){
                   System.out.println("Detalle de proforma ingresado correctamente");
               }
               else{
                   System.out.println("Problema al ingresar detalle proforma");
               }
           }
           else{
               System.out.println("Proforma ingresada de manera incorrecta");
           }
        }catch(Exception e){
            System.out.println(e.toString());
            if(con.isEstado())
                con.cerrarConexion();
        }
        finally{
            con.cerrarConexion();
        }
    
}
    
}
