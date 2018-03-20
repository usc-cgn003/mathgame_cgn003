package au.edu.usc.ict221;

import java.util.Random;
import java.util.Scanner;

/**
 * @class PlayGameRound
 *
 */


public class PlayGameRound {

    private int l_max_questions;
    private int l_max_number;
    private int l_number_correct;

    public PlayGameRound(int a_max_questions, int a_max_number) {
        l_max_questions = a_max_questions;
        l_max_number = a_max_number;
    }

    public int playGameRound(int a_max_questions, int a_max_number){

        l_number_correct = 0;  // reset per round of questions in the game

        // define method constants
        String OPERATOR_LIST = "+-*/%";

        // load instances of essential library objects
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        // now let us run a series of questions for this round, with MAX_QUESTIONS in the round
        for(int i=0;i<l_max_questions; i++){

            // Adding 1 to the numbers has two affects:
            // 1. it takes the maximum random number up to the specified constant MAX_NUM_SIZE
            // 2. it avoids the game asking questions that are too easy or result in division by zero errors
            int a=rand.nextInt(l_max_number)+1;
            int b=rand.nextInt(l_max_number)+1;
            int op_index=rand.nextInt(OPERATOR_LIST.length());
            String operator=OPERATOR_LIST.substring(op_index,op_index+1);

            // This program or class controls the degere of difficuklty and therefore
            // we adapt the number set here rather than within the Question class
            if(operator.equals("/")&&a<b){
                // Gross up the numerator for a more useful question
                a*=b;
            }
            if(operator.equals("%")&&a<b){
                // switch the numbers to avoid questions being too easy (ie. merely a 0 or 1 answer)
                int c=a;
                a=b;
                b=c;
            }
            if(b==1){
                // too easy at any level, so just add 1 to make it a bit more challenging
                b++;
            }

            /*
             * This next part attempts to introduce classes in place of finite variables
             * to issue the questions.
             */

            Question q=new Question(a,b,operator);
            q.showQuestion();

            // Accept the response [to the question] from the user
            int response=input.nextInt();

            // Check whether the users response is the correct answer or solution
            if(q.checkAnswer(response))
                l_number_correct++;

        }  // end for MAX_QUESTIONS

        return l_number_correct;

    }  // End playGameRound
}
