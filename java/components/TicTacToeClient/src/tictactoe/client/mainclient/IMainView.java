// ##########################################################################
// # File Name:	IMainView.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.view.IView;

/****************************************************************************
 * 
 */
public 
interface IMainView 
    extends IView
{
    public IMainView 
    setActiveTab(MainTabKind activeTab);
    
    public MainTabKind
    getActiveTab();
}

// ##########################################################################
