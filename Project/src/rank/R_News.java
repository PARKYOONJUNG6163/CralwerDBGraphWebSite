package rank;

public class R_News implements Comparable<R_News>{
		String title;
		String url;
		String date;
		int count;
		
		public R_News(String title,String url,String date,int count) {
			this.title = title;
			this.url = url;
			this.date = date;
			this.count = count;
		}
		
		public String getTitle() {
			return this.title;
		}
		
		public String getURL() {
			return this.url;
		}
		
		public int getCount() {
			return this.count;
		}
		
		public String getDate() {
			return this.date;
		}
		
		@Override
		public int compareTo(R_News n) {
			// TODO Auto-generated method stub
			if(this.count < n.count) {
				return 1;
			}else if(this.count > n.count) {
				return -1;
			}
			return 0;
		}
}
