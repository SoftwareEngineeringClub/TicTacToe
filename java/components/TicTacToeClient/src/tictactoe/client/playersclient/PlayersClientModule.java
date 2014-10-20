// ##########################################################################
// # File Name:	GameClientModule.java
// ##########################################################################

package tictactoe.client.playersclient;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

/****************************************************************************
 * 
 */
public abstract 
class PlayersClientModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new GameClientModule. 
     *
     * @param name
     */
    public 
    PlayersClientModule(String name)
    {
        super( name );
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
                bindType(IPlayersController.class)
                    .toType(getPlayersControllerType())
                    .withScope(new SingletonScope<IPlayersController>()));
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IPlayersController>
    getPlayersControllerType();
}

// ##########################################################################
