import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter lives;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.lives = new Counter(7);
        this.score = new Counter(0);
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            if (this.lives.getValue() == 0) {
                this.animationRunner.getGui().close();
            }
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.lives, this.score,
                    this.keyboardSensor);
            level.initialize();
            while (!level.isLevelComplete()) {
                level.run();
            }
            if (levelInfo.numberOfBlocksToRemove() == 0) {
                this.score.increase(100);
            }
        }
        Boolean ifwin = false;
        if (this.lives.getValue() > 0) {
            ifwin = true;
        }
        EndScreen end = new EndScreen(ifwin, this.score, this.keyboardSensor);
        KeyPressStoppableAnimation endScreen = new KeyPressStoppableAnimation(this.keyboardSensor, "space", end);
        this.animationRunner.run(endScreen);
        this.animationRunner.getGui().close();

    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }


}
