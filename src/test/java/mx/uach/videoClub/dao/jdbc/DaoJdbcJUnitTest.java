package mx.uach.videoClub.dao.jdbc;

import java.util.Date;
import java.util.List;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.enums.CRUD;
import mx.uach.videoclub.dao.jdbc.VideoDaoJDBC;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;
import mx.uach.videoclub.modelos.enums.EEstatusPrestamo;
import mx.uach.videoclub.modelos.enums.EGenero;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Paquete de pruebas del proyecto del videoclub
 *
 * @author BS
 */
public class DaoJdbcJUnitTest {
 
	/**
     * Pruebas unitarias para director
     * {@code Director}.
     */
    @Test
    public void testDirector(){
        VideoDao dao = new VideoDaoJDBC();
        Director d = new Director("Jose");
        dao.directorProcess(d, CRUD.CREATE);
        List<Director> dlist = (List) dao.getDirectoresByCriteria("nombre like 'Jose'");
        assertTrue(dlist.size() != 0);
        assertTrue(dlist.get(0).getNombre().equals("Jose"));
        
        Director d2 = dirs.get(0);
        d = (Director) dao.getDirectorById(d2.getId());
        assertTrue(d2.getNombre().equals(d.getNombre()));
        
        d.setNombre("Paris");
        dao.directorProcess(d, CRUD.UPDATE);
        d2 = (Director) dao.getDirectorById(d.getId());
        assertTrue(d2.getNombre().equals("Paris"));
        
        dao.directorProcess(d, CRUD.DELETE);
        d = (List) dao.getDirectoresByCriteria("nombre like 'Paris'");
        assertTrue(d.isEmpty());
        
    }
    
    /**
     * Pruebas unitarias para Actor 
     * {@code Actor}.
     */
    @Test
    public void testActor(){
        VideoDao dao = new VideoDaoJDBC();
        Actor act = new Actor("Ignacio", "Flores");
        dao.actorProcces(act, CRUD.CREATE);
        List<Actor> acts = (List) dao.getActoresByCriteria("nombre like 'Ignacio'");
        assertTrue(!acts.isEmpty());
        assertTrue(acts.get(0).getNombre().equals("Ignacio"));
        
        
        Actor act2 = acts.get(0);
        act = (Actor) dao.getActorById(act2.getId());
        assertTrue(act2.getNombre().equals(act.getNombre()));
        
        act.setNombre("Alejandro");
        dao.actorProcces(act, CRUD.UPDATE);
        act2 = (Actor) dao.getActorById(act.getId());
        assertTrue(act2.getNombre().equals("Alejandro"));
        
        dao.actorProcces(act, CRUD.DELETE);
        acts = (List) dao.getActoresByCriteria("nombre like 'Alejandro'");
        assertTrue(acts.isEmpty());
        
    }
    
    /**
     * Pruebas unitarias para Cinta 
     * {@code Cinta}.
     */
    @Test
    public void testCinta(){
        VideoDao dao = new VideoDaoJDBC();
        Pelicula pelicula = dao.getPeliculaById(1);
        Cinta cin = new Cinta(3, pelicula);
        dao.cintaProcces(cin, CRUD.CREATE);
        List<Cinta> cins = (List) dao.getCintasByCriteria("numero_copia = 3");
        assertTrue(!cins.isEmpty());
        assertTrue(cins.get(0).getNumeroCopia().equals(10));
        
        
        Cinta cin2 = cins.get(0);
        cin = (Cinta) dao.getCintaById(cin2.getId());
        assertTrue(cin2.getNumeroCopia().equals(cin.getNumeroCopia()));
        
        cin.setNumeroCopia(4);
        dao.cintaProcces(cin, CRUD.UPDATE);
        cin2 = (Cinta) dao.getCintaById(cin.getId());
        assertTrue(cin2.getNumeroCopia().equals(4));
        
        dao.cintaProcces(cin, CRUD.DELETE);
        cins = (List) dao.getCintasByCriteria("numero_copia = 4");
        assertTrue(cins.isEmpty());
        
    }
    
    /**
     * Pruebas unitarias para Ficha 
     * {@code Ficha}.
     */
    @Test
    public void testFicha(){
        VideoDao dao = new VideoDaoJDBC();
        Socio socio = dao.getSocioById(4);
        Ficha f = new Ficha(new Date(), socio);
        dao.fichaProcces(f, CRUD.CREATE);
        List<Ficha> fichas = (List) dao.getFichasByCriteria(String.format("socios_id = %s", socio.getId()));
        assertTrue(!fichas.isEmpty());
        assertTrue(fichas.get(0).getSocio().getId().equals(socio.getId()));
        
        
        Ficha f2 = fichas.get(0);
        f = (Ficha) dao.getFichaById(f2.getId());
        assertTrue(f2.getSocio().getId().equals(f.getSocio().getId()));
        
        socio = dao.getSocioById(3);
        f.setSocio(socio);
        dao.fichaProcces(f, CRUD.UPDATE);
        f2 = (Ficha) dao.getFichaById(f.getId());
        assertTrue(f2.getSocio().getId().equals(3));
        
        dao.fichaProcces(f, CRUD.DELETE);
        fichas = (List) dao.getFichasByCriteria(String.format("socios_id = %s", socio.getId()));
        assertTrue(fichas.isEmpty());    
    }
    
    /**
     * Pruebas unitarias para Lista 
     * {@code Lista}.
     */
    @Test
    public void testLista(){
        VideoDao dao = new VideoDaoJDBC();
        Socio socio = dao.getSocioById(2);
        Pelicula pelicula = dao.getPeliculaById(1);
        Lista l = new Lista(new Date(), new Date(), Boolean.TRUE, socio, pelicula);
        dao.listaProcces(l, CRUD.CREATE);
        List<Lista> listas = (List) dao.getListasByCriteria(String.format("socios_id = %s", socio.getId()));
        assertTrue(!listas.isEmpty());
        assertTrue(listas.get(0).getSocio().getId().equals(socio.getId()));
        
        
        Lista lis2 = listas.get(0);
        l = (Lista) dao.getListaById(lis2.getId());
        assertTrue(lis2.getSocio().getId().equals(lis.getSocio().getId()));
        
        socio = dao.getSocioById(3);
        l.setSocio(socio);
        dao.listaProcces(l, CRUD.UPDATE);
        lis2 = (Lista) dao.getListaById(l.getId());
        assertTrue(lis2.getSocio().getId().equals(3));
        
        dao.listaProcces(l, CRUD.DELETE);
        listas = (List) dao.getListasByCriteria(String.format("socios_id = %s", socio.getId()));
        assertTrue(listas.isEmpty());    
    }
    
    /**
     * Pruebas unitarias para Pelicula 
     * {@code Pelicula}.
     */
    @Test
    public void testPelicula(){
        VideoDao dao = new VideoDaoJDBC();
        Director dir = dao.getDirectorById(1);
        Pelicula p = new Pelicula("conjuro", EGenero.TERROR, 102, dir);
        dao.peliculaProcces(p, CRUD.CREATE);
        List<Pelicula> peliculas = dao.getPeliculaByCriteria("titulo like 'conjuro'");
        assertTrue(!peliculas.isEmpty());
        assertTrue(peliculas.get(0).getTitulo().equals("conjuro"));
        
        
        Pelicula p2 = peliculas.get(0);
        p = dao.getPeliculaById(p2.getId());
        assertTrue(p2.getTitulo().equals(p.getTitulo()));
        
        p.setTitulo("club de la pelea");
        dao.peliculaProcces(p, CRUD.UPDATE);
        p2 = dao.getPeliculaById(p.getId());
        assertTrue(p2.getTitulo().equals("club de la pelea"));
        
        dao.peliculaProcces(p, CRUD.DELETE);
        peliculas = dao.getPeliculaByCriteria("titulo like 'club de la pelea'");
        assertTrue(peliculas.isEmpty());    
    }
    
    /**
     * Pruebas unitarias de Prestamo
     * {@code Prestamo}.
     */
    @Test
    public void testPrestamo(){
        VideoDao dao = new VideoDaoJDBC();
        Ficha f = dao.getFichaById(10);
        Cinta c = dao.getCintaById(1);
        Prestamo p = new Prestamo(new Date(), EEstatusPrestamo.E, f, c);
        dao.prestamoProcces(p, CRUD.CREATE);
        List<Prestamo> prestamos = dao.getPrestamosByCriteria(String.format("fichas_id = %s", f.getId()));
        assertTrue(!prestamos.isEmpty());
        assertTrue(prestamos.get(0).getFicha().getId().equals(10));
        
        
        Prestamo p2 = prestamos.get(0);
        p = dao.getPrestamoById(p2.getId());
        assertTrue(p2.getFicha().getId().equals(p.getFicha().getId()));
        
        f = dao.getFichaById(12);
        p.setFicha(f);
        dao.prestamoProcces(p, CRUD.UPDATE);
        p2 = dao.getPrestamoById(p.getId());
        assertTrue(p2.getFicha().getId().equals(12));
        
        dao.prestamoProcces(p, CRUD.DELETE);
        prestamos = dao.getPrestamosByCriteria(String.format("fichas_id = %s", f.getId()));
        assertTrue(prestamos.isEmpty());    
    }
    
    /**
     * Pruebas unitarias de Socio 
     * {@code Socio}.
     */
    @Test
    public void testSocio(){
        VideoDao dao = new VideoDaoJDBC();
        Socio s = new Socio("Luis", "Ramirez","351");
        dao.socioProcces(s, CRUD.CREATE);
        List<Socio> socios = dao.getSociosByCriteria("nombre like 'Luis'");
        assertTrue(!socios.isEmpty());
        assertTrue(socios.get(0).getNombre().equals("Luis"));
        
        
        Socio s2 = socios.get(0);
        socio = dao.getSocioById(socio2.getId());
        assertTrue(socio2.getNombre().equals(s.getNombre()));
        
        s.setNombre("Aristides");
        dao.socioProcces(s, CRUD.UPDATE);
        s2 = dao.getSocioById(s.getId());
        assertTrue(s2.getNombre().equals("Aristides"));
        
        dao.socioProcces(s, CRUD.DELETE);
        socios = dao.getSociosByCriteria("nombre like 'Luis'");
        assertTrue(socios.isEmpty());    
    }

}
