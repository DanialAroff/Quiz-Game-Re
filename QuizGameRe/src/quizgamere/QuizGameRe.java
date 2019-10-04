
package quizgamere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizGameRe {

    public static void main(String[] args) {
        
        // testing on reading csv files
        Scanner s;
        try {
            s = new Scanner(new File("game_questions.csv"), "UTF-8");
            s.useDelimiter(",");
            while(s.hasNext()) {
                System.out.print(s.next() + " ");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuizGameRe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // testing on printing questions
        System.out.println("\n");
        ArrayList<String> a = new ArrayList<>();
        a.add("Turdak");
        a.add("Parvati");
        a.add("White Rider");
        a.add("Orpheus Telos");
        Collections.shuffle(a);
        Question j = new Question("Which Persona?", a, "D");
        j.printQuestion();
    }
    
}
