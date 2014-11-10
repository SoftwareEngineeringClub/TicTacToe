// ##########################################################################
// # File Name:	GetPlayersReply.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * 
 */
public 
class GetPlayersReply 
	extends PlayerReply
{

	private static final long serialVersionUID	= -4779075358154470856L;
	
	private final ArrayList<PlayerData> itsPlayerData;

	/************************************************************************
	 * Creates a new GetPlayersReply. 
	 *
	 */
	public 
	GetPlayersReply(GetPlayersRequest originatingRequest)
	{
	    super( originatingRequest.getRequestId() );
	    itsPlayerData = new ArrayList<PlayerData>();
	}

	/************************************************************************
	 *  
	 *
	 * @param playerData
	 * @return
	 */
	public GetPlayersReply
	insertPlayerData(PlayerData playerData)
	{
	    itsPlayerData.add( playerData );
	    return this;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public PlayerData
	getOne()
	{
	    return itsPlayerData.get( 0 );
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public List<PlayerData>
	getAll()
	{
	    return itsPlayerData;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public int
	getSize()
	{
	    return itsPlayerData.size();
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public boolean
	isEmpty()
	{
	    return itsPlayerData.isEmpty();
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public boolean
	hasOne()
	{
	    return itsPlayerData.size() == 1;
	}	
	
}

// ##########################################################################
