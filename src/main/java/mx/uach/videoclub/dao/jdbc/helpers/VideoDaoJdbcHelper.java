package mx.uach.videoclub.dao.jdbc.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uach.videoclub.modelos.Director;

/**
 * Helper que genera objetos del Dao
 *
 * @author BS
 */
public class VideoDaoJdbcHelper {

    public final static Director makeDirector(ResultSet rs) throws SQLException {
        Director obj = new Director(rs.getInt(Director.FIELDS[0]),
                rs.getString(Director.FIELDS[1]));
        return obj;
    }

}
