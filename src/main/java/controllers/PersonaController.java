/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataViews.PersonaDAO;
import DataViews.Persona_JuridicaDAO;
import DataViews.Persona_NaturalDAO;
import Models.Persona;
import Models.Persona_Juridica;
import Models.Persona_Natural;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.event.RowEditEvent;

@Named(value = "personaController")
@RequestScoped
public class PersonaController implements Serializable {

    //Declaro mis clases Persona y PersonaDAO
    Persona persona;
    PersonaDAO personaDAO;

    //Declaro mis clases Persona_Natural y Persona_NaturalDAO
    Persona_Natural persona_Natural;
    Persona_NaturalDAO persona_NaturalDAO;

    //Declaro mis clases Persona_Juridica y Persona_JuridicaDAO
    Persona_Juridica persona_Juridica;
    Persona_JuridicaDAO persona_JuridicaDAO;

    //Declaro mi listaCliente que van hacer cargadas en el datatable
    List<Persona> listaCliente;

    //Constructor que instancia mis clases declaradas
    public PersonaController() {
        persona = new Persona();
        personaDAO = new PersonaDAO();

        persona_Juridica = new Persona_Juridica();
        persona_Natural = new Persona_Natural();

        listaCliente = new ArrayList<>();
        listaCliente = personaDAO.obtener_Todos_Los_Clientes();

//        persona_Juridica.setRazon_Social("TIA 2");
//        persona_Juridica.setId_tipoCliente(1);
//        persona_Juridica.setIdentificacion("3242342");
//        persona_Juridica.setDireccion("mas alla");
//        persona_Juridica.setId_Tipo_Idenficacion(2);
//        persona_Juridica.setCorreo("HOLA@GMAIL.COM");
//        persona_Juridica.setTlf1("0938472");
//        persona_Juridica.setTlf2("0938472");
//        
//        
//        registrar_Cliente_Juridico();
        //actualizar_Cliente_Juridico();
        //obtener_Un_Cliente_Natural();
//          editar_cliente();
    }

    public void mostrar() {
        listaCliente = personaDAO.obtener_Todos_Los_Clientes();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersonaDAO(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public List<Persona> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Persona> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Persona_Natural getPersona_Natural() {
        return persona_Natural;
    }

    public void setPersona_Natural(Persona_Natural persona_Natural) {
        this.persona_Natural = persona_Natural;
    }

    public Persona_Juridica getPersona_Juridica() {
        return persona_Juridica;
    }

    public void setPersona_Juridica(Persona_Juridica persona_Juridica) {
        this.persona_Juridica = persona_Juridica;
    }

    public void anularCliente(int id) {
        System.out.println(id);
        if (personaDAO.deshabilitar_cliente(id) > 0) {
            System.out.print("Cliente Anulado");
            listaCliente = personaDAO.obtener_Todos_Los_Clientes();
        } else {
            System.out.print("Error al anular");
        }
    }
    
    public void activarCliente(int id) {
        System.out.println(id);
        if (personaDAO.habilitar_cliente(id) > 0) {
            System.out.print("Cliente Activado");
            listaCliente = personaDAO.obtener_Todos_Los_Clientes();
        } else {
            System.out.print("Error al activar");
        }
    }
    

    public void registrar_Cliente_Juridico() {
        persona_JuridicaDAO = new Persona_JuridicaDAO(persona_Juridica);
        System.out.println(persona_Juridica.getRazon_Social());
        if (persona_JuridicaDAO.insertar_Cliente_Juridico() > 0) {
            System.out.println("Si se ejecuta if");
            System.out.println("Se Ingresó Correctamente el cliente." + persona_Juridica.getRazon_Social());
        }
        System.out.println("Si se ejecuta cliente juridico");
    }

    public void registrar_Cliente_Natural() {
        persona_NaturalDAO = new Persona_NaturalDAO(persona_Natural);
        if (persona_NaturalDAO.insertar_Cliente_Natural() > 0) {
            System.out.println("Se Ingresó Correctamente el cliente." + persona_Natural.getNombre1());
        }
    }

    public void editar_cliente(int idCliente) {

        if (personaDAO.identificar_cliente(idCliente).equals("J")) {
            //obtener_Un_Cliente_Juridico(idCliente);
            System.out.println("Es Juridico");
        } else if (personaDAO.identificar_cliente(idCliente).equals("N")) {
            obtener_Un_Cliente_Natural(idCliente);
            System.out.println("Es Natural");
        } else {
            System.out.println("Falló inesperadamente...");
        }

    }

    //Al momento de darle click al icono de editar, se ejecuta este procedi.
    public void obtener_Un_Cliente_Juridico(RowEditEvent<Persona_Juridica> event) {
        persona_Juridica = event.getObject();
        System.out.println(persona_Juridica.getId_Cliente());
        //Se almacena el id cliente en una variable auxiliar
        int aux = persona_Juridica.getId_Cliente();
        persona_JuridicaDAO = new Persona_JuridicaDAO(persona_Juridica);
        //Se obtiene ese cliente por el id
        Persona_Juridica per_juridica = persona_JuridicaDAO.obtener_Cliente_Juridico();
        //Se remplazan los objetos
        persona_Juridica = per_juridica;
        //Ubicamos nuevamente el id de la variable auxiliar
        persona_Juridica.setId_Cliente(aux);

        //Se instancia nuevamente la personaJuridicaDAO pero con todos los 
        //datos recopilados
        persona_JuridicaDAO = new Persona_JuridicaDAO(persona_Juridica);
    }

    public void actualizar_Cliente_Juridico() {
        if (persona_JuridicaDAO.actualizar_Cliente_Juridico() > 0) {
            System.out.println("Se Editó Correctamente");
            listaCliente = personaDAO.obtener_Todos_Los_Clientes();
        } else {
            System.out.println("No se Editó");
        }
    }

    public void obtener_Un_Cliente_Natural(int id) {
        persona_Natural.setId_Cliente(id);
        System.out.println(persona_Natural.getId_Cliente());

        //Se almacena el id cliente en una variable auxiliar
        int aux = persona_Natural.getId_Cliente();
        persona_NaturalDAO = new Persona_NaturalDAO(persona_Natural);

        //Se obtiene ese cliente por el id
        Persona_Natural per_Natural = persona_NaturalDAO.obtener_Cliente_Natural();

        //Se remplazan los objetos
        persona_Natural = per_Natural;

        //Ubicamos nuevamente el id de la variable auxiliar
        persona_Natural.setId_Cliente(aux);
        System.out.println(persona_Natural.getIdentificacion());
        //Se instancia nuevamente la personaJuridicaDAO pero con todos los 
        //datos recopilados
        persona_NaturalDAO = new Persona_NaturalDAO(persona_Natural);

    }

    public void actualizar_Cliente_Natural() {
        if (persona_NaturalDAO.actualizar_Cliente_Natural() > 0) {
            System.out.println("Se Editó Correctamente");
            listaCliente = personaDAO.obtener_Todos_Los_Clientes();
        } else {
            System.out.println("No se Editó");
        }

    }

}
