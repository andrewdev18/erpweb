/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataViews.PersonaDAO;
import DataViews.RetencionDAO;
import Models.Persona;
import Models.Retencion;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author PERSONAL
 */
@Named(value = "retencionController")
@RequestScoped
public class RetencionController {

    int aux = 0;
    Retencion retencion;
    RetencionDAO retencionDAO;
    Persona persona;
    List<Retencion> listaRetenciones;  
    List<SelectItem> listaCliente;
    List<SelectItem> listaVenta;
    
    public RetencionController() {
        retencion = new Retencion();
        retencionDAO = new RetencionDAO();
        listaRetenciones = new ArrayList<>();
        listaRetenciones = retencionDAO.obtener_retenciones(3);
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    public List<Retencion> getListaRetenciones() {
        return listaRetenciones;
    }
    
    public void cargarRetenciones(int idVenta){
        listaRetenciones = new ArrayList<>();
        listaRetenciones=retencionDAO.obtener_retenciones(idVenta);
    }

    public void setListaRetenciones(List<Retencion> listaRetenciones) {
        this.listaRetenciones = listaRetenciones;
    }
    
    public List<SelectItem> getListaCliente() {
        listaCliente = new ArrayList<>();
        PersonaDAO personaDAO = new PersonaDAO();
        List<Persona> p = personaDAO.obtenerNombresClientes();
        listaCliente.clear();
        for (Persona nombres : p) {
            SelectItem nombresItem = new SelectItem(nombres.getId_Cliente(),nombres.getRazon_nombre());
            this.listaCliente.add(nombresItem); 
        }
        return listaCliente;
    }

    public void setListaCliente(List<SelectItem> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public List<SelectItem> getListaVentas(){
        listaVenta=new ArrayList<>();
        this.retencionDAO = new RetencionDAO();
        List<Retencion> r=retencionDAO.obtener_Ventas(5);
        for(Retencion lret:r){
            SelectItem ventasItem=new SelectItem(lret.getId_Venta());
            this.listaVenta.add(ventasItem);
        }
        return listaVenta;
    }
    
    public void registrarRetencion() {
        retencionDAO=new RetencionDAO(retencion);
        if(retencionDAO.insertar_Retencion(3)>0){
            System.out.println("Se Ingresó Correctamente la retencion.");
        } else {
            System.out.println("No se Registró");
        }
    }
    
    public void btnEditar(int idretencion){
        System.out.println(idretencion);
        retencion.setId_Retencion(idretencion);
        aux = retencion.getId_Retencion();
    }
    
    public void actualizarRetencion(){
        retencion.setId_Retencion(aux);
        System.out.println(aux);
        retencionDAO=new RetencionDAO(retencion);
        if(retencionDAO.actualizar_Retencion()>0){
            System.out.println("Se Editó Correctamente");
            //Aqui se ubica codigo para cargar nuevamente la tabla de retenciones
        } else {
            System.out.println("No se Editó");
        }
    }
}
