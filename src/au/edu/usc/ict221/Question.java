package au.edu.usc.ict221;

public interface Question {
    /**
     * METHOD showQuestion prints the Question for the player to read and respond
     * NO PARAMs as the parent class has teh constants and variables encapsulated
     */

    void showQuestion();

    /**
     * @param response - the player's response to the printed question
     * @return boolean - true if player response matcheds the expected answer
     * METHOD checkAnswer compares the player's response with the expected answer
     */
    boolean checkAnswer(int response);
}
