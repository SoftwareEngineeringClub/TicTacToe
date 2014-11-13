// ##########################################################################
// # File Name:	IPlayersModel.java
// ##########################################################################

package tictactoe.client.playersclient;

import tictactoe.service.playerservice.PlayerData;

import strata1.client.model.IModel;

import java.util.List;

/****************************************************************************
 * 
 */
public 
interface IPlayersModel 
    extends IModel
{
    public IPlayersModel 
    setSessionId(Long sessionId);

    public IPlayersModel
    setUserId(Long userId);
    
    public Long
    getSessionId();
    
    public Long
    getUserId();

    public List<PlayerData>
    getPlayerData();
    
    public void 
    refreshPlayerData();
}

// ##########################################################################
