// ##########################################################################
// # File Name:	IPlayersView.java
// ##########################################################################

package tictactoe.client.playersclient;

import strata1.client.view.IView;

/****************************************************************************
 * 
 */
public 
interface IPlayersView 
    extends IView
{
    public IPlayersView
    insertPlayer(String playerName,String status);
    
    public String
    getSelectedPlayer();
}

// ##########################################################################
