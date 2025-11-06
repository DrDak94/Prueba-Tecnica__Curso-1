package persistence;

import jakarta.persistence.*;

public class ConfigJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public static EntityManager getEMF(){
        return emf.createEntityManager();
    }

    public static void closeEMF(){
        emf.close();
    }
}
