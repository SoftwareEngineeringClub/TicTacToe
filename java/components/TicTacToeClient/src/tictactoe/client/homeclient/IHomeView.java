// ##########################################################################
// # File Name:	IHomeView.java
// ##########################################################################

package tictactoe.client.homeclient;

import tictactoe.service.playerservice.PlayerData;

import strata1.client.view.IView;

import java.math.BigDecimal;

/****************************************************************************
 * 
 */
public 
interface IHomeView extends IView
{
    public IHomeView
    setPlayerData(PlayerData playerData);
    
    public void
    displayError(String message);
}

// ##########################################################################
