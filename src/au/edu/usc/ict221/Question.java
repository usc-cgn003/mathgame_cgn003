package au.edu.usc.ict221;

public class Question {
    private int l_value1;
    private int l_value2;
    private String l_operator;
    private int l_answer;

    // define and load the Joke Question & Answer Arrays
    String c_joke_format = "How many %s does it take to screw in a light bulb?";
    String[] c_joke_question_text = {
            "psychiatrists", "men", "flies", "rabbits"
    };
    int[] c_joke_answer_number = {0, 1, 2, 4};
    String[] c_joke_answer_text = {
            "... It will change itself when it is good and ready.",
            "... But you will just need to wait until the football finishes on TV.",
            "... But do not ask us how they got in there!",
            "... at least - because once they start screwing they tend to multiple like... well, Rabbits."
    };

    public Question(int a_value1, int a_value2, String a_operator) {
        l_value1 = a_value1;
        l_value2 = a_value2;
        l_operator = a_operator;
    }

    /**
     * @method showQuestion prints the Question for the player to read and respond
     */

    public void showQuestion() {
        // Display the question to prompt a response from the user
        if (l_operator.equals("J")) {
            // Correlate the values with a joke question from the array
            int l_joke_index = (l_value1 * l_value2) % c_joke_answer_number.length;
            System.out.printf(c_joke_format, c_joke_question_text[l_joke_index]);
        }
        else {
            System.out.printf("What is %2d %s %2d? ", l_value1, l_operator, l_value2);
        }
    }

    /**
     * @param response - the player's response to the printed question
     * @return boolean - true if player response matcheds the expected answer
     * @method checkAnswer compares the player's response with the expected answer
     */
    public boolean checkAnswer(int response) {

        // caclulate what the answer should be
        int l_answer = 0;
        int l_joke_index = (l_value1 * l_value2) % c_joke_answer_number.length;

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
        else if (l_operator.equals("J")) {
            // Correlate the question with an answer constructed from the answer array
            // System.out.println("Let us just check the encyclopaedia for the answer to that one...");
            l_answer = c_joke_answer_number[l_joke_index];
        } else {
            System.out.println("Input exception - invalid operator");
        }  /// endif operator "case"

        // compare the user's response with the calculated answer (solution)
        if (response == l_answer) {
            System.out.printf("  Yes!\n");
            if (l_operator.equals("J")) {
                // Conclude with the punchline constructed from the answer array
                System.out.println(c_joke_answer_text[l_joke_index]);
            }
            return true;
        } else {
            System.out.printf("  No, the answer is %d.\n", l_answer);
            if (l_operator.equals("J")) {
                // Conclude with the punchline constructed from the answer array
                System.out.println(c_joke_answer_text[l_joke_index]);
            }
            return false;
        }
    }
}