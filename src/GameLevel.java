// Naor Nahman 207829185

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter amoutOfBlocks;
    private Counter amoutOfBalls;
    private Counter score;
    private Counter lives;
    private Paddle paddle;
    private LevelInformation levelInformation;

    private AnimationRunner runner;

    private boolean running;
    private boolean levelComplete;

    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level information
     * @param animationRunner  the animation runner
     * @param lives            the lives
     * @param score            the score
     * @param keyboardSensor   the keyboard sensor
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner animationRunner, Counter lives,
                     Counter score, KeyboardSensor keyboardSensor) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.amoutOfBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.amoutOfBalls = new Counter(levelInformation.numberOfBalls());
        this.score = score;
        this.lives = lives;
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.levelComplete = false;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Create all blocks.
     *
     * @param y the y vaule of the start of the blocks
     */
    public void createAllBlocks(int y) {
        double height = 30;
        createLineOFBlocks(12, Color.GRAY, y);
        createLineOFBlocks(11, Color.RED, y + height);
        createLineOFBlocks(10, Color.YELLOW, y + 2 * height);
        createLineOFBlocks(9, Color.BLUE, y + 3 * height);
        createLineOFBlocks(8, Color.PINK, y + 4 * height);
        createLineOFBlocks(7, Color.GREEN, y + 5 * height);
    }

    /**
     * Create line of blocks.
     *
     * @param num   the num
     * @param color the color
     * @param y     the y vaule of the start of the blocks
     */
    public void createLineOFBlocks(int num, Color color, double y) {
        double width = 40, height = 30;
        Point leftPoint = new Point(735, y);
        BlockRemover blockRemover = new BlockRemover(this, this.amoutOfBlocks);
        ScoreTrackingListener score = new ScoreTrackingListener(this.score);
        for (int i = 0; i < num; i++) {
            Block block = new Block(new Rectangle(leftPoint, width, height));
            block.getRectangle().setColor(color);
            //     amoutOfBlocks.increase(1);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(score);
            leftPoint = new Point(leftPoint.getX() - width, leftPoint.getY());
        }
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {

        //createAll();

        // Background
        this.addSprite(this.levelInformation.getBackground());

        // Create all Hit Listeners
        HitListener scoreListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this.lives, new Rectangle(new Point(0, 0),
                800, 25));
        // for the level name (i put it in scoreIndicator instead of open 1 new class because is has the same rectangle)
        scoreIndicator.setLevelName(this.levelInformation.levelName());
        // for the lives (i put it in scoreIndicator instead of open 1 new class because is has the same rectangle)
        scoreIndicator.setLives(this.lives);
        scoreIndicator.addToGame(this);
        HitListener blockRemover = new BlockRemover(this, this.amoutOfBlocks);
        HitListener ballRemover = new BallRemover(this, this.amoutOfBalls);


        // create gui blocks - same for every level!
        double guiMaxHeight = 550, guiMinHeight = 25, guiMinHeightWithScore = 50, guiMaxWidth = 800, guiMinWidth = 25,
                maxY = 600, maxX = 775, minXY = 0;
        Point leftUpGui0 = new Point(minXY, guiMinHeight);
        Point leftDownGui1 = new Point(minXY, maxY);
        Point rightUp2 = new Point(minXY, guiMinHeightWithScore);
        Point rightDown3 = new Point(maxX, guiMinHeightWithScore);
        // left up - left down line
        Rectangle rectangle0 = new Rectangle(leftUpGui0, guiMaxWidth, guiMinHeight);
        rectangle0.setColor(Color.GRAY);
        // left down - right down line
        Rectangle rectangle1 = new Rectangle(leftDownGui1, guiMaxWidth, guiMinHeight);
        rectangle1.setColor(Color.GRAY);
        // right up-right down line
        Rectangle rectangle2 = new Rectangle(rightUp2, guiMinWidth, guiMaxHeight);
        rectangle2.setColor(Color.GRAY);
        // up left-right left line
        Rectangle rectangle3 = new Rectangle(rightDown3, guiMinWidth, guiMaxHeight);
        rectangle3.setColor(Color.GRAY);
        Block guiBoard0 = new Block(rectangle0);
        Block guiBoard1 = new Block(rectangle1);
        // guiBoard1 is the lower line that if the ball hit him and ball is gone
        guiBoard1.addHitListener(ballRemover);
        Block guiBoard2 = new Block(rectangle2);
        Block guiBoard3 = new Block(rectangle3);
        guiBoard0.addToGame(this);
        guiBoard1.addToGame(this);
        guiBoard2.addToGame(this);
        guiBoard3.addToGame(this);


        // Create the blocks the player need to hit
        for (
                Block block : this.levelInformation.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            block.addToGame(this);
        }
        // Create the paddle
        createPaddle();
        // create the balls from the ballList
        createBalls();


    }

    /**
     * Create balls.
     */
    public void createBalls() {
        List<Ball> ballsList = this.levelInformation.ballsList();
        for (int i = 0; i < ballsList.size(); i++) {
            ballsList.get(i).setGameEnvi(this.environment);
            ballsList.get(i).setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ballsList.get(i).addToGame(this);
        }
    }

    /**
     * Create paddle.
     */
    public void createPaddle() {
        this.paddle = new Paddle(this.levelInformation.paddleSpeed(), this.levelInformation.paddleWidth(),
                this.keyboard, this.runner.getGui());
        this.paddle.getCollisionRectangle().setColor(Color.MAGENTA);
        this.paddle.addToGame(this);
    }

    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void run() {
        //   resetLevel();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.levelComplete = false;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (!this.levelComplete) {
            resetLevel();
            this.runner.run(this);
        }
        if (this.levelComplete) {
            return;
        } else {
            if (this.lives.getValue() == 0) {
                EndScreen end = new EndScreen(false, this.score, this.keyboard);
                KeyPressStoppableAnimation endScreen = new KeyPressStoppableAnimation(this.keyboard, "space", end);
                this.runner.run(endScreen);
                this.runner.getGui().close();
            }
        }
    }

    private void resetLevel() {
        this.removeSprite(this.paddle);
        this.removeCollidable(this.paddle);
        // create new
        createPaddle();
        createBalls();
        resetBallsTomiddle();
        this.amoutOfBalls.increase(levelInformation.numberOfBalls());
    }

    private void resetBallsTomiddle() {
        List<Ball> ballList = this.levelInformation.ballsList();
        for (int i = 0; i < ballList.size(); i++) {
            ballList.get(i).setCenter(400, 555);
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollideables().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.paddle.setKeyboard();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.amoutOfBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
            this.levelComplete = true;
        }
        if (this.amoutOfBalls.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
            //   this.levelComplete = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Is level complete boolean.
     *
     * @return the boolean
     */
    public boolean isLevelComplete() {
        return this.levelComplete;
    }
}