class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int cnt[] = new int[n + 1];

        for (int i = 0; i < n; i++) {
            cnt[Math.min(arr[i], n)]++;
        }

        int val = 0;
        for (int i = 1; i <= n; i++) {
            val = Math.min(i, val + cnt[i]);
        }

        return val;
    }
}
