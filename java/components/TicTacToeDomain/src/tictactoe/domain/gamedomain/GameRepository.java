// ##########################################################################
// # File Name:	GameRepository.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class GameRepository 
    implements IGameRepository
{
    private final IGameRepositoryProvider itsProvider;
    
    /************************************************************************
     * Creates a new GameRepository. 
     *
     */
    @Inject
    public 
    GameRepository(IGameRepositoryProvider provider)
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
    public Game 
    insertGame(Game game) 
        throws InsertFailedException
    {
        return itsProvider.insertNew( game );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Game 
    updateGame(Game game) 
        throws UpdateFailedException
    {
        return itsProvider.updateExisting( game );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    removeGame(Game game) 
        throws RemoveFailedException
    {
        itsProvider.removeExisting( game );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Game 
    getGame(Long gameId)
    {
        return itsProvider.getExisting( gameId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasGame(Long gameId)
    {
        return itsProvider.hasExisting( gameId );
    }

}

// ##########################################################################
