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
	onChallenge(ChallengeEvent event);
	
	public void
	onPlayerException(PlayerException exception);
}

// ##########################################################################
