package com.uc.test3;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.BubbleChart;
import org.achartengine.chart.CombinedXYChart.XYCombinedChartDef;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Test2 extends Activity
{
	private LinearLayout chartLayout;
	private GraphicalView mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showCoBarGraph();
	}

	private void showCoBarGraph()
	{
		chartLayout = (LinearLayout) findViewById(R.id.graphHolder);

		final double[] personCount = { 11, 21, 31, 41, 51 };
		final Date[] timeValue = { new Date(12356), new Date(12356), new Date(12356), new Date(12356),
				new Date(12356) };
		XYValueSeries sunSeries = new XYValueSeries("Sunshine hours");
		sunSeries.add(1f, 35, 4.3);
		sunSeries.add(2f, 35, 4.9);
		sunSeries.add(3f, 35, 5.9);
		sunSeries.add(4f, 35, 8.8);
		sunSeries.add(5f, 35, 10.8);
		sunSeries.add(6f, 35, 11.9);
		sunSeries.add(7f, 35, 13.6);
		sunSeries.add(8f, 35, 12.8);
		sunSeries.add(9f, 35, 11.4);
		sunSeries.add(10f, 35, 9.5);
		sunSeries.add(11f, 35, 7.5);
		sunSeries.add(12f, 35, 5.5);
		// XYValueSeries sunSeries = new XYValueSeries("Sunshine hours");
		/*
		 * Create XYSeries and fill it with 24 int Values for X-Axis and PersonCount
		 * for Y-Axis
		 */
		XYValueSeries series = new XYValueSeries("Test");
		for(int i = 0; i < timeValue.length; i++)
		{
			series.add(i, personCount[i]);
			// sunSeries.add(i, 20*i);
		}

		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(sunSeries);

		// Now I create the Series renderer
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.RED);
		XYSeriesRenderer lightRenderer = new XYSeriesRenderer();
		lightRenderer.setColor(Color.YELLOW);

		// last step, Creating the renderer that controls the full charts and add
		// the single renderer for each series
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(lightRenderer);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		lightRenderer.setPointStyle(PointStyle.SQUARE);
		lightRenderer.setFillPoints(true);

		// Use DateFormatter for having X Labels in the form of HH:mm
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		// for (int i = 0; i < timeValue.length; i++) {
		// // add custom Labels with correct format
		// if(i%2==0){
		// mRenderer.addXTextLabel(i, formatter.format(timeValue[i]));
		// }
		// }

		mRenderer.setYLabels(10);
		mRenderer.setShowGrid(true);
		mRenderer.setXLabelsAlign(Align.CENTER);
		mRenderer.setYLabelsAlign(Align.RIGHT);
		// disable the default labels
		// mRenderer.setXLabels(0);
		mRenderer.setShowCustomTextGrid(true);
		mRenderer.setZoomButtonsVisible(true);
		// mRenderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		// mRenderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setChartTitle("Persons");

		XYCombinedChartDef[] types = { new XYCombinedChartDef(BarChart.TYPE, 0),
				new XYCombinedChartDef(BubbleChart.TYPE, 1) };

		mChart = (GraphicalView) ChartFactory.getCombinedXYChartView(this, dataset, mRenderer, types);

		chartLayout.addView(mChart);
	}
}
