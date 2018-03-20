package au.edu.usc.ict221;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    @Test
    void testCreate() {
        Question q1 = new Question(3, 4, "+");
        assertFalse(q1.checkAnswer(0));
        assertTrue(q1.checkAnswer(7));
    }
    @Test
    void testCreate2() {
        Question q2 = new Question(3, 4, "-");
        assertFalse(q2.checkAnswer(0));
        assertTrue(q2.checkAnswer(-1));
    }
    @Test
    void testCreate3() {
        Question q3 = new Question(11, 4, "/");
        assertFalse(q3.checkAnswer(4));
        assertTrue(q3.checkAnswer(2));
    }
    @Test
    void testCreate4() {
        Question q4 = new Question(12, 4, "*");
        assertFalse(q4.checkAnswer(42));
        assertTrue(q4.checkAnswer(48));
    }
    void testCreate5() {
        Question q5 = new Question(11, 4, "%");
        assertFalse(q5.checkAnswer(4));
        assertTrue(q5.checkAnswer(3));
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }
//    @Test
//    public void out() {
//        Question q5 = new Question(3, 4, "+");
//        assertEquals("What is  3 +  4?", outContent.toString());
//    }
}
