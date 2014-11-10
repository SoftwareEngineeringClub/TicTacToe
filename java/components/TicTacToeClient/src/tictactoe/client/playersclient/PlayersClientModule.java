// ##########################################################################
// # File Name:	GameClientModule.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.client.homeclient.IHomeController;
import tictactoe.client.homeclient.IHomeModel;
import tictactoe.client.homeclient.IHomeView;

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
                bindType(IPlayersView.class)
                    .toType(getPlayersViewType())
                    .withScope(new SingletonScope<IPlayersView>()))
            .insertBinding( 
                bindType(IPlayersModel.class)
                    .toType(getPlayersModelType())
                    .withScope(new SingletonScope<IPlayersModel>()))
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
    protected abstract Class<? extends IPlayersView>
    getPlayersViewType();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IPlayersModel>
    getPlayersModelType();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IPlayersController>
    getPlayersControllerType();
}

// ##########################################################################
