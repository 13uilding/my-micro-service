package com.revature.MyMicroService.HackerRank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HackerRankQuestions {

//  // Question Easy
//  public static int deepestLevel;
//  public static int height(Node root) {
//    recusionTranversing(root, 0);
//
//    return deepestLevel;
//  }
//  public static void recusionTranversing(Node cur, int currentLevel) {
//    deepestLevel = Math.max(currentLevel, deepestLevel);
//    // Does it have left?
//    if (cur.left != null) {
//      System.out.println("We have a left at level " + currentLevel + ", and it's value is " + cur.left.data);
//      recusionTranversing(cur.left, currentLevel + 1);
//    }
//    // Does it have right?
//    if (cur.right != null) {
//      System.out.println(
//          "We have a right at level " + currentLevel + ", and it's value is " + cur.right.data);
//      recusionTranversing(cur.right, currentLevel + 1);
//    }
//  }

  // Question medium: Is this a Binary Search Tree
  // all left nodes must be less than data value of that node
  // all right nodes must be greater than data values of that node
  // all data must be distinct
  public static Set<Integer> nodeValues = new HashSet<Integer>();
  public static boolean isBinaryTree = true;

  public static void traversingTree(Node cur) {
    if (isBinaryTree == false) return;
    if (cur.left != null) {
      if (nodeValues.contains(cur.left.data)) {
        isBinaryTree = false;
        return;
      }
      if (cur.left.data > cur.data) {
        isBinaryTree = false;
        return;
      }
      nodeValues.add(cur.left.data);
      traversingTree(cur.left);
      }
    if (cur.right != null) {
      if (nodeValues.contains(cur.right.data)) {
        isBinaryTree = false;
        return;
      }
      if (cur.right.data < cur.data) {
        isBinaryTree = false;
        return;
      }
      nodeValues.add(cur.right.data);
      traversingTree(cur.right);
    }
  }

  public static boolean checkBST(Node root) {
    traversingTree(root);
    return isBinaryTree;
  }

//  // This was their solution
//  boolean checkBST(Node root) {
//    return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
//  }
//  boolean checkBST(Node node, int min, int max) {
//    if (node == null) return true;
//    return  min < node.data && node.data < max &&
//            checkBST(node.left, min, node.data) &&
//            checkBST(node.right, node.data, max);
//  }

  // Final binary tree for today:

  public static Node lca(Node root, int v1, int v2) {
    int currentVal = root.data;
    if (currentVal > v1 && currentVal < v2) return root;
    if (currentVal > v1 && currentVal > v2) return lca(root.left, v1, v2);
    else return lca(root.right, v1, v2);
  }



  public static Node insert(Node root, int data) {
    if (root == null) {
      return new Node(data);
    } else {
      Node cur;
      // Inserts left if the current data smaller than root.data
      if (data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
        // Else insert right
      } else {
        cur = insert(root.right, data);
        root.right = cur;
      }
      return root;
    }
  }

  public static void main(String[] args) {
    //
    Scanner scan = new Scanner(System.in);
    int t = scan.nextInt();
    Node root = null;
    while (t-- > 0) {
      int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
//    int height = height(root);
//    System.out.println(height);
//    boolean isBTS = checkBST(root);
//    System.out.println(isBTS ? "Yes" : "No");

  }

}

class Node {
  Node left;
  Node right;
  int data;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}
