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

/**
 *
 * @author Andres
 */
@Named(value="VentaMB")
@RequestScoped
public class VentaManagedBean implements Serializable{
    private ClienteVenta cliente;
    private ClienteVentaDao clienteDAO;
    
    @PostConstruct
    public void VentaManagedBean(){
        cliente = new ClienteVenta();
        clienteDAO = new ClienteVentaDao();
    }
       
    public void BuscarCliente(String idnum){
        this.cliente = clienteDAO.BuscarCliente(idnum);
    }
}