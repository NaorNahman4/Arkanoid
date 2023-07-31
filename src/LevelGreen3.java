import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level green 3.
 */
public class LevelGreen3 implements LevelInformation {

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
     * Instantiates a new Level green 3.
     *
     * @param gui   the gui
     * @param lives the lives
     * @param score the score
     */
    public LevelGreen3(GUI gui, Counter lives, Counter score) {
        this.lives = lives;
        this.score = score;
        this.gui = gui;
        this.numberOfBalls = 2;
        this.paddleSpeed = 10;
        this.paddleWidth = 150;
        this.ballsVelocities = new ArrayList<>();
        this.levelName = "Green 3";
        this.background = new LevelGreen3Background();
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
        int y = 200;
        createLineOFBlocks(10, Color.GRAY, y);
        createLineOFBlocks(9, Color.RED, y + height);
        createLineOFBlocks(8, Color.YELLOW, y + 2 * height);
        createLineOFBlocks(7, Color.BLUE, y + 3 * height);
        createLineOFBlocks(6, Color.WHITE, y + 4 * height);
    }

    /**
     * Create line of blocks.
     *
     * @param num   the num
     * @param color the color
     * @param y     the y vaule of the start of the blocks
     */
    public void createLineOFBlocks(int num, Color color, double y) {
        double width = 50, height = 30;
        Point leftPoint = new Point(725, y);
        for (int i = 0; i < num; i++) {
            Block block = new Block(new Rectangle(leftPoint, width, height));
            block.getRectangle().setColor(color);
            this.blocks.add(block);
            leftPoint = new Point(leftPoint.getX() - width, leftPoint.getY());
        }
    }


    /**
     * Create balls.
     */
    public void createBalls() {
        double x = 400, y = 565 - 10;
        // create two ball start in the same position
        for (int i = 0; i < 2; i++) {
            Ball ball = new Ball(x, y, 5, Color.WHITE);
            this.ballList.add(ball);
        }
        int ball1Anglel = 135, ball2Angel = 45, ballsSpeed = 5;
        // add their two velocity.
        this.ballsVelocities.add(Velocity.fromAngleAndSpeed(ball1Anglel, ballsSpeed));
        this.ballsVelocities.add(Velocity.fromAngleAndSpeed(ball2Angel, ballsSpeed));
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
