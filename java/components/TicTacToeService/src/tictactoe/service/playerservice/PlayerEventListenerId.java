// ##########################################################################
// # File Name:	PlayerEventListenerId.java
// ##########################################################################

package tictactoe.service.playerservice;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/****************************************************************************
 * 
 */
public 
class PlayerEventListenerId 
	implements Serializable
{
	private static final long   serialVersionUID = -6503000859037422253L;
	private static final Random theirGenerator   = new SecureRandom();
	private final String        itsImp;
	
	public
	PlayerEventListenerId()
	{
	    this( generateId() );
	}
	
	/************************************************************************
	 * Creates a new PlayerEventListenerId. 
	 *
	 * @param imp
	 */
	public 
	PlayerEventListenerId(String imp)
	{
	    itsImp = imp;
	}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    equals(Object other)
    {
        if ( other instanceof PlayerEventListenerId )
            return itsImp.equals( ((PlayerEventListenerId)other).itsImp );
        
        return false;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public int 
    hashCode()
    {
        int hash = 31 * itsImp.hashCode();
        
        return hash; 
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    toString()
    {
        return itsImp;
    }
	
    /************************************************************************
     * Generates a random identifier. 
     *
     * @return
     */
    private static String
    generateId()
    {
        long id = theirGenerator.nextLong();
        
        return new Long(id < 0 ? -id : id).toString();
    }
	
}

// ##########################################################################
