/* File			:  JobDaoHibernate.java
 * Project		:  persistencemanager
 * Created on	:  3 Feb 2012
 */
package com.mango.mif.connector.dispatcher.dao;

import org.springframework.stereotype.Repository;

import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.core.dao.impl.GenericDaoHibernate;
/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Job objects.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Repository("jobDao")
public class JobDao extends GenericDaoHibernate<Job, Long> {
    /**
     * Constructor that sets the entity to Job.class.
     */
    public JobDao() {
        super(Job.class);
    }


}
