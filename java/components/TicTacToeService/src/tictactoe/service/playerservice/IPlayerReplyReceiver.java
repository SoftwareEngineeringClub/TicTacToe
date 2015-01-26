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
	onIssueChallenge(IssueChallengeReply reply);
	
	public void
	onAcceptChallenge(AcceptChallengeReply reply);
	
	public void
	onDeclineChallenge(DeclineChallengeReply reply);
	
	public void
	onStartListening(StartListeningReply reply);
	
	public void
	onPlayerException(PlayerException exception);
	
	public void
	onThrowable(Throwable throwable);
}

// ##########################################################################
