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
import mx.uach.videoclub.modelos.Director;

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
                    Director.Q_WHRE_ID, id));
            Director obj = null;
            while (rs.next()) {
                obj = VideoDaoJdbcHelper.makeDirector(rs);
            }
            return obj;
        } catch (SQLException ex) {

        }
        return null;
    }

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

}
