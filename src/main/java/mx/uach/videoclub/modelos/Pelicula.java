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
public class Pelicula extends Model{

    public static final String TABLA = "Peliculas";

    public static final String[] FIELDS = {"id", "titulo", "genero", "duracion", "directores_id"};

    public static final String Q = String.format("SELECT %s FROM %s",
            fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);

    public static final String INSERT_PELICULA =
            String.format("%s %s (%s) VALUES (%s);",
                    Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE),
                    paramsToStatement(FIELDS, Boolean.TRUE));

    public static final String UPDATE_PELICULA =
            String.format("%s %s SET %s WHERE %s = ?",
                    Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE),
                    ID);

    public static final String DELETE_PELICULA =
            String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private String titulo;
//    private Genero genero;
    private Integer duracion;
    private Director director;
    private List<Actor> actores;

    public Pelicula() {
    }

    public Pelicula(String titulo, Integer duracion, Director director, List<Actor> actores) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.director = director;
        this.actores = actores;
    }

    public Pelicula(Integer id, String titulo, Integer duracion, Director director, List<Actor> actores) {
        this(titulo, duracion, director, actores);
        this.setId(id);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }
}
