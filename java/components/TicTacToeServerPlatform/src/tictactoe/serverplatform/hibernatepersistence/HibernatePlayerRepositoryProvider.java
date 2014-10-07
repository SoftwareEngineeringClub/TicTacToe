// ##########################################################################
// # File Name:	HibernatePlayerRepositoryProvider.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import tictactoe.domain.playerdomain.IPlayerRepositoryProvider;
import tictactoe.domain.playerdomain.Player;

import strata1.entity.hibernaterepository.HibernateRepositoryContext;
import strata1.entity.hibernaterepository.HibernateRepositoryProvider;
import strata1.entity.repository.IRepositoryContext;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class HibernatePlayerRepositoryProvider 
    extends    HibernateRepositoryProvider<Long,Player>
    implements IPlayerRepositoryProvider
{
    /************************************************************************
     * Creates a new HibernatePlayerRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    HibernatePlayerRepositoryProvider(IRepositoryContext context)
    {
        super( Player.class,(HibernateRepositoryContext)context );
    }

}

// ##########################################################################
