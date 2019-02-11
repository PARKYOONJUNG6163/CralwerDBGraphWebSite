package rank;

public class L_News implements Comparable<L_News>{
	String title;
	String url;
	int like;
	int bad;
	int sum;
	
	public L_News(String title,String url,int like,int bad,int sum) {
		this.title = title;
		this.url = url;
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
	public int compareTo(L_News l) {
		// TODO Auto-generated method stub
		if(this.sum < l.sum) {
			return 1;
		}else if(this.sum > l.sum) {
			return -1;
		}
		return 0;
	}
}
