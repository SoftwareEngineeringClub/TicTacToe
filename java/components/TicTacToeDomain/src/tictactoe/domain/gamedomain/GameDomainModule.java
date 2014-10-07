// ##########################################################################
// # File Name:	GameDomainModule.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

/****************************************************************************
 * 
 */
public 
class GameDomainModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new GameDomainModule. 
     *
     */
    public 
    GameDomainModule()
    {
        super( "GameDomainModule" );
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
                bindType( IGameRepository.class )
                    .toType( GameRepository.class )
                    .withScope( new ThreadScope<IGameRepository>() ) );
    }

}

// ##########################################################################
