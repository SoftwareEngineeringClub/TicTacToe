// ##########################################################################
// # File Name:	GameException.java
// ##########################################################################

package tictactoe.domain.gamedomain;

/****************************************************************************
 * 
 */
public abstract 
class GameException 
    extends Exception
{

    private static final long serialVersionUID = 1382786463331959326L;

    /************************************************************************
     * Creates a new GameException. 
     *
     */
    public 
    GameException()
    {
    }

    /************************************************************************
     * Creates a new GameException. 
     *
     * @param message
     */
    public 
    GameException(String message)
    {
        super( message );

    }

    /************************************************************************
     * Creates a new GameException. 
     *
     * @param cause
     */
    public 
    GameException(Throwable cause)
    {
        super( cause );

    }

}

// ##########################################################################
