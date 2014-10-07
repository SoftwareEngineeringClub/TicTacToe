// ##########################################################################
// # File Name:	IPlayerRepository.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.sessiondomain.User;

import strata1.entity.repository.IRepository;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

/****************************************************************************
 * 
 */
public 
interface IPlayerRepository 
    extends IRepository
{
    /************************************************************************
     *  
     *
     * @param player
     * @return
     * @throws InsertFailedException
     */
    public Player
    insertPlayer(Player player)
        throws InsertFailedException;
    
    /************************************************************************
     *  
     *
     * @param player
     * @return
     * @throws UpdateFailedException
     */
    public Player
    updatePlayer(Player player)
        throws UpdateFailedException;
    
    /************************************************************************
     *  
     *
     * @param player
     * @throws RemoveFailedException
     */
    public void
    removePlayer(Player player)
        throws RemoveFailedException;
    
    /************************************************************************
     *  
     *
     * @param playerId
     * @return
     */
    public Player
    getPlayer(Long playerId);
    
    /************************************************************************
     *  
     *
     * @param user
     * @return
     */
    public Player
    getPlayerFor(User user)
        throws InvalidInputException,NotUniqueException;
    
    /************************************************************************
     *  
     *
     * @param playerId
     * @return
     */
    public boolean
    hasPlayer(Long playerId);
    
    /************************************************************************
     *  
     *
     * @param user
     * @return
     */
    public boolean
    hasPlayerFor(User user)
        throws InvalidInputException,NotUniqueException;
}

// ##########################################################################
