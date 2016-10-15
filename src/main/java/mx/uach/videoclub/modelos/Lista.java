/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.videoclub.modelos;

import mx.uach.videoclub.modelos.genericos.Model;

import java.util.Date;

/**
 *
 * @author BS
 */
public class Lista extends Model{

    public static final String TABLA = "Listas";

    public static final String[] FIELDS = {"id", "fecha", "estatus", "peliculas_id", "socios_id", "hora"};

    public static final String Q = String.format("SELECT %s FROM %s",
            fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);

    public static final String INSERT_LISTA =
            String.format("%s %s (%s) VALUES (%s);",
                    Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE),
                    paramsToStatement(FIELDS, Boolean.TRUE));

    public static final String UPDATE_LISTA =
            String.format("%s %s SET %s WHERE %s = ?",
                    Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE),
                    ID);

    public static final String DELETE_LISTA =
            String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private Date fecha;
    private Date hora;
    private Boolean estatus;
    private Socio socio;
    private Pelicula pelicula;

    public Lista() {
    }

    public Lista(Date fecha, Date hora, Boolean estatus, Socio socio, Pelicula pelicula) {
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.socio = socio;
        this.pelicula = pelicula;
    }

    public Lista(Integer id, Date fecha, Date hora, Boolean estatus, Socio socio, Pelicula pelicula) {
        this(fecha, hora, estatus, socio, pelicula);
        this.setId(id);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
