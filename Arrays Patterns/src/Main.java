import java.util.*;

//Expected Output:
//true
//true
//[0, 2]
//[[act, cat], [pots, tops, stop], [hat]]
//[3, 2]
//4#neet4#code4#love3#you
//[neet, code, love, you]
//[48, 24, 12, 8]
//4
//true
public class Main {
    public static void main(String[] args) {
        boolean res1 = hasDuplicate(new int[]{1, 2, 3, 3});
        System.out.println(res1);
        boolean res2 = isAnagram("racecar","carrace");
        System.out.println(res2);
        int[] res3 = twoSum(new int[]{4,5,6},10);
        System.out.println(Arrays.toString(res3));
        List<List<String>> res4 = groupAnagrams(new String[]{"act","pots","tops","cat","stop","hat"});
        System.out.println(res4);
        int[] res5 = topKFrequent(new int[]{1,2,2,3,3,3},2);
        System.out.println(Arrays.toString(res5));
        String res6 = encode(Arrays.asList("neet","code","love","you"));
        System.out.println(res6);
        List<String> res7 = decode(res6);
        System.out.println(res7);
        int[] res8 = productExceptSelf(new int[]{1,2,4,6});
        System.out.println(Arrays.toString(res8));
        int res9 = longestConsecutive(new int[]{2,20,4,10,3,4,5});
        System.out.println(res9);
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValid = isValidSudoku(board);
        System.out.println(isValid);

    }

    //Time complexity for hashset or hashmaps for one operation takes O(1)
    //time complexity: O(n)
    //space complexity: O(n)
    public static boolean hasDuplicate(int[] nums) {

        //Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
        //
        //Example 1:
        //
        //Input: nums = [1, 2, 3, 3]
        //
        //Output: true
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    //time complexity: O(m+n) => m for string s and n for string t
    //space complexity: O(1)
    public static boolean isAnagram(String s, String t) {
        //Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
        //
        //An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
        //
        //Example 1:
        //
        //Input: s = "racecar", t = "carrace"
        //
        //Output: true
        int[] sCounter = new int[26];
        int[] tCounter = new int[26];
        for(int i=0;i<s.length();i++){
            sCounter[s.charAt(i)-'a']++;
        }

        for(int j=0;j<t.length();j++){
            tCounter[t.charAt(j)-'a']++;
        }

        if(Arrays.equals(sCounter,tCounter)){
            return true;
        }else{
            return false;
        }
    }

    //Important Note: if the question is add upto n, then use sum, if it requires individual indices, no need to use sum
    //time complexity: O(n)
    //space complexity: O(n)
    public static int[] twoSum(int[] nums, int target) {
        //Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.
        //
        //You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
        //
        //Return the answer with the smaller index first.
        //
        //Example 1:
        //
        //Input:
        //nums = [3,4,5,6], target = 7
        //
        //Output: [0,1]
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }

            map.put(nums[i],i);
        }

        return null;
    }

    //Anagram Groups
    //time complexity: O(n*m) => m is the length of the string and n is the number of strings
    //space complexity: O(n*m)
    public static List<List<String>> groupAnagrams(String[] strs) {
        //Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
        //
        //An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
        //
        //Example 1:
        //
        //Input: strs = ["act","pots","tops","cat","stop","hat"]
        //
        //Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
        HashMap<String,List<String>> map = new HashMap<>();
        for(String s:strs){
            int[] count = new int[26];
            for(char c:s.toCharArray()){
                count[c-'a']++;
            }

            String key = Arrays.toString(count);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    // we can solve this using priority queue, but for arrays basic we used this method
    //time complexity: O(n+mlogm) => m is number of unique elements and n is no of elements in input array
    // space complexity: O(m+k) => m is no of unique elements and k is no of top elements
    public static int[] topKFrequent(int[] nums, int k) {
        //Top K Elements in List
        //Solved
        //Given an integer array nums and an integer k, return the k most frequent elements within the array.
        //
        //The test cases are generated such that the answer is always unique.
        //
        //You may return the output in any order.
        //
        //Example 1:
        //
        //Input: nums = [1,2,2,3,3,3], k = 2
        //
        //Output: [2,3]
        int[] res = new int[k];
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            freqMap.put(nums[i],freqMap.getOrDefault(nums[i],0)+1);
        }

        List<int[]> freqList = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:freqMap.entrySet()){
            freqList.add(new int[]{entry.getKey(),entry.getValue()});
        }

        freqList.sort((a,b) -> Integer.compare(b[1],a[1]));

        for(int j=0;j<k;j++){
            res[j] = freqList.get(j)[0];
        }

        return res;

    }

    //String Encode and Decode
    //Solved
    //Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
    //
    //Please implement encode and decode
    //
    //Example 1:
    //
    //Input: ["neet","code","love","you"]
    //
    //Output:["neet","code","love","you"]
    //time complexity: O(n+m) => n strings and tot length of n strings is m
    //space complexity: O(m)
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s:strs){
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    //time complexity: O(m) -> m is the length of the string
    // space complexity: O(m)
    public static List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i=0;
        while(i<str.length()){
            int j=i;
            while(str.charAt(j)!='#'){
                j++;
            }
            int len = Integer.parseInt(str.substring(i,j));
            i=j+1;
            res.add(str.substring(i,i+len));
            i=i+len;
        }

        return res;
    }

    //timeComplexity: O(n)
    //spaceComplexity: O(1)
    public static int[] productExceptSelf(int[] nums) {
        //Products of Array Discluding Self
        //Solved
        //Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
        //
        //Each product is guaranteed to fit in a 32-bit integer.
        //
        //Follow-up: Could you solve it in
        //O(n)
        //O(n) time without using the division operation?
        //
        //Example 1:
        //
        //Input: nums = [1,2,4,6]
        //
        //Output: [48,24,12,8]
        int left=1;
        int right=1;
        int[] res = new int[nums.length];

        for(int i=0;i<nums.length;i++){
            res[i] = left;
            left = left * nums[i];
        }

        for(int j=nums.length-1;j>=0;j--){
            res[j] = res[j] * right;
            right = right * nums[j];
        }

        return res;


    }


    //time complexity: O(n)
    //space complexity: O(m) where m is number of unique elements
    public static int longestConsecutive(int[] nums) {

        //Longest Consecutive Sequence
        //Given an array of integers nums, return the length of the longest consecutive sequence of elements.
        //
        //A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element.
        //
        //You must write an algorithm that runs in O(n) time.
        //
        //Example 1:
        //
        //Input: nums = [2,20,4,10,3,4,5]
        //
        //Output: 4
        HashSet<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for(int num:nums){
            set.add(num);
        }

        for(int num:set){
            if(!set.contains(num-1)){
                int len = 1;
                while(set.contains(num+len)){
                    len = len+1;
                }

                max = Math.max(max,len);
            }
        }

        return max;
    }

    //Valid Sudoku
    //Solved
    //You are given a a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:
    //
    //Each row must contain the digits 1-9 without duplicates.
    //Each column must contain the digits 1-9 without duplicates.
    //Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
    //Return true if the Sudoku board is valid, otherwise return false
    //
    //Note: A board does not need to be full or be solvable to be valid.
    //
    //Example 1:
    //
    //
    //
    //Input: board =
    //[["1","2",".",".","3",".",".",".","."],
    // ["4",".",".","5",".",".",".",".","."],
    // [".","9","8",".",".",".",".",".","3"],
    // ["5",".",".",".","6",".",".",".","4"],
    // [".",".",".","8",".","3",".",".","5"],
    // ["7",".",".",".","2",".",".",".","6"],
    // [".",".",".",".",".",".","2",".","."],
    // [".",".",".","4","1","9",".",".","8"],
    // [".",".",".",".","8",".",".","7","9"]]
    //
    //Output: true
    //Time Complexity:
    //O(1)
    //
    //The board is always 9x9, so iterating through it and checking constraints is constant time with respect to the input size, making the complexity effectively
    //O(1).
    //Space Complexity:
    //𝑂
    //(
    //1
    //)
    //O(1)
    //
    //We use a fixed number of HashSet objects (27 sets for 9 rows, 9 columns, and 9 sub-boxes), regardless of the input size, leading to constant space usage.
    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for(int i=0;i<9;i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for(int r=0;r<9;r++){
            for(int c=0;c<9;c++){
                char curr = board[r][c];

                if(curr!='.'){

                    if(rows[r].contains(curr)){
                        return false;
                    }

                    rows[r].add(curr);

                    if(cols[c].contains(curr)){
                        return false;
                    }

                    cols[c].add(curr);

                    int boxIndex = (r/3)*3 + c/3;
                    if(boxes[boxIndex].contains(curr)){
                        return false;
                    }
                    boxes[boxIndex].add(curr);
                }
            }
        }

        return true;
    }
}