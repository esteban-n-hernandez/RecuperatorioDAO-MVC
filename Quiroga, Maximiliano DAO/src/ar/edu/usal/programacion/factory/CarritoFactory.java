package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.CarritoDAO;
import ar.edu.usal.programacion.dao.impl.CarritoDAOImpl;

public class CarritoFactory {


    public static CarritoDAO getArticuloFactory(String source) {
        return new CarritoDAOImpl();
    }

}
