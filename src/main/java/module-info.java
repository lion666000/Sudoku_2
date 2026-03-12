module com.example.sudoku_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudoku_2 to javafx.fxml;
    exports com.example.sudoku_2;
}