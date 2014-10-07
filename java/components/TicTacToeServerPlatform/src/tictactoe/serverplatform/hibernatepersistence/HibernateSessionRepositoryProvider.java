// ##########################################################################
// # File Name:	HibernateSessionRepositoryProvider.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import tictactoe.domain.sessiondomain.ISessionRepositoryProvider;
import tictactoe.domain.sessiondomain.Session;

import strata1.entity.hibernaterepository.HibernateRepositoryContext;
import strata1.entity.hibernaterepository.HibernateRepositoryProvider;
import strata1.entity.repository.IRepositoryContext;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class HibernateSessionRepositoryProvider 
    extends    HibernateRepositoryProvider<Long,Session> 
    implements ISessionRepositoryProvider
{

    /************************************************************************
     * Creates a new HibernateSessionRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    HibernateSessionRepositoryProvider(IRepositoryContext context)
    {
        super( Session.class,(HibernateRepositoryContext)context );
        
    }



}

// ##########################################################################
