package core;

public class LFSR
{
	private int N; //number of bits in the LFSR
	private long reg;
	private int tap; //index of tap bit
	
	//constructor to create LFSR with seed and tap
	public LFSR(String seed, int tap) {
		this.tap = tap;
		this.reg = 0;
		this.N = seed.length();
		
		//this.temporary = new int[N];
		
		for(int i = 0; i < seed.length(); i++) {
			
			if(seed.charAt(i) == '0'){
				reg = reg << 1L;
			} else {
				reg = reg << 1L;
				reg |= 1L;
			}
			
		}

	}
	
	//simulates the output of 1 step.
	public int step() {
		
		long nextNum = (reg >> ((long)N - 1L) & 1L) ^ (reg >> ((long) tap) & 1L);
		
		reg &= ~(1L << (long)N - 1L); //clear last bit
		reg <<= 1L; //shift
		reg |= nextNum; //set first bit
		
		//int next =
		
		return (int) nextNum;
	}
	
	//simulates k step
	public int generate(int k) {
		int num = 0;
		for(int i = 0; i < k; i++)
		{
			int value = step();
			num = (num * 2) + value;
		}
		return num;
	}
	
	@Override
	//returns string representation of LFSR
	public String toString() {
		String s = Long.toBinaryString(reg);
		String leadingZeroes = "";
		
		for(int i = 0; i < N - s.length(); i++)
			leadingZeroes += "0";
		return leadingZeroes + s;
	}
	
	
}
