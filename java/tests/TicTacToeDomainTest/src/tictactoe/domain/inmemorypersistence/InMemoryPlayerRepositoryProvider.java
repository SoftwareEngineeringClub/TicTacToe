// ##########################################################################
// # File Name:	InMemoryPlayerRepositoryProvider.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.playerdomain.IPlayerRepositoryProvider;
import tictactoe.domain.playerdomain.Player;
import tictactoe.domain.sessiondomain.User;

import strata1.entity.inmemoryrepository.IEntityReplicator;
import strata1.entity.inmemoryrepository.InMemoryRepositoryContext;
import strata1.entity.inmemoryrepository.InMemoryRepositoryProvider;
import strata1.entity.repository.IKeyRetriever;
import strata1.entity.repository.IRepositoryContext;

import javax.inject.Inject;

import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public 
class InMemoryPlayerRepositoryProvider 
    extends    InMemoryRepositoryProvider<Long,Player> 
    implements IPlayerRepositoryProvider
{
    private static final Random theirGenerator = new SecureRandom();

    /************************************************************************
     * Creates a new InMemoryPlayerRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    InMemoryPlayerRepositoryProvider(IRepositoryContext context)
    {
        super( 
            Player.class,
            (InMemoryRepositoryContext)context,
            new IEntityReplicator<Long,Player>()
            {  
                {}
                @Override
                public Long 
                generateKey()
                {
                    return theirGenerator.nextLong();
                }

                @Override
                public Player 
                replicate(Player original)
                {
                    Player replicant = new Player(original);
                    
                    if ( mustGenerateKey( replicant.getPlayerId() ) )
                        replicant.setPlayerId( generateKey() );
                    
                    return replicant;
                }
                
                private boolean
                mustGenerateKey(Long oldKey)
                {
                    return
                        (oldKey == null ) ||
                        (oldKey == 0L);
                }

            },
            new IKeyRetriever<Long,Player>()
            {
                {}
                @Override
                public Long 
                getKey(Player player)
                {
                    return player.getPlayerId();
                }   
            }); 
    }
}

// ##########################################################################
