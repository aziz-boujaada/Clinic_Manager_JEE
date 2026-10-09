package Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class DatabaseConfig {

    private final EntityManagerFactory entityManagerFactory;

    private DatabaseConfig() {
        this.entityManagerFactory =
                Persistence.createEntityManagerFactory("clinicPU");
    }

    private static class SingletonHolder {
        private static final DatabaseConfig INSTANCE = new DatabaseConfig();
    }

    public static DatabaseConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void close() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}