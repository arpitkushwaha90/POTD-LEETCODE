class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int ans = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()) {
            int m = list.size();
            if (m < 3) continue;
            for (int i = 0; i < m - 2; i++) {
                int j = i + 1, k = i + 2;
                int a = list.get(i), b = list.get(j), c = list.get(k);
                int dist = Math.abs(a - b) + Math.abs(b - c) + Math.abs(c - a);
                ans = Math.min(ans, dist);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
