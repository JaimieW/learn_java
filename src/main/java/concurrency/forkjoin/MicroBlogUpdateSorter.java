package concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MicroBlogUpdateSorter extends RecursiveAction {

	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = -7407022137083291197L;
	
	
	private static final int SMALL_ENOUGH = 32;
	private final Update[] updates;
	private final int start, end;
	private final Update[] result;
	
	public MicroBlogUpdateSorter(Update[] updates) {
		this(updates, 0, updates.length);
	}
	public MicroBlogUpdateSorter(Update[] updates, int startPos, int endPos){
		this.start = startPos;
		this.end = endPos;
		this.updates = updates;
		this.result = new Update[updates.length];
	}
	
	private void merge(MicroBlogUpdateSorter left, MicroBlogUpdateSorter right){
		int i = 0;
		int lCt = 0;
		int rCt = 0;
		while(lCt < left.size() && rCt < right.size()){
			result[i++] = (left.result[lCt].compareTo(right.result[rCt]) < 0)? left.result[lCt++] : right.result[rCt++];
		}
		while(lCt < left.size()){result[i++] = left.result[lCt++];}
		while(rCt < right.size()){result[i++] = right.result[rCt++];}
	}
	
	public int size(){
		return end-start;
	}
	
	public Update[] getResult(){ return result;}

	@Override
	protected void compute() {
		if(size() < SMALL_ENOUGH){
			System.arraycopy(updates, start, result, 0, size());
			Arrays.sort(result, 0, size());
		} else {
			int mid = size() / 2;
			MicroBlogUpdateSorter left = new MicroBlogUpdateSorter(updates, start, start+mid);
			MicroBlogUpdateSorter right = new MicroBlogUpdateSorter(updates, start+mid, end);
			invokeAll(left, right);
			merge(left, right);
		}
	}

}