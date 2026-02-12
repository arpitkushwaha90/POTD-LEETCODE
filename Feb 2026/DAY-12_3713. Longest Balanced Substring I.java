
class Solution {
    private boolean isBalanced(int[] arr){
        int count = 0;
        for(int i : arr){
            if(i == 0) continue;
            if(count == 0) count = i;
            else if(count != i) return false;
        }
        return true;
    }
    public int longestBalanced(String s) {
        int n = s.length();
        int max = 0;

        for(int i = 0; i < n; i++){
            int[] freq = new int[26];
            for(int j = i; j < n; j++){
                freq[s.charAt(j)-'a']++;
                if(isBalanced(freq)) max = Math.max(max, j-i+1);
            }
        }
        return max;
    }
}
