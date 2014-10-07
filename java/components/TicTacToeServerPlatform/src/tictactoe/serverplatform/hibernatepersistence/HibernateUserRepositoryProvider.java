// ##########################################################################
// # File Name:	HibernateUserRepositoryProvider.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import tictactoe.domain.sessiondomain.IUserRepositoryProvider;
import tictactoe.domain.sessiondomain.User;

import strata1.entity.hibernaterepository.HibernateRepositoryContext;
import strata1.entity.hibernaterepository.HibernateRepositoryProvider;
import strata1.entity.repository.IRepositoryContext;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class HibernateUserRepositoryProvider 
    extends    HibernateRepositoryProvider<Long,User> 
    implements IUserRepositoryProvider
{

    /************************************************************************
     * Creates a new HibernateUserRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    HibernateUserRepositoryProvider(IRepositoryContext context)
    {
        super( User.class,(HibernateRepositoryContext)context );  
    }
}

// ##########################################################################
