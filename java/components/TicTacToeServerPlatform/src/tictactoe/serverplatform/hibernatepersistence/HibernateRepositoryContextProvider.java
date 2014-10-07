// ##########################################################################
// # File Name:	HibernateRepositoryContextProvider.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import strata1.entity.hibernaterepository.DateTimeType;
import strata1.entity.hibernaterepository.HibernateRepositoryContext;
import strata1.entity.repository.IRepositoryContext;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.inject.Provider;

/****************************************************************************
 * 
 */
public 
class HibernateRepositoryContextProvider 
    implements Provider<IRepositoryContext>
{

    /************************************************************************
     * Creates a new HibernateRepositoryContextProvider. 
     *
     */
    public 
    HibernateRepositoryContextProvider() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IRepositoryContext 
    get()
    {
        Configuration configuration = new Configuration();

        configuration.configure();                    

        return 
            new HibernateRepositoryContext(
                configuration.buildSessionFactory(
                    new ServiceRegistryBuilder()
                        .applySettings(
                            configuration.getProperties())
                        .buildServiceRegistry()));
    }
    
}

// ##########################################################################
