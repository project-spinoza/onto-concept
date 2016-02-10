package org.projectspinoza.ontology.javafx;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BubbleGraph {
	
	//
//	Map<String, Object> map = new HashMap<String, Object>();
	//
	private static Map<String, Object> data_map;

	public BubbleGraph() {
	}

	public static void setMap(Map<String, Object> map) {
		data_map = map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Group start() throws Exception {
		Group bubble = new Group();
		final NumberAxis xAxis = new NumberAxis(0, 50, 2);
		final NumberAxis yAxis = new NumberAxis(0, 50, 2);
		final BubbleChart<Number, Number> bubbleChart = new BubbleChart<Number, Number>(xAxis, yAxis);
		yAxis.setLabel("Indivisual Frequency");
		xAxis.setLabel("Overall Frequency ");
		bubbleChart.setTitle("Onto-concept");
		

		for (String key : data_map.keySet()) {
			XYChart.Series tag = new XYChart.Series();
			tag.setName(key);
			int[] frequences = (int[]) data_map.get(key);
			tag.getData().add(new XYChart.Data(frequences[0], frequences[1]));
			bubbleChart.getData().add(tag);
		}
		bubbleChart.legendVisibleProperty().set(false);;
//		bubbleChart.setLegendSide(Side.LEFT);
		bubbleChart.setMinSize(1200, 650);
//		bubbleChart.setPrefSize(1000, 700);
		bubble.getChildren().add(bubbleChart);
//		return bubbleChart;
		return bubble;
	}
}
