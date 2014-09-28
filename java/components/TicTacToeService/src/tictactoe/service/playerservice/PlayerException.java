// ##########################################################################
// # File Name:	PlayerException.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class PlayerException 
	extends Exception
{

	private static final long serialVersionUID	= -8146943968763019593L;

	/************************************************************************
	 * Creates a new PlayerException. 
	 *
	 */
	public 
	PlayerException()
	{
	}

	/************************************************************************
	 * Creates a new PlayerException. 
	 *
	 * @param arg0
	 */
	public 
	PlayerException(String arg0)
	{
		super( arg0 );

	}

	/************************************************************************
	 * Creates a new PlayerException. 
	 *
	 * @param arg0
	 */
	public 
	PlayerException(Throwable arg0)
	{
		super( arg0 );

	}

	/************************************************************************
	 * Creates a new PlayerException. 
	 *
	 * @param arg0
	 * @param arg1
	 */
	public 
	PlayerException(String arg0,Throwable arg1)
	{
		super( arg0,arg1 );

	}

}

// ##########################################################################
