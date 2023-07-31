// Naor Nahman 207829185

import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level direct hit.
 */
public class LevelDirectHit implements LevelInformation {
    private final int numberOfBalls;
    private final int paddleSpeed;
    private int paddleWidth;
    private List<Velocity> ballsVelocities;
    private final String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private GUI gui;
    private List<Ball> ballList;
    private Counter score;
    private Counter lives;
    private boolean levelComplete;


    /**
     * Instantiates a new Level direct hit.
     *
     * @param gui
     * @param score
     * @param lives
     */
    public LevelDirectHit(GUI gui, Counter lives, Counter score) {
        this.score = score;
        this.lives = lives;
        this.gui = gui;
        this.numberOfBalls = 1;
        this.paddleSpeed = 10;
        this.paddleWidth = 150;
        this.ballsVelocities = new ArrayList<>();
        this.levelName = "Direct Hit";
        this.background = new LevelDirectHitBackground();
        this.blocks = new ArrayList<>();
        this.ballList = new ArrayList<>();
        // create target center
        int xCenter = 400 - 15, yCenter = 170 - 15;
        Point centerOfTarget = new Point(xCenter, yCenter);
        int squareLine = 30;
        Rectangle rectangle = new Rectangle(centerOfTarget, squareLine, squareLine);
        Block block = new Block(rectangle);
        this.blocks.add(block);
        this.numberOfBlocksToRemove = blocks.size();
        Ball ball = new Ball(400, 565 - 10, 5, Color.WHITE);
        this.ballList.add(ball);
        this.ballsVelocities.add(Velocity.fromAngleAndSpeed(90, 5));
        this.levelComplete = false;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    @Override
    public List<Ball> ballsList() {
        return this.ballList;
    }

    @Override
    public boolean levelComplete() {
        return this.levelComplete;
    }
}
