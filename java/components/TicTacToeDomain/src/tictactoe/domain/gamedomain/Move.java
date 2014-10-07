// ##########################################################################
// # File Name:	Move.java
// ##########################################################################

package tictactoe.domain.gamedomain;

/****************************************************************************
 * 
 */
public 
class Move
{
    private final long itsPlayerId;
    private final int  itsRow;
    private final int  itsColumn;
    private final char itsMarker;
    
    /************************************************************************
     * Creates a new Move. 
     *
     * @param playerId
     * @param row
     * @param column
     * @param marker
     */
    public 
    Move(
        long playerId,
        int  row,
        int  column,
        char marker)
    {
        itsPlayerId = playerId;
        itsRow      = row;
        itsColumn   = column;
        itsMarker   = marker;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public long
    getPlayerId()
    {
        return itsPlayerId;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public int
    getRow()
    {
        return itsRow;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public int
    getColumn()
    {
        return itsColumn;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public char
    getMarker()
    {
        return itsMarker;
    }
}

// ##########################################################################
