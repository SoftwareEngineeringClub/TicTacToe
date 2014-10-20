// ##########################################################################
// # File Name:	GameClientModule.java
// ##########################################################################

package tictactoe.client.gameclient;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

/****************************************************************************
 * 
 */
public abstract 
class GameClientModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new GameClientModule. 
     *
     * @param name
     */
    public 
    GameClientModule(String name)
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
                bindType(IGameController.class)
                    .toType(getGameControllerType())
                    .withScope(new SingletonScope<IGameController>()));
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IGameController>
    getGameControllerType();
}

// ##########################################################################
