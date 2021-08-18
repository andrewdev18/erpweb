
package Models;

import java.time.LocalDate;

public class Persona_Natural extends Persona{
    
    //Declaración de las Variables
    private String sexo;
    private String genero;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellidos2;
    private LocalDate fecha_nacimiento = LocalDate.now();

    
    public Persona_Natural() {
    }

    //Constructor con los datos personales de un cliente natural
    public Persona_Natural(String sexo, String genero, String nombre1, 
                           String nombre2, String apellido1, String apellidos2,
                           LocalDate fecha_nacimiento, int id_Tipo_Idenficacion, 
                           String direccion, String identificacion, boolean estado,
                           String tlf1, String tlf2, String correo, int id_tipoCliente) {
        super(id_Tipo_Idenficacion, direccion, identificacion, estado, tlf1, tlf2, correo, id_tipoCliente);
        this.sexo = sexo;
        this.genero = genero;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellidos2 = apellidos2;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellidos2() {
        return apellidos2;
    }

    public void setApellidos2(String apellidos2) {
        this.apellidos2 = apellidos2;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
}
