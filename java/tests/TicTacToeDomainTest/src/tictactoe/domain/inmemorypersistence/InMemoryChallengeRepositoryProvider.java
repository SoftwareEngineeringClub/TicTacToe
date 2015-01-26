// ##########################################################################
// # File Name:	InMemoryPlayerRepositoryProvider.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.playerdomain.Challenge;
import tictactoe.domain.playerdomain.IChallengeRepositoryProvider;
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
class InMemoryChallengeRepositoryProvider 
    extends    InMemoryRepositoryProvider<Long,Challenge> 
    implements IChallengeRepositoryProvider
{
    private static final Random theirGenerator = new SecureRandom();

    /************************************************************************
     * Creates a new InMemoryPlayerRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    InMemoryChallengeRepositoryProvider(IRepositoryContext context)
    {
        super( 
            Challenge.class,
            (InMemoryRepositoryContext)context,
            new IEntityReplicator<Long,Challenge>()
            {  
                {}
                @Override
                public Long 
                generateKey()
                {
                    return theirGenerator.nextLong();
                }

                @Override
                public Challenge 
                replicate(Challenge original)
                {
                    Challenge replicant = new Challenge(original);
                    
                    if ( mustGenerateKey( replicant.getChallengeId() ) )
                        replicant.setChallengeId( generateKey() );
                    
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
            new IKeyRetriever<Long,Challenge>()
            {
                {}
                @Override
                public Long 
                getKey(Challenge challenge)
                {
                    return challenge.getChallengeId();
                }   
            }); 
    }
}

// ##########################################################################
