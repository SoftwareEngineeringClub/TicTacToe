// ##########################################################################
// # File Name:	ISessionView.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.view.IView;
import strata1.common.authentication.ICredential;

/****************************************************************************
 * 
 */
public 
interface ISessionView 
    extends IView
{
    public void
    reset();
    
    public void
    displayMessage(String message);
    
    public ICredential
    getLoginCredential();
    
    public ICredential
    getRegisterCredential();
}

// ##########################################################################
