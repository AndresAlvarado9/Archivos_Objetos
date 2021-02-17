/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import ups.edu.ec.modelo.Persona;

/**
 *
 * @author diego
 */
public class ControladorPersona {
    
    private List<Persona> personalist;

    public ControladorPersona() {
        personalist = new ArrayList();
    }
    
    public void cargarDatos(String ruta) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream archivo = new FileInputStream(ruta);
        ObjectInputStream datos = new ObjectInputStream(archivo);
        personalist = (List<Persona>) datos.readObject();
    }
    
    public void guardarDatos(String ruta) throws FileNotFoundException, IOException {
        FileOutputStream archivo = new FileOutputStream(ruta);
        ObjectOutputStream datos = new ObjectOutputStream(archivo);
        datos.writeObject(personalist);
    }
    /**
     * Este metodo permite asignar un codigo, para ello recorre hasta el ultimo registro y a ese valor le suma 1, entonces basicamente es un incrementador de codigos de 1 en 1
     * @return 
     */
    
    public int generarId() {
        if(personalist.size() > 0) {
            return personalist.get(personalist.size() -1 ).getId() + 1;
        }
        return 1;
    }
    
    public boolean crear(String cedula, String nombre, String apellido) {
        Persona persona = new Persona(generarId(), cedula, nombre, apellido);
        return personalist.add(persona);
    }
    public Persona buscar(String cedula) {
        for (Persona persona : personalist) {
            if (persona.getCedula().equals(cedula))
                return persona;
        }
        return null;
    }
    public int posicion(String cedula){
        for (int i = 0; i < personalist.size(); i++) {
            Persona persona = personalist.get(i);
            if (persona.getCedula().equals(cedula))
                return i;
        }
        return -1;
    }
    
    public boolean actualizar(String cedula, String nombre, String apellido) {
        Persona persona = buscar(cedula);
        if(persona != null) { 
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            personalist.set(posicion(cedula), persona);
            return true;
        }
        return false;
    }
    public boolean eliminar(String cedula) {
        Persona persona = buscar(cedula);
        if(persona != null){
            return personalist.remove(persona);
        }
        return false;
    }

    public List<Persona> getPersonalist() {
        return personalist;
    }

    public void setPersonalist(List<Persona> personalist) {
        this.personalist = personalist;
    }

}
