// ##########################################################################
// # File Name:	HibernatePersistenceModule.java
// ##########################################################################

package tictactoe.serverplatform.hibernatepersistence;

import tictactoe.domain.gamedomain.IGameRepositoryProvider;
import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.domain.playerdomain.IPlayerRepositoryProvider;
import tictactoe.domain.sessiondomain.ISessionRepositoryProvider;
import tictactoe.domain.sessiondomain.IUserRepositoryProvider;

import strata1.entity.repository.IRepositoryContext;

import javax.inject.Provider;

/****************************************************************************
 * 
 */
public 
class HibernatePersistenceModule 
    extends PersistenceModule
{

    /************************************************************************
     * Creates a new HibernatePersistenceModule. 
     *
     */
    public HibernatePersistenceModule()
    {
        super( "HibernatePersistenceModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Provider<IRepositoryContext> 
    getRepositoryContextProvider()
    {
        return new HibernateRepositoryContextProvider();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends ISessionRepositoryProvider> 
    getSessionRepositoryProviderType()
    {
        return HibernateSessionRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IUserRepositoryProvider> 
    getUserRepositoryProviderType()
    {
        return HibernateUserRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IPlayerRepositoryProvider> 
    getPlayerRepositoryProviderType()
    {
        return HibernatePlayerRepositoryProvider.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IGameRepositoryProvider> 
    getGameRepositoryProviderType()
    {
        return HibernateGameRepositoryProvider.class;
    }

}

// ##########################################################################
