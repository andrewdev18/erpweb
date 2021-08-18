/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataViews;

import java.io.Serializable;
import Models.ClienteVenta;
import DataViews.Conexion;

public class ClienteVentaDao implements Serializable {
    Conexion con;
    ClienteVenta clienteVenta;
    
    public ClienteVentaDao(){
        this.con = new Conexion();
    }
    
    public ClienteVenta BuscarCliente(){
        con.abrirConexion();
        ClienteVenta temp = new ClienteVenta();
        return null;
    } 
}
