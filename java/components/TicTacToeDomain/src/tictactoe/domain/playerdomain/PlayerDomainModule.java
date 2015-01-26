// ##########################################################################
// # File Name:	PlayerDomainModule.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

/****************************************************************************
 * 
 */
public 
class PlayerDomainModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new PlayerDomainModule. 
     *
     */
    public 
    PlayerDomainModule()
    {
        super( "PlayerDomainModule" );
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
                bindType( IPlayerRepository.class )
                    .toType( PlayerRepository.class )
                    .withScope( new ThreadScope<IPlayerRepository>() ) )
            .insertBinding( 
                bindType( IChallengeRepository.class )
                    .toType( ChallengeRepository.class )
                    .withScope( new ThreadScope<IChallengeRepository>() ) );
    }

}

// ##########################################################################
