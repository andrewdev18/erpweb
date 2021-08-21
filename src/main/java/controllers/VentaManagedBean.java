/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named(value = "VentaMB")
@ViewScoped
public class VentaManagedBean implements Serializable {

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

    //Constructor
    @PostConstruct
    public void VentaManagedBean() {
        this.cliente = new ClienteVenta();
        this.clienteDAO = new ClienteVentaDao();

        this.producto = new Producto();
        this.productoDao = new ProductoDAO();
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

        this.productoSeleccionado = null;
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
            detalle.setPrecio(this.producto.getPrecioUnitario());
            detalle.setProducto(this.producto);
            
            BigDecimal controldecimal = new BigDecimal((this.cantidad * this.precioProducto)).setScale(2, RoundingMode.UP);
            double tempSubTotal = controldecimal.doubleValue();
            detalle.setSubTotal(tempSubTotal);
            
            this.subTotalVenta = this.subTotalVenta + controldecimal.doubleValue();

            this.listaDetalle.add(detalle);
            this.cantidad = 1;
            this.codigoProducto = 0;
            this.nombreProducto = "";
            
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
    public void EliminarProducto(DetalleVenta detalle) {
        this.listaDetalle.remove(detalle);
        
        BigDecimal precioRedondeado = new BigDecimal((detalle.getProducto().getPrecioUnitario())).setScale(2, RoundingMode.UP);
        BigDecimal cantidadRedondeada = new BigDecimal((detalle.getCantidad())).setScale(2, RoundingMode.UP);
        
        this.subTotalVenta -= precioRedondeado.doubleValue() * cantidadRedondeada.doubleValue();
        
        if (detalle.getProducto().getIva() != 0) {
                this.subtotal12 -= precioRedondeado.doubleValue() * cantidadRedondeada.doubleValue();
            } else {
                this.subtotal0 -= precioRedondeado.doubleValue() * cantidadRedondeada.doubleValue();
            }
        
            this.iva = this.iva - detalle.getProducto().getIva() * cantidadRedondeada.doubleValue();
            this.ice = this.iva - detalle.getProducto().getIce() * cantidadRedondeada.doubleValue();

            this.total = this.subtotal0 + this.subtotal12 + this.iva + this.ice;
        
        this.productoSeleccionado = null;
        PrimeFaces.current().ajax().update("ventaForm:itemsTable");
        System.out.println("Eliminado");
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

    public double getSubTotalVenta() {
        return subTotalVenta;
    }

    public void setSubTotalVenta(double subTotalVenta) {
        this.subTotalVenta = subTotalVenta;
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

    public DetalleVenta getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(DetalleVenta productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

}
