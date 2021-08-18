
package Models;

public class Persona_Juridica extends Persona{
    
    //Declaración de las Variables
    private String razon_Social;

    public Persona_Juridica() {
    }
    
    public Persona_Juridica(String razon_Social, int id_Tipo_Idenficacion,
                            String direccion, String identificacion,
                            boolean estado, String tlf1, String tlf2, 
                            String correo, int id_tipoCliente) {
        super(id_Tipo_Idenficacion, direccion, identificacion, estado, tlf1, tlf2, 
                correo, id_tipoCliente);
        
        this.razon_Social = razon_Social;
    }

    public String getRazon_Social() {
        return razon_Social;
    }

    public void setRazon_Social(String razon_Social) {
        this.razon_Social = razon_Social;
    }

}
