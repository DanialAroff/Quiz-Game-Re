
package quizgamere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    // for testing purposes
    public void printQuestion() {
        System.out.println(question);
        String[] opt = {"A", "B", "C", "D"};
        for (int i = 0; i < opt.length; i++) {
            System.out.println(opt[i] + ". " + answers.get(i));
        }
    }
} 

public class QuizGame {
    ArrayList<Question> setOfQuestions;
    int score;
    
    public QuizGame() {
        setOfQuestions = new ArrayList<>();
    }
    
    public void loadQuestion(String questionfile) {
        String question = "";
        ArrayList<String> answers = new ArrayList<>();
        String correctAns = "";
        
        Scanner s;
        try {
            s = new Scanner(new File(questionfile), "UTF-8");
            s.useDelimiter(",");
            while(s.hasNext()) {
                int i = 0;
                while (i < 6) {
                    if (i == 0) question = s.next();
                    else if (i > 0 && i < 5) answers.add(s.next());
                    else correctAns = s.next();   
                    i++;
                }        
                setOfQuestions.add(new Question(question, answers, correctAns));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuizGameRe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
