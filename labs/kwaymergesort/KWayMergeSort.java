package kwaymergesort;

import timing.Ticker;

public class KWayMergeSort {
	
	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] mergeSorted(Integer[] a, Integer[] b, Ticker ticker){
		Integer[] arr=new Integer[a.length+b.length];
		int aI=0;
		int bI=0;
		int i=0;
		while(i<arr.length){
			ticker.tick();
			if(aI!=(a.length) && bI!=(b.length)){
			if(a[aI]<b[bI]){
				arr[i]=a[aI];
				i++;
				aI++;
			}
			else{
				arr[i]=b[bI];
				i++;
				
				bI++;
			}
			}
			if(aI==(a.length)){
				for(int j=bI; j<b.length; j++){
				arr[i]=b[j];
				i++;
				}
				break;
			}
			else if(bI==(b.length)){
				for(int j=aI; j<a.length; j++){
					arr[i]=a[j];
					i++;
					}
					break;
				}
		
		}
		return arr;
	}
	
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;
		if(n==1){
			return input;
		}
		Integer[][] subArrays=new Integer[K][n/K];
		int index=0;
		for(int i=0; i<K;i++){

			for(int j=0; j<n/K;j++){
				ticker.tick();
			subArrays[i][j]=input[index];
			++index;
			}
			subArrays[i]=kwaymergesort(K,subArrays[i],ticker);
			
		}
		
		while(K>1){
		Integer[][] temp=new Integer[K/2][n/K*2];
		for(int h=0; h<K;h+=2){
			ticker.tick();
			temp[h/2]=mergeSorted(subArrays[h],subArrays[h+1], ticker);
			
		}
		ticker.tick();
		subArrays=temp;
		K=K/2;
		}
		
		input=subArrays[0];

		Integer[] ans = new Integer[n];
		for (int i=0; i < n; ++i) {
			
			ans[i] = input[i];
			ticker.tick();
		}
		
		return ans;
	}

	
	
public static void main(String[] args){
	//System.out.println("working?");
	Integer[] a=new Integer[16];
	for(int i=0; i<a.length; i++){
		a[i]=(int)(Math.random()*100);
	}
	Ticker t=new Ticker();

	Integer[] sorted=kwaymergesort(2, a,t);
	
	
	for(int j:sorted){
		System.out.println(j);
	}
}
}
