package it.sirziphonprojects.hibernateentitymodel.connector;

import org.hibernate.SessionFactory;

/**
 * Interface for hibernate bootstrap classes
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
 */
public interface HibernateBaseConnector {

    /**
     * This method return a SessionFactory instance
     *
     * @return the SessionFactory instance
     */
    public SessionFactory getSessionFactory();

    /**
     * this method close the db connection
     */
    public void shutdown();
}
