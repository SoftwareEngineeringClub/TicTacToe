// ##########################################################################
// # File Name:	IPlayerEventListener.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
interface IPlayerEventListener
{
	public void
	onPlayerChange(PlayerChangeEvent event);
	
	public void
	onChallengeAccepted(ChallengeAcceptedEvent event);
	
	public void
	onChallengeDeclined(ChallengeDeclinedEvent event);
	
	public void
	onPlayerException(PlayerException exception);
}

// ##########################################################################
