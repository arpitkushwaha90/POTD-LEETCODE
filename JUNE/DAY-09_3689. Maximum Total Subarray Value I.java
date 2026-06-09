class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            max = Math.max(max,nums[i]);
            min = Math.min(min,nums[i]);
        }
        return 1L*(max-min)*k;
    }
}
