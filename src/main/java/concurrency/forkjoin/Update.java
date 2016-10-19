package concurrency.forkjoin;

import java.text.SimpleDateFormat;
import java.util.Date;

class Update implements Comparable<Update>{

	private final Author author;
	private final String updateText;
	private final long createTime;
	
	private Update(Builder b){
		author = b.author;
		updateText = b.updateText;
		createTime = b.createTime;
	}
	public Author getAuthor(){return author;}
	public String getUpdateText(){return updateText;}
	
	
	@Override public String toString(){
		return author.getName() + ": created: "+ SimpleDateFormat.getInstance().format(new Date(createTime)) + ": "+updateText;
	}
	
	@Override
	public int compareTo(Update o) {
		return this.updateText.compareTo(o.updateText);
	}

	static class Builder{
		private Author author;
		private String updateText;
		private long createTime;
		
		public Builder author(Author author){
			this.author = author;
			return this;
		}
		
		public Builder updateText(String updateText){
			this.updateText = updateText;
			return this;
		}
		
		public Builder createTime(long time){
			this.createTime = time;
			return this;
		}
		
		public Update build(){
			return new Update(this);
		}
	}
	
}