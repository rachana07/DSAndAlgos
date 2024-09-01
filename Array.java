import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Array {
    public static void main(String[] args){
        int[] arr = {3,2,4,7,10,6,5};
        int[] arr1 = {2,4,5,0,9,0,7,0,6};
        int[] arr3 = {3,1,5,4};
        int[] res1 = removeEvenIntegers(arr);
        int[] res2 = reverseAnArray(arr);
        int res3 = findMinValue(arr);
        int res4 = findSecondMaxValue(arr);
        int[] res5 = moveZeroesToEnd(arr1);
        int res6 = findMissingNumber(arr3);
        boolean res7 = isPalindrome("that");
        boolean res8 = isPalindrome("aba");
        System.out.println("Remove Even Integers"+ Arrays.toString(res1));
        System.out.println("Reverse an Array"+ Arrays.toString(res2));
        System.out.println("Min value of an array " + res3);
        System.out.println("Max value of an array " + res4);
        System.out.println("Move Zeroes to End "+ Arrays.toString(res5));
        System.out.println("Find Missing Number "+ res6);
        System.out.println("Is Palindrome "+ res7);
        System.out.println("Is Palindrome "+ res8);
    }

    private static boolean isPalindrome(String word) {
        char[] chars = word.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while(start < end){
            if(chars[start] != chars[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // sum of first n natural numbers is n(n+1)/2
    private static int findMissingNumber(int[] arr3) {
        int n = arr3.length-1;
        int sum = n*(n+1)/2;
        int res= 0;
        for(int i = 0; i < arr3.length; i++){
            res = sum - arr3[i];
        }

        return res;
    }

    private static int[] moveZeroesToEnd(int[] arr) {
        int[] res = new int[arr.length];
        int a = 0;
        int b = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0){
                res[b] = arr[i];
                b--;
            } else {
                res[a] = arr[i];
                a++;
            }
        }

        return res;
    }

    private static int findSecondMaxValue(int[] arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]> max1){
                max2 = max1;
                max1 = arr[i];
            } else if(arr[i]>max2 && arr[i]!=max1){
                max2 = arr[i];
            }
        }
        return max2;
    }

    private static int findMinValue(int[] arr) {
        int min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    private static int[] reverseAnArray(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int i=arr.length-1;i>=0;i--){
            res.add(arr[i]);
        }
        return res.stream().mapToInt(i->i).toArray();
    }

    private static int[] removeEvenIntegers(int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
           if(arr[i]%2!=0){
               res.add(arr[i]);
           }
        }

        int[] resArr = new int[res.size()];
        for(int i=0;i<res.size();i++){
            resArr[i] = res.get(i);
        }

        return resArr;
    }
}