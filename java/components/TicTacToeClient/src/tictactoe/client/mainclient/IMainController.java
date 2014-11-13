// ##########################################################################
// # File Name:	IMainController.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.controller.IController;

/****************************************************************************
 * 
 */
public 
interface IMainController 
    extends IController
{    
    public IMainController
    setSessionController(IMainSubController sessionController);
    
    public IMainController
    setHomeController(IMainSubController homeController);
    
    public IMainController
    setPlayersController(IMainSubController playersController);
    
    public IMainController
    setGameController(IMainSubController gameController);
    
    public IMainController
    setSession(ISessionModel input);
    
    public void
    completeLogin();
    
    public void
    completeLogout();
    
    public void
    exit();
}

// ##########################################################################
