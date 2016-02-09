package org.projectspinoza.ontology.javafx;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TabbedApp extends Application {
    Group root = new Group();
    TabPane tabPane = new TabPane();

    public void init() throws Exception {
        // Prepare tab pane with set of tabs
        BorderPane borderPane = new BorderPane();
        //tabPane.setPrefSize(1000,650);
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        BubbleChart<Number, Number> bubbleChart = new BubbleGraph().start();
        Group pieChart = new PieGraph().start();
        SwingNode treeGraph = new TreeGraph().start();
        
        /** tab for pie graph **/
        final Tab pie = new Tab("Pie Chart");
        pie.setId("1");
        pie.setContent(pieChart);
        /** ends here  **/
        
        /** tab for bubble graph **/
        final Tab bubble = new Tab("Bubble Chart");
        bubble.setId("2");
        bubble.setContent(bubbleChart);
        /** ends here  **/
        
        /**	tab for tree graph **/
        final Tab tree = new Tab("Tree Graph");
        tree.setId("3");
        tree.setContent(treeGraph);
        /** ends here**/
        
        tabPane.getTabs().addAll( pie,bubble, tree);
        borderPane.setCenter(tabPane);
        root.getChildren().addAll(borderPane);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root);
       
        //scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet/style.css").toExternalForm());
        //.scene.getStylesheets().add(TabbedApp.class.getResource("stylesheet/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void navigateTo(String tab) {
        for (Tab t : tabPane.getTabs()) {
            if (tab.equals("#" + t.getId())) {
                tabPane.getSelectionModel().select(t);
                return;
            }
        }
    }
}