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
            rs = con.ejecutarConsulta("select * from public.productos where codprincipal = " + code + " or codauxiliar = " + code + ";");

            if(rs != null)
                System.out.println("Producto " + code + " existe");
            else
                System.out.println("Producto " + code + " NO existe");
            
            con.cerrarConexion();
            if (rs.next()) {
                product.setCodigo(rs.getInt(1));
                product.setCodigoAux(rs.getInt(2));
                product.setStock(rs.getInt(3));
                product.setDescripcion(rs.getString(4));
                product.setDetalleAdicional(rs.getString(5));
                product.setPrecioUnitario(rs.getFloat(6));
                product.setSubsidio(rs.getFloat(7));
                product.setIce(rs.getFloat(8));
                product.setIva(rs.getFloat(9));
                product.setDescuento(rs.getFloat(10));
            }
        } catch (Exception e) {

        }
        return product;
    }
}
