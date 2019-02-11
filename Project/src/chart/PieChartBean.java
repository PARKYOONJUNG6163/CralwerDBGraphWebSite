package chart;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.*;
import org.jfree.data.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.plot.*;
import java.awt.*;

public class PieChartBean {

	public static void main(String arg[]){
		PieChartBean p = new PieChartBean();
		JFreeChart chart = p.getPieChart();
		ChartFrame frame1=new ChartFrame("Pie Chart",chart);
		frame1.setSize(800,450);  
		frame1.setVisible(true);
	}

	public JFreeChart getPieChart() {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	
        for(int i =0; i<10; i++){
            int n = (int) (Math.random() * 10) + 1;
            dataset.setValue(""+(i+1), n);
        }
		JFreeChart chart = ChartFactory.createPieChart3D("", dataset, true,true, false);
		chart.setBackgroundPaint(Color.white);
		
		return chart;
	}

}