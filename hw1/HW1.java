package comp_272.hw1;

/*
 * *** MALEC BASEL TARABEIN (002) ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

 import java.util.Stack;

 public class HW1 {
 
     /*
      * Class LinkedList
      *
      * This class builds a singly linked list. Each node of the linked-list
      * contains a single integer values.
      *
      * Methods:
      *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
      *                                   sorted fashion
      *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
      *                                   than 'val'
      *  - void   removeElement(val)    - Removes all values in the linked list of
      *                                   value 'val'
      *  - String toString()            - coverts and returns the linked-lists as a string
      *                                   delimited by brackets []
      *
      */
 
     static class LinkedList {
         static class Node {
             int data;
             Node next;
 
             Node(int d)       // Constructor
             {
                 data = d;
                 next = null;
             }
         }
         Node head;            // head of Linked-list
 
         /*
          * Method sortedInsert() - this method will insert a new node to the
          * linked list containing the value specific in the parameter 'data'.
          * The newly inserted node will be inserted in sorted order within
          * the linked-list.
          *
          */
         public void sortedInsert ( int data )
         {
             Node new_node = new Node(data);
 
             new_node.next = null;
 
             // Special case for head node.
             if (this.head == null || head.data >= new_node.data ) {
                 new_node.next = head;
                 head = new_node;
             } else {
                 // locate the node before the point of insertion
                 Node current = this.head;
 
                 // Identify where to place the item to insert
                 while (current.next != null && current.next.data < data) {
                     current = current.next;
                 }
                 new_node.next = current.next;
                 current.next = new_node;
             }
 
             return;
         }
 
 
         /*
          * Method removeElementsLT() - the method removes all nodes that contain a
          * value that is less than the provided parameter 'ltValue'.
          *
          * The method will invoke the method removeElements for each element
          * found in the linked-list that is less than thr parameter value passed.
          */
         public void removeElementsLT ( int ltValue ){
            while (this.head != null && this.head.data < ltValue)
                this.head = this.head.next;
         }
 
         /*
          * Method removeElement() - the method removes all nodes that contain a
          * value equal to the value the provided parameter 'value'.
          */
         public void removeElement ( int value ){
            if (this.head != null && this.head.data <= value) {

                if (this.head.data == value) { // special case for head == value
                    while (this.head != null && this.head.data == value) {
                        this.head = this.head.next;
                    }
                    
                } else { // if head < value

                    // find the node directly below target nodes
                    Node lower = this.head;
                    while (lower.next != null && lower.next.data < value) {
                        lower = lower.next;
                    }

                    // find the node directly above target nodes
                    Node upper = lower.next;
                    while (upper != null && upper.data == value) {
                        upper = upper.next;
                    }

                    // link lower 
                    lower.next = upper;
                }
            }
         }
 
         /*
          * Method toString() - this is a helper method for printing / constructing
          * a string object from the linked-list.
          */
         public String toString () // Method to output the LinkedList as a String
         {
             String output = "[";
             Node currNode = this.head;
             while (currNode != null) {
                 output += currNode.data + " ";
                 currNode = currNode.next;
             }
             return output.trim() + "]";
         }
 
     } // End class LinkedList
 
 
 
 
     /*
      * Class Stacks
      *
      * This class utilizes the Java Common Framework Library Stack class.
      *
      * The intent of this class is to write methods which utilize the Java
      * library's Stack class. You need to utilize this library class in
      * your solution.
      *
      * Methods:
      *  - boolean isPalindrome(string)   - method returns true or false if 'string'
      *                                     is a palindrome
      *  - int     findlargestK(stack, k) - method accepts a stack and returns the
      *                                     the largest index in the stack containing
      *                                     value 'k' (stack index starts at 0)
      *
      */
 
     static class Stacks {
 
         /*
          * Method isPalindrome() - This method will return the boolean value 'true'
          * or 'false' on if the passed in parameter string is a palindrome or not.
          *
          * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
          * Moreover, spaces are ignore, so both 'race car' and 'racecar' are plaindromes.
          *
          * The method should utilize the provided Stack class.
          */
         public static boolean isPalindrome(String input) {
            // Setting up stack, char array, & iteration position tracker
            Stack<Character> stack = new Stack<>();
            char[] inputCA = input.toLowerCase().replaceAll("\\s+", "").toCharArray();
            int i = 0;

            // Iterating through the first half of the array
            while(i < inputCA.length/2)
                stack.push(inputCA[i++]);

            // In the case of an odd length input, skip the middle letter
            i += inputCA.length%2;

            // Iterating through the rest of the array
            boolean result = true;
            while(result && !stack.isEmpty())
                result = stack.pop() == inputCA[i++];
            return result;
         }
 
 
         /*
          * Method findLargestk() - This method will return the largest index
          * position in the stack for the value specified by the parameter 'k'.
          *
          * Note that the bottom of the stack is index location 0. So it you push
          * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
          * value '4' for the parameter k, then the largest index position is index
          * location 6.
          *
          * The method should utilize the provided Stack class. This method should NOT
          * destroy the passed in stack, meaning when the method returns, the passed in
          * stack should be identical to when this method is passed. One trick as you
          * pop elements off the passed in stack, place them in a temp stack. Then when
          * completed, place them all back in teh original stack.
          */
         public static int findLargestK(Stack<Integer> stack, int k) {
            
            // setup: largest index for target val, index tracker, temporary stack item storage
            int largestIndex = -1;
            int tempIndex = stack.size();
            Stack<Integer> tempStack = new Stack<>();

            // read last elements until target value is found or entire stack is iterated
            while(largestIndex == -1 && tempIndex-- > 0) {
                if (stack.peek() == k) {
                    largestIndex = tempIndex;
                } else {
                    tempStack.push(stack.pop());
                }
            }

            // put elements back
            while(!tempStack.isEmpty())
                stack.push(tempStack.pop());
            
            // we got the solution yay
            return largestIndex;
         }
        
        
 
     }  // End class Stacks
 
 
     /*******************************
      *
      * Algorithm Analysis
      *
      * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
      * Modify the return statement to return the correct option number.
      *
      *********************************/
 
     public static int algorithmAnalysis1(int n, int m) {
         int a = 0, b = 0;
 
         for (int i=0; i < n; i++)
             a+= Math.random();
 
         for (int j=0; j < m; j++)
             b+= Math.random();
 
         /*
          * Select the correct option listed below:
          *   1. O(N * M) time, O(1) space
          *   2. O(N + M) time, O(N + M) space
          *   3. O(N + M) time, O(1) space
          *   4. O(N * M) time, O(N + M) space
          *
          * return the answer (which option is correct), in the return statement
         */
 
         return 3;
     }
 
 
     public static int algorithmAnalysis2(int n) {
         int i, j, k = 0;
         for (i = n/2; i <= n; i++)
             for ( j = 2; j <= n; j = j*2 )
                 k+= n/2;
 
         /*
          * Select the correct option listed below:
          *   1. O(N) time
          *   2. O(N log N) time
          *   3. O(N^2) time
          *   4. O(N^2Log n) time
          *
          * return the answer (which option is correct), in the return statement
          */
 
         return 2;
     }
 
 }