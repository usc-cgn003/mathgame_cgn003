package au.edu.usc.ict221;

public class JokeQuestion implements Question {
    private int l_value1;
    private int l_value2;
    // NOTE:  no operator required, as the Class instance is limited to operator "J" - Joke
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

    public JokeQuestion(int a_value1, int a_value2) {
        l_value1 = a_value1;
        l_value2 = a_value2;
    }

    /**
     *  METHOD showQuestion prints the Question for the player to read
     *  param  - no paramaters, as the class instance has the cinstants and variables stored for reference
     */

    @Override
    public void showQuestion() {
        // Display the question to prompt a response from the user

        // Correlate the values with a joke question from the array
        int l_joke_index = (l_value1 * l_value2) % c_joke_answer_number.length;
        System.out.printf(c_joke_format, c_joke_question_text[l_joke_index]);
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
        int l_joke_index = (l_value1 * l_value2) % c_joke_answer_number.length;

        // Correlate the question with an answer constructed from the answer array
        // System.out.println("Let us just check the encyclopaedia for the answer to that one...");
        l_answer = c_joke_answer_number[l_joke_index];

        // compare the user's response with the calculated answer (solution)
        if (response == l_answer) {
            System.out.printf("  Yes! ");
            // Conclude with the punchline constructed from the answer array
            System.out.println(c_joke_answer_text[l_joke_index]);
            return true;
        } else {
            System.out.printf("  No, the answer is %d.\n", l_answer);
            // Conclude with the punchline constructed from the answer array
            System.out.println(c_joke_answer_text[l_joke_index]);
            return false;
        }
    }
}