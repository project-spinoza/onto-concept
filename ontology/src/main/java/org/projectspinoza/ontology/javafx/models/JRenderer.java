package org.projectspinoza.ontology.javafx.models;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

public class JRenderer implements Renderer.Vertex<JNode, JEdge> {

	@Override
	public void paintVertex(RenderContext<JNode, JEdge> renderContext,Layout<JNode, JEdge> layout, JNode vertex) {
		GraphicsDecorator graphicsContext = renderContext.getGraphicsContext();
        Point2D center = layout.transform(vertex);

        Shape shape = null;
        Color color = null;

        shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
        Random random = new Random();
        int r = random.nextInt(256);  
        int g = random.nextInt(200);
        int b = random.nextInt(250);

        color = new Color(r, g, b);
        graphicsContext.setPaint(color);
        graphicsContext.fill(shape);
	}
}