// ##########################################################################
// # File Name:	FakePlayersController.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.client.mainclient.IMainController;
import tictactoe.client.mainclient.IMainSubController;

import strata1.client.command.ICommand;
import strata1.client.event.IChangeEvent;

/****************************************************************************
 * 
 */
public 
class FakePlayersController 
    implements IPlayersController
{

    /************************************************************************
     * Creates a new FakePlayersController. 
     *
     */
    public 
    FakePlayersController()
    {
    }

    @Override
    public void 
    start()
    {
    }

    @Override
    public ICommand 
    getCommand(String arg0)
    {
        return null;
    }

    @Override
    public boolean 
    hasCommand(String arg0)
    {
        return false;
    }

    @Override
    public void 
    processChange(IChangeEvent arg0)
    {
    }

    @Override
    public IMainSubController setSessionId(Long sessionId)
    {
        return null;
    }

    @Override
    public IMainSubController setUserId(Long userId)
    {
        return null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startListening()
    {
    }

    @Override
    public IMainController getMainController()
    {
        return null;
    }

    @Override
    public IMainSubController showView()
    {
        return null;
    }

}

// ##########################################################################
