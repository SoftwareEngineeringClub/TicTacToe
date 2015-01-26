// ##########################################################################
// # File Name:	IPlayersView.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.service.playerservice.PlayerData;

import strata1.client.view.IView;

/****************************************************************************
 * 
 */
public 
interface IPlayersView 
    extends IView
{
    public IPlayersView
    insertPlayer(PlayerData playerData);
    
    public IPlayersView
    displayChallenge(PlayerData challenger);
    
    public IPlayersView
    displayChallengeAccepted(PlayerData challenged);
    
    public IPlayersView
    displayChallengeDeclined(PlayerData challenged);

    public IPlayersView
    removePlayers();
    
    public String
    getSelectedPlayer();
}

// ##########################################################################
