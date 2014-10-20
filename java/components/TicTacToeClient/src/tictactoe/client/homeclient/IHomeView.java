// ##########################################################################
// # File Name:	IHomeView.java
// ##########################################################################

package tictactoe.client.homeclient;

import strata1.client.view.IView;

import java.math.BigDecimal;

/****************************************************************************
 * 
 */
public 
interface IHomeView extends IView
{
    public IHomeView
    setPlayerName(String playerName);
    
    public IHomeView
    setPlayerRank(BigDecimal playerRank);
    
    public IHomeView
    setGamesPlayed(Integer gamesPlayed);
    
    public IHomeView
    setWins(Integer wins);
    
    public IHomeView
    setLosses(Integer losses);
    
    public IHomeView
    setTies(Integer ties);
}

// ##########################################################################
