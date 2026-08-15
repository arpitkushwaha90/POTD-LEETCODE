class Solution {
    public int longestSubsequence(int[] nums) {

        int xor = 0;
        boolean nonZero = false;

        for (int num : nums) {

            xor = xor ^ num;

            if (num != 0) {
                nonZero = true;
            }
        }

        if (xor != 0) {
            return nums.length;
        }

        if (nonZero) {
            return nums.length - 1;
        }

        return 0;
    }
}
