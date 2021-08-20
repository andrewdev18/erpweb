/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataViews.ClienteVentaDao;
import DataViews.ProductoDAO;
import Models.ClienteVenta;
import Models.Producto;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value="VentaMB")
@ViewScoped
public class VentaManagedBean implements Serializable{
    
    private ClienteVenta cliente;
    private ClienteVentaDao clienteDAO;
    private String clienteIdNum;
    private String clienteNombre;
    
    private ProductoDAO productoDao;
    private Producto producto;
    private int codigoProducto;
    private String nombreProducto;
    private float precioProducto;

    
    //Constructor
    public VentaManagedBean(){
        this.cliente = new ClienteVenta();
        this.clienteDAO = new ClienteVentaDao();
        
        this.producto = new Producto();
        this.productoDao = new ProductoDAO();
        this.codigoProducto = 0;
        this.nombreProducto = "XXXXXXX";
        
    }
       
    //Buscar cliente
    public void BuscarClienteVenta(){
        this.cliente = clienteDAO.BuscarCliente(this.clienteIdNum);
        if(this.cliente != null)
            this.clienteNombre = this.cliente.getNombre();
        else
            System.out.print("No hay cliente");
        
        if(this.cliente.getNombre() != null)
            System.out.print("Cliente: " + clienteNombre);
        else
            System.out.print("Sin cliente");
    }

    
    //Buscar Producto
    public void BuscarProducto(){
        this.nombreProducto = "";
        this.producto = this.productoDao.ObtenerProducto(this.codigoProducto);
        if(this.producto == null)
            System.out.print("No existe el producto " + this.nombreProducto);
        else{
            System.out.print("Existe el producto" + this.nombreProducto);
            this.nombreProducto = this.producto.getDescripcion();
            this.precioProducto = this.producto.getPrecioUnitario();
        }
    }
    
    //--------------------Getter y Setter-------------------//
    public ClienteVenta getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVenta cliente) {
        this.cliente = cliente;
    }
   
    
    public ClienteVentaDao getClienteDAO() {
        return clienteDAO;
    }

    public String getClienteIdNum() {
        return clienteIdNum;
    }

    public void setClienteIdNum(String clienteIdNum) {
        this.clienteIdNum = clienteIdNum;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public ProductoDAO getProductoDao() {
        return productoDao;
    }

    public void setProductoDao(ProductoDAO productoDao) {
        this.productoDao = productoDao;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    
}