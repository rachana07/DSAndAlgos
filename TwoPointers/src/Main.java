import java.util.*;

public class Main {

    //Two pointers: move in same direction or move in opposite direction
    // we can avoid two for loops(time complexity: O(n^2) with two pointers and solve the problems in O(n)
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    //Prefix sum is used when we asked the sum of subarrays and sliding window is used when we asked the question about contiguous array of certain size or condition
    // works with sorted list only
    public static void main(String[] args) {
        int res1 = removeDuplicates(Arrays.asList(0, 0, 1, 1, 1, 2, 2));
        System.out.println(res1);
        Node<Integer> node = new Node<>(0);
        node.next = new Node<>(1);
        node.next.next = new Node<>(2);
        node.next.next.next = new Node<>(3);
        node.next.next.next.next = new Node<>(4);
        int res2 = middleOfLinkedList(node);
        System.out.println(res2);
        List<Integer> res3 = moveZeros(Arrays.asList(1, 0, 2, 0, 0, 7));
        System.out.println(res3);
        List<Integer> res4 = twoSumSorted(Arrays.asList(2, 3, 4, 5, 8, 11, 18),21);
        System.out.println(res4);
        boolean res5 = isPalindrome("Do geese see God?");
        System.out.println(res5);
        List<Integer> res6 = subarraySum(Arrays.asList(1,3,7,8),10);
        System.out.println(res6);
        int res7 = subarraySumTotal(Arrays.asList(3,2,4,3),7);
        System.out.println(res7);
        Node<Integer> node0 = new Node<>(0);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        // Link nodes to form a list
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        boolean res8 = hasCycle(node0);
        System.out.println(res8);
        String res9 = reverseVowels("leetcode");
        System.out.println(res9);
        int res10 = numberOfBoats(Arrays.asList(3,5,3,4),5);
        System.out.println(res10);
        List<List<Integer>> res11 = threeSumZero(Arrays.asList(-1,0,1,2,-1,-4));
        System.out.println(res11);
        int res12 = longestSubstringWithAtmostTwoDistinctCharacters("eceba");
        System.out.println(res12);
        int res13 = maxArea(new int[]{1,7,2,5,4,7,3,6});
        System.out.println(res13);



    }

    public static class Node<T>{
        public T data;
        public Node<T> next;
        public Node(T data) {
            this.data = data;
        }
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    // two pointers in same direction (fast and slow pointers)
    public static int removeDuplicates(List<Integer> arr) {
        //Given a sorted list of numbers, remove duplicates and return the new length. You must do this in-place and without using extra memory.
        //Input: [0, 0, 1, 1, 1, 2, 2].
        //Output: 3.
        //Your function should modify the list in place so that the first three elements become 0, 1, 2. Return 3 because the new length is 3.
        int slow=0;
        for(int fast=0;fast< arr.size();fast++){
            if(!Objects.equals(arr.get(fast), arr.get(slow))) {
                slow++;
                arr.set(slow, arr.get(fast));
            }
        }
        return slow+1;
    }

    public static int middleOfLinkedList(Node<Integer> head) {
        //Find the middle node of a linked list.
        //
        //Input: 0 1 2 3 4
        //
        //Output: 2
        //
        //If the number of nodes is even, then return the second middle node.
        //
        //Input: 0 1 2 3 4 5
        //
        //Output: 3
       Node<Integer> fast = head;
       Node<Integer> slow = head;
       while(fast!=null && fast.next!=null){
           fast = fast.next.next;
           slow=slow.next;
       }

       return slow.data;
    }

    public static List<Integer> moveZeros(List<Integer> nums) {
        //Given an array of integers, move all the 0s to the back of the array while maintaining the relative order of the non-zero elements. Do this in-place using constant auxiliary space.
        //
        //Input:
        //
        //[1, 0, 2, 0, 0, 7]
        //Output:
        //
        //[1, 2, 7, 0, 0, 0]
        int slow=0;
        for(int fast=0;fast<nums.size();fast++){
            if(nums.get(fast)!=0){
                int slowNum = nums.get(slow);
                nums.set(slow, nums.get(fast));
                nums.set(fast, slowNum);
                slow++;
            }
        }

        return nums;
    }

    // opposite direction: two sum
    public static List<Integer> twoSumSorted(List<Integer> arr, int target) {
        //Given an array of integers sorted in ascending order, find two numbers that add up to a given target. Return the indices of the two numbers in ascending order. You can assume elements in the array are unique and there is only one solution. Do this in O(n) time and with constant auxiliary space.
        //
        //Input:
        //
        //arr: a sorted integer array
        //target: the target sum we want to reach
        //Sample Input: [2, 3, 4, 5, 8, 11, 18], 8
        //
        //Sample Output: 1 3
        int left=0;
        int right=arr.size()-1;
        while(left<=right){
            int sum = arr.get(left)+arr.get(right);
            if(sum == target){
                return Arrays.asList(left,right);
            }
            if(sum<target){
                left++;
            }else{
                right--;
            }
        }

        return null;
    }

    //palindromic string
    public static boolean isPalindrome(String s) {
        //Determine whether a string is a palindrome, ignoring non-alphanumeric characters and case. Examples:
        //
        //Input: Do geese see God? Output: True
        int left=0;
        int right = s.length()-1;
        while(left<=right){
            while(left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(right>left && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
           if(Character.toLowerCase(s.charAt(left))!=Character.toLowerCase(s.charAt(right))){
               return false;
           }
           left++;
           right--;
        }

        return true;
    }

    // Prefix sum
    public static List<Integer> subarraySum(List<Integer> arr, int target) {
        //Given an array of integers and an integer target, find a subarray that sums to target and return the start and end indices of the subarray.
        //
        //Input: arr: 1 2 3 4 target: 5
        //
        //Output: 1 3
        //
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum=0;
        int complement=0;
        for(int i=0;i<arr.size();i++){
            sum = sum + arr.get(i);
            complement = sum - target;
            if(map.containsKey(complement)){
                return Arrays.asList(map.get(complement)+1,i);
            }
            map.put(sum,i);
        }
        return null;
    }

    //Find the total number of subarrays that sums up to target.
    //input:[1,1,1]  output:2 ([1,1],[1,1])
    public static int subarraySumTotal(List<Integer> arr, int target) {
       int sum=0;
       int count=0;
       HashMap<Integer,Integer> map = new HashMap<>();
       map.put(0,1);
       for(int i=0;i<arr.size();i++){
           sum = sum+arr.get(i);
           int complement = sum - target;
           if(map.containsKey(complement)){
               count = count + map.get(complement);
           }
           map.put(sum, map.getOrDefault(sum,0)+1);
       }

       return count;
    }

    //Given a linked list with potentially a loop, determine whether the linked list from the first node contains a cycle in it. For bonus points, do this with constant space.
    //LinkedList Cycle
    public static boolean hasCycle(Node<Integer> nodes) {
        Node<Integer> fast = nodes;
        Node<Integer> slow = nodes;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }


    //The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
    //
    //Example 1:
    //
    //Input: s = "hello"
    //Output: "holle"
    //
    //Example 2:
    //
    //Input: s = "leetcode"
    //Output: "leotcede"
    public static String reverseVowels(String s) {

        char[] chars = s.toCharArray();
        int left=0;
        int right=s.length()-1;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        while(left<right){
            while(left<right && !vowels.contains(s.charAt(left))){
                left++;
            }
            while(left<right && !vowels.contains(s.charAt(right)) ){
                right--;
            }

            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;

        }
        return new String(chars);
    }


   //You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
   //
   //Return the minimum number of boats to carry every given person.
   //
   //Example 1:
   //
   //Input: people = [1,2], limit = 3
   //Output: 1
   //Explanation: 1 boat (1, 2)
    public static int numberOfBoats(List<Integer> people, int limit) {
        Collections.sort(people);
        int left=0;
        int right = people.size()-1;
        int boat=0;
        while(left<=right){
            if(people.get(left)+people.get(right) <= limit){
                left++;
            }
            boat = boat+1;
            right--;
        }
        return boat;
    }

    //Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    //
    //Notice that the solution set must not contain duplicate triplets.
    //
    //Example 1:
    //
    //Input: nums = [-1,0,1,2,-1,-4]
    //Output: [[-1,-1,2],[-1,0,1]]
    //Explanation:
    //nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
    //nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
    //nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
    //The distinct triplets are [-1,0,1] and [-1,-1,2].
    //Notice that the order of the output and the order of the triplets does not matter.
    public static List<List<Integer>> threeSumZero(List<Integer> nums){
        Collections.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

       for(int i=0;i<nums.size()-2;i++){

           if(i>0 && nums.get(i).equals(nums.get(i-1))){
               continue;
           }

           int left=i+1;
           int right = nums.size()-1;

           while(left<right) {
               int sum = nums.get(i) + nums.get(left) + nums.get(right);
               if (sum == 0) {
                   res.add(Arrays.asList(nums.get(i), nums.get(left), nums.get(right)));
                   left++;
                   right--;

                   if (left < right && nums.get(left).equals(nums.get(left - 1))) {
                       left++;
                   }

                   if (left < right && nums.get(right).equals(nums.get(right + 1))) {
                       right--;
                   }
               } else if (sum < 0) {
                   left++;
               } else {
                   right--;
               }

           }

       }
        return res;
    }

    //Given a string s, return the length of the longest substring that contains at most two distinct characters.
    //
    //Example 1:
    //
    //Input: s = "eceba"
    //Output: 3
    //Explanation: The substring is "ece" which its length is 3.
    public static int longestSubstringWithAtmostTwoDistinctCharacters(String s){
        int left=0;
        HashMap<Character,Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for(int right=0;right<s.length();right++) {
            map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)+1);

            while(map.size()>2){
                map.put(s.charAt(left), map.getOrDefault(s.charAt(left),0)-1);
                if(map.get(s.charAt(left)) == 0){
                    map.remove(s.charAt(left));
                }
                left++;
            }
            max = Math.max(max, right-left+1);
        }
        return max;

    }

    //You are given an integer array heights where heights[i] represents the height of the
    //i
    //t
    //h
    //i
    //th
    //  bar.
    //
    //You may choose any two bars to form a container. Return the maximum amount of water a container can store.
    //
    //Example 1:
    //
    //
    //
    //Input: height = [1,7,2,5,4,7,3,6]
    //
    //Output: 36
    //max container problem
    public static int maxArea(int[] heights) {
        int left=0;
        int right=heights.length-1;
        int max= Integer.MIN_VALUE;
        while(left<right){
            int area = (right-left) * Math.min(heights[left],heights[right]);
            max = Math.max(max, area);
            if(heights[left]<heights[right]){
                left++;
            }else{
                right--;
            }
        }

        return max;
    }




}


