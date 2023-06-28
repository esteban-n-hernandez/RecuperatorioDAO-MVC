package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.ClienteDAO;
import ar.edu.usal.programacion.dao.impl.ClienteDAOImpl;

public class ClienteFactory  {

    public static ClienteDAO getArticuloFactory(String source) {
        return new ClienteDAOImpl();
    }

}