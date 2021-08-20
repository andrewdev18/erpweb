/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataViews;

import java.io.Serializable;
import Models.Producto;
import DataViews.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;

@ManagedBean
@RequestScoped
public class ProductoDAO {

    private Producto product;
    Conexion con;

    public ProductoDAO(Producto product) {
        con.abrirConexion();
        this.product = product;
    }

    public ProductoDAO() {
        this.con = new Conexion();
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    public Producto ObtenerProducto(int id) {
        ResultSet rs;

        try {
            String code = String.valueOf(id);
            con.abrirConexion();
            rs = con.ejecutarConsulta("select * from public.buscarproductocodigo(" + code + ")");

            if (rs == null) {
                System.out.println("No existen registros");
            } else {
                System.out.println("Existen registros ");
                
                int aux = 0;
                while(rs.next())
                    aux++;
                System.out.println("Registros obtenidos: " + String.valueOf(aux));
                
                rs.first();
                
                while (rs.next()) {
                    this.product.setCodigo(rs.getInt(1));
                    this.product.setCodigoAux(rs.getInt(2));
                    this.product.setStock(rs.getInt(3));
                    this.product.setDescripcion(rs.getString(4));
                    this.product.setPrecioUnitario(rs.getFloat(5));
                    this.product.setSubsidio(rs.getFloat(6));
                    this.product.setIce(rs.getFloat(7));
                    this.product.setIva(rs.getFloat(8));
                    this.product.setDescuento(rs.getFloat(9));
                }
            }
            con.cerrarConexion();

            return this.product;
        } catch (Exception e) {
            if (con.isEstado()) {
                con.cerrarConexion();
            }
        }
        
        return null;
    }
}
