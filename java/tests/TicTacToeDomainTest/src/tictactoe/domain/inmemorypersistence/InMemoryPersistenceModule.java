// ##########################################################################
// # File Name:	SessionDomainTestModule.java
// ##########################################################################

package tictactoe.domain.inmemorypersistence;

import tictactoe.domain.gamedomain.IGameRepositoryProvider;
import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.domain.playerdomain.IChallengeRepositoryProvider;
import tictactoe.domain.playerdomain.IPlayerRepositoryProvider;
import tictactoe.domain.sessiondomain.ISessionRepositoryProvider;
import tictactoe.domain.sessiondomain.IUserRepositoryProvider;

import strata1.entity.repository.IRepositoryContext;

import javax.inject.Provider;

/****************************************************************************
 * 
 */
public 
class InMemoryPersistenceModule 
    extends PersistenceModule
{

    /************************************************************************
     * Creates a new SessionDomainTestModule. 
     *
     * @param moduleName
     */
    public 
    InMemoryPersistenceModule()
    {
        super( "InMemoryPersistenceModule" );

    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Provider<IRepositoryContext> 
    getRepositoryContextProvider()
    {
        return new InMemoryRepositoryContextProvider();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends ISessionRepositoryProvider> 
    getSessionRepositoryProviderType()
    {
        return InMemorySessionRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IUserRepositoryProvider> 
    getUserRepositoryProviderType()
    {
        return InMemoryUserRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IPlayerRepositoryProvider> 
    getPlayerRepositoryProviderType()
    {
        return InMemoryPlayerRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IChallengeRepositoryProvider> 
    getChallengeRepositoryProviderType()
    {
        return InMemoryChallengeRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IGameRepositoryProvider> 
    getGameRepositoryProviderType()
    {
        return InMemoryGameRepositoryProvider.class;
    }

}

// ##########################################################################
