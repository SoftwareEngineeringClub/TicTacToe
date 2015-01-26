// ##########################################################################
// # File Name:	IPlayerService.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.util.concurrent.TimeoutException;

/****************************************************************************
 * 
 */
public 
interface IPlayerService
{
	public void
	getPlayers(
	    IPlayerReplyReceiver receiver,
	    GetPlayersRequest    request);
	
	public void
	issueChallenge(
	    IPlayerReplyReceiver  receiver,
	    IssueChallengeRequest request);
	
	public void
	acceptChallenge(
	    IPlayerReplyReceiver   receiver,
	    AcceptChallengeRequest request);
	
	public void
	declineChallenge(
	    IPlayerReplyReceiver    receiver,
	    DeclineChallengeRequest request);
	
	public void
	startListening(
	    IPlayerReplyReceiver  receiver, 
	    StartListeningRequest request);
	
	public void
	stopListeningForEvents(PlayerEventListenerId listenerId);
	
}

// ##########################################################################
