class Solution {
public:
    int numMagicSquaresInside(vector<vector<int>>& grid) {
        int n = grid.size(), m = grid[0].size(), count = 0;
        // begin traverse from where magic matrix is possible
        for (int i = 2; i < n; i++) {
            for (int j = 2; j < m; j++) {
                int tr = i - 2, tc = j - 2, f = 0;
                // vectors to store sum of each row and col in magic matrix
                vector<int> row(3, 0);
                vector<int> col(3, 0);
                // keep track of distinct elements
                vector<bool> v(10, false);
                for (int p1 = tr; p1 <= i; p1++) {
                    for (int p2 = tc; p2 <= j; p2++) {
                        // check current element is valid or not
                        if (grid[p1][p2] == 0 || grid[p1][p2] > 9 ||
                            v[grid[p1][p2]]) {
                            f = 1;
                            break;
                        }
                        // add number to row and col sum
                        row[i - p1] += grid[p1][p2];
                        col[j - p2] += grid[p1][p2];
                        // mark availability
                        v[grid[p1][p2]] = true;
                    }
                    if (f)
                        break;
                }
                // skip this 3x3 matrix if required condition didn't meet
                if (f)
                    continue;
                // find sum of both diagonals
                int d1 =
                    grid[tr][tc] + grid[tr + 1][tc + 1] + grid[tr + 2][tc + 2];
                int d2 =
                    grid[tr][tc + 2] + grid[tr + 1][tc + 1] + grid[tr + 2][tc];
                // compare all values are same or not
                if (d1 != d2)
                    continue;
                if (row[0] != row[1] || row[1] != row[2])
                    continue;
                if (col[0] != col[1] || col[1] != col[2])
                    continue;
                // count current matrix as magic matrix
                count++;
            }
        }
        return count;
    }
};
