// ##########################################################################
// # File Name:	IllegalMoveException.java
// ##########################################################################

package tictactoe.domain.gamedomain;

/****************************************************************************
 * 
 */
public 
class IllegalMoveException 
    extends GameException
{

    private static final long serialVersionUID = 607549799073828758L;

    /************************************************************************
     * Creates a new IllegalMoveException. 
     *
     */
    public 
    IllegalMoveException() {}

    /************************************************************************
     * Creates a new IllegalMoveException. 
     *
     * @param message
     */
    public 
    IllegalMoveException(String message)
    {
        super( message );
    }

    /************************************************************************
     * Creates a new IllegalMoveException. 
     *
     * @param cause
     */
    public 
    IllegalMoveException(Throwable cause)
    {
        super( cause );
    }

}

// ##########################################################################
