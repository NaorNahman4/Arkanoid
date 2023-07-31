// naor Nahman 207829185

import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level final four.
 */
public class LevelFinalFour implements LevelInformation {


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
    private Counter lives;
    private Counter score;
    private boolean levelComplete;

    /**
     * Instantiates a new Level final four.
     *
     * @param gui   the gui
     * @param lives
     * @param score
     */
    public LevelFinalFour(GUI gui, Counter lives, Counter score) {
        this.score = score;
        this.lives = lives;
        this.gui = gui;
        this.numberOfBalls = 3;
        this.paddleSpeed = 10;
        this.paddleWidth = 150;
        this.ballsVelocities = new ArrayList<>();
        this.levelName = "Final Four";
        this.background = new LevelFinalFourBackground();
        this.blocks = new ArrayList<>();
        this.ballList = new ArrayList<>();
        this.createBlocks();
        this.numberOfBlocksToRemove = blocks.size();
        this.createBalls();
        this.levelComplete = false;

    }


    private void createBlocks() {
        double height = 30;
        // y is the y vaule of the start of the block
        int y = 150;
        createLineOFBlocks(15, Color.GRAY, y);
        createLineOFBlocks(15, Color.RED, y + height);
        createLineOFBlocks(15, Color.YELLOW, y + 2 * height);
        createLineOFBlocks(15, Color.GREEN, y + 3 * height);
        createLineOFBlocks(15, Color.WHITE, y + 4 * height);
        createLineOFBlocks(15, Color.PINK, y + 5 * height);
        createLineOFBlocks(15, Color.CYAN, y + 6 * height);


    }

    /**
     * Create line of blocks.
     *
     * @param num   the num
     * @param color the color
     * @param y     the y vaule of the start of the blocks
     */
    private void createLineOFBlocks(int num, Color color, double y) {
        double width = 50, height = 30;
        Point leftPoint = new Point(725, y);
        for (int i = 0; i < num; i++) {
            Block block = new Block(new Rectangle(leftPoint, width, height));
            block.getRectangle().setColor(color);
            this.blocks.add(block);
            leftPoint = new Point(leftPoint.getX() - width, leftPoint.getY());
        }
    }

    private void createBalls() {
        double x = 400, y = 565 - 10;
        // create three ball start in the same position
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(x, y, 5, Color.WHITE);
            this.ballList.add(ball);
        }
        int ball1Anglel = 135, ball2Angel = 45, ball3Angel = 90, ballsSpeed = 5;
        // add their two velocity.
        this.ballsVelocities.add(Velocity.fromAngleAndSpeed(ball1Anglel, ballsSpeed));
        this.ballsVelocities.add(Velocity.fromAngleAndSpeed(ball2Angel, ballsSpeed));
        this.ballsVelocities.add(Velocity.fromAngleAndSpeed(ball3Angel, ballsSpeed));

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
