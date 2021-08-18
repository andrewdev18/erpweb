
package Models;

import java.io.Serializable;
import java.time.LocalDate;

public class Abono implements Serializable{
    //Declaracion de las variables que necesita un abono.
    private int idAbono;
    private int idPlanDePago;
    private int idFormaDePago;
    private double valorAbonado;
    private LocalDate fechaAbono=LocalDate.now();
    
    //Constructor vacio

    public Abono() {
    }
    
    //Constructor con todas las variables del modelo.

    public Abono(int idAbono, int idPlanDePago, int idFormaDePago, double valorAbonado) {
        this.idAbono = idAbono;
        this.idPlanDePago = idPlanDePago;
        this.idFormaDePago = idFormaDePago;
        this.valorAbonado = valorAbonado;
    }

    //Declaracion de  Getters y Setters
    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public int getIdPlanDePago() {
        return idPlanDePago;
    }

    public void setIdPlanDePago(int idPlanDePago) {
        this.idPlanDePago = idPlanDePago;
    }

    public int getIdFormaDePago() {
        return idFormaDePago;
    }

    public void setIdFormaDePago(int idFormaDePago) {
        this.idFormaDePago = idFormaDePago;
    }

    public double getValorAbonado() {
        return valorAbonado;
    }

    public void setValorAbonado(double valorAbonado) {
        this.valorAbonado = valorAbonado;
    }

    public LocalDate getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(LocalDate fechaAbono) {
        this.fechaAbono = fechaAbono;
    }
}
