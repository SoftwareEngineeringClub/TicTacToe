// ##########################################################################
// # File Name:	IMainModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.model.IModel;

/****************************************************************************
 * 
 */
public 
interface IMainModel 
    extends IModel
{
    public IMainModel
    setSession(ISessionModel session);
    
    public IMainModel
    setActiveTab(MainTabKind activeTab);
    
    public ISessionModel
    getSession();
    
    public MainTabKind 
    getActiveTab();

}

// ##########################################################################
