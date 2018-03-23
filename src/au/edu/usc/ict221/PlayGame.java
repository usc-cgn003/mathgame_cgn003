package au.edu.usc.ict221;

import java.util.Scanner;

/**
 * Simple math game to test the mathematical skills of a student with a variety of random equations and/or novelty questions.

 * The degree of difficulty and number of questions per round for each game/session is controlled
 * via parameters passed from the main method to the various classes and methods within the game program.

 * Those parameters are:
 * MAX_NUMBER - the highest number used in the equation
 * TARGET_PERCENT - the percentage of correct answers the student/player needs to answer correctly before they are
 *     acknowledged/congratulated for having shown an acceptable level of mathematical skill.
 *     MAX_QUESTIONS is used to deine the length of each round of the game in terms of number of questions asked before a score is given.
 *
 *  The game calculates the total number of correct answers and displays the number questions asked
 *   and also the percentage that were answered correctly.
 */

public class PlayGame {
    private int l_max_questions;
    private int l_max_number;
    private float l_target_percent;    // target (whole number) percentage of correct answers through all rounds
    private String l_operator_list = "";
    private boolean d_allow_retries = true;

    public PlayGame(int a_max_questions, int a_max_number, float a_target_percent) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_target_percent = a_target_percent;
    }

    public PlayGame(int a_max_questions, int a_max_number, float a_target_percent, String a_operator_list) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_target_percent = a_target_percent;
        l_operator_list = a_operator_list;
    }

    public float playGame(int a_max_questions, int a_max_number, float a_target_percent, String a_operator_list) {

        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_target_percent = a_target_percent;
        l_operator_list = a_operator_list;

        float l_high_score = 0;    // target (whole number) percentage of correct answers through all rounds
        int l_total_asked = 0;
        int l_cumulative_num_correct = 0;
        int l_number_correct = 0;    //  initialization may be reducndant, but good habit to initialize as much as possible
        String l_continue = "Y";  //  initialization may be reducndant, but good habit to initialize as much as possible
        double l_seconds_taken = 0.0;
        float l_cumulative_percent_correct = 0;

        // load instances of essential library objects

        Scanner input = new Scanner(System.in);

        // let's play one or more rounds of the math game

        do {
            l_number_correct = 0;  //first initialisation may be redundant, but code reads better initialized here
            l_seconds_taken = 0.0;  //first initialisation may be redundant, but code reads better initialized here
            double l_round_start_time = System.currentTimeMillis() / 1000.0;  // Start Time of this round of the game

            PlayGameRound GameRound = new PlayGameRound(l_max_questions, l_max_number, l_operator_list, d_allow_retries);
            l_number_correct = GameRound.playGameRound(l_max_questions, l_max_number, l_operator_list, d_allow_retries);

            l_total_asked += l_max_questions;

            float l_percent_correct = (float) l_number_correct / (float) l_max_questions * 100;
            if (l_total_asked > 1 && l_percent_correct >= l_target_percent) {
                System.out.printf("Congratulations! %3.0f percent first attempt target met.\n", l_target_percent);
            }
            System.out.printf("You answered %2d of %2d correctly (%3.0f percent)\n",
                    l_number_correct, l_max_questions, l_percent_correct);
            l_cumulative_num_correct += l_number_correct;  // tally of number answered correctly across all rounds

            // to challenge the student player a bit more we'll report how long it took them

            double l_round_end_time = System.currentTimeMillis() / 1000.0;  // End Time of this round of the game

            l_seconds_taken = l_round_end_time - l_round_start_time;

            if (l_seconds_taken >= 60) {
                double l_minutes_taken = l_seconds_taken / 60;
                System.out.printf("Time Taken was %2.0f minute(s) and %2.0f seconds\n", l_minutes_taken, l_seconds_taken % 60);
            } else {
                System.out.printf("Time Taken was %2.0f seconds\n", l_seconds_taken);
            }

            System.out.print("Play another game?");
            l_continue = input.next();

            if (l_percent_correct > l_high_score)
                l_high_score = l_percent_correct;

        } while (l_continue.toUpperCase().startsWith("Y"));

        l_cumulative_percent_correct = (float) l_cumulative_num_correct / (float) l_total_asked * 100;

        // if the player only played a single round then it's redundant to display a multi-round total
        if (l_total_asked > l_max_questions) {
            if (l_cumulative_percent_correct >= l_target_percent) {
                System.out.printf("Congratulations! %3.0f percent first attempt target met.\n", l_target_percent);
            }
            System.out.printf("You answered %3d of %3d correctly (%3.0f percent), high score of %3.0f percent\n",
                    l_cumulative_num_correct, l_total_asked, l_cumulative_percent_correct, l_high_score);
        }
        return l_cumulative_percent_correct;
    }  // End playGame Method
}  // End PlayGame Class