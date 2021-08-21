/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import DataViews.ClienteVentaDao;
import DataViews.DetalleVentaDAO;
import DataViews.ProductoDAO;
import Models.ClienteVenta;
import Models.DetalleVenta;
import Models.Producto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named(value="ProformaMB")
@ViewScoped

public class ProformaManageBean implements Serializable {
    private ClienteVenta cliente;
    private ClienteVentaDao clienteDAO;
    private String clienteIdNum;
    private String clienteNombre;

    private DetalleVenta productoSeleccionado;

    private ProductoDAO productoDao;
    private Producto producto;
    private int codigoProducto;
    private String nombreProducto;
    private float precioProducto;
    private double subTotalVenta;

    private DetalleVenta detalleVenta;
    private DetalleVentaDAO detalleDAO;
    private List<DetalleVenta> listaDetalle;
    private double cantidad;

    private double subtotal12;
    private double subtotal0;
    private double descuento;
    private double ice;
    private double iva;
    private double total;

    public ProformaManageBean() {
        this.cliente = new ClienteVenta();
        this.clienteDAO = new ClienteVentaDao();
        
        this.productoSeleccionado = null;
        
        this.productoDao = new ProductoDAO();
        this.producto = new Producto();
        this.codigoProducto = 0;
        this.nombreProducto = "XXXXXXX";
        this.subTotalVenta = 0;
        
        this.subtotal12 = 0;
        this.subtotal0 = 0;
        this.descuento = 0;
        this.ice = 0;
        this.iva = 0;
        this.total = 0;
        
        this.listaDetalle = new ArrayList<>();
        this.cantidad = 1;
    }

    public ClienteVenta getCliente() {
        return cliente;
    }

     //Buscar cliente
    public void BuscarClienteVenta() {
        this.cliente = clienteDAO.BuscarCliente(this.clienteIdNum);
        if (this.cliente != null) {
            this.clienteNombre = this.cliente.getNombre();
        } else {
            System.out.print("No hay cliente");
        }

        if (this.cliente.getNombre() != null) {
            System.out.print("Cliente: " + clienteNombre);
        } else {
            System.out.print("Sin cliente");
        }
    }

    //Buscar Producto
    public void BuscarProducto() {
        this.nombreProducto = "";
        this.producto = this.productoDao.ObtenerProducto(this.codigoProducto);
        if (this.producto == null) {
            System.out.print("Producto nulo");
        } else {
            System.out.print("Existe el producto" + this.nombreProducto);
            this.nombreProducto = this.producto.getDescripcion();
            this.precioProducto = this.producto.getPrecioUnitario();
        }
    }

    //Agregar producto a la lista de detalle
    public void AgregarProductoLista() {
        if (this.producto.getCodigo() > 0) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCodprincipal(this.producto.getCodigo());
            detalle.setCantidad(this.cantidad);
            detalle.setDescuento(this.producto.getDescuento());
            detalle.setPrecio(this.precioProducto);
            detalle.setProducto(this.producto);
            detalle.setSubTotal(this.cantidad * this.precioProducto);
            
            BigDecimal controldecimal = new BigDecimal((this.cantidad * this.precioProducto)).setScale(2, RoundingMode.UP);
            double tempSubTotal = controldecimal.doubleValue();
            detalle.setSubTotal(tempSubTotal);
            this.subTotalVenta = this.subTotalVenta + controldecimal.doubleValue();

            this.listaDetalle.add(detalle);
            this.cantidad = 1;
            this.codigoProducto = 0;
            this.nombreProducto = "XXXXXX";
            this.precioProducto=0;

            if (this.producto.getIva() != 0) {
                this.subtotal12 += this.producto.getPrecioUnitario() * detalle.getCantidad();
            } else {
                this.subtotal0 += this.producto.getPrecioUnitario() * detalle.getCantidad();
            }

            this.iva = this.iva + this.producto.getIva();
            this.ice = this.iva + this.producto.getIce();

            this.total = this.subtotal0 + this.subtotal12 + this.iva + this.ice;

            this.producto = null;
        } else {
            System.out.println("No hay producto seleccionado");
        }
    }
    
    //Eliminar un producto de la lista
    public void EliminarProducto() {
        this.listaDetalle.remove(this.productoSeleccionado);
        System.out.println("Eliminado");
    }
    
    public void onRowSelect(SelectEvent event){
        this.productoSeleccionado = ((DetalleVenta) event.getObject());
    }

    //--------------------Getter y Setter-------------------//    
    public void setCliente(ClienteVenta cliente) {
        this.cliente = cliente;
    }

    public ClienteVentaDao getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteVentaDao clienteDAO) {
        this.clienteDAO = clienteDAO;
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

    public DetalleVenta getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(DetalleVenta productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
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

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
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

    public double getSubTotalVenta() {
        return subTotalVenta;
    }

    public void setSubTotalVenta(double subTotalVenta) {
        this.subTotalVenta = subTotalVenta;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public DetalleVentaDAO getDetalleDAO() {
        return detalleDAO;
    }

    public void setDetalleDAO(DetalleVentaDAO detalleDAO) {
        this.detalleDAO = detalleDAO;
    }

    public List<DetalleVenta> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleVenta> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal12() {
        return subtotal12;
    }

    public void setSubtotal12(double subtotal12) {
        this.subtotal12 = subtotal12;
    }

    public double getSubtotal0() {
        return subtotal0;
    }

    public void setSubtotal0(double subtotal0) {
        this.subtotal0 = subtotal0;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIce() {
        return ice;
    }

    public void setIce(double ice) {
        this.ice = ice;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
}
