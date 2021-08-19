/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataViews.ProductoDAO;
import Models.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "productoManagedBean")
@ViewScoped
public class ProductoManagedBean implements Serializable{
    int codigoproducto=1001; 
    ProductoDAO productdao;
    Producto product;
    List<Producto> listaproforma;
    public ProductoManagedBean() {
        productdao= new ProductoDAO();
        product=new Producto();
        
    }

    public int getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(int codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }
    
    public void obtenerProducto(){
//        listaproforma = new ArrayList();
//        productdao = new ProductoDAO(product);
//        Producto pro = productdao.ObtenerProducto(codigoproducto);
//        System.out.print(product.getCodigo());
//        product = pro;
//        System.out.print("Si se ejecuta ");
//        System.out.print(pro.getProducto());
    }
    
}
