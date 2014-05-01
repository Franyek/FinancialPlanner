package graphical_elements;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.PieDataset;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart extends JPanel {

	private static final long serialVersionUID = 1L;
	private String name;

	public PieChart (ArrayList<Double> amounts, ArrayList<String> names, String Chartname) {
		name = Chartname;
		final PieDataset dataset = createDataset(amounts, names);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 400));
		add(chartPanel);
	}

	private PieDataset createDataset(ArrayList<Double> amounts, ArrayList<String> names) { 
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(int i = 0; amounts.size() > i; i++){
			dataset.setValue(names.get(i), amounts.get(i));
		}
		return dataset;
	}

	private JFreeChart createChart(final PieDataset dataset) {
		final JFreeChart chart = ChartFactory.createPieChart(
		name, dataset, false, true, true);
		return chart;
	}
}
