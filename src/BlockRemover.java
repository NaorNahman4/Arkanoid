// naor Nahman 207829185
/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel       the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
        this.gameLevel = gameLevel;

    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // a block was hit so -1.
        this.remainingBlocks.decrease(1);
        //remove the block from the game
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
    }
}