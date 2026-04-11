class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDist = Integer.MAX_VALUE;
        boolean found = false;

        for (List<Integer> indices : map.values()) {
            if (indices.size() < 3) continue;

            for (int i = 0; i <= indices.size() - 3; i++) {
                int d = 2 * (indices.get(i + 2) - indices.get(i));
                minDist = Math.min(minDist, d);
                found = true;
            }
        }

        return found ? minDist : -1;
    }
}
