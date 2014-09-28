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
	
	public ChangeListenerId
	startListeningForChanges(IPlayerChangeListener listener);
	
	public void
	stopListeningForChanges(ChangeListenerId listenerId);
}

// ##########################################################################
