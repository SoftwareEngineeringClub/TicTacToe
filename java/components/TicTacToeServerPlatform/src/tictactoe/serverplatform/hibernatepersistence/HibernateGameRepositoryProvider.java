// ##########################################################################
// # File Name:	HibernateGameRepositoryProvider.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import tictactoe.domain.gamedomain.IGameRepositoryProvider;
import tictactoe.domain.gamedomain.Game;

import strata1.entity.hibernaterepository.HibernateRepositoryContext;
import strata1.entity.hibernaterepository.HibernateRepositoryProvider;
import strata1.entity.repository.IRepositoryContext;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class HibernateGameRepositoryProvider 
    extends    HibernateRepositoryProvider<Long,Game>
    implements IGameRepositoryProvider
{
    /************************************************************************
     * Creates a new HibernateGameRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    HibernateGameRepositoryProvider(IRepositoryContext context)
    {
        super( Game.class,(HibernateRepositoryContext)context );
    }

}

// ##########################################################################
