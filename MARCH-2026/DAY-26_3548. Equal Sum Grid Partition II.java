
class Solution {
    private void fillMap(HashMap<Long, Integer> mp, int[][] grid) {
        for(int[] row : grid) {
            for(int ele : row) mp.put((long)ele, mp.getOrDefault((long)ele, 0)+1);
        }
    }

    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[][] sum = new long[n][m];

        HashMap<Long, Integer> mp1 = new HashMap<>();
        HashMap<Long, Integer> mp2 = new HashMap<>();

        for(int i = 0; i < n; i++) {
            sum[i][0] = grid[i][0];

            for(int j = 1; j < m; j++) {
                sum[i][j] = sum[i][j-1] + (long)grid[i][j];
            }
        }

        for(int j = 0; j < m; j++) {
            for(int i = 1; i < n; i++) {
                sum[i][j] += sum[i-1][j];
            }
        }

        // for(long[] row : sum) System.out.println(Arrays.toString(row));

        fillMap(mp2, grid);

        for(int i = 0; i < n-1; i++) {
            long part1 = sum[i][m-1];
            long part2 = sum[n-1][m-1] - part1;

            if (part1 == part2) return true;
            // System.out.println(part1 + " " + part2);

            for(int j = 0; j < m; j++) {
                long ele = grid[i][j];

                mp1.put(ele, mp1.getOrDefault(ele, 0)+1);
                mp2.put(ele, mp2.get(ele)-1);

                if(mp2.get(ele) == 0) mp2.remove(ele);
            }

            if (part1 > part2) {
                if (i == 0) {
                    if(part1 - (long)grid[0][0] == part2
                            || part1 - (long)grid[0][m-1] == part2) return true;
                }

                if (m == 1) {
                    if(part1 - (long)grid[0][0] == part2
                            || part1 - (long)grid[i][0] == part2) return true;
                }

                if(i != 0 && m != 1) {
                    long diff = part1 - part2;
                    if(mp1.containsKey(diff)) return true;
                }
            }
            else {
                if (i == n-2) {
                    if (part2 - (long)grid[n-1][0] == part1
                            || part2 - (long)grid[n-1][m-1] == part1) return true;
                }

                if(m == 1) {
                    if(part2 - (long)grid[n-1][0] == part1
                            || part2 - (long)grid[i+1][0] == part1) return true;
                }

                if(i != n-2 && m != 1) {
                    long diff = part2 - part1;
                    if(mp2.containsKey(diff)) return true;
                }
            }
        }

        fillMap(mp2, grid);
        mp1.clear();

        for(int j = 0; j < m-1; j++) {
            long part1 = sum[n-1][j];
            long part2 = sum[n-1][m-1] - part1;

            if(part1 == part2) return true;

            for(int i = 0; i < n; i++) {
                long ele = grid[i][j];

                mp1.put(ele, mp1.getOrDefault(ele, 0)+1);
                mp2.put(ele, mp2.get(ele)-1);

                if(mp2.get(ele) == 0) mp2.remove(ele);
            }

            if (part1 > part2) {
                if(j == 0) {
                    if(part1 - (long)grid[0][0] == part2
                            || part1 - (long)grid[n-1][0] == part2) return true;
                }
                if(n == 1) {
                    if(part1 - (long)grid[0][0] == part2
                            || part1 - (long)grid[0][j] == part2) return true;
                }
                if(j != 0 && n != 1) {
                    long diff = part1 - part2;
                    if(mp1.containsKey(diff)) return true;
                }
            }
            else {
                if(j == m-2) {
                    if(part2 - (long)grid[0][m-1] == part1
                            || part2 - (long)grid[n-1][m-1] == part1) return true;
                }
                if(n == 1) {
                    if(part2 - (long)grid[0][m-1] == part1
                            || part2 - (long)grid[0][j+1] == part1) return true;
                }
                if(j != 0 && n != 1) {
                    long diff = part2 - part1;
                    if(mp2.containsKey(diff)) return true;
                }
            }
        }
        return false;
    }
}
