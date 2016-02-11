package org.projectspinoza.ontology.javafx;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javafx.embed.swing.SwingNode;

import org.apache.commons.collections15.functors.ConstantTransformer;
import org.projectspinoza.ontology.javafx.models.JEdge;
import org.projectspinoza.ontology.javafx.models.JNode;
import org.projectspinoza.ontology.util.Term;

import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class TreeGraph {

	private static List<Term> ontoData;
	public static List<Term> getOntoData() {
		return ontoData;
	}
	public static void setOntoData(List<Term> ontoData) {
		TreeGraph.ontoData = ontoData;
	}

	/**
	 * the graph
	 */
	GraphZoomScrollPane panel;
	Forest<JNode, JEdge> graph;
	
	/**
	 * the visual component and renderer for the graph
	 */
	VisualizationViewer<JNode, JEdge> vv;
	TreeLayout<JNode, JEdge> layout;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TreeGraph() {

		// create a simple graph for ontology data
		graph = new DelegateForest<JNode, JEdge>();
		this.addData();
		layout = new TreeLayout<JNode, JEdge>(graph);
	    
		vv = new VisualizationViewer<JNode, JEdge>(layout, new Dimension(1500, 700));
		vv.setBackground(Color.LIGHT_GRAY);
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<JNode, JEdge>());
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeDrawPaintTransformer(new ConstantTransformer(Color.BLACK));
		vv.getRenderContext().setVertexFillPaintTransformer(new ConstantTransformer(Color.DARK_GRAY));
		
		panel = new GraphZoomScrollPane(vv);

		final DefaultModalGraphMouse<?, ?> graphMouse = new DefaultModalGraphMouse();
		vv.setGraphMouse(graphMouse);
		graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
	}

	/**
	 * Adding data to graph
	 * 
	 * @param data
	 */
	public void addData(){
//		JNode root = new JNode("ontology");
//		graph.addVertex(root);
		for (Term term : ontoData) {
			JNode jchild = new JNode(term.getTerm());
			graph.addVertex(jchild);
//			graph.addEdge(new JEdge(), root, jchild , EdgeType.DIRECTED);
			if (term.getChilds() != null) {
				for (Term child : term.getChilds()) {
					JNode jsubchild = new JNode(child.getTerm());
					graph.addEdge(new JEdge(), jchild, jsubchild, EdgeType.DIRECTED);
					if (child.getChilds() != null) {
						for (Term subchild : child.getChilds()) {
							graph.addEdge(new JEdge(), jsubchild, new JNode(subchild.getTerm()), EdgeType.DIRECTED);
						}
					}
				}
			}
		}
	}

	/**
	 * A driver for this Graph
	 */
	public SwingNode start() {
		final SwingNode swingNode = new SwingNode();
		swingNode.setContent(panel);
		return swingNode;
	}
}
