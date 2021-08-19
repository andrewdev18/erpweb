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
@SessionScoped
public class VentaManagedBean implements Serializable{
    
    private ClienteVenta cliente;
    private ClienteVentaDao clienteDAO;

    
    //Constructor
    public VentaManagedBean(){
        cliente = new ClienteVenta();
        clienteDAO = new ClienteVentaDao();
        System.out.println("Started");
    }
       
    public void BuscarClienteVenta(){
        this.cliente = clienteDAO.BuscarCliente(this.cliente.getIdentificacion());
        if(this.cliente.getNombre() != null)
            System.out.print("Cliente: " + cliente.getNombre());
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

    
}