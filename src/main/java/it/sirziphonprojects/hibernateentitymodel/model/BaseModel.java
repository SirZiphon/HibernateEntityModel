package it.sirziphonprojects.hibernateentitymodel.model;

import com.sun.istack.NotNull;
import it.sirziphonprojects.hibernateentitymodel.connector.HibernateBaseConnector;
import it.sirziphonprojects.hibernateentitymodel.entity.KeyMapper;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

/**
 * This class will be used as base for the implementation of all entities models
 * class
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.1.0
 * @param <T> the Entity class
 */
public abstract class BaseModel<T extends KeyMapper> {

    protected List<T> list;

    private final HibernateBaseConnector hibernateConnector;
    private final Class<T> classType;
    private final String updateListNamedQuery;
    private final String selectElementByPkNamedQuery;
    private final String deleteElementByPkNamedQuery;

    /**
     * constructor with parameters
     * if at least one of the passed parameters is null, an IllegalArgumentException is throw
     *
     * @param classType
     * @param updateListNamedQuery
     * @param selectElementByPkNamedQuery
     * @param deleteElementByPkNamedQuery
     * @param hibernateConnector
     */
    protected BaseModel(Class<T> classType, String updateListNamedQuery, String selectElementByPkNamedQuery, String deleteElementByPkNamedQuery, HibernateBaseConnector hibernateConnector) throws IllegalArgumentException {
        // check parameters
        if (classType == null || updateListNamedQuery == null || selectElementByPkNamedQuery == null || deleteElementByPkNamedQuery == null || hibernateConnector == null) {
            String message = "";
            message += classType == null ? " - classType\n" : "";
            message += updateListNamedQuery == null ? " - updateListNamedQuery\n" : "";
            message += selectElementByPkNamedQuery == null ? " - selectElementByPkNamedQuery\n" : "";
            message += deleteElementByPkNamedQuery == null ? " - deleteElementByPkNamedQuery\n" : "";
            message += hibernateConnector == null ? " - hibernateConnector" : "";

            throw new IllegalArgumentException("the follow parameters are null: \n" + message);
        }

        this.hibernateConnector = hibernateConnector;
        this.classType = classType;
        this.updateListNamedQuery = updateListNamedQuery;
        this.selectElementByPkNamedQuery = selectElementByPkNamedQuery;
        this.deleteElementByPkNamedQuery = deleteElementByPkNamedQuery;
        this.updateList();
    }

    /**
     * this method return the current list of entities; if update is <code>true</code>, the list will be updated
     * before the return
     *
     * @param update
     * @return List of T entities
     */
    public List<T> getList(boolean update) {
        if (update)
            this.updateList();

        return this.list;
    }

    /**
     * this method update the list with the database dates
     */
    private void updateList() {
        // get the session
        try (Session session = this.hibernateConnector.getSessionFactory().openSession()) {
            session.beginTransaction();

            this.list = session.createNamedQuery(this.updateListNamedQuery, this.classType).getResultList();

            session.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * this method return the searched element; if no element match the passed
     * key, <code>null</code> will be return.
     * if the the param searchInLocal is true, the element will be searched into the current list present
     * into the class; this for reduce the access requests to the database
     *
     * @param keyMap the param to search the element
     * @param searchInLocal
     * @return the element searched or <code>null</code>
     */
    @NotNull
    public T getElement(Map<String, Object> keyMap, boolean searchInLocal) {
        // check the parameters
        if (keyMap == null || keyMap.isEmpty())
            return null;

        // execute the right choice
        if (searchInLocal)
            return this.searchInLocal(keyMap);
        else
            return this.searchIntoDatabase(keyMap);
    }

    /**
     * this method execute the search directly into the database
     *
     * @param keyMap
     * @return
     */
    @NotNull
    private T searchIntoDatabase(Map<String, Object> keyMap) {
        T searchedElement = null;

        // get the session
        try (Session session = this.hibernateConnector.getSessionFactory().openSession()) {
            session.beginTransaction();

            final Query<T> query = session.createNamedQuery(this.selectElementByPkNamedQuery, this.classType);

            keyMap.forEach((key, value) -> {
                query.setParameter(key, value);
            });

            searchedElement = query.uniqueResult();

            session.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            System.err.println(ex);
        }

        return searchedElement;
    }

    /**
     * this method execute the search into the local list
     *
     * @param keyMap
     * @return
     */
    @NotNull
    private T searchInLocal(Map<String, Object> keyMap) {
        // check if the list exist
        if (this.list == null)
            return null;

        // search the element into the list
        for (T element : this.list) {
            if (element.equals(keyMap))
                return element;
        }

        return null;
    }

    /**
     * this method insert the passed element into the db; return <code>true</code>
     * if the element is successfully inserted, <code>false</code> otherwise
     *
     * @param element the element to insert
     * @return <code>true</code> for successfully insertion, <code>false</code> otherwise
     */
    @NotNull
    public boolean insertElement(T element) {
        // check the parameter
        if (element == null)
            return false;

        // get the session
        try (Session session = this.hibernateConnector.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(element);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(ex);
            return false;
        }

        this.list.add(element);
        return true;
    }

    /**
     * this method delete the passed element from the db; return <code>true</code>
     * if the element is successfully deleted, <code>false</code> otherwise
     *
     * @param element the element to delete
     * @return <code>true</code> for successfully deletion, <code>false</code> otherwise
     */
    @NotNull
    public boolean deleteElement(T element) {
        // check the param
        if ( element == null )
            return false;

        return this.deleteElement(element.getAsMap());
    }

    /**
     * this method update the passed element; return <code>true</code>
     * if the element is successfully changed, <code>false</code> otherwise
     *
     * @param updateElement
     * @return <code>true</code> if the element is successfully changed, <code>false</code> otherwise
     */
    @NotNull
    public boolean editElement(T updateElement) {
        // check the param
        if (updateElement == null)
            return false;

        // get the session
        try (Session session = this.hibernateConnector.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(updateElement);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(ex);
            return false;
        }

        // update the element into the local list
        final int pointer = this.list.indexOf(updateElement);

        if (pointer > -1)
            this.list.set(pointer, updateElement);

        return true;
    }

    /**
     * variation of the deleteElement(T element), in this case the element is delete
     * using only the primary key
     *
     * @param keyMap the primary keys of the element to delete
     * @return  <code>true</code> for successfully deletion, <code>false</code> otherwise
     */
    @NotNull
    public boolean deleteElement(Map<String, Object> keyMap) {
        // check the parameter
        if (keyMap == null || keyMap.isEmpty())
            return false;

        int deletedRows = 0;

        // get the session
        try (Session session = this.hibernateConnector.getSessionFactory().openSession()) {
            session.beginTransaction();

            final Query query = session.createNamedQuery(this.deleteElementByPkNamedQuery);

            keyMap.forEach((key, value) -> query.setParameter(key, value));

            deletedRows = query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(ex);
            return false;
        }

        // check the result
        switch (deletedRows) {
            case 0:
                // no row deleted
                return false;

            case 1:
                // row deleted
                return true;

            default:
                // more then one row deleted
                return true;
        }
    }
}