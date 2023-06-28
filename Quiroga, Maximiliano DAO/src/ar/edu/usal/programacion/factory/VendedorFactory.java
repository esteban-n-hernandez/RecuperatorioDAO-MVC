package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.VendedorDAO;
import ar.edu.usal.programacion.dao.impl.VendedorDAOImpl;

public class VendedorFactory {


    public static VendedorDAO getArticuloFactory(String source) {
        return new VendedorDAOImpl();
    }

}
