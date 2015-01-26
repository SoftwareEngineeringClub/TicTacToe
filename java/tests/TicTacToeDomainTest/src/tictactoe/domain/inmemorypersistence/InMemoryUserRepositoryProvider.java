// ##########################################################################
// # File Name:	InMemoryUserRepositoryProvider.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.sessiondomain.IUserRepositoryProvider;
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
class InMemoryUserRepositoryProvider 
    extends    InMemoryRepositoryProvider<Long,User> 
    implements IUserRepositoryProvider
{
    private static final Random theirGenerator = new SecureRandom();

    /************************************************************************
     * Creates a new InMemoryUserRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    InMemoryUserRepositoryProvider(IRepositoryContext context)
    {
        super( 
            User.class,
            (InMemoryRepositoryContext)context,
            new IEntityReplicator<Long,User>()
            {  
                {}
                @Override
                public Long 
                generateKey()
                {
                    return theirGenerator.nextLong();
                }

                @Override
                public User 
                replicate(User original)
                {
                    User replicant = new User(original);
                    
                    if ( mustGenerateKey( replicant.getUserId() ) )
                        replicant.setUserId( generateKey() );
                    
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
            new IKeyRetriever<Long,User>()
            {
                {}
                @Override
                public Long 
                getKey(User user)
                {
                    return user.getUserId();
                }   
            }); 
    }
}

// ##########################################################################
