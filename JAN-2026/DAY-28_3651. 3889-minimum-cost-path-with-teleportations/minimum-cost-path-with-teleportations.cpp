#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minCost(vector<vector<int>>& grid, int k) {
        int m = (int)grid.size();
        int n = (int)grid[0].size();
        const long long INF = (long long)4e18;

        // Find maximum value in grid to size value-buckets tightly
        int maxV = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                maxV = max(maxV, grid[i][j]);

        // dp_prev: best costs using at most t teleports (initialized for t=0 after we compute it)
        vector<vector<long long>> dp_prev(m, vector<long long>(n, INF));

        // Helper: forward DP on right/down DAG with arbitrary source costs "init"
        auto forward_relax = [&](const vector<vector<long long>>& init) {
            vector<vector<long long>> dp(m, vector<long long>(n, INF));
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    long long best = init[i][j]; // using this cell as a source (no extra cost)
                    if (i > 0 && dp[i-1][j] != INF) {
                        best = min(best, dp[i-1][j] + grid[i][j]);
                    }
                    if (j > 0 && dp[i][j-1] != INF) {
                        best = min(best, dp[i][j-1] + grid[i][j]);
                    }
                    dp[i][j] = best;
                }
            }
            return dp;
        };

        // Base layer t = 0 (no teleports): only source is start (0,0) with cost 0
        vector<vector<long long>> init0(m, vector<long long>(n, INF));
        init0[0][0] = 0; // starting cell has cost 0 (you pay cost on entering a destination cell)
        dp_prev = forward_relax(init0);

        // Build up to k teleports
        for (int t = 1; t <= k; ++t) {
            // best_by_value[v] = min dp_prev over all cells with grid value exactly v
            vector<long long> best_by_value(maxV + 2, INF);
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int v = grid[i][j];
                    best_by_value[v] = min(best_by_value[v], dp_prev[i][j]);
                }
            }

            // suf_min[v] = min best_by_value[w] for all w >= v
            vector<long long> suf_min(maxV + 2, INF);
            for (int v = maxV; v >= 0; --v) {
                suf_min[v] = min(best_by_value[v], suf_min[v + 1]);
            }

            // init for this layer: min of (no teleport) and (one teleport from any >= value)
            vector<vector<long long>> init(m, vector<long long>(n, INF));
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int v = grid[i][j];
                    long long teleport_seed = suf_min[v];      // best cost to reach any cell with value >= v
                    long long keep_prev     = dp_prev[i][j];   // do not teleport now
                    init[i][j] = min(keep_prev, teleport_seed);
                }
            }

            // Forward relax to propagate right/down from all seeds
            auto dp_curr = forward_relax(init);
            dp_prev.swap(dp_curr);
        }

        return (int)dp_prev[m - 1][n - 1];
    }
};
