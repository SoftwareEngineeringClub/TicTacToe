// ##########################################################################
// # File Name:	PlayerRepository.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.sessiondomain.User;

import strata1.entity.repository.IFinder;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class PlayerRepository 
    implements IPlayerRepository
{
    private final IPlayerRepositoryProvider itsProvider;
    
    /************************************************************************
     * Creates a new PlayerRepository. 
     *
     */
    @Inject
    public 
    PlayerRepository(IPlayerRepositoryProvider provider)
    {
        itsProvider = provider;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IRepositoryContext 
    getContext()
    {
        return itsProvider.getContext();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IUnitOfWork 
    getUnitOfWork()
    {
        return getContext().getUnitOfWork();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Player 
    insertPlayer(Player player) 
        throws InsertFailedException
    {
        return itsProvider.insertNew( player );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Player 
    updatePlayer(Player player) 
        throws UpdateFailedException
    {
        return itsProvider.updateExisting( player );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    removePlayer(Player player) 
        throws RemoveFailedException
    {
        itsProvider.removeExisting( player );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Player 
    getPlayer(Long playerId)
    {
        return itsProvider.getExisting( playerId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Player 
    getPlayerFor(User user) 
        throws InvalidInputException,NotUniqueException
    {
        IFinder<Player> finder = getFinder("getPlayerWithUserId");
        
        finder.setInput( "userId",user.getUserId() );
        return finder.getUnique();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasPlayer(Long playerId)
    {
        return itsProvider.hasExisting( playerId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasPlayerFor(User user)
        throws InvalidInputException,NotUniqueException
    {
        return getPlayerFor( user ) != null;
    }

    /************************************************************************
     *  
     *
     * @param finderName
     * @return
     */
    private IFinder<Player> 
    getFinder(String finderName)
    {
        return 
            itsProvider.getFinder( 
                "tictactoe.domain.playerdomain.Player." + finderName );
    }

}

// ##########################################################################
