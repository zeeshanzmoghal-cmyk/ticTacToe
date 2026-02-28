package com.zzm.appGUI.splashText;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class titlePageSplashText {

    List<String> splashText = new ArrayList<>(Arrays.asList(
            "your tic tac toe server",
            "ticcy tic tac tic ticcy tic tac toe",
            "where x meets o",
            "think fast place faster",
            "three in a row glory",
            "small grid big brain",
            "simple rules endless fun",
            "outsmart your opponent",
            "every move matters",
            "classic game modern twist",
            "x o and mind games",
            "strategy in nine squares",
            "easy to learn hard to master",
            "the ultimate x and o battle",
            "quick rounds big wins",
            "brain battles in a grid",
            "play smart play bold",
            "make your mark",
            "claim your three",
            "nine squares infinite tactics",
            "simple grid serious strategy",
            "where legends draw lines",
            "outplay outthink outwin",
            "x goes first",
            "o waits patiently",
            "every square counts",
            "victory in three",
            "lines decide destiny",
            "think ahead always",
            "mind games begin here",
            "small board big plays",
            "fast games sharp minds",
            "click place conquer",
            "turn based tension",
            "minimal board maximum fun",
            "read your opponent",
            "win with wit",
            "lose with honor",
            "draws are still battles",
            "quick thinking required",
            "precision over luck",
            "strategy beats speed",
            "where rivals meet",
            "friendly battles welcome",
            "casual fun competitive edge",
            "play relax repeat",
            "simple fun serious focus",
            "short games long memories",
            "outsmart in seconds",
            "your move matters",
            "every match is unique",
            "thinking time starts now",
            "first move advantage",
            "one grid endless stories",
            "play the classics",
            "retro rules modern vibes",
            "timeless game fresh feel",
            "mind vs mind",
            "brainpower over buttons",
            "small game big pride",
            "nine tiles infinite drama",
            "perfect lines only",
            "read the grid",
            "predict adapt win",
            "fast matches fierce minds",
            "logic over luck",
            "grid based greatness",
            "think place win",
            "tactical moves only",
            "clean board clear mind",
            "where strategy shines",
            "minimal moves maximum impact",
            "smart plays only",
            "win lose rematch",
            "practice makes perfect",
            "simple design deep play",
            "battle in silence",
            "lines define victory",
            "stay sharp",
            "calculated moves",
            "play smart",
            "game on",
            "your turn",
            "choose wisely",
            "focus wins games",
            "strategy starts here",
            "master the grid",
            "outthink to win"
    ));

    public String splashSubtitle = getSplashSubtitle(splashText);

    public static String getSplashSubtitle(List<String> subTitleList) {
        if(subTitleList == null || subTitleList.isEmpty()) {
            return null;
        }

        int subTitleSplashIndex = ThreadLocalRandom.current().nextInt(subTitleList.size());

        return subTitleList.get(subTitleSplashIndex);
    }

}
