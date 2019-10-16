
package quizgamere;

import java.io.*;
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
     * @return true if user's answer is the same as the correct answer 
     */
    public boolean isCorrect(String ans) {
        if (ans.equalsIgnoreCase("A")) return answers.get(0).equals(correctAns);
        else if (ans.equalsIgnoreCase("B")) return answers.get(1).equals(correctAns);
        else if (ans.equalsIgnoreCase("C")) return answers.get(2).equals(correctAns);
        else if (ans.equalsIgnoreCase("D")) return answers.get(3).equals(correctAns);
        else return false;
    }
    
    /**
     * Shuffle the order of answers
     */
    public void shuffleAnswers() {
        Collections.shuffle(answers);
    }
    
    /**
     * Prints out the question with choice of answers
     */
    public void printQuestion() {
        System.out.println(question);
        String[] opt = {"A", "B", "C", "D"};
        for (int i = 0; i < opt.length; i++) {
            System.out.println(opt[i] + ". " + answers.get(i));
        }
    }
    
    @Override
    public String toString() {
        return question;
    }
} 

public class QuizGame {
    private ArrayList<Question> setOfGameQuestions;
    private ArrayList<Question> setOfWarmupQuestions;
    private float score;
    private String playerName;
    private Scanner in;
    
    public QuizGame() {
        setOfGameQuestions = new ArrayList<>();
        setOfWarmupQuestions = new ArrayList<>();
        in = new Scanner(System.in, "UTF-8");
        loadQuestion("game_questions.csv", setOfGameQuestions);
        loadQuestion("warmup_questions.csv", setOfWarmupQuestions);
        
        File file = new File("score.txt");
        if (!file.isFile()){
           resetScore();
        }
    }
    
    public void mainMenu() {
        Collections.shuffle(setOfGameQuestions);
        Collections.shuffle(setOfWarmupQuestions);
        System.out.print("\t\t\tJAVA PROGRAM QUIZ GAME\n");
        System.out.print("\n\t\t\t   WELCOME ");
        System.out.print("\n\t\t\t      to ");
        System.out.print("\n\t\t\t   THE GAME ");
        System.out.print("\n\t\t________________________________________");
        System.out.print("\n\t\t________________________________________");
        System.out.print("\n\t\t   BECOME A MILLIONAIRE!!!!!!!!!!!    ") ;
        System.out.print("\n\t\t________________________________________");
        System.out.print("\n\t\t________________________________________");
        System.out.print("\n\t\t > Press S to start the game");
        System.out.print("\n\t\t > Press V to view the highest score  ");
        System.out.print("\n\t\t > Press R to reset score");
        System.out.print("\n\t\t > press H for help            ");
        System.out.print("\n\t\t > press Q to quit             ");
        System.out.print("\n\t\t________________________________________\n\n"); 
 
        String input = in.next();
        // to start the game
        if (input.equalsIgnoreCase("S")) {
            startWarmupGame();
        }
        // to view the highest score
        else if (input.equalsIgnoreCase("V")) {
            try {
                Scanner s = new Scanner(new File("score.txt"), "UTF-8");
                String name = s.nextLine();
                float scr = Float.valueOf(s.next());
                System.out.print("\n\n\n\n\t\t*************************************************************\n");
                System.out.printf("\t\t %s has secured the Highest Score %.2f", name, scr);
                System.out.print("\n\t\t*************************************************************\n\n\n\n");
            } catch (FileNotFoundException ex) {}
            try {
                System.in.read();
            } catch (IOException e) {} 
            mainMenu();
        }
        else if (input.equalsIgnoreCase("R")) {
            resetScore();
            mainMenu();
        }
        // prints HELP section
        else if (input.equalsIgnoreCase("H")) {
            System.out.printf("%n%n                              HELP");
            System.out.printf("%n -------------------------------------------------------------------------");
            System.out.printf("%n ......................... JAVA program Quiz Game...........");
            System.out.printf("%n >> There are two rounds in the game, WARMUP ROUND & CHALLENGE ROUND");
            System.out.printf("%n >> In warmup round you will be asked a total of 3 questions to test your general");
            System.out.printf("%n    knowledge. You will be eligible to play the game if you can give atleast 2");
            System.out.printf("%n    right answers otherwise you can't play the Game...........");
            System.out.printf("%n >> Your game starts with the CHALLENGE ROUND. In this round you will be asked");
            System.out.printf("%n    total 10 questions each right answer will be awarded $100,000.");
            System.out.printf("%n    By this way you can win upto ONE MILLION cash prize in USD...............");
            System.out.printf("%n >> You will be given 4 options and you have to press A, B ,C or D for the");
            System.out.printf("%n    right option");
            System.out.printf("%n >> You will be asked questions continuously if you keep giving the right answers.");
            System.out.printf("%n >> No negative marking for wrong answers");

            System.out.print("\n\n\t*********************BEST OF LUCK*********************************");
            System.out.println(" ");
            System.out.print("\n\n\t*****C PROGRAM QUIZ GAME is developed by CODE WITH C TEAM********");
            System.out.println("\n\tPress any key to return to the main menu!");
            try {
                System.in.read();
            } catch (IOException e) {} 
            mainMenu();
        }
        else if (input.equalsIgnoreCase("Q")) System.exit(0);
    }  
    private void startWarmupGame() {
        System.out.print("\n\n\n\n\n\n\n\n\n\t\t\tRegister your name: ");
        in.nextLine();
        playerName = in.nextLine();
        System.out.printf("%n ------------------  Welcome %s to Java Program Quiz Game -------------------------- ", playerName); // formatting
        System.out.print("\n\n Here are some tips you might wanna know before playing:");
        System.out.print("\n -------------------------------------------------------------------------");
        System.out.print("\n >> There are 2 rounds in this Quiz Game,WARMUP ROUND & CHALLENGE ROUND");
        System.out.print("\n >> In warmup round you will be asked a total of 3 questions to test your");
        System.out.print("\n    general knowledge. You are eligible to play the game if you give at least 2");
        System.out.print("\n    right answers, otherwise you can't proceed further to the Challenge Round.");
        System.out.print("\n >> Your game starts with CHALLENGE ROUND. In this round you will be asked a");
        System.out.print("\n    total of 10 questions. Each right answer will be awarded $100,000!");
        System.out.print("\n    By this way you can win upto ONE MILLION cash prize!!!!!..........");
        System.out.print("\n >> You will be given 4 options and you have to press A, B ,C or D for the");
        System.out.print("\n    right option.");
        System.out.print("\n >> You will be asked questions continuously, till right answers are given");
        System.out.print("\n >> No negative marking for wrong answers!");
        System.out.print("\n\n\t!!!!!!!!!!!!! ALL THE BEST !!!!!!!!!!!!!");
        System.out.print("\n\n\n Press Y  to start the game!");
        System.out.print("\n Press any other key to return to the main menu!\n\n"); 
        
        int count = 0;
        String input = in.next();
        if (!input.equalsIgnoreCase("Y")) {
            in = new Scanner(System.in, "UTF-8");
            mainMenu();
        }
        else {
            for (int i = 0; i < 3; i++) {
                Question q = setOfWarmupQuestions.get(i);
                q.printQuestion();
                System.out.println("");
                String ans = in.next();
                if (q.isCorrect(ans)) {
                    System.out.println("\nCorrect!!!\n");
                    count++;
                }
                else {
                    System.out.println("\nWrong!!! The correct answer is " + q.correctAns + "\n");
                }
            }
            if (count >= 2) {
                System.out.printf("%n%n\t*** CONGRATULATIONS %s you are eligible to play the Game ***", playerName);
                System.out.print("\n\n\n\tPress any key to Start the Game!\n");
                try {
                    System.in.read();
                }
                catch (IOException e) {}   
                startGame();
            }
            else {
                System.out.print("\n\nSORRY YOU ARE NOT ELIGIBLE TO PLAY THIS GAME, BETTER LUCK NEXT TIME");
                try {
                    System.in.read();
                }
                catch (IOException e) {} 
                mainMenu();
            }
        }    
    }
    private void startGame() {
        for (int i = 0; i < 10; i++) {
            Question q = setOfGameQuestions.get(i);
            q.printQuestion();
            System.out.println("");
            String ans = in.next();
            if (q.isCorrect(ans)) {
                System.out.println("\nCorrect!!!\n");
                score += 100000;
            }
            else {
                System.out.println("\nWrong!!! The correct answer is " + q.correctAns + "\n");
                break;
            }
        }
        if (score > 0 && score < 1000000) {
            System.out.print("\n\n\t\t**************** CONGRATULATIONS *****************");
            System.out.printf("%n\t\t\t\tYou won $%.2f", score);
        }
        else if (score == 1000000) {
            System.out.print("\n\n\t\t**************** CONGRATULATION ****************");
            System.out.print("\n\t\t\t\t YOU ARE A MILLIONAIRE!!!!!!!!!");
            System.out.printf("%n\t\t\t\tYou won $%.2f", score);
            System.out.println("\t\tThank You!!");
        }
        updateScore();
        score = 0; // reset score fot next game
        System.out.println("\n\nPress Y if you want to play next game");
        System.out.println("Press any key if you want to go main menu");
        try {
            System.in.read();
        }
        catch (IOException e) {} 
        mainMenu();
    }    
    private void updateScore() {
        try {
            FileWriter w = new FileWriter("score.txt");
            FileReader r = new FileReader("score.txt");
            r.read();
            float recorded_score = Float.valueOf(r.read());
            if (score >= recorded_score) {
                w.write(playerName);
                w.write(String.format("%n"));
                w.write(String.valueOf(score));
                w.close();
            }
        } catch (IOException ex) {}
    }
    private void resetScore() {
        try {  
            FileWriter w = new FileWriter("score.txt");
            w.write("NO_ONE");
            w.write(String.format("%n"));
            w.write("0.00");
            w.close();
        } catch (IOException ex){}
    }
    private void loadQuestion(String questionfile, ArrayList questionlist) {
        String question = "";
        ArrayList<String> answers = new ArrayList<>();
        String correctAns;
        int index = 0;
        
        Scanner sc; 
        try {
            sc = new Scanner(new File(questionfile), "UTF-8");   
            sc.useDelimiter(",");
            while (sc.hasNext()) {             
                if (index == 0) {
                    question = sc.next();
                    index++;
                }
                else if (index > 0 && index < 5) {
                    answers.add(sc.next().substring(1));
                    index++;
                }
                else {
                    // the last item in csv need to be handled differently
                    correctAns = sc.nextLine().replaceAll(", ", ""); 
                    questionlist.add(new Question(question, answers, correctAns));
                    answers = new ArrayList<>();
                    index = 0;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuizGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        QuizGame g = new QuizGame();
        g.mainMenu();
    }
}
