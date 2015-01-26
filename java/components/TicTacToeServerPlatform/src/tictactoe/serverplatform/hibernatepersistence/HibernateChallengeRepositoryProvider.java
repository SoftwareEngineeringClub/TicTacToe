// ##########################################################################
// # File Name:	HibernateChallengeRepositoryProvider.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import tictactoe.domain.playerdomain.Challenge;
import tictactoe.domain.playerdomain.IChallengeRepositoryProvider;
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
class HibernateChallengeRepositoryProvider 
    extends    HibernateRepositoryProvider<Long,Challenge>
    implements IChallengeRepositoryProvider
{
    /************************************************************************
     * Creates a new HibernateChallengeRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    HibernateChallengeRepositoryProvider(IRepositoryContext context)
    {
        super( Challenge.class,(HibernateRepositoryContext)context );
    }

}

// ##########################################################################
