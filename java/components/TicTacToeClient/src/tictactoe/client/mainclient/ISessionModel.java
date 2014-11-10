// ##########################################################################
// # File Name:	ISessionModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.model.IModel;
import strata1.common.authentication.ICredential;

/****************************************************************************
 * 
 */
public 
interface ISessionModel 
    extends IModel
{
    public void
    login(ICredential credential);
    
    public void
    logout();
    
    public void
    register(ICredential credential);
    
    public Long
    getSessionId();
    
    public Long
    getUserId();
    
    public String
    getLoginError();
    
    public String
    getRegisterError();
    
    public boolean
    isLoggedIn();
    
    public boolean
    isRegistered();
}

// ##########################################################################
