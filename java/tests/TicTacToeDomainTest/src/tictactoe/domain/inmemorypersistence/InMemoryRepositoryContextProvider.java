// ##########################################################################
// # File Name:	InMemoryRepositoryContextProvider.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.playerdomain.Challenge;
import tictactoe.domain.playerdomain.Player;
import tictactoe.domain.sessiondomain.Session;
import tictactoe.domain.sessiondomain.User;

import strata1.entity.inmemoryrepository.IPredicate;
import strata1.entity.inmemoryrepository.InMemoryFinder;
import strata1.entity.inmemoryrepository.InMemoryRepositoryContext;
import strata1.entity.repository.IRepositoryContext;

import javax.inject.Provider;

import java.util.Map;

/****************************************************************************
 * 
 */
public 
class InMemoryRepositoryContextProvider 
    implements Provider<IRepositoryContext>
{

    /************************************************************************
     * Creates a new InMemoryRepositoryContextProvider. 
     *
     */
    public 
    InMemoryRepositoryContextProvider() {}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IRepositoryContext 
    get()
    {
        InMemoryRepositoryContext context = 
            new InMemoryRepositoryContext();

        new InMemoryFinder<Session>(
            context, 
            "getSessionFor", 
            Session.class, 
            new IPredicate<Session>() 
            {
                {}
                
                @Override
                public boolean 
                evaluate(Session session,Map<String,Object> parameters)
                {
                    Long userId = (Long)parameters.get( "userId" );
                    
                    return session.getUserId().equals( userId );
                }
            });

        new InMemoryFinder<User>(
            context, 
            "getUsers", 
            User.class, 
            new IPredicate<User>() 
            {
                {}
                
                @Override
                public boolean 
                evaluate(User user,Map<String,Object> parameters)
                {
                    return true;
                }
            });

        
        new InMemoryFinder<Player>(
            context, 
            "getPlayerWithUserId", 
            Player.class, 
            new IPredicate<Player>() 
            {
                {}
                
                @Override
                public boolean 
                evaluate(Player player,Map<String,Object> parameters)
                {
                    Long userId = (Long)parameters.get( "userId" );
                    
                    return player.getUserId() == userId;
                }
            });

        new InMemoryFinder<Challenge>(
            context, 
            "getChallenges", 
            Challenge.class, 
            new IPredicate<Challenge>() 
            {
                {}
                
                @Override
                public boolean 
                evaluate(Challenge challenge,Map<String,Object> parameters)
                {
                    return true;
                }
            });

        new InMemoryFinder<Challenge>(
            context, 
            "getPendingChallengeFor", 
            Challenge.class, 
            new IPredicate<Challenge>() 
            {
                {}
                
                @Override
                public boolean 
                evaluate(Challenge challenge,Map<String,Object> parameters)
                {
                    Long challengerUserId = (Long)parameters.get( "challengerUserId" );
                    Long challengedUserId = (Long)parameters.get( "challengedUserId" );
                    
                   return 
                       challenge.getChallengerUserId().equals( challengerUserId ) &&
                       challenge.getChallengedUserId().equals( challengedUserId );
                }
            });

        new InMemoryFinder<Challenge>(
            context, 
            "hasPendingChallengeFor", 
            Challenge.class, 
            new IPredicate<Challenge>() 
            {
                {}
                
                @Override
                public boolean 
                evaluate(Challenge challenge,Map<String,Object> parameters)
                {
                    Long challengerUserId = (Long)parameters.get( "challengerUserId" );
                    Long challengedUserId = (Long)parameters.get( "challengedUserId" );
                    
                   return 
                       challenge.getChallengerUserId().equals( challengerUserId ) &&
                       challenge.getChallengedUserId().equals( challengedUserId );
                }
            });

        return context;
    }

}

// ##########################################################################
