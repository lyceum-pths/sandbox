import java.util.*;

public class AnnealingQueens {
	public static int[] inevitable(int n) throws IllegalArgumentException {
		if ((n-2)*(n-3) == 0) {
			throw new IllegalArgumentException("No solutions");
			
		}
		int[] res = new int[n];
		if(n%2!=0) {
			int[] pres = inevitable(n-1);
			for(int i = 0; i < n-1; i++) {
				res[i]=pres[i];
			}
			res[n-1]=n;
		}
		else if(n%6==2) {
			//(i, 1 + ((2i + n/2 - 3 )(mod n))) and (n + 1 - i, n - ((2i + n/2 - 3 )(mod n))) 
				for(int i = 0; i < n/2; i++) {
					int j = (2*i+(n/2)-1)%n;
					res[i]=j+1;
					res[n-1-i] = n-j;
				}
			}
		else {
			for(int i = 0; i < n/2; i++) {
				res[i]=2*i+2;
				res[n/2+i]=2*i+1;
			}
		}
		return res;
		
	}
	public static int halfbeat(int[] nums) {
		int result = 0;
		for(int i = 0; i < nums.length; i++) {
			for(int j = i+1; j < nums.length; j++) {
				if(nums[i] == nums[j]) {
					result++;
				}
				if(nums[i]+i == nums[j]+j) {
					result++;
				}
				if(nums[i]-i == nums[j]-j) {
					result++;
				}
			}
		}
		return result;
	}
	public static int beating(int[] nums, int i) {
		int result = -1;
			for(int j = 0; j < nums.length; j++) {
				
				if(nums[i] == nums[j]) {
					result++;
				}
				if(nums[i]+i == nums[j]+j) {
					result++;
				}
				if(nums[i]-i == nums[j]-j) {
					result++;
				}
			}
		
		return result;
	}
	public static  void swap(int[] nums, int i, int j) {
		int swapper = nums[i];
		nums[i] = nums[j];
		nums[j] = swapper;
		
	}
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.println("Print the number of queens");
		int n = in.nextInt();
		in.close();
		if((n<1)||(n == 2)||(n == 3)) {
			System.out.println(-1);
		}
		else if(n == 1){
			System.out.println(1);
		}
		else {
				int[] nums = new int[n];
				for(int i = 0; i < n; i++) {
					nums[i]=i+1;
				}
				int beats = 1;
				int noa=0;
				while((beats!=0)) {
				double temp = beats;
				noa++;
				while(temp > 0.001) {
					Random ran = new Random();
					for(int i = 0; i < n; i++) {
						for(int j = 0; j < n; j++) {
							if(i == j) {
								continue;
							}
							int sum = beating(nums, i)+beating(nums, j);
							swap(nums, i, j);
							
							double prob = Math.pow(Math.E, (sum-beating(nums, i)-beating(nums, j))/temp);
							//p = e^(-E/T)
									
									
									if(ran.nextDouble()>prob) {
										 swap(nums, i, j);
										 //it will cancel!
									}
						}
					}
					
					
						
					 
					temp = temp/2;
				}
				beats = halfbeat(nums);
				
			
			}
			for(int i = 0; i < n; i++) {
				for(int j = 1; j <= n; j++) {
					if(j!=nums[i]) {
						System.out.print("*");
					}
					else {
						System.out.print("Q");
					}
					
				}
				System.out.println("");
			}
			System.out.println(noa+ " attempts");
		}
	}
	public static int[] stat(int n, int tests) throws Exception {
		
		if((n<1)||(n == 2)||(n == 3)) {
			return null;
		}
		else if(n == 1){
			return new int[tests];
		}
		else {
				int[] res = new int[tests];
				for(int k = 0; k < tests; k++) {
					int[] nums = new int[n];
					for(int i = 0; i < n; i++) {
						nums[i]=i+1;
					}
					int beats = 1;
					int noa=0;
					while((beats!=0)) {
						double temp = beats;
						noa++;
						while(temp > 0.001) {
							Random ran = new Random();
							for(int i = 0; i < n; i++) {
								for(int j = 0; j < n; j++) {
									if(i == j) {
										continue;
									}
									int sum = beating(nums, i)+beating(nums, j);
									swap(nums, i, j);
									
									double prob = Math.pow(Math.E, (sum-beating(nums, i)-beating(nums, j))/temp);
									//p = e^(-E/T)
											
											
											if(ran.nextDouble()>prob) {
												 swap(nums, i, j);
												 //it will cancel!
											}
								}
							}
							
							
								
							 
							temp = temp/2;
						}
						beats = halfbeat(nums);
						
					
					}
				
				res[k]=noa;
			}
			return res;	
		}
			
	}

}
