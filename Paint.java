import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Paint extends Application {

    private Color currentBrushColor = Color.BLACK;
    private BrushColorSelect currentColorRectangle = new BrushColorSelect(currentBrushColor);
    private Text eraserText = new Text(" Eraser");
    private int radius = 16;

    public void start(Stage stage) {
        GridPane brushColors = new GridPane();
        brushColors.add(new BrushColorSelect(Color.CRIMSON), 0, 0);
        brushColors.add(new BrushColorSelect(Color.BLUE), 0, 1);
        brushColors.add(new BrushColorSelect(Color.GREEN), 1, 0);
        brushColors.add(new BrushColorSelect(Color.YELLOW), 1, 1);
        brushColors.add(new BrushColorSelect(Color.PURPLE), 2, 0);
        brushColors.add(new BrushColorSelect(Color.ORANGE), 2, 1);
        brushColors.add(new BrushColorSelect(Color.PINK), 3, 0);
        brushColors.add(new BrushColorSelect(Color.BLACK), 3, 1);
        brushColors.add(currentColorRectangle, 5, 0);
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: white;");
        BorderPane borderPane = new BorderPane(canvas);
        HBox hBox = new HBox(new Text("Eraser"), new BrushColorSelect(Color.WHITE));
        hBox.setSpacing(5);
        Button clearCanvasButton = new Button("Clear Canvas");
        clearCanvasButton.setOnAction(e -> canvas.getChildren().clear());
        VBox vBox = new VBox(hBox, clearCanvasButton);
        vBox.setSpacing(12);
        eraserText.setVisible(false);
        VBox currentColorVBox = new VBox(currentColorRectangle, eraserText);
        currentColorVBox.setSpacing(5);
        ComboBox<Integer> brushSizeComboBox = new ComboBox<>();
        for(int i = 1; i < 31; i++)
            brushSizeComboBox.getItems().add(i);
        brushSizeComboBox.setValue(radius);
        brushSizeComboBox.setOnAction(e -> radius = brushSizeComboBox.getValue());
        FlowPane colorSelect = new FlowPane(new Text("Select Brush Size"), brushSizeComboBox, brushColors, new Text("Current Brush Color"), currentColorVBox, vBox);
        colorSelect.setHgap(22);
        colorSelect.setAlignment(Pos.CENTER);
        borderPane.setBottom(colorSelect);
        Scene scene = new Scene(borderPane, 1600, 950);
        canvas.setOnMousePressed(e ->{ canvas.getChildren().add(new Circle(e.getX(), e.getY(),radius, currentBrushColor));});
        canvas.setOnMouseDragged(e ->{ canvas.getChildren().add(new Circle(e.getX(), e.getY(),radius, currentBrushColor));});
        stage.setScene(scene);
        stage.show();
    }

    class BrushColorSelect extends Rectangle {

        BrushColorSelect(Color color){
            super(50, 50, color);
            this.setStroke(Color.BLACK);
            this.setOnMousePressed(e -> {
                currentBrushColor = color;
                currentColorRectangle.setFill(currentBrushColor);
                if(currentColorRectangle.getFill() == Color.WHITE)
                    eraserText.setVisible(true);
                else
                    eraserText.setVisible(false);
            });
        }

    }

  
}
