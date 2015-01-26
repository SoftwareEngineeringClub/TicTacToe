// ##########################################################################
// # File Name:	ChallengeRepository.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import strata1.entity.repository.IFinder;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * 
 */
public 
class ChallengeRepository 
    implements IChallengeRepository
{
    private final IChallengeRepositoryProvider itsProvider;
    
    /************************************************************************
     * Creates a new ChallengeRepository. 
     *
     */
    @Inject
    public 
    ChallengeRepository(IChallengeRepositoryProvider provider)
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
    public Challenge 
    insertChallenge(Challenge challenge) 
        throws InsertFailedException
    {
        return itsProvider.insertNew( challenge );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Challenge 
    updateChallenge(Challenge challenge) 
        throws UpdateFailedException
    {
        return itsProvider.updateExisting( challenge );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    removeChallenge(Challenge challenge) 
        throws RemoveFailedException
    {
        itsProvider.removeExisting( challenge );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public List<Challenge> 
    getChallenges()
    {
        IFinder<Challenge> finder = getFinder( "getChallenges" );
        
        return new ArrayList<Challenge>(finder.getAll());
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Challenge 
    getChallenge(Long challengeId)
    {
        return itsProvider.getExisting( challengeId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Challenge 
    getPendingChallengeFor(Player challenger,Player challenged) 
            throws InvalidInputException, NotUniqueException
    {
        IFinder<Challenge> finder = getFinder( "getPendingChallengeFor" );
        
        finder.setInput( "challengerUserId",challenger.getUserId() );
        finder.setInput( "challengedUserId",challenged.getUserId() );
        return finder.getUnique();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasChallenge(Long challengeId)
    {
        return itsProvider.hasExisting( challengeId );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    hasPendingChallengeFor(Player challenger,Player challenged) 
            throws InvalidInputException, NotUniqueException
    {
        IFinder<Challenge> finder = getFinder( "hasPendingChallengeFor" );
        
        finder.setInput( "challengerUserId",challenger.getUserId() );
        finder.setInput( "challengedUserId",challenged.getUserId() );
        return finder.hasUnique();
    }
    
    /************************************************************************
     *  
     *
     * @param finderName
     * @return
     */
    private IFinder<Challenge> 
    getFinder(String finderName)
    {
        return 
            itsProvider.getFinder( 
                "tictactoe.domain.playerdomain.Challenge." + finderName );
    }

}

// ##########################################################################
