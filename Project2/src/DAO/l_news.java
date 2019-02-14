package DAO;

public class l_news implements Comparable<l_news>{
	String title;
	String url;
	String date;
	int like;
	int bad;
	int sum;
	
	public l_news(String title,String url,String date,int like,int bad,int sum) {
		this.title = title;
		this.url = url;
		this.date = date;
		this.like = like;
		this.bad = bad;
		this.sum = sum;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getURL() {
		return this.url;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getLike() {
		return this.like;
	}
	
	public int getBad() {
		return this.bad;
	}
	
	public int getSum() {
		return this.sum;
	}
	
	@Override
	public int compareTo(l_news l) {
		// TODO Auto-generated method stub
		if(this.sum < l.sum) {
			return 1;
		}else if(this.sum > l.sum) {
			return -1;
		}
		return 0;
	}
}
