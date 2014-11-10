// ##########################################################################
// # File Name:	PlayerAppModule.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.service.playerservice.IPlayerService;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.ThreadScope;

/****************************************************************************
 * 
 */
public 
class PlayerAppModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new PlayerAppModule. 
     *
     * @param arg0
     */
    public 
    PlayerAppModule()
    {
        super( "PlayerAppModule" );

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
                bindType(IPlayerProcessor.class)
                    .toType(PlayerProcessor.class)
                    .withScope( new ThreadScope<IPlayerProcessor>() ) )
            .insertBinding( 
                bindType(IPlayerService.class)
                    .withKey( "PlayerServiceImplementation" )
                    .toType(PlayerServiceImp.class)
                    .withScope( new ThreadScope<IPlayerService>() ) );
    }

}

// ##########################################################################
