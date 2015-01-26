// ##########################################################################
// # File Name:	GameStateData.java
// ##########################################################################

package tictactoe.service.gameservice;

import java.io.Serializable;

/****************************************************************************
 * 
 */
public 
class GameStateData 
    implements Serializable
{
    public static final char OPEN_MARKER = '-';
    public static final char X_MARKER    = 'X';
    public static final char O_MARKER    = 'O';

    private static final long serialVersionUID = -4443341014153361200L;
    
    private Integer       itsDimension;
    private StringBuilder itsState;
    
    /************************************************************************
     * Creates a new GameStateData. 
     *
     */
    public 
    GameStateData()
    {
        itsDimension = 3;
        itsState = new StringBuilder("---------");
    }

    /************************************************************************
     * Creates a new GameStateData. 
     *
     */
    public 
    GameStateData(String state)
    {
        itsDimension = 3;
        itsState = new StringBuilder(state);
    }

    /************************************************************************
     *  
     *
     * @param row
     * @param column
     * @param value
     * @return
     */
    public GameStateData
    setCell(Integer row,Integer column,Character value)
    {
        itsState.setCharAt( getIndex( row,column ),value );
        return this;
    }
    
    /************************************************************************
     *  
     *
     * @param row
     * @param column
     * @return
     */
    public Character
    getCell(Integer row,Integer column)
    {
        return itsState.charAt( getIndex(row,column) );
    }
    
    /************************************************************************
     *  
     *
     * @param row
     * @param column
     * @return
     */
    public boolean
    isOpen(Integer row,Integer column)
    {
        return getCell( row,column ).equals( OPEN_MARKER );
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    toString()
    {
        return itsState.toString();
    }

    /************************************************************************
     *  
     *
     * @param move
     * @return
     */
    protected int
    getIndex(Integer row,Integer column)
    {       
        return row*itsDimension + column;
    }

}

// ##########################################################################
