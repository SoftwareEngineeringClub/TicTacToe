// ##########################################################################
// # File Name:	InMemorySessionRepositoryProvider.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.sessiondomain.ISessionRepositoryProvider;
import tictactoe.domain.sessiondomain.Session;
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
class InMemorySessionRepositoryProvider 
    extends    InMemoryRepositoryProvider<Long,Session> 
    implements ISessionRepositoryProvider
{
    private static final Random theirGenerator = new SecureRandom();
    
    /************************************************************************
     * Creates a new InMemorySessionRepositoryProvider. 
     *
     * @param context
     */
    @Inject
    public 
    InMemorySessionRepositoryProvider(IRepositoryContext context)
    {
        super( 
            Session.class,
            (InMemoryRepositoryContext)context,
            new IEntityReplicator<Long,Session>()
            {  
                {}
                @Override
                public Long 
                generateKey()
                {
                    return theirGenerator.nextLong();
                }

                @Override
                public Session 
                replicate(Session original)
                {
                    Session replicant = new Session(original);
                    
                    if ( mustGenerateKey( replicant.getSessionId() ) )
                        replicant.setSessionId( generateKey() );
                    
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
            new IKeyRetriever<Long,Session>()
            {
                {}
                @Override
                public Long 
                getKey(Session session)
                {
                    return session.getSessionId();
                }   
            } ); 
    }
}

// ##########################################################################
