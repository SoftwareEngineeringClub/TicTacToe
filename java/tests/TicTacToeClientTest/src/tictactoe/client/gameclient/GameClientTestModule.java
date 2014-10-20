// ##########################################################################
// # File Name:	GameClientTestModule.java
// ##########################################################################

package tictactoe.client.gameclient;

/****************************************************************************
 * 
 */
public 
class GameClientTestModule 
    extends GameClientModule
{

    /************************************************************************
     * Creates a new GameClientTestModule. 
     *
     */
    public 
    GameClientTestModule()
    {
        super( "GameClientTestModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IGameController> 
    getGameControllerType()
    {
        return FakeGameController.class;
    }

}

// ##########################################################################
