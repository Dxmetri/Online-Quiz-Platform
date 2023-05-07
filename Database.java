// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: Database.java
// Description: The Database class serves as a data storage and management system for the quiz application.
// It contains static fields and methods for reading user and quiz data from external files, as well as storing quiz scores in a queue.
// The class provides functionality for authenticating user login credentials and sorting quiz questions alphabetically.
// The readTrueFalseQuiz and readMCQs methods read in quiz questions and answers from external files and return them as Quiz objects.
// **********************************************************************************
package com.example.javafxonlinequizplatform.model;
import com.example.javafxonlinequizplatform.datastructure.UserBinarySearchTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Database {

    static UserBinarySearchTree users=new UserBinarySearchTree();

    static ArrayList<Quiz> quizArrayList=new ArrayList<>();
    static Queue<Integer> scoresQueue=new LinkedList<>();//this will store all scores
    public static void readUsers()
    {
        try {
            FileReader fr=new FileReader("users.txt");
            BufferedReader br=new BufferedReader(fr);

            String line=null;
            while ((line=br.readLine())!=null)
            {
                String token[]=line.split(",");
                User user=new User(token[0],token[1]);
                users.insert(user);

            }
            br.close();
        }
        catch (Exception e)
        {

        }
    }

    public static User authenticateUser(String nm,String ps)
    {

        return users.authenticateUser(nm,ps);

    }

    public static ArrayList<Quiz>  readTrueFalseQuiz()
    {
        try {

            FileReader fr=new FileReader("truefalse.txt");
            BufferedReader br=new BufferedReader(fr);
            String line=null;
            while ((line=br.readLine())!=null)
            {
                String token[]=line.split("#");
                String question=token[0];
                boolean answer= token[1].equalsIgnoreCase("true")?true:false;
                TrueFalseQuiz quiz=new TrueFalseQuiz(question,"TF",answer);
                quizArrayList.add(quiz);
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        Collections.shuffle(quizArrayList);
        return quizArrayList;
    }

    public static ArrayList<Quiz>  readMCQs()
    {
        try {

            FileReader fr=new FileReader("mcqs.txt");
            BufferedReader br=new BufferedReader(fr);
            String line=null;
            while ((line=br.readLine())!=null)
            {
                String token[]=line.split("#");
                String question=token[0];
                String op1=token[1];
                String op2=token[2];
                String op3=token[3];
                String op4=token[4];
                int correct=Integer.parseInt(token[5]);
                Quiz quiz=new MultipleChoiceQuiz(question,"MCQS",op1,op2,op3,op4,correct);
                quizArrayList.add(quiz);
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        Collections.shuffle(quizArrayList);
        return quizArrayList;
    }

    public static void addScores(int score)
    {
        scoresQueue.add(score);
    }



    //This Function will sort questions alphabetically
    public static void sortQuiz() {
        // Call the quickSort() method to sort the entire ArrayList
        quickSort(quizArrayList, 0, quizArrayList.size() - 1);
    }

    private static void quickSort(ArrayList<Quiz> quizList, int left, int right) {
        // If there is more than one element in the sub-list to be sorted
        if (left < right) {
            // Choose a pivot index and partition the sub-list around the pivot element
            int pivotIndex = partition(quizList, left, right);
            // Recursively sort the left and right sub-lists
            quickSort(quizList, left, pivotIndex - 1);
            quickSort(quizList, pivotIndex + 1, right);
        }
    }

    private static int partition(ArrayList<Quiz> quizList, int left, int right) {
        // Choose a pivot element
        int pivotIndex = (left + right) / 2;
        Quiz pivotValue = quizList.get(pivotIndex);
        // Move the pivot element to the end of the sub-list
        swap(quizList, pivotIndex, right);
        int storeIndex = left;
        // Iterate through the sub-list, moving elements less than the pivot element to the left
        for (int i = left; i < right; i++) {
            if (quizList.get(i).getQuestion().compareTo(pivotValue.getQuestion()) < 0) {
                swap(quizList, i, storeIndex);
                storeIndex++;
            }
        }
        // Move the pivot element back to its final position
        swap(quizList, storeIndex, right);
        return storeIndex;
    }

    private static void swap(ArrayList<Quiz> quizList, int i, int j) {
        // Swap two elements in the ArrayList
        Quiz temp = quizList.get(i);
        quizList.set(i, quizList.get(j));
        quizList.set(j, temp);
    }


}