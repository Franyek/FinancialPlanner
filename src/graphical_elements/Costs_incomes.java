package graphical_elements;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class Costs_incomes extends JPanel {

	public Costs_incomes(double[] in_out) {
		final CategoryDataset dataset = createDataset(in_out[0], in_out[1]);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(300, 250));
		add(chartPanel);
	}

	private CategoryDataset createDataset(double income, double costs) { 
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(income, "Money", "Incomes");
		dataset.setValue(costs, "Money", "Costs");
		return dataset;
	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart3D(
		"E havi bevételeink és kiadásaink", "", "", dataset,
		PlotOrientation.VERTICAL, false, true, true);
		return chart;
	}
}
