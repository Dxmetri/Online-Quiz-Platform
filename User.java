// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: User.java
// Description: the User class is a simple data model that stores information about a user in the system.
// It is used by other classes to authenticate users and to store and retrieve their quiz scores.
// **********************************************************************************
package com.example.javafxonlinequizplatform.model;

    public class User {
        private String name;
        private String password;
        private int score;


        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        public void setScore(int score)
        {
            this.score=score;
        }

        public int getScore() {
            return score;
        }
    }


