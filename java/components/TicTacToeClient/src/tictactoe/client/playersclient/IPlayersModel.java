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
    
    public IPlayersModel
    setChallengedUser(String userName);
    
    public Long
    getSessionId();
    
    public Long
    getUserId();

    public List<PlayerData>
    getPlayerData();
       
    public Long
    getChallengeId();

    public PlayerData
    getChallenger();
    
    public PlayerData
    getChallenged();
    
    public Long
    getNewGameId();
    
    public boolean
    isChallengeAccepted();
    
    public void
    issueChallenge();
    
    public void
    acceptChallenge();
    
    public void
    declineChallenge();
    
    public void 
    startListening();

    public void 
    refreshPlayerData();
}

// ##########################################################################
