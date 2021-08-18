package Models;

import java.io.Serializable;

public class Persona implements Serializable {

    //Declaración de las Variables
    private int id_Tipo_Idenficacion;
    private String direccion;
    private String identificacion;
    private boolean estado;
    private String tlf1;
    private String tlf2;
    private String correo;
    //Declaracion de las variables para mostrar clientes
    private int id_Cliente;
    private String descr_identificacion;
    private String razon_nombre;
    private int id_tipoCliente;
    private String descr_tipo_Cliente;
    private String descr_Estado;

    public Persona() {
    }

    //Constructor con los datos importantes
    public Persona(int id_Tipo_Idenficacion, String direccion,
            String identificacion, boolean estado, String tlf1,
            String tlf2, String correo, int id_tipoCliente) {
        this.id_Tipo_Idenficacion = id_Tipo_Idenficacion;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.estado = estado;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.correo = correo;
        this.id_tipoCliente = id_tipoCliente;
    }

    //Constructor con los datos que se mostraran en la tabla
    public Persona(int id_Cliente, String identificacion,
            String descr_identificacion, String razon_nombre,
            String direccion, String tlf1, String tlf2, String correo,
            String descr_tipo_Cliente, String descr_Estado) {
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.correo = correo;
        this.id_Cliente = id_Cliente;
        this.descr_identificacion = descr_identificacion;
        this.razon_nombre = razon_nombre;
        this.descr_tipo_Cliente = descr_tipo_Cliente;
        this.descr_Estado = descr_Estado;
    }

    public Persona(int id_Cliente, String razon_nombre) {
        this.id_Cliente = id_Cliente;
        this.razon_nombre = razon_nombre;
    }
    

    public String createLabel() {

        switch (descr_Estado) {

            case "Activo":
                return "SUCCESS";

            case "Anulado":
                return "FAILURE";

            default:
                return "DEFAULT";
        }
    }

    public int getId_Tipo_Idenficacion() {
        return id_Tipo_Idenficacion;
    }

    public void setId_Tipo_Idenficacion(int id_Tipo_Idenficacion) {
        this.id_Tipo_Idenficacion = id_Tipo_Idenficacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTlf1() {
        return tlf1;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_tipoCliente() {
        return id_tipoCliente;
    }

    public void setId_tipoCliente(int id_tipoCliente) {
        this.id_tipoCliente = id_tipoCliente;
    }

    public String getDescr_identificacion() {
        return descr_identificacion;
    }

    public void setDescr_identificacion(String descr_identificacion) {
        this.descr_identificacion = descr_identificacion;
    }

    public String getRazon_nombre() {
        return razon_nombre;
    }

    public void setRazon_nombre(String razon_nombre) {
        this.razon_nombre = razon_nombre;
    }

    public String getDescr_tipo_Cliente() {
        return descr_tipo_Cliente;
    }

    public void setDescr_tipo_Cliente(String descr_tipo_Cliente) {
        this.descr_tipo_Cliente = descr_tipo_Cliente;
    }

    public String getDescr_Estado() {
        return descr_Estado;
    }

    public void setDescr_Estado(String descr_Estado) {
        this.descr_Estado = descr_Estado;
    }
}
