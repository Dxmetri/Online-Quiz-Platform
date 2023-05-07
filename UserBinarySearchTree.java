// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: UserBinarySearchTree.java
// Description: The UserBinarySearchTree class  implements a binary search tree that stores and searches user objects based on their name and password.
// The insert method inserts a new user into the tree by traversing through the tree and adding the new node at the appropriate position based on the name of the user.
// The authenticateUser method takes a name and password as input and searches the tree for a matching user, returning the user if found, otherwise returning null.
// **********************************************************************************
package com.example.javafxonlinequizplatform.datastructure;

import com.example.javafxonlinequizplatform.model.User;

public class UserBinarySearchTree {
    private Node root;

    public void insert(User user) {
        if (root == null) {
            root = new Node(user);
        } else {
            insert(root, user);
        }
    }

    private void insert(Node node, User user) {
        if (user.getName().compareTo(node.user.getName()) < 0) {
            if (node.left == null) {
                node.left = new Node(user);
            } else {
                insert(node.left, user);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(user);
            } else {
                insert(node.right, user);
            }
        }
    }

    public User authenticateUser(String name, String password) {
        Node node = authenticateUser(root, name, password);
        if (node != null) {
            return node.user;
        }
        return null;
    }

    private Node authenticateUser(Node node, String name, String password) {
        if (node == null) {
            return null;
        }
        if (name.compareTo(node.user.getName()) < 0) {
            return authenticateUser(node.left, name, password);
        } else if (name.compareTo(node.user.getName()) > 0) {
            return authenticateUser(node.right, name, password);
        } else if (password.equals(node.user.getPassword())) {
            return node;
        } else {
            return null;
        }
    }

    private static class Node {
        private User user;
        private Node left;
        private Node right;

        public Node(User user) {
            this.user = user;
        }
    }
}

