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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value="VentaMB")
@RequestScoped
public class VentaManagedBean implements Serializable{
    
    private ClienteVenta cliente;
    private ClienteVentaDao clienteDAO;
    private String clienteIdNum;
    private String clienteNombre;

    
    //Constructor
    public VentaManagedBean(){
        cliente = new ClienteVenta();
        clienteDAO = new ClienteVentaDao();
        System.out.println("Started");
    }
       
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

    
}