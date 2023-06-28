package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.TarjetaDAO;
import ar.edu.usal.programacion.dao.impl.TarjetaDAOImpl;

public class TarjetaFactory {


    public static TarjetaDAO getArticuloFactory(String source) {
        return new TarjetaDAOImpl();
    }

}
