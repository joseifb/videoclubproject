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
public class Prestamo extends Model{

    public static final String TABLA = "Prestamos";

    public static final String[] FIELDS = {"id", "titulo", "genero", "duracion", "directores_id"};

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

    private Date fechaEntrega;
//    private Status estatus;
    private Ficha ficha;
    private Cinta cinta;

    public Prestamo() {
    }

    public Prestamo(Date fechaEntrega, Ficha ficha, Cinta cinta) {
        this.fechaEntrega = fechaEntrega;
        this.ficha = ficha;
        this.cinta = cinta;
    }

    public Prestamo(Integer id, Date fechaEntrega, Ficha ficha, Cinta cinta) {
        this(fechaEntrega, ficha, cinta);
        this.setId(id);
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Cinta getCinta() {
        return cinta;
    }

    public void setCinta(Cinta cinta) {
        this.cinta = cinta;
    }
}
