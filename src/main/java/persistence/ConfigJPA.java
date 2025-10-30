package persistence;

import jakarta.persistence.*;

public class ConfigJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public static EntityManager getEM(){
        return emf.createEntityManager();
    }

    public static void closeEM(){
        emf.close();
    }
}
