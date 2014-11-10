// ##########################################################################
// # File Name:	IHomeModel.java
// ##########################################################################

package tictactoe.client.homeclient;

import tictactoe.service.playerservice.PlayerData;

import strata1.client.model.IModel;

/****************************************************************************
 * 
 */
public 
interface IHomeModel 
    extends IModel
{

    public IHomeModel 
    setSessionId(Long sessionId);

    public IHomeModel
    setUserId(Long userId);

    public PlayerData
    getPlayerData();
    
    public void 
    refreshPlayerData();
}

// ##########################################################################
