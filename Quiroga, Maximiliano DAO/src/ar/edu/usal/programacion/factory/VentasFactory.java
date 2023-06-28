package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.VentasDAO;
import ar.edu.usal.programacion.dao.impl.VentasDAOImpl;

public class VentasFactory {

    public static VentasDAO getArticuloFactory(String source) {
        return new VentasDAOImpl();
    }

}

