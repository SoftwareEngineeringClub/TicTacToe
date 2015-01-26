// ##########################################################################
// # File Name:	IChallengeRepository.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import tictactoe.domain.sessiondomain.User;

import strata1.entity.repository.IRepository;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.InvalidInputException;
import strata1.entity.repository.NotUniqueException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;

import java.util.List;

/****************************************************************************
 * 
 */
public 
interface IChallengeRepository
    extends IRepository
{
    public Challenge
    insertChallenge(Challenge challenge) 
        throws InsertFailedException;
    
    public Challenge
    updateChallenge(Challenge challenge) 
        throws UpdateFailedException;
    
    public void
    removeChallenge(Challenge challenge) 
        throws RemoveFailedException;
    
    public List<Challenge>
    getChallenges();
    
    public Challenge
    getChallenge(Long challengeId);
    
    public Challenge
    getPendingChallengeFor(Player challenger,Player challenged) 
        throws InvalidInputException, NotUniqueException;
    
    public boolean
    hasChallenge(Long challengeId);
    
    public boolean
    hasPendingChallengeFor(Player challenger,Player challenged) 
        throws InvalidInputException, NotUniqueException;
}

// ##########################################################################
