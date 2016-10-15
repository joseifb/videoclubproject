/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.videoclub.modelos;

import mx.uach.videoclub.modelos.genericos.Model;

import java.util.List;

/**
 *
 * @author BS
 */
public class Socio extends Model{

    public static final String TABLA = "Socios";

    public static final String[] FIELDS = {"id", "nombre", "direccion", "telefono"};

    public static final String Q = String.format("SELECT %s FROM %s",
            fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);

    public static final String INSERT_PRESTAMO =
            String.format("%s %s (%s) VALUES (%s);",
                    Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE),
                    paramsToStatement(FIELDS, Boolean.TRUE));

    public static final String UPDATE_PRESTAMO =
            String.format("%s %s SET %s WHERE %s = ?",
                    Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE),
                    ID);

    public static final String DELETE_PRESTAMO =
            String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private String nombre;
    private String direccion;
    private String telefono;
    private List<Director> directores;
    private List<Actor> actores;

    public Socio() {
    }

    public Socio(String nombre, String direccion, String telefono, List<Director> directores, List<Actor> actores) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.directores = directores;
        this.actores = actores;
    }

    public Socio(Integer id, String nombre, String direccion, String telefono, List<Director> directores, List<Actor> actores) {
        this(nombre, direccion, telefono, directores, actores);
        this.setId(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Director> getDirectores() {
        return directores;
    }

    public void setDirectores(List<Director> directores) {
        this.directores = directores;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }
}
