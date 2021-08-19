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
    
    private List<Producto> listadecompra = new ArrayList();
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
    
    public Producto ObtenerProducto(int id){
        ResultSet rs;
        try {
            con.abrirConexion();
            rs = con.ejecutarConsulta("Select * from buscarproductocodigo('" + id + "')");
            con.cerrarConexion();
            if (rs.next()) {
                product.setCodigo(rs.getInt(1));
                product.setStock(rs.getInt(2));
                product.setProducto(rs.getString(3));
                product.setPrecio_unitario(rs.getFloat(4));
                product.setSubsidio(rs.getFloat(5));
                product.setIce(rs.getFloat(6));
                product.setIva(rs.getFloat(7));
                product.setDescuento(rs.getFloat(9));
            }
        } catch (Exception e) {

        }
        return product;
    }

    public List<Producto> getListadecompra() {
        return listadecompra;
    }

    public void setListadecompra(List<Producto> listadecompra) {
        this.listadecompra = listadecompra;
    }
}
