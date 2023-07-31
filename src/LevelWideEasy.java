// Naor Nahman 207829185

import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level wide easy.
 */
public class LevelWideEasy implements LevelInformation {

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
     * Instantiates a new Level wide easy.
     *
     * @param gui
     * @param lives
     * @param score
     */
    public LevelWideEasy(GUI gui, Counter lives, Counter score) {
        this.gui = gui;
        this.score = score;
        this.lives = lives;
        this.numberOfBalls = 10;
        this.paddleSpeed = 2;
        this.paddleWidth = 600;
        this.ballsVelocities = new ArrayList<>();
        this.levelName = "Wide Easy";
        this.background = new LevelWideEasyBackground();
        this.blocks = new ArrayList<>();
        this.ballList = new ArrayList<>();
        this.createBlocks();
        this.numberOfBlocksToRemove = blocks.size();
        this.createBalls();
        this.levelComplete = false;

    }

    private void createBalls() {
        // create 10 ball with same position
        double x = 400, y = 565 - 10;
        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball(x, y, 5, Color.WHITE);
            this.ballList.add(ball);
        }
        // create their velocity.
        int angle = 160, speed = 5;
        // first 5 balls - left
        for (int i = 0; i < 5; i++) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle = angle - 10;
        }
        angle = 20;
        for (int i = 0; i < 5; i++) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle = angle + 10;
        }
    }


    private void createBlocks() {
        Color color = Color.RED;
        double xBlcok = 25, yBlock = 300;
        for (int i = 0; i < 15; i++) {
            switch (i) {
                case 2:
                case 3:
                    color = Color.ORANGE;
                    break;
                case 4:
                case 5:
                    color = Color.YELLOW;
                    break;
                case 6:
                case 7:
                case 8:
                    color = Color.GREEN;
                    break;
                case 9:
                case 10:
                    color = Color.BLUE;
                    break;
                case 11:
                case 12:
                    color = Color.PINK;
                    break;
                case 13:
                case 14:
                    color = Color.CYAN;
                    break;
                default:
            }

            int width = 50, height = 20;
            Block block = new Block(new Rectangle(new Point(xBlcok, yBlock), width, height));
            block.getRectangle().setColor(color);
            blocks.add(block);
            xBlcok = xBlcok + width;
        }
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
