// ##########################################################################
// # File Name:	IPlayerProcessor.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.service.playerservice.ChallengePlayerReply;
import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;

import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;

/****************************************************************************
 * 
 */
public 
interface IPlayerProcessor
{
    public GetPlayersReply
    getPlayers(GetPlayersRequest request) 
        throws NotUniqueException,InvalidInputException;
    
    public ChallengePlayerReply
    challengePlayer(ChallengePlayerRequest request);
}

// ##########################################################################
