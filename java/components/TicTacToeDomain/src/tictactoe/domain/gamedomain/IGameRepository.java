// ##########################################################################
// # File Name:	IGameRepository.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import strata1.entity.repository.IRepository;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

/****************************************************************************
 * 
 */
public 
interface IGameRepository 
    extends IRepository
{
    /************************************************************************
     *  
     *
     * @param game
     * @return
     * @throws InsertFailedException
     */
    public Game
    insertGame(Game game)
        throws InsertFailedException;
    
    /************************************************************************
     *  
     *
     * @param game
     * @return
     * @throws UpdateFailedException
     */
    public Game
    updateGame(Game game)
        throws UpdateFailedException;
    
    /************************************************************************
     *  
     *
     * @param game
     * @throws RemoveFailedException
     */
    public void
    removeGame(Game game)
        throws RemoveFailedException;
    
    /************************************************************************
     *  
     *
     * @param gameId
     * @return
     */
    public Game
    getGame(Long gameId);
    
    /************************************************************************
     *  
     *
     * @param gameId
     * @return
     */
    public boolean
    hasGame(Long gameId);
}

// ##########################################################################
