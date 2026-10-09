package Config;

import jakarta.persistence.EntityManager;

public class DatabaseTest {

    public static void main(String[] args) {

        EntityManager em = null;

        try {
            DatabaseConfig config = DatabaseConfig.getInstance();

            em = config.createEntityManager();

            System.out.println("Database connection successful!");

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}