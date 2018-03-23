package au.edu.usc.ict221;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @class PlayGameRound
 *
 */

public class PlayGameRound {

    String OPERATOR_LIST = "+-*/%J";   // Super-set of operators that can apply in methis of this class

    private int l_max_questions;
    private int l_max_number;
    private int l_number_correct;
    private int l_repeat_list_size;
    private int l_repeated_attempts;
    private boolean l_allow_retries = true;  // default is to retry indefinitely
    private String l_operator_list = OPERATOR_LIST;  // default is to include the full range of operators

    // Simplest Game Round Construction doesn't offer the ability to retry incorrect answers
    public PlayGameRound(int a_max_questions, int a_max_number) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
    }

    // Alternative Game Round Construction 1 offer the ability to restrict teh set of operators used

    public PlayGameRound(int a_max_questions, int a_max_number, String a_operator_list) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_operator_list = a_operator_list;
    }

    // Alternative Game Round Construction 2 offer the ability to omit retry incorrect answers
    public PlayGameRound(int a_max_questions, int a_max_number, boolean a_allow_retries) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_allow_retries = a_allow_retries;
    }

    // Alternative Game Round Construction 3 offer the ability to:
    // restrict operators
    // omit retry incorrect answers
    public PlayGameRound(int a_max_questions, int a_max_number, String a_operator_list, boolean a_allow_retries) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_operator_list = a_operator_list;
        l_allow_retries = a_allow_retries;
    }

    public int playGameRound(int a_max_questions, int a_max_number, String a_operator_list, boolean a_allow_retries){

        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
        l_operator_list = a_operator_list;
        l_allow_retries = a_allow_retries;

        l_number_correct = 0;  // reset per round of questions in the game
        l_repeat_list_size = 0; // new set of questions to repeat per round
        l_repeated_attempts = 0; // new set of questions to repeat per round perhaps multiple re-tries
        boolean l_correct_answer;

        // Load method default constants if not already set
        if (l_operator_list.length() == 0)
            l_operator_list = OPERATOR_LIST;

        // load instances of essential library objects
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        // create an array list instance of Questions to the appopriate maximum size
        ArrayList<Question> l_question_list = new ArrayList<Question>();

        // now let us run a series of questions for this round, with MAX_QUESTIONS in the round
        for(int i=0;i<l_max_questions; i++){

            // Adding 1 to the numbers has two affects:
            // 1. it takes the maximum random number up to the specified constant MAX_NUM_SIZE
            // 2. it avoids the game asking questions that are too easy or result in division by zero errors
            int a=rand.nextInt(l_max_number)+1;
            int b=rand.nextInt(l_max_number)+1;
            int op_index=rand.nextInt(l_operator_list.length());
            String operator=l_operator_list.substring(op_index,op_index+1);

            /*
             * This next section applies class methods to issue the question and analyse the response.
             */

            if(operator.equals("J")) {  // j = Construct a Joke Question
                System.out.println("Surprise!! for something a bit different..."); // random suprise for the player
                Question jq = new JokeQuestion(a, b);
                jq.showQuestion();

                // Accept the response [to the question] from the user
                int response=input.nextInt();

                l_correct_answer = jq.checkAnswer(response);

            }
            else {
                // This program or class controls the degere of difficuklty and therefore
                // we adapt the number set here rather than within the Question class
                if (operator.equals("/") && (a <= b)) {
                    // Gross up the numerator for a more useful question
                    a *= b;
                }
                if (operator.equals("%") && (a < b)) {
                    // switch the numbers to avoid questions being too easy (ie. merely a 0 or 1 answer)
                    int c = a;
                    a = b;
                    b = c;
                }
                if (b == 1) {
                    // too easy at any level, so just add 1 to make it a bit more challenging
                    b++;
                }
                Question mq = new MathQuestion(a, b, operator);

                mq.showQuestion();
                // Accept the response [to the question] from the user
                int response=input.nextInt();

                l_correct_answer = mq.checkAnswer(response);
                if (!l_correct_answer && l_allow_retries) {
                        // load the Question instance into the Question List Array
                        l_question_list.add(l_repeat_list_size++, mq);
                }
            }

            // Check whether the users response is the correct answer or solution
            if(l_correct_answer)
                l_number_correct++;

        }  // end for MAX_QUESTIONS
        // for the last question, use a Joke Question

        /* If there are any questions the player failed to answer correctly then we keep asking until they
         * correctly answer them.
         * But the fact that we have gievn them the answer the first time makes it easy for them to cheat
         * the second time around.
        */

        while (l_repeat_list_size > 0 ) {
            for (int j = 0; j < l_repeat_list_size; j++) {

                System.out.printf("Repeat(%d)-", ++l_repeated_attempts); //count repeats or re-tries per round
                Question q2 = l_question_list.get(j);
                q2.showQuestion();

                // Accept the response [to the question] from the user
                int response=input.nextInt();

                // Check whether the users response is the correct answer or solution
                if(q2.checkAnswer(response)) {

                    // remove the question from the list array
                    l_question_list.remove(j--);
                    l_repeat_list_size--;
                }
            }
        }

        return l_number_correct;

    }  // End playGameRound
}
