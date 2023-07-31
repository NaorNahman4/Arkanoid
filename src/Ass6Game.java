//naor nahman 207829185

import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AnimationRunner animation = new AnimationRunner(new GUI("Arkanoid", 800, 600),
                60);
        GameFlow game = new GameFlow(animation, animation.getGui().getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 1 && (args[i].equals("1") || args[i].equals("2") || args[i].equals("3")
                    || args[i].equals("4"))) {
                int numLevel = Integer.parseInt(args[i]);
                switch (numLevel) {
                    case 1:
                        levels.add(new LevelDirectHit(animation.getGui(), game.getLives(), game.getScore()));
                        break;
                    case 2:
                        levels.add(new LevelWideEasy(animation.getGui(), game.getLives(), game.getScore()));
                        break;
                    case 3:
                        levels.add(new LevelGreen3(animation.getGui(), game.getLives(), game.getScore()));
                        break;
                    case 4:
                        levels.add(new LevelFinalFour(animation.getGui(), game.getLives(), game.getScore()));
                        break;
                    default:
                }
            }
        }
        // mean that player didnt enter any arguments
        if (levels.isEmpty()) {
            LevelFinalFour level4 = new LevelFinalFour(animation.getGui(), game.getLives(), game.getScore());
            LevelGreen3 level3 = new LevelGreen3(animation.getGui(), game.getLives(), game.getScore());
            LevelWideEasy leve2 = new LevelWideEasy(animation.getGui(), game.getLives(), game.getScore());
            LevelDirectHit level1 = new LevelDirectHit(animation.getGui(), game.getLives(), game.getScore());
            levels.add(level1);
            levels.add(leve2);
            levels.add(level3);
            levels.add(level4);
        }
        game.runLevels(levels);
    }
}
