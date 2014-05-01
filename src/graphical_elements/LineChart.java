package graphical_elements;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class LineChart extends JPanel {
	
	private String name;
	
	public LineChart(ArrayList<Double> amounts, ArrayList<String> names) {
		final CategoryDataset dataset = createDataset(amounts, names);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 400));
		add(chartPanel);
	}
	
	public LineChart (ArrayList<Double> amounts, ArrayList<String> names, String Chartname) {
		name = Chartname;
		final CategoryDataset dataset = createDataset(amounts, names);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 400));
		add(chartPanel);
	}

	private CategoryDataset createDataset(ArrayList<Double> amounts, ArrayList<String> names) { 
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i = 0; amounts.size() > i; i++){
			dataset.setValue(amounts.get(i), "Money",names.get(i));
		}
		return dataset;
	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createLineChart3D(
		name, "", "", dataset,
		PlotOrientation.VERTICAL, false, true, true);
		return chart;
	}
}
