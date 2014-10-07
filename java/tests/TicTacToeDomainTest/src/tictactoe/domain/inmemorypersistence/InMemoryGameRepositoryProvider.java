// ##########################################################################
// # File Name:	InMemoryGameRepositoryProvider.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.gamedomain.IGameRepositoryProvider;
import tictactoe.domain.gamedomain.Game;
import tictactoe.domain.playerdomain.Player;

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
class InMemoryGameRepositoryProvider 
    extends    InMemoryRepositoryProvider<Long,Game> 
    implements IGameRepositoryProvider
{
    private static final Random theirGenerator = new SecureRandom();

    /************************************************************************
     * Creates a new InMemoryGameRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    InMemoryGameRepositoryProvider(IRepositoryContext context)
    {
        super( 
            Game.class,
            (InMemoryRepositoryContext)context,
            new IEntityReplicator<Long,Game>()
            {  
                {}
                @Override
                public Long 
                generateKey()
                {
                    return theirGenerator.nextLong();
                }

                @Override
                public Game 
                replicate(Game original)
                {
                    Game replicant = new Game(original);
                    
                    if ( mustGenerateKey( replicant.getGameId() ) )
                        replicant.setGameId( generateKey() );
                    
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
            new IKeyRetriever<Long,Game>()
            {
                {}
                @Override
                public Long 
                getKey(Game game)
                {
                    return game.getGameId();
                }   
            }); 
    }
}

// ##########################################################################
