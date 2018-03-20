package au.edu.usc.ict221;

// package au.edu.usc.ict221;

//        import java.util.Random;
//        import java.util.Scanner;

/**
 * A simple console-based maths quiz for primary school children.
 *
 * @author  Gary Nagyidai (CGN003)
 * @author Author2
 */

public class Main {

    public static void main(String[] args) {

        // set program constants

        int MAX_QUESTIONS = 10; // Questions per round of the game
        int MAX_NUMBER = 20;  // Highest number to use in the game (ie. Degree of Difficulty)
        float TARGET_PERCENT = 70;    // target (whole number) percentage of correct answers through all rounds

        float g_high_score = 0;    // potential to retain the high score (percentage) between game players or sessions
        int g_total_asked = 0;     // we may also wish to record the total number of questions asked in the session
        int g_cumulative_num_correct = 0;  // potentially may tally the total answered correctly, not just percent
        float g_percent_correct = 0;   // just the percentage correct in this player session
        int g_number_correct = 0;    //  initialization may be redundant, but good habit to initialize as much as possible

        // let's play one or more rounds of the math game

        PlayGame Game = new PlayGame(MAX_QUESTIONS, MAX_NUMBER, TARGET_PERCENT);
        g_percent_correct = Game.playGame(MAX_QUESTIONS, MAX_NUMBER, TARGET_PERCENT);

    }
}
