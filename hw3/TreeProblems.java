package comp_272.hw3;

/*
 * *** Malec Basel Tarabein (002) ***
 *
 * This java file contains several simple tree problems that need to be
 * codified. These routines  must use the TreeMap and TreeSet library
 * classes from the Java Collection Framework.
 *
 */

 import java.util.*;

 public class TreeProblems {
 
   /**
    * Method different()
    *
    * Given two TreeSets of integers, return a TreeSet containing all elements 
    * that are NOT in both sets. In other words, return a TreeSet of all the
    * elements that are in one set but not the other.
    */
   
   public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {
 
     // INSERT CODE HERE - DO NOT FORGET TO PLACE YOUR NAME ABOVE
     //
     // This can be done numerous ways, but once such will only that
     // *several* lines of code. Hint: create two temporary TreeSets and utilize the
     // methods retainAll(), addAll(), and removeAll(). But in the end, get something to work.
     Set<Integer> result = new TreeSet<Integer>();
     Set<Integer> secondary = new TreeSet<Integer>();

     result.addAll(setA); // contains A
     result.addAll(setB); // contains AUB
     secondary.addAll(setA); // contains A
     secondary.retainAll(setB); // contains A^B
     result.removeAll(secondary); // contains (AUB)/(A^B) aka (A/B)U(B/A)
 
     return result;
   }
 
 
   /**
    * Method removeEven()
    *
    * Given a treeMap with the key as an integer, and the value as a String,
    * remove all <key, value> pairs where the key is even. 
    */
 
   public static void removeEven(Map<Integer, String> treeMap) {
 
     // INSERT CODE HERE.
    
      Object[] temp = treeMap.keySet().toArray();

      for (Object item : temp) {
        if(((Number)item).intValue()%2 == 0) {
            treeMap.remove(item);
        }
      }

    }
 
 
   /**
    * Method treesEqual()
    *
    * Given two treeMaps, each with the key as an integer, and the value as a String,
    * return a boolean value indicating if the two trees are equal or not.
    */
 
   public boolean treesEqual(Map<Integer, String> tree1,Map<Integer, String> tree2 ) {
 
     // INSERT CODE HERE

     Boolean result = true;;

     if(tree1 != null && tree2 != null) {
        Object[] arr1 = tree1.keySet().toArray();
        Object[] arr2 = tree2.keySet().toArray();
        int i = 0;
        result = arr1.length == arr2.length;

        while (result && i < arr1.length) {
          result = tree1.get(((Number)arr1[i]).intValue()).equals(tree2.get(((Number)arr2[i++]).intValue()));
        }

     } else if ((tree1 == null)^(tree2 == null)) {
        result = false;
     }

     return result;
 
   }
 
 } // end treeProblems class