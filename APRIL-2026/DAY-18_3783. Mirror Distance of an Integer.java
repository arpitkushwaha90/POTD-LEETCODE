class Solution {
    public int mirrorDistance(int n) {

        int rev = Integer.parseInt(
            new StringBuilder(String.valueOf(n))
            .reverse()
            .toString());

        return Math.abs(rev-n);    


        
    }
}
