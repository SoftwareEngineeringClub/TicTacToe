// ##########################################################################
// # File Name:	Move.java
// ##########################################################################

package tictactoe.client.gameclient;

/****************************************************************************
 * 
 */
public 
class Move
{
    private final String  itsPlayer;
    private final Integer itsRow;
    private final Integer itsColumn;
    
    /************************************************************************
     * Creates a new Move. 
     *
     */
    public 
    Move(String player,Integer row,Integer column)
    {
        itsPlayer = player;
        itsRow    = row;
        itsColumn = column;
    }

    public String
    getPlayer()
    {
        return itsPlayer;
    }
    
    public Integer
    getRow()
    {
        return itsRow;
    }
    
    public Integer
    getColumn()
    {
        return itsColumn;
    }
}

// ##########################################################################
