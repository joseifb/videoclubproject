/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.videoclub.modelos;

import mx.uach.videoclub.modelos.genericos.Model;

/**
 *
 * @author BS
 */
public class Cinta extends Model{

    public static final String TABLA = "Cintas";

    public static final String[] FIELDS = {"id", "numero_copia", "peliculas_id"};

    public static final String Q = String.format("SELECT %s FROM %s",
            fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);

    public static final String INSERT_CINTA =
            String.format("%s %s (%s) VALUES (%s);",
                    Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE),
                    paramsToStatement(FIELDS, Boolean.TRUE));

    public static final String UPDATE_CINTA =
            String.format("%s %s SET %s WHERE %s = ?",
                    Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE),
                    ID);

    public static final String DELETE_CINTA =
            String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private Integer numeroCopia;
    private Pelicula pelicula;

    public Cinta() {
    }

    public Cinta(Integer numeroCopia, Pelicula pelicula) {
        this.numeroCopia = numeroCopia;
        this.pelicula = pelicula;
    }

    public Cinta(Integer id, Integer numeroCopia, Pelicula pelicula) {
        this(numeroCopia, pelicula);
        this.setId(id);
    }

    public Integer getNumeroCopia() {
        return numeroCopia;
    }

    public void setNumeroCopia(Integer numeroCopia) {
        this.numeroCopia = numeroCopia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
