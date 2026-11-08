package pt.unips.tapoo;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphApp extends Application {

    @Override
    public void start(Stage stage) {
        Graph<String, Integer> graph = new GraphEdgeList<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "C", 3);
        graph.insertEdge("C", "D", 4);
        graph.insertEdge("D", "E", 5);

        SmartGraphPanel<String, Integer> graphView =
                new SmartGraphPanel<>(graph, new SmartCircularSortedPlacementStrategy());

        stage.setTitle("Undirected Graph");
        stage.setScene(new Scene(graphView, 800, 600));
        stage.show();

        // Uncomment for automatic layout
        //graphView.setAutomaticLayout(true);
    }
}
