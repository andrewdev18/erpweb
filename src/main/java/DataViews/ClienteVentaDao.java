/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataViews;

import java.io.Serializable;
import Models.ClienteVenta;
import DataViews.Conexion;
import java.sql.ResultSet;
import java.util.HashSet;

public class ClienteVentaDao implements Serializable {

    Conexion con;
    ClienteVenta clienteVenta;

    public ClienteVentaDao() {
        this.con = new Conexion();
    }

    public ClienteVenta BuscarCliente(String id) {
        ResultSet rs;
        ClienteVenta temp = new ClienteVenta();
        try {
            con.abrirConexion();
            if (id.length() == 10) {
                rs = con.ejecutarConsulta("select * from public.buscarclientenatural('" + id + "')");
            } else if (id.length() == 13) {
                rs = con.ejecutarConsulta("select * from public.buscarclientejuridico('" + id + "')");
            } else {
                rs = null;
            }
            con.cerrarConexion();

            if (rs.next()) {
                temp.setIdCliente(rs.getInt(1));
                temp.setIdentificacion(rs.getString(2));
                temp.setNombre(rs.getString(3));
                temp.setIdTipoCliente(rs.getInt(4));
                temp.setTipoCliente(rs.getString(5));
                temp.setDireccion(rs.getString(6));
                temp.setContacto(rs.getString(7));
                return temp;
            }
        } catch (Exception e) {

        }

        return null;
    }
}