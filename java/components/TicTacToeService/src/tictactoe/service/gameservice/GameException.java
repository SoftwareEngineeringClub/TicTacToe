// ##########################################################################
// # File Name:	GameException.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class GameException 
	extends Exception
{

	private static final long serialVersionUID	= -8146943968763019593L;
	
	private final String itsReturnAddress;
	private final Long   itsOriginatingRequestId;

	/************************************************************************
	 * Creates a new GameException. 
	 *
	 */
	public 
	GameException(GameRequest request)
	{
	    itsReturnAddress        = request.getReturnAddress();
	    itsOriginatingRequestId = request.getRequestId();
	}

	/************************************************************************
	 * Creates a new GameException. 
	 *
	 * @param arg0
	 */
	public 
	GameException(GameRequest request,String arg0)
	{
		super( arg0 );
		
        if ( request != null )
        {
            itsReturnAddress        = request.getReturnAddress();
            itsOriginatingRequestId = request.getRequestId();
        }
        else
        {
            itsReturnAddress        = null;
            itsOriginatingRequestId = null;
        }
	}

	/************************************************************************
	 * Creates a new GameException. 
	 *
	 * @param arg0
	 */
	public 
	GameException(GameRequest request,Throwable arg0)
	{
		super( arg0 );
		
		if ( request != null )
		{
            itsReturnAddress        = request.getReturnAddress();
            itsOriginatingRequestId = request.getRequestId();
		}
		else
		{
		    itsReturnAddress        = null;
		    itsOriginatingRequestId = null;
		}
	}

	/************************************************************************
	 * Creates a new GameException. 
	 *
	 * @param arg0
	 * @param arg1
	 */
	public 
	GameException(GameRequest request,String arg0,Throwable arg1)
	{
		super( arg0,arg1 );
        itsReturnAddress        = request.getReturnAddress();
        itsOriginatingRequestId = request.getRequestId();
	}

	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public String
	getReturnAddress()
	{
	    return itsReturnAddress;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public Long
	getOriginatingRequestId()
	{
	    return itsOriginatingRequestId;
	}
}

// ##########################################################################
