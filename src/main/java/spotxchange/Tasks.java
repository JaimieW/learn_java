package spotxchange;
import java.util.Arrays;
public class Tasks {

	public int solution(int[] A) {
		int node = 0;
		int size = 1;
		while(A[node] != -1){
			size++;
			node = A[node];
		}
		return size;
    }
	
	int solution(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 1;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
                int tmp = count[A[i]];
                if (tmp >= maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp + 1;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }
	
	
	public String solution(String S, String T) {
        // write your code in Java SE 8
		if(S.equals(T)) return "NOTHING";
		if(Math.abs(S.length()-T.length()) > 1) return "IMPOSSIBLE";
			
		if(S.length() > T.length()){
			return checkForDelete(S,T);
		} else if (S.length()  < T.length()){
			return checkForInsert(S, T);
		} else {
			return checkForSwap(S, T);
		}
		
	}
	
	private String checkForDelete(String S, String T){
		StringBuilder s = new StringBuilder(S);
		for(int i=0; i<S.length();i++){
			char c = S.charAt(i);
			if(s.deleteCharAt(i).toString().equals(T)) return "DELETE "+c;
			s.insert(i, c);
		}
		return "IMPOSSIBLE";
	}
	
	private String checkForInsert(String S, String T){
		StringBuilder s = new StringBuilder(S);
		for(int i=0; i<T.length();i++){
			char c = T.charAt(i);
			if(s.insert(i, c).toString().equals(T)) return "INSERT "+c;
			s.deleteCharAt(i);
		}
		return "IMPOSSIBLE";
	}
	
	private String checkForSwap(String S, String T){
		char[] sChars = S.toCharArray();
		char[] tChars = T.toCharArray();
		for(int i=0 ; i<sChars.length -1; i++){
			sChars = swap(sChars, i, i+1);
			if(Arrays.equals(sChars, tChars)) return "SWAP "+sChars[i+1]+ " "+ sChars[i];
			sChars = swap(sChars, i+1, i);
		}
		return "IMPOSSIBLE";
	}
	
	private char[] swap(char[] chars, int i, int j){
		char tmp = chars[i];
		chars[i] = chars[j];
		chars[j] = tmp;
		return chars;
	}
}
