// ##########################################################################
// # File Name:	PlayerNotifierModule.java
// ##########################################################################

package tictactoe.integration.playernotifier;

import tictactoe.service.playerservice.IPlayerEventListener;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

/****************************************************************************
 * 
 */
public 
class PlayerNotifierModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new PlayerNotifierModule. 
     *
     */
    public 
    PlayerNotifierModule()
    {
        super( "PlayerNotifierModule" );
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
                bindType(IPlayerEventListener.class)
                    .withKey( "PlayerEventRouter" )
                    .toType(PlayerEventRouter.class)
                    .withScope(new ThreadScope<IPlayerEventListener>()))
            .insertBinding( 
                bindType(IPlayerEventListener.class)
                    .withKey( "PlayerEventBroadcaster" )
                    .toType(PlayerEventBroadcaster.class)
                    .withScope(new ThreadScope<IPlayerEventListener>()));
    }

}

// ##########################################################################
