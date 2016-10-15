package mx.uach.videoclub.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uach.videoclub.conexiones.Conexion;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.enums.CRUD;
import mx.uach.videoclub.dao.jdbc.helpers.VideoDaoJdbcHelper;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;

/**
 *
 * @author BS
 */
public class VideoDaoJDBC implements VideoDao {

    public VideoDaoJDBC() {
    }

    /**
     * Regresa un director basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Director} si el id es valido.
     */
    @Override
    public Director getDirectorById(Integer id) {
        try {
            Statement st = Conexion.getInstance().getCon().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s %s ", Director.Q,
                    Director.Q_WHERE_ID, id));
            Director obj = null;
            while (rs.next()) {
                obj = VideoDaoJdbcHelper.makeDirector(rs);
            }
            return obj;
        } catch (SQLException ex) {

        }
        return null;
    }

     /**
     * Regresa un director basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Director} que coincidieron con el criterio
     * {@code Director} si el criterio es valido.
     */
    @Override
    public List<Director> getDirectoresByCriteria(String criterio) {
        List<Director> objects = new ArrayList<>();
        try {
            Statement st = Conexion.getInstance().getCon().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s %s ", Director.Q,
                    criterio.isEmpty() ? "" : Director.Q_WHERE, criterio));
            Director obj = null;
            while (rs.next()) {
                obj = VideoDaoJdbcHelper.makeDirector(rs);
                objects.add(obj);
            }

        } catch (SQLException ex) {

        }
        return objects;
    }
    
     /**
     * Genera una inserccion, cambio o elimina en {@code Director}
     *
     * @param director para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */

    @Override
    public void directorProcess(Director director, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().
                    getCon().prepareStatement(Director.INSERT_DIRECTOR);
                    ps.setString(1, director.getNombre());
                    break;
                case UPDATE:
                    //UPDATE TABLA SET()
                    ps = Conexion.getInstance().
                    getCon().prepareStatement(Director.UPDATE_DIRECTOR);
                    ps.setString(1, director.getNombre());
                    ps.setInt(2, director.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().
                    getCon().prepareStatement(Director.DELETE_DIRECTOR);
                    ps.setInt(1, director.getId());
                    break;
                default:
                    break;
            }
            
            Boolean result = ps.execute();            
            
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
        
    }
    
/**
     * Regresa un Actor basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Actor} si el id es valido.
     */
    @Override
    public Actor getActorById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Actor.Q, Actor.Q_WHERE_ID, id));
            Actor obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeActor(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     /**
     * Regresa un Actor basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Actor} que coincidieron con el criterio
     * {@code Actor} si el criterio es valido.
     */
 
    @Override
    public List<Actor> getActoresByCriteria(String criterio) {
        List<Actor> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Actor.Q, criterio.isEmpty() ? "" : Actor.Q_WHERE, criterio));
            Actor obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeActor(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }
/**
     * Genera una inserccion, cambio o elimina en {@code Actor}
     *
     * @param actor para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */

    @Override
    public void actorProcces(Actor actor, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Actor.INSERT_ACTOR);
                    ps.setString(1, actor.getNombre());
                    ps.setString(2, actor.getNombre());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Actor.UPDATE_ACTOR);
                    ps.setString(1, actor.getNombre());
                    ps.setString(2, actor.getNombre());
                    ps.setInt(3, actor.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Actor.DELETE_ACTOR);
                    ps.setInt(1, actor.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

   /**
     * Regresa un Cinta basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Cinta} si el id es valido.
     */
    @Override
    public Cinta getCintaById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Cinta.Q, Cinta.Q_WHERE_ID, id));
            Cinta obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeCinta(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     /**
     * Regresa un Cinta basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Cinta} que coincidieron con el criterio
     * {@code Cinta} si el criterio es valido.
     */
    @Override
    public List<Cinta> getCintasByCriteria(String criterio) {
        List<Cinta> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Cinta.Q, criterio.isEmpty() ? "" : Cinta.Q_WHERE, criterio));
            Cinta obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeCinta(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

 /**
     * Genera una inserccion, cambio o elimina en {@code Cinta}
     *
     * @param cinta para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */
    @Override
    public void cintaProcces(Cinta cinta, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Cinta.INSERT_CINTA);
                    ps.setInt(1, cinta.getNumeroCopia());
                    ps.setInt(2, cinta.getPelicula().getId());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Cinta.UPDATE_CINTA);
                    ps.setInt(1, cinta.getNumeroCopia());
                    ps.setInt(2, cinta.getPelicula().getId());
                    ps.setInt(3, cinta.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Cinta.DELETE_CINTA);
                    ps.setInt(1, cinta.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

  /**
     * Regresa un Ficha basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Ficha} si el id es valido.
     */
    @Override
    public Ficha getFichaById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Ficha.Q, Ficha.Q_WHERE_ID, id));
            Ficha obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeFicha(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

  /**
     * Regresa una Ficha basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Ficha} que coincidieron con el criterio
     * {@code Ficha} si el criterio es valido.
     */
    @Override
    public List<Ficha> getFichasByCriteria(String criterio) {
        List<Ficha> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Ficha.Q, criterio.isEmpty() ? "" : Ficha.Q_WHERE, criterio));
            Ficha obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeFicha(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

 /**
     * Genera una inserccion, cambio o elimina en {@code Ficha}
     *
     * @param ficha para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */
    @Override
    public void fichaProcces(Ficha ficha, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Ficha.INSERT_FICHA);
                    ps.setDate(1, new java.sql.Date(ficha.getFechaPrestamo().getTime()));
                    ps.setInt(2, ficha.getSocio().getId());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Ficha.UPDATE_FICHA);
                    ps.setDate(1, new java.sql.Date(ficha.getFechaPrestamo().getTime()));
                    ps.setInt(2, ficha.getSocio().getId());
                    ps.setInt(3, ficha.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Ficha.DELETE_FICHA);
                    ps.setInt(1, ficha.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

/**
     * Regresa un Lista basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Lista} si el id es valido.
     */
    @Override
    public Lista getListaById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Lista.Q, Lista.Q_WHERE_ID, id));
            Lista obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeLista(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 /**
     * Regresa una Lista basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Lista} que coincidieron con el criterio
     * {@code Lista} si el criterio es valido.
     */
    @Override
    public List<Lista> getListasByCriteria(String criterio) {
        List<Lista> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Lista.Q, criterio.isEmpty() ? "" : Lista.Q_WHERE, criterio));
            Lista obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeLista(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

/**
     * Genera una inserccion, cambio o elimina en {@code Lista}
     *
     * @param lista para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */
    @Override
    public void listaProcces(Lista lista, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Lista.INSERT_LISTA);
                    ps.setInt(1, lista.getPelicula().getId());
                    ps.setInt(2, lista.getSocio().getId());
                    ps.setDate(3, new java.sql.Date(lista.getFecha().getTime()));
                    ps.setDate(4, new java.sql.Date(lista.getHora().getTime()));
                    ps.setBoolean(5, lista.getEstatus());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Lista.UPDATE_LISTA);
                    ps.setInt(1, lista.getPelicula().getId());
                    ps.setInt(2, lista.getSocio().getId());
                    ps.setDate(3, new java.sql.Date(lista.getFecha().getTime()));
                    ps.setDate(4, new java.sql.Date(lista.getHora().getTime()));
                    ps.setBoolean(5, lista.getEstatus());
                    ps.setInt(6, lista.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Lista.DELETE_LISTA);
                    ps.setInt(1, lista.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

 /**
     * Regresa un Pelicula basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Pelicula} si el id es valido.
     */
    @Override
    public Pelicula getPeliculaById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Pelicula.Q, Pelicula.Q_WHERE_ID, id));
            Pelicula obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePelicula(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 /**
     * Regresa un Pelicula basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Pelicula} que coincidieron con el criterio
     * {@code Pelicula} si el criterio es valido.
     */

    @Override
    public List<Pelicula> getPeliculaByCriteria(String criterio) {
        List<Pelicula> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Pelicula.Q, criterio.isEmpty() ? "" : Pelicula.Q_WHERE, criterio));
            Pelicula obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePelicula(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

/**
     * Genera una inserccion, cambio o elimina en {@code Pelicula}
     *
     * @param pelicula para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */
    @Override
    public void peliculaProcces(Pelicula pelicula, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Pelicula.INSERT_PELICULA);
                    ps.setString(1, pelicula.getTitulo());
                    ps.setString(2, pelicula.getGenero().toString());
                    ps.setInt(3, pelicula.getDuracion());
                    ps.setInt(4, pelicula.getDirector().getId());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Pelicula.UPDATE_PELICULA);
                    ps.setString(1, pelicula.getTitulo());
                    ps.setString(2, pelicula.getGenero().toString());
                    ps.setInt(3, pelicula.getDuracion());
                    ps.setInt(4, pelicula.getDirector().getId());
                    ps.setInt(5, pelicula.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Pelicula.DELETE_PELICULA);
                    ps.setInt(1, pelicula.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }


    /**
     * Regresa un Prestamo basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Prestmo} si el id es valido.
     */
    @Override
    public Prestamo getPrestamoById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Prestamo.Q, Prestamo.Q_WHERE_ID, id));
            Prestamo obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePrestamo(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     /**
     * Regresa un Prestamo basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Prestamo} que coincidieron con el criterio
     * {@code Prestamo} si el criterio es valido.
     */

    @Override
    public List<Prestamo> getPrestamosByCriteria(String criterio) {
        List<Prestamo> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Prestamo.Q, criterio.isEmpty() ? "" : Prestamo.Q_WHERE, criterio));
            Prestamo obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePrestamo(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

   /**
     * Genera una inserccion, cambio o elimina en {@code Prestamo}
     *
     * @param prestamo para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */
    @Override
    public void prestamoProcces(Prestamo prestamo, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Prestamo.INSERT_PRESTAMO);
                    ps.setInt(1, prestamo.getCinta().getId());
                    ps.setInt(2, prestamo.getFicha().getId());
                    ps.setDate(3, new java.sql.Date(prestamo.getFechaEntrega().getTime()));
                    ps.setString(4, prestamo.getEstatus().toString());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Prestamo.UPDATE_PRESTAMO);
                    ps.setInt(1, prestamo.getCinta().getId());
                    ps.setInt(2, prestamo.getFicha().getId());
                    ps.setDate(3, new java.sql.Date(prestamo.getFechaEntrega().getTime()));
                    ps.setString(4, prestamo.getEstatus().toString());
                    ps.setInt(5, prestamo.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Prestamo.DELETE_PRESTAMO);
                    ps.setInt(1, prestamo.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

  /**
     * Regresa un Socio basado en un id del registro de la base de datos.
     *
     * @param id entero que identifica la entidad.
     * @return null si el id no se encuentra en la base de datos ó un
     * {@code Socio} si el id es valido.
     */
    @Override
    public Socio getSocioById(Integer id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Socio.Q, Socio.Q_WHERE_ID, id));
            Socio obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeSocio(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   /**
     * Regresa un Socio basado en un criterio/campo del registro de la base de datos.
     *
     * @param string que identifica la entidad.
     * @return {@code List} de {@code Socio} que coincidieron con el criterio
     * {@code Socio} si el criterio es valido.
     */
    @Override
    public List<Socio> getSociosByCriteria(String criterio) {
        List<Socio> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Socio.Q, criterio.isEmpty() ? "" : Socio.Q_WHERE, criterio));
            Socio obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeSocio(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }
/**
     * Genera una inserccion, cambio o elimina en {@code Socio}
     *
     * @param socio para donde se va realizar la operacion
     * @param crud {@code CRUD} segun la operacion a realizar
     */
    @Override
    public void socioProcces(Socio socio, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Socio.INSERT_SOCIO);
                    ps.setString(1, socio.getNombre());
                    ps.setString(2, socio.getDireccion());
                    ps.setString(3, socio.getTelefono());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Socio.UPDATE_SOCIO);
                    ps.setString(1, socio.getNombre());
                    ps.setString(2, socio.getDireccion());
                    ps.setString(3, socio.getTelefono());
                    ps.setInt(4, socio.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Socio.DELETE_SOCIO);
                    ps.setInt(1, socio.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

	

}
