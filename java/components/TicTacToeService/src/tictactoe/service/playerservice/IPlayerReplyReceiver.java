// ##########################################################################
// # File Name:	IPlayerReplyReceiver.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
interface IPlayerReplyReceiver
{
	public void
	onGetPlayers(GetPlayersReply reply);
	
	public void
	onChallengePlayer(ChallengePlayerReply reply);
	
	public void
	onPlayerException(PlayerException exception);
	
	public void
	onThrowable(Throwable throwable);
}

// ##########################################################################
