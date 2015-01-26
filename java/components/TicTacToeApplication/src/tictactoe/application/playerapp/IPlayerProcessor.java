// ##########################################################################
// # File Name:	IPlayerProcessor.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.AcceptChallengeRequest;
import tictactoe.service.playerservice.ChallengeEvent;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeRequest;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.IssueChallengeRequest;
import tictactoe.service.playerservice.StartListeningReply;
import tictactoe.service.playerservice.StartListeningRequest;

import strata1.common.utility.Pair;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.UpdateFailedException;

/****************************************************************************
 * 
 */
public 
interface IPlayerProcessor
{
    public GetPlayersReply
    getPlayers(GetPlayersRequest request) 
        throws NotUniqueException,InvalidInputException;
    
    public ChallengeEvent
    issueChallenge(IssueChallengeRequest request)
        throws 
            InsertFailedException,
            InvalidInputException,
            NotUniqueException;
    
    public Pair<IssueChallengeReply,AcceptChallengeReply>
    acceptChallenge(AcceptChallengeRequest request)
        throws InsertFailedException,UpdateFailedException;
    
    public Pair<IssueChallengeReply,DeclineChallengeReply>
    declineChallenge(DeclineChallengeRequest request)
        throws UpdateFailedException;
    
    public StartListeningReply
    startListening(StartListeningRequest request)
        throws InsertFailedException,UpdateFailedException;
}

// ##########################################################################
