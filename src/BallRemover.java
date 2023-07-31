/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
        private GameLevel gameLevel;
        private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel           the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.gameLevel = gameLevel;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // the ball hit the death block
        this.remainingBalls.decrease(1);
        //remove the ball from the game
        hitter.removeFromGame(gameLevel);
    }

    }
