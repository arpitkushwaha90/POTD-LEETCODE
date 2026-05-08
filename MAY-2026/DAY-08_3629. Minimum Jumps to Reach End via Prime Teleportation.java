
class Solution{
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

        // 1. Precompute Primes and Factorization using Sieve
        int[] minPrime = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (minPrime[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (minPrime[j] == 0) minPrime[j] = i;
                }
            }
        }

        // 2. Map each prime to indices where nums[j] is divisible by that prime
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            while (temp > 1) {
                int p = minPrime[temp];
                primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                while (temp % p == 0) temp /= p;
            }
        }

        // 3. BFS for shortest path
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        
        Set<Integer> visitedPrimes = new HashSet<>();

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == n - 1) return dist[curr];

            // Option 1: Adjacent Steps
            int[] neighbors = {curr - 1, curr + 1};
            for (int next : neighbors) {
                if (next >= 0 && next < n && dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }

            // Option 2: Prime Teleportation
            // Check if nums[curr] is prime
            if (minPrime[nums[curr]] == nums[curr] && nums[curr] > 1) {
                int p = nums[curr];
                if (!visitedPrimes.contains(p)) {
                    visitedPrimes.add(p);
                    for (int nextIdx : primeToIndices.getOrDefault(p, new ArrayList<>())) {
                        if (dist[nextIdx] == -1) {
                            dist[nextIdx] = dist[curr] + 1;
                            queue.offer(nextIdx);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
