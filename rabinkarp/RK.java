package rabinkarp;

public class RK {
	
	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//
	char[] arr;
	int buffSize;
	int hash;
	int cm;
	int numChars;

	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		//System.out.println("new rk made..");
		buffSize=m;
		//System.out.println("m="+m);
		arr=new char[m];
		for(char a:arr){
			arr[a]=0;
		}
		hash=0;
		numChars=0;
	cm=1;
	for(int i=0; i<m; i++){
		cm=(cm*31%511);
	}
	
	}
	

	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {
		int index=numChars%buffSize;
		char rN=arr[index];
		
		
	//	System.out.println("current index is:"+index+" numChars is:"+numChars);
		arr[index]=d;
		//System.out.println("replaced char is:"+rN+" d="+d);
		numChars++;
		hash=(int)((hash*31-cm*rN+d)%511);
		if(hash<0){
			hash=hash+511;
		}
		//System.out.println("hash is "+hash);
		
		
		return hash;
		
	}
	/*
public static void main(String[] args){
	RK test=new RK(4);
	test.nextCh('a');
	test.nextCh('a');
	test.nextCh('b');
	test.nextCh('a');
	test.nextCh('a');
	test.nextCh('c');
	
	
}*/
}
