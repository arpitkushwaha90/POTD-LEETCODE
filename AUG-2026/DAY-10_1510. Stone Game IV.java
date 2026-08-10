class Solution {
    public boolean winnerSquareGame(int n) {
        // Intuition: plain recursive minimax, Alice maximizes, Bob minimizes, no caching
        return game(n, true);
    }

    private boolean game(int n, boolean isAlice) {
        if (n == 0) return !isAlice;
        for (int i = 1; i * i <= n; i++) {
            if (isAlice) {
                if (game(n - i * i, false)) return true;
            } else {
                if (!game(n - i * i, true)) return false;
            }
        }
        return !isAlice;
    }
}
