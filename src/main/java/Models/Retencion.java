
package Models;

import java.time.LocalDate;

public class Retencion {
    private int id_Cliente;
    private int id_Retencion;
    private int id_Venta;
    private int porcen_Retencion;
    private LocalDate fecha_Emision=LocalDate.now();
    private Double BaseImponible;
    private String descImpuesto;
    private String EjerFiscal;
    private Double valorRetenido;

    public Retencion() {
    }

    public Retencion(int id_Retencion, int id_Venta, int porcen_Retencion, LocalDate fecha_Emision, Double BaseImponible, String descImpuesto, String EjerFiscal, Double valorRetenido) {
        this.id_Retencion = id_Retencion;
        this.id_Venta = id_Venta;
        this.porcen_Retencion = porcen_Retencion;
        this.fecha_Emision = fecha_Emision;
        this.BaseImponible = BaseImponible;
        this.descImpuesto = descImpuesto;
        this.EjerFiscal = EjerFiscal;
        this.valorRetenido = valorRetenido;
    }

    public Retencion(int id_Venta, int porcen_Retencion, Double BaseImponible, String descImpuesto, String EjerFiscal, Double valorRetenido) {
        this.id_Venta = id_Venta;
        this.porcen_Retencion = porcen_Retencion;
        this.BaseImponible = BaseImponible;
        this.descImpuesto = descImpuesto;
        this.EjerFiscal = EjerFiscal;
        this.valorRetenido = valorRetenido;
    }
    
    public Retencion(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public int getId_Retencion() {
        return id_Retencion;
    }

    public void setId_Retencion(int id_Retencion) {
        this.id_Retencion = id_Retencion;
    }


    public String getEjerFiscal() {
        return EjerFiscal;
    }

    public void setEjerFiscal(String EjerFiscal) {
        this.EjerFiscal = EjerFiscal;
    }

    public Double getBaseImponible() {
        return BaseImponible;
    }

    public void setBaseImponible(Double BaseImponible) {
        this.BaseImponible = BaseImponible;
    }

    public String getDescImpuesto() {
        return descImpuesto;
    }

    public void setDescImpuesto(String descImpuesto) {
        this.descImpuesto = descImpuesto;
    }

    public int getPorcen_Retencion() {
        return porcen_Retencion;
    }

    public void setPorcen_Retencion(int porcen_Retencion) {
        this.porcen_Retencion = porcen_Retencion;
    }

    public Double getValorRetenido() {
        return valorRetenido;
    }

    public void setValorRetenido(Double valorRetenido) {
        this.valorRetenido = valorRetenido;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public LocalDate getFecha_Emision() {
        return fecha_Emision;
    }

    public void setFecha_Emision(LocalDate fecha_Emision) {
        this.fecha_Emision = fecha_Emision;
    }

   
    
    
    
}
