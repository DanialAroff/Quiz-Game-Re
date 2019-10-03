
package quizgamere;

import java.util.ArrayList;
import java.util.Collections;

class Question {
    String question;
    ArrayList<String> answers; 
    String correctAns; // A, B, C or D
    
    Question (String question, ArrayList answers, String correctAns) {
        this.question = question;
        this.answers = answers;
        this.correctAns = correctAns;
    }
    
    public String getQuestion() {
        return question;
    }
    public ArrayList getAnswers() {
        return answers;
    }
    
    /**
     * 
     * @param ans
     * @return true if user's answer is equal to the correct answer 
     */
    public boolean isCorrect(String ans) {
        return ans.equals(correctAns);
    }
    
    /**
     * Shuffle the order of answers
     */
    public void shuffleAnswers() {
        Collections.shuffle(answers);
    }
} 

public class QuizGame {
    
}
