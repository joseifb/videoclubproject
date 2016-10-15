package mx.uach.videoclub.dao;

import java.util.List;
import mx.uach.videoclub.dao.enums.CRUD;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;

/**
 * Declaración de los métodos de escritura y lectura de un modelo a otro.
 *
 * @author BS
 * @version 1.0
 */
public interface VideoDao {
    
	  /**
     * Realiza la busqueda de un {@code Director} por su identificador unico.
     * 
     * @param id {@code Integer} identificador unico de la base de datos
     * @return {@code Director} encontrado
     */ 
    public Director getDirectorById(Integer id);
    
    public List<Director> getDirectoresByCriteria(String criterio);
    
    public void directorProcess(Director director, CRUD crud);
    
    public Actor getActorById(Integer id);
    
    public List<Actor> getActoresByCriteria(String criterio);
    
    public void actorProcces(Actor actor, CRUD crud);
    
    public Cinta getCintaById(Integer id);
    
    public List<Cinta> getCintasByCriteria(String criterio);
    
    public void cintaProcces(Cinta cinta, CRUD crud);
    
    public Ficha getFichaById(Integer id);
    
    public List<Ficha> getFichasByCriteria(String criterio);
    
    public void fichaProcces(Ficha ficha, CRUD crud);
    
    public Lista getListaById(Integer id);
    
    public List<Lista> getListasByCriteria(String criterio);
    
    public void listaProcces(Lista lista, CRUD crud);
    
    public Pelicula getPeliculaById(Integer id);
    
    public List<Pelicula> getPeliculaByCriteria(String criterio);
    
    public void peliculaProcces(Pelicula pelicula, CRUD crud);
    
    public Prestamo getPrestamoById(Integer id);
    
    public List<Prestamo> getPrestamosByCriteria(String criterio);
    
    public void prestamoProcces(Prestamo prestamo, CRUD crud);
    
    public Socio getSocioById(Integer id);
    
    public List<Socio> getSociosByCriteria(String criterio);
    
    public void socioProcces(Socio socio, CRUD crud);
    
}
