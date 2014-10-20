// ##########################################################################
// # File Name:	PlayersClientTestModule.java
// ##########################################################################

package tictactoe.client.playersclient;

/****************************************************************************
 * 
 */
public 
class PlayersClientTestModule 
    extends PlayersClientModule
{

    /************************************************************************
     * Creates a new PlayersClientTestModule. 
     *
     */
    public 
    PlayersClientTestModule()
    {
        super( "PlayersClientTestModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IPlayersController> 
    getPlayersControllerType()
    {
        return FakePlayersController.class;
    }

}

// ##########################################################################
