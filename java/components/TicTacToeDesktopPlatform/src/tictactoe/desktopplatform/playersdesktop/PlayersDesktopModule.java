// ##########################################################################
// # File Name:	PlayersDesktopModule.java
// ##########################################################################

package tictactoe.desktopplatform.playersdesktop;

import tictactoe.client.playersclient.IPlayersController;
import tictactoe.client.playersclient.IPlayersModel;
import tictactoe.client.playersclient.IPlayersView;
import tictactoe.client.playersclient.PlayersClientModule;
import tictactoe.client.playersclient.PlayersController;
import tictactoe.client.playersclient.PlayersModel;

/****************************************************************************
 * 
 */
public 
class PlayersDesktopModule 
    extends PlayersClientModule
{

    /************************************************************************
     * Creates a new PlayersDesktopModule. 
     *
     */
    public 
    PlayersDesktopModule()
    {
        super( "PlayersDesktopModule" );

    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IPlayersView> 
    getPlayersViewType()
    {
        return SwtPlayersView.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IPlayersModel> 
    getPlayersModelType()
    {
        return PlayersModel.class;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IPlayersController> 
    getPlayersControllerType()
    {
        return PlayersController.class;
    }

}

// ##########################################################################
