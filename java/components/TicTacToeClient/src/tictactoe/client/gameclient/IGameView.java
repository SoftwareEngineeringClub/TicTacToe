// ##########################################################################
// # File Name:	IGameView.java
// ##########################################################################

package tictactoe.client.gameclient;

import tictactoe.service.gameservice.GameStateData;

import strata1.client.view.IView;

/****************************************************************************
 * 
 */
public 
interface IGameView 
    extends IView
{
    public IGameView
    setPlayerX(String playerX);
    
    public IGameView
    setPlayerO(String playerO);
    
    public IGameView
    setThisPlayer(String thisPlayer);
    
    public IGameView
    setGameState(GameStateData state);
    
    public Move
    getCurrentMove();
}

// ##########################################################################
