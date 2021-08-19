/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataViews.ClienteVentaDao;
import Models.ClienteVenta;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named(value="VentaMB")
@RequestScoped
public class VentaManagedBean implements Serializable{
    private ClienteVenta cliente;
    private ClienteVentaDao clienteDAO;
    private String nombreCliente;
    private String idNumCliente;
    
    @PostConstruct
    public void VentaManagedBean(){
        cliente = new ClienteVenta();
        clienteDAO = new ClienteVentaDao();
    }
       
    public void BuscarCliente(){
        this.cliente = clienteDAO.BuscarCliente(this.idNumCliente);
        this.nombreCliente = this.cliente.getNombre();
    }

    public ClienteVenta getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVenta cliente) {
        this.cliente = cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdNumCliente() {
        return idNumCliente;
    }

    public void setIdNumCliente(String idNumCliente) {
        this.idNumCliente = idNumCliente;
        this.cliente.setIdentificacion(idNumCliente);
    }
    
    
    
}