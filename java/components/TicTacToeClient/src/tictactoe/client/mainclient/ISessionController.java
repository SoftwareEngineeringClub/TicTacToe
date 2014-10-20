// ##########################################################################
// # File Name:	ISessionController.java
// ##########################################################################

package tictactoe.client.mainclient;


/****************************************************************************
 * 
 */
public 
interface ISessionController 
    extends IMainSubController
{    
    public ISessionView
    getLoginView();
    
    public ISessionModel
    getLoginModel();
    
    public IMainController
    getMainController();
}

// ##########################################################################
