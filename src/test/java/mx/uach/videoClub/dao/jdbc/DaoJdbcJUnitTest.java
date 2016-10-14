package mx.uach.videoClub.dao.jdbc;

import java.util.List;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.enums.CRUD;
import mx.uach.videoclub.dao.jdbc.VideoDaoJDBC;
import mx.uach.videoclub.modelos.Director;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Dao JDBC
 *
 * @author BS
 */
public class DaoJdbcJUnitTest {
    
    

    
    @Test
    public void directorByIdTest(){
        VideoDao dao = new VideoDaoJDBC();
        
        // Id = 1 James Cameron
        Director d = dao.getDirectorById(1);
        
        assertNotNull(d);
        assertEquals(d.getNombre(), "James Cameron");
        
        assertNotEquals(d.getNombre(), "Alfonso Cuaron");
        
        //Id = 2 Alfonso Cuaron
        Director d2 = dao.getDirectorById(2);
        
        assertNotNull(d2);
        assertEquals(d2.getNombre(), "Alfonso Cuaron");
        
        assertNotEquals(d2.getNombre(), "James Cameron");
        
        
        List<Director> directores = dao.getDirectoresByCriteria("");
        //assertEquals(3, directores.size());
        
    }
    
   /* @Test
    public void makeDirector(){
        VideoDao dao = new VideoDaoJDBC();
        dao.directorProcess(new Director("Tim Burton"), CRUD.CREATE);
        
        Director d = dao.getDirectorById(4);
        assertNotNull(d);
        assertEquals(d.getNombre(), "Tim Burton");

    }*/
    
    
    @Test
    public void updateDirector(){
        VideoDao dao = new VideoDaoJDBC();
       
        Director d = dao.getDirectorById(3);
        assertNotNull(d);
        
        d.setNombre("David Yates");
        dao.directorProcess(d, CRUD.UPDATE);
        
        d = dao.getDirectorById(3);
        assertNotNull(d);
        assertEquals(d.getNombre(), "David Yates");


    }
    
    
    @Test
    public void deleteDirector(){
        VideoDao dao = new VideoDaoJDBC();
       
        Director d = dao.getDirectorById(6);
        assertNotNull(d);
        
       
        dao.directorProcess(d, CRUD.DELETE);
        
        d = dao.getDirectorById(6);
        assertNull(d);
        
    }

}
