// ##########################################################################
// # File Name:	IMainSubController.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.controller.IController;

/****************************************************************************
 * 
 */
public 
interface IMainSubController 
    extends IController
{
    public IMainSubController
    setSessionId(Long sessionId);
    
    public IMainSubController
    setUserId(Long userId);
    
    public void 
    startListening();

    public IMainController
    getMainController();
    
    public IMainSubController
    showView();
}

// ##########################################################################
