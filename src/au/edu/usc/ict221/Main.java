package au.edu.usc.ict221;

// package au.edu.usc.ict221;

        import java.util.Random;
        import java.util.Scanner;

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
        String OPERATOR_LIST = "+-*/%";

        float g_high_score = 0;    // target (whole number) percentage of correct answers through all rounds
        int g_total_asked = 0;
        int g_cumulative_num_correct = 0;
        int l_num_correct = 0;    //  initialization may be reducndant, but good habit to initialize as much as possible
        String l_continue = "Y";  //  initialization may be reducndant, but good habit to initialize as much as possible
        double l_seconds_taken = 0.0;

        // load instances of essential library objects

        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        // let's play one or more rounds of the math game

        do {
            l_num_correct = 0;  //first initialisation may be redundant, but code reads better initialized here
            l_seconds_taken = 0.0;  //first initialisation may be redundant, but code reads better initialized here
            double l_round_start_time = System.currentTimeMillis() / 1000.0;  // Start Time of this round of the game

            // now let us run a series of questions for this round, with MAX_QUESTIONS in the round
            for (int i = 0; i < MAX_QUESTIONS; i++) {

                // Adding 1 to the numbers has two affects:
                // 1. it takes the maximum random number up to the specified constant MAX_NUM_SIZE
                // 2. it avoids the game asking questions that are too easy or result in division by zero errors
                int a = rand.nextInt(MAX_NUMBER) + 1;
                int b = rand.nextInt(MAX_NUMBER) + 1;
                int op_index = rand.nextInt(OPERATOR_LIST.length());
                String operator = OPERATOR_LIST.substring(op_index, op_index + 1);

                // This program or class controls the degere of difficuklty and therefore
                /// we adapt the number set here rather than within the Question class
                if (operator.equals("/") && a < b){
                    // Gross up the numerator for a more useful question
                    a *= b;
                }
                if (operator.equals("%") && a < b) {
                    // switch the numbers to avoid questions being too easy (ie. merely a 0 or 1 answer)
                    int c = a;
                    a = b;
                    b = c;
                }
                if (b == 1) {
                    // too easy at any level, so just add 1 to make it a bit more challenging
                    b++;
                }

                /*
                 * This next part attempts to introduce classes in place of finite variables
                 * to issue the questions.
                 */

                Question q = new Question(a, b, operator);
                q.showQuestion();

                g_total_asked++;

                // Accept the response [to the question] from the user
                int response = input.nextInt();

                // Check whether the users response is the correct answer or solution
                if (q.checkAnswer(response))
                    l_num_correct++;

            }  // end for MAX_QUESTIONS

            float l_percent_correct = (float) l_num_correct / (float) MAX_QUESTIONS * 100;
            if (l_percent_correct >= TARGET_PERCENT) {
                System.out.printf("Congratulations! ");
            }
            System.out.printf("You answered %2d of %2d correctly (%3.0f percent)\n",
                    l_num_correct, MAX_QUESTIONS, l_percent_correct);
            g_cumulative_num_correct += l_num_correct;  // tally of number answered correctly across all rounds

            // to challenge the student player a bit more we'll report how long it took them

            double l_round_end_time = System.currentTimeMillis() / 1000.0;  // End Time of this round of the game

            l_seconds_taken = l_round_end_time - l_round_start_time;

            if (l_seconds_taken < 60) {
                double l_minutes_taken = l_seconds_taken / 60;
                System.out.printf("Tame Taken was %2.0f minute(s) and %2.0f seconds\n", l_minutes_taken, l_seconds_taken % 60);
            } else {
                System.out.printf("Tame Taken was %2.0f seconds\n", l_seconds_taken);
            }

            System.out.print("Play another game?");
            l_continue = input.next();

            if (l_percent_correct > g_high_score)
                g_high_score = l_percent_correct;

        } while (l_continue.toUpperCase().startsWith("Y"));

        if (g_total_asked > MAX_QUESTIONS){
            float l_cumulative_percent_correct = (float) g_cumulative_num_correct / (float) g_total_asked * 100;
            if (l_cumulative_percent_correct >= TARGET_PERCENT) {
                System.out.printf("Congratulations! ");
            }
            System.out.printf("You answered %3d of %3d correctly (%3.0f percent), high score of %3.0f percent\n",
                    g_cumulative_num_correct, g_total_asked, l_cumulative_percent_correct, g_high_score);
        }
    }
}