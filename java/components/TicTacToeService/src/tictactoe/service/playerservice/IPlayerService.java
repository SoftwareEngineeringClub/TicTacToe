// ##########################################################################
// # File Name:	IPlayerService.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
interface IPlayerService
{
	public void
	getPlayers(IPlayerReplyReceiver reply,GetPlayersRequest request);
	
	public void
	challengePlayer(IPlayerReplyReceiver reply,ChallengePlayerRequest request);
	
	public PlayerEventListenerId
	startListeningForEvents(IPlayerEventListener listener);
	
	public void
	stopListeningForEvents(PlayerEventListenerId listenerId);
	
}

// ##########################################################################
