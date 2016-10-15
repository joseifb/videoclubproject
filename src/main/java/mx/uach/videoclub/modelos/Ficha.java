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
public class Ficha extends Model{

    public static final String TABLA = "Fichas";

    public static final String[] FIELDS = {"id", "fecha_prestamo", "socios_id"};

    public static final String Q = String.format("SELECT %s FROM %s",
            fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);

    public static final String INSERT_FICHA =
            String.format("%s %s (%s) VALUES (%s);",
                    Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE),
                    paramsToStatement(FIELDS, Boolean.TRUE));

    public static final String UPDATE_FICHA =
            String.format("%s %s SET %s WHERE %s = ?",
                    Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE),
                    ID);

    public static final String DELETE_FICHA =
            String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private Date fechaPrestamo;
    private Socio socio;

    public Ficha() {
    }

    public Ficha(Date fechaPrestamo, Socio socio) {
        this.fechaPrestamo = fechaPrestamo;
        this.socio = socio;
    }

    public Ficha(Integer id, Date fechaPrestamo, Socio socio) {
        this(fechaPrestamo, socio);
        this.setId(id);
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}
