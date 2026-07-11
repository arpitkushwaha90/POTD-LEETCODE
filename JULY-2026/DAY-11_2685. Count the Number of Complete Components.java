class Solution {
    private int[] par;
    private int[] sz;
    private int[] cnt_edge;

    public int countCompleteComponents(int n, int[][] edges) {
        if (n == 1) return 1;

        cnt_edge = new int[n];
        sz = new int[n];
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            sz[i] = 1;
        }

        for (int[] e : edges) {
            merge(e[0], e[1]);
        }

        int ans = 0;
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            int p = find(i);
            if (vis[p]) continue;
            vis[p] = true;

            if (cnt_edge[p] == (sz[p] * (sz[p] - 1)) / 2) {
                ans++;
            }
        }
        return ans;
    }

    private int find(int node) {
        if (par[node] == node) return node;
        return par[node] = find(par[node]);
    }

    private void merge(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) {
            cnt_edge[rootU]++;
            return;
        }

        if (sz[rootU] > sz[rootV]) {
            int temp = rootU;
            rootU = rootV;
            rootV = temp;
        }

        par[rootU] = rootV;
        sz[rootV] += sz[rootU];
        cnt_edge[rootV] += cnt_edge[rootU] + 1;
    }
}
