package mx.uach.videoclub.dao;

import java.util.List;
import mx.uach.videoclub.dao.enums.CRUD;
import mx.uach.videoclub.modelos.Director;

/**
 * Declaración de los métodos de escritura y lectura de un modelo a otro.
 *
 * @author BS
 * @version 1.0
 */
public interface VideoDao {

    /**
     * Regresa un director basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code mx.uach.videoclub.modelos.Director} si el id es valido.
     */
    public Director getDirectorById(Integer id);

    public List<Director> getDirectoresByCriteria(String criterio);

    public void directorProcess(Director director, CRUD crud);

}
