package rank;

public class R_News implements Comparable<R_News>{
		String title;
		String url;
		int count;
		
		public R_News(String title,String url,int count) {
			this.title = title;
			this.url = url;
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
