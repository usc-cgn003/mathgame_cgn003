package au.edu.usc.ict221;

public class MathQuestion implements Question {
    private int l_value1;
    private int l_value2;
    private String l_operator;
    private int l_answer;

    public MathQuestion(int a_value1, int a_value2, String a_operator) {
        l_value1 = a_value1;
        l_value2 = a_value2;
        l_operator = a_operator;
    }

    /**
     *  METHOD showQuestion prints the Question for the player to read
     *  param  - no paramaters, as the class instance has the cinstants and variables stored for reference
     */

    @Override
    public void showQuestion() {
        // Display the question to prompt a response from the user
        System.out.printf("What is %2d %s %2d? ", l_value1, l_operator, l_value2);
    }

    /**
     * METHOD checkAnswer compares the player's response with the expected answer
     * @param response - the player's response to the printed question
     * @return boolean - true if player response matcheds the expected answer
     */

    @Override
    public boolean checkAnswer(int response) {

        // caclulate what the answer should be
        int l_answer = 0;
        if (l_operator.equals("+"))
            l_answer = l_value1 + l_value2;
        else if (l_operator.equals("-"))
            l_answer = l_value1 - l_value2;
        else if (l_operator.equals("*"))
            l_answer = l_value1 * l_value2;
        else if (l_value2 == 0) {
            // it should never hit this exception, as the calling routine should pre-alidate to remove zeros
            System.out.println("Divisor is Zero, so we accept 0 as your answer (even though it's not strictly correct)");
            l_answer = 0;
        } else if (l_operator.equals("/"))
            l_answer = l_value1 / l_value2;
        else if (l_operator.equals("%"))
            l_answer = l_value1 % l_value2;
        else {
            System.out.println("Input exception - invalid operator");
        }  /// endif operator "case"

        // compare the user's response with the calculated answer (solution)
        if (response == l_answer) {
            System.out.printf("  Yes!\n");
            return true;
        } else {
            System.out.printf("  No, the answer is %d.\n", l_answer);
            return false;
        }
    }
}