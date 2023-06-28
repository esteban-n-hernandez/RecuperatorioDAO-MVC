package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.ArticuloDAO;
import ar.edu.usal.programacion.dao.impl.ArticuloDAOImpl;

public class ArticuloFactory  {
    public static ArticuloDAO getArticuloFactory(String source) {
        return new ArticuloDAOImpl();
    }
}


