// ##########################################################################
// # File Name:	PersistenceModule.java
// ##########################################################################

package tictactoe.domain.persistence;

import tictactoe.domain.gamedomain.IGameRepositoryProvider;
import tictactoe.domain.playerdomain.IPlayerRepositoryProvider;
import tictactoe.domain.sessiondomain.ISessionRepositoryProvider;
import tictactoe.domain.sessiondomain.IUserRepositoryProvider;

import strata1.entity.repository.IRepositoryContext;
import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

import javax.inject.Provider;

/****************************************************************************
 * 
 */
public abstract 
class PersistenceModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new PersistenceModule. 
     *
     * @param moduleName
     */
    protected 
    PersistenceModule(String moduleName)
    {
        super( moduleName );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        container
            .insertBinding( 
                bindType( IRepositoryContext.class )
                    .toProvider( getRepositoryContextProvider() )
                    .withScope( 
                        new ThreadScope<IRepositoryContext>() ) )
            .insertBinding( 
                bindType( ISessionRepositoryProvider.class )
                    .toType( getSessionRepositoryProviderType() )
                    .withScope( 
                        new ThreadScope<ISessionRepositoryProvider>() ) )
            .insertBinding( 
                bindType( IUserRepositoryProvider.class )
                    .toType( getUserRepositoryProviderType() )
                    .withScope( 
                        new ThreadScope<IUserRepositoryProvider>() ) )
            .insertBinding( 
                bindType( IPlayerRepositoryProvider.class )
                    .toType( getPlayerRepositoryProviderType() )
                    .withScope( 
                        new ThreadScope<IPlayerRepositoryProvider>() ) )
            .insertBinding( 
                bindType( IGameRepositoryProvider.class )
                    .toType( getGameRepositoryProviderType() )
                    .withScope( 
                        new ThreadScope<IGameRepositoryProvider>() ) );
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Provider<IRepositoryContext> 
    getRepositoryContextProvider();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends ISessionRepositoryProvider> 
    getSessionRepositoryProviderType();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IUserRepositoryProvider> 
    getUserRepositoryProviderType();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IPlayerRepositoryProvider> 
    getPlayerRepositoryProviderType();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IGameRepositoryProvider> 
    getGameRepositoryProviderType();

}

// ##########################################################################
