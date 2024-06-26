package exercise;

public class Solver {
	/**
	 * Check if exist two numbers in an array that add up to K.
	 * @param nums the array
	 * @param K the sum to be found
	 * @return true if exist, false otherwise
	 */
    public boolean isExistTwoSum(int[] nums, int K) {
        for (int i = 0; i < nums.length; i++) {
        	for (int j = 0; j < nums.length; j++) {
        		if (i != j && nums[i] + nums[j] == K) return true;
        	}
        }
        return false;
    }

    public static void main(String[] args) {
    	Solver solver = new Solver();
        System.out.println(solver.isExistTwoSum(new int[]{1, 2, 5}, 7));
        System.out.println(solver.isExistTwoSum(new int[]{1, 2, 5}, 11));
    }
}
