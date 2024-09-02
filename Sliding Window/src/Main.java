import java.util.*;
//Expected Output
//14
//[0, 1, 2]
//4
//2
//3
//4
//6
//4
//YXAZ
//true
//[2, 2, 4, 4, 6]
//baec
public class Main {

    //Sliding Window
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // fixed sliding window
    //private static W slidingWindowFixed(List<T> input, int windowSize) {
    //    W ans = window = input[0..windowSize)
    //    for (int right = windowSize; right < input.length(); ++right) {
    //        int left = right - windowSize
    //        remove input[left] from window
    //        append input[right] to window
    //        ans = optimal(ans, window);
    //    }
    //    return ans
    //}
    // flexible sliding window - longest
    // private static W slidingWindowFlexibleLongest(List<T> input) {
    //    initialize window, ans
    //    int left = 0;
    //    for (int right = 0; right < input.size(); ++right) {
    //        append input.get(right) to window
    //        while (invalid(window)) {         // update left until window is valid again
    //            remove input.get(left) from window
    //            ++left;
    //        }
    //        ans = max(ans, window);           // window is guaranteed to be valid here
    //    }
    //    return ans;
    //}
    // flexible sliding window - shortest
    //private static W slidingWindowFlexibleShortest(List<T> input) {
    //    initialize window, ans
    //    int left = 0;
    //    for (int right = 0; right < input.size(); ++right) {
    //        append input.get(right) to window
    //        while (valid(window)) {
    //            ans = min(ans, window);   // window is guaranteed to be valid here
    //            remove input.get(left) from window
    //            ++left;
    //        }
    //    }
    //    return ans;
    //}
    // if we have to find the window equals to, iterate right array, find invalid if condition, inc left pointer
    public static void main(String[] args) {
        int res1 = subarraySumFixed(Arrays.asList(1, 2, 3, 7, 4, 1), 3);
        System.out.println(res1);
        List<Integer> res2 = findAllAnagrams("abab","ab");
        System.out.println(res2);
        int res3 = subarraySumLongest(Arrays.asList(1, 6, 3, 1, 2, 4, 5),10);
        System.out.println(res3);
        int res4 = longestSubstringWithoutRepeatingCharacters("aaaabaaa");
        System.out.println(res4);
        int res5 = subarraySumShortest(Arrays.asList(1, 6, 3, 1, 2, 4, 5),10);
        System.out.println(res5);
        int res6 = leastConsecutiveCardsToMatch(Arrays.asList(3, 4, 2, 3, 4, 7));
        System.out.println(res6);
        int res7 = maxProfit(new int[]{10,1,5,6,7,1});
        System.out.println(res7);
        int res8 = characterReplacement("XYYX",2);
        System.out.println(res8);
        String res9 = minWindow("OUZODYXAZV", "XYZ");
        System.out.println(res9);
        boolean res10 = checkInclusion("ab","lecabee");
        System.out.println(res10);
        int[] res11 = maxSlidingWindow(new int[]{1,2,1,0,4,2,6},3); //toughest using deque
        System.out.println(Arrays.toString(res11));
        String res12 = getMinimumWindow("cdbaebaecd","abc");
        System.out.println(res12);
    }

    // sliding window problem of fixed size
    //time complexity: O(n)
    //space complexity: O(1)
    public static int subarraySumFixed(List<Integer> nums, int k) {
        // Given an array (list) nums consisted of only non-negative integers, find the largest sum among all subarrays of length k in nums.
        //For example, if the input is nums = [1, 2, 3, 7, 4, 1], k = 3, then the output would be 14 as the largest length 3 subarray sum is given by [3, 7, 4] which sums to 14.
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum = maxSum + nums.get(i);
        }
        int largest = maxSum;

        for (int right = k; right < nums.size(); right++) {
            int left = right - k;
            maxSum = maxSum - nums.get(left);
            maxSum = maxSum + nums.get(right);
            largest = Math.max(largest, maxSum);
        }
        return largest;
    }

    //sliding problem of fixed size k
    //time complexity: O(n)
    //space complexity: O(1)
    public static List<Integer> findAllAnagrams(String original, String check) {
        //Given a string original and a string check, find the starting index of all substrings of original that is an anagram of check. The output must be sorted in ascending order.
        //Input: original = "cbaebabacd", check = "abc"
        //Output: [0, 6]

        int originalLength = original.length();
        int checkLength = check.length();
        List<Integer> res = new ArrayList<>();
        int[] originalCount = new int[26];
        int[] checkCount = new int[26];
        for (int i = 0; i < checkLength; i++) {
            originalCount[original.charAt(i) - 'a']++;
            checkCount[check.charAt(i) - 'a']++;
        }

        if(Arrays.equals(originalCount, checkCount)) {
            res.add(0);
        }

        for(int right = checkLength; right < originalLength; right++) {
            originalCount[original.charAt(right-checkLength) - 'a']--;
            originalCount[original.charAt(right) - 'a']++;
            if(Arrays.equals(originalCount, checkCount)) {
                res.add(right-checkLength+1);
            }
        }

        return res;
    }

    // sliding window of flexible size - longest
    // 1, 2, 3, 7, 4, 1
    //time complexity: O(n)
    //space complexity: O(1)
    public static int subarraySumLongest(List<Integer> nums, int target) {
        // nums = [1, 6, 3, 1, 2, 4, 5] and target = 10, output: 4(length of [3,1,2,4])
        int sum =0;
        int left=0;
        int maxLen = 0;
       for(int right=0; right < nums.size(); right++) {
           sum = sum + nums.get(right);
           while(sum>target){
               sum = sum - nums.get(left);
               left++;
           }
           maxLen = Math.max(maxLen, right-left+1);

       }
        return maxLen;
    }

    // sliding window with flexible length - longest
    // time complexity: O(n)
    //space complexity: O(min(n,m))
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
       // Find the length of the longest substring of a given string without repeating characters.
        //Input: abccabcabcc
        //Output: 3
        int longest=0;
        int left=0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int right=0; right < s.length(); right++) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)+1);
            while(map.get(s.charAt(right))>1){
                map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0)-1);
                left++;
            }
            longest = Math.max(longest, right-left+1);
        }
        return longest;
    }

    // sliding window with flexible length - smallest
    //time complexity: O(n)
    //space complexity: O(1)
    public static int subarraySumShortest(List<Integer> nums, int target) {
    //Recall the same example with input nums = [1, 4, 1, 7, 3, 0, 2, 5] and target = 10, then the smallest window with the sum >= 10 is [7, 3] with length 2. So the output is 2.
        int left = 0;
        int smallest =Integer.MAX_VALUE;
        int sum=0;
        for(int right=0;right<nums.size();right++){
            sum = sum + nums.get(right);
            while(sum >= target){
                smallest = Math.min(smallest, right-left+1);
                sum = sum - nums.get(left);
                left++;
            }

        }
        return smallest;
    }

    // sliding window with flexible length - smallest
    //time complexity: O(n)
    //space complexity: O(1)
    public static int leastConsecutiveCardsToMatch(List<Integer> cards) {
        //Given a list of integers cards, your goal is to match a pair of cards, but you can only pick up cards in a consecutive manner. What's the minimum number of cards that you need to pick up to make a pair? If there are no matching pairs, return -1.
        //
        //For example, given cards = [3, 4, 2, 3, 4, 7], then picking up [3, 4, 2, 3] makes a pair of 3s and picking up [4, 2, 3, 4] matches two 4s. We need 4 consecutive cards to match a pair of 3s and 4 consecutive cards to match 4s, so you need to pick up at least 4 cards to make a match.

        int left=0;
        int len=cards.size()+1;


        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int right=0;right<cards.size();right++){
            map.put(cards.get(right), map.getOrDefault(cards.get(right),0)+1);
            while(map.get(cards.get(right)) == 2){
                len = Math.min(len, right-left+1);
                map.put(cards.get(left), map.get(cards.get(left))-1);
                left++;
            }

        }
        return len == cards.size()+1?-1:len ;
    }

    //Buy and Sell Crypto
    //time complexity: O(n)
    //space complexity: O(1)
    public static int maxProfit(int[] prices) {
        //You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
        //You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.
        //Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit would be 0.
        //Input: prices = [10,1,5,6,7,1]
        //Output: 6
        int left=0;
        int maxProfit = 0;
        for(int right=1;right<prices.length;right++){
            if(prices[left]<prices[right]){
                maxProfit= Math.max(maxProfit, prices[right]-prices[left]);
            }else{
                left = right;
            }
        }

        return maxProfit;
    }

    //Long repeating substring with replacement (equal to condition)
    //time complexity: O(n)
    //space complexity: O(1)
    public static int characterReplacement(String s, int k) {
        //You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k characters of the string and replace them with any other uppercase English character.
        //Input: s = "XYYX", k = 2
        //Output: 4
        int[] arr = new int[26];
        int left=0;
        int max=Integer.MIN_VALUE;
        int res=0;
        for(int right=0;right<s.length();right++){
            arr[s.charAt(right) - 'A']++;
            max = Math.max(max, arr[s.charAt(right)-'A']);
            if(right-left+1-max>k){
                arr[s.charAt(left)-'A']--;
                left++;
            }
            res = Math.max(res, right-left+1);
        }
        return res;
    }

    //time complexity: O(n)
    //space complexity: O(m), where m is length of t
    public static String minWindow(String s, String t) {
        //Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. If such a substring does not exist, return an empty string "".
        //Input: s = "OUZODYXAZV", t = "XYZ"
        //Output: "YXAZ"
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<t.length();i++){
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        }
        int left = 0;
        int minLen = s.length()+1;
        int matched = 0;
        int start=0;
        for(int right=0;right<s.length();right++){
            if(map.containsKey(s.charAt(right))){
                map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)-1);
                if(map.get(s.charAt(right)) == 0){
                    matched++;
                }
            }

            while(matched == map.size()){
                if(minLen > right-left+1){
                    minLen = right-left+1;
                    start = left;
                }
                if(map.containsKey(s.charAt(left))){
                    if(map.get(s.charAt(left)) == 0){
                        matched--;
                    }
                    map.put(s.charAt(left), map.getOrDefault(s.charAt(left),0)+1);
                }
                left++;
            }
        }
        return minLen>s.length()?"":s.substring(start, start+minLen);
    }

    //Permutation String
    //time complexity: O(n)
    //space complexity: O(1)
    public static boolean checkInclusion(String s1, String s2) {
       //You are given two strings s1 and s2
        //Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a substring of s2, then return true.
        int[] s1Counter = new int[26];
        int[] s2Counter = new int[26];
        int left=0;
        for(int i=0;i<s1.length();i++){
            s1Counter[s1.charAt(i)-'a']++;
            s2Counter[s2.charAt(i)-'a']++;
        }

        if(Arrays.equals(s1Counter,s2Counter)){
            return true;
        }

        for(int right=s1.length();right<s2.length();right++){
            s2Counter[s2.charAt(left)-'a']--;
            s2Counter[s2.charAt(right)-'a']++;
            left++;
            if(Arrays.equals(s1Counter,s2Counter)){
                return true;
            }
        }

        return false;
    }

    //time complexity: O(n)
    //space complexity: O(m) where m is the length of check
    public static String getMinimumWindow(String original, String check) {
        // Given two strings, original and check, return the minimum substring of original such that each character in check, including duplicates, are included in this substring. By "minimum", I mean the shortest substring. If two substrings that satisfy the condition have the same length, the one that comes lexicographically first is smaller.
        //Input: original = "cdbaebaecd", check = "abc"
        //
        //Output: baec
        //
        //Explanation: baec is the shortest substring of original that contains all of a, b, and c.
        HashMap<Character,Integer> map = new HashMap<>();
        int matched = 0;
        int minLen = Integer.MAX_VALUE;
        int left=0;
        String result = "";
        for(int i=0;i<check.length();i++){
            map.put(check.charAt(i),map.getOrDefault(check.charAt(i),0)+1);
        }
        for(int right=0;right<original.length();right++){
            if(map.containsKey(original.charAt(right))){
                map.put(original.charAt(right),map.get(original.charAt(right))-1);
                if(map.get(original.charAt(right)) == 0){
                    matched++;
                }
            }

            while(matched == map.size()){
                int windowLen = right-left+1;
                String currWindow = original.substring(left,right+1);
                if(minLen>windowLen || (minLen == windowLen && currWindow.compareTo(result)<0)){
                    minLen=windowLen;
                    result = currWindow;
                }
                if(map.containsKey(original.charAt(left))){
                    if(map.get(original.charAt(left)) == 0){
                        matched--;
                    }
                    map.put(original.charAt(left),map.get(original.charAt(left))+1);
                }
                left++;
            }
        }
        return result;
    }

    //Toughest
    //time complexity: O(n)
    //space complexity: O(k), due to deque
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //You are given an array of integers nums and an integer k. There is a sliding window of size k that starts at the left edge of the array. The window slides one position to the right until it reaches the right edge of the array.
        //
        //Return a list that contains the maximum element in the window at each step.
        //
        //Example 1:
        //
        //Input: nums = [1,2,1,0,4,2,6], k = 3
        //
        //Output: [2,2,4,4,6]
        //
        //Explanation:
        //Window position            Max
        //---------------           -----
        //[1  2  1] 0  4  2  6        2
        // 1 [2  1  0] 4  2  6        2
        // 1  2 [1  0  4] 2  6        4
        // 1  2  1 [0  4  2] 6        4
        // 1  2  1  0 [4  2  6]       6
        Deque<Integer> queue = new ArrayDeque<>();
        int left=0;
        int[] res = new int[nums.length-k+1];

        for(int right=0;right<nums.length;right++){
            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[right]){
                queue.pollLast();
            }

            queue.offer(right);

            if(left > queue.peekFirst()){
                queue.pollFirst();
            }

            if(right+1>=k){
                res[left] = nums[queue.peekFirst()];
                left++;
            }
        }

        return res;
    }




}