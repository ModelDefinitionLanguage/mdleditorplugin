
package com.mango.mif.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Preconditions;
import com.mango.mif.core.dao.GenericDao;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic
 *
 * SessionFactory will be setter injected by Spring
 * 
 * <p/>
 * <p>To register this class in the Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.mango.dao.hibernate.GenericDaoImpl"&gt;
 *          &lt;constructor-arg value="com.mango.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    
    private Class<T> persistentClass;
    private SessionFactory sessionFactory;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when sub-classing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;                               
    }

	/**
     * Return the session factory. 
     */
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

	/**
     * Set the session factory.
     */
    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get the current session active within the session factory.
     * Note: The Session object is NON-THREAD-SAFE and cannot be shared.
     * @return the current session
     */    
    private Session getCurrentSession() {    	
        return this.getSessionFactory().getCurrentSession();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + persistentClass.getName()).list();
    }       

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> getAllDistinct() {
        return new ArrayList(new LinkedHashSet(getAll()));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Preconditions.checkNotNull( id != null );
        return (T) getCurrentSession().get( persistentClass, id );        
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = (T) getCurrentSession().get( persistentClass, id );
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Preconditions.checkNotNull( object );
        return (T) getCurrentSession().merge( object );
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        final T entity = this.get( id );
        Preconditions.checkState( entity != null );
        this.delete( entity );
    }

    /**
     * {@inheritDoc}
     */
    public void delete( final T entity) {
        Preconditions.checkNotNull( entity );
        getCurrentSession().delete( entity );
    }       
}
