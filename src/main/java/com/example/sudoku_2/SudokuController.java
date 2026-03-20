package com.example.sudoku_2;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SudokuController {

    int[][] sudokuGrid = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 0, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 0, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 0, 6, 1, 7, 9}
    };

    @FXML
    private GridPane gridPane;

    @FXML
    protected void wrongBase(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Zadání");
        alert.setHeaderText(null); // Removes the header space
        alert.setContentText("Zadání je špatně");

        alert.showAndWait();
    }

    @FXML
    protected void wrongSolution(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Řešení");
        alert.setHeaderText(null); // Removes the header space
        alert.setContentText("Chyba v řešení");

        alert.showAndWait();
    }

    @FXML
    protected void correctSolution(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Řešení");
        alert.setHeaderText(null); // Removes the header space
        alert.setContentText("Řešení je správně");

        alert.showAndWait();
    }

    @FXML
    protected void correctBase(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zadání");
        alert.setHeaderText(null); // Removes the header space
        alert.setContentText("Zadání je správně");

        alert.showAndWait();
    }

    protected void generateNumbers(){
        ArrayList<String> numbers =  new ArrayList<>();

        int collumn = 0;
        int row = 0;

        for (int j = 0; j < 9; j++){
            for (int i = 1; i <= 9; i++){
                numbers.add(String.valueOf(i));
            }



            for  (String n : numbers){
                gridPane.add(new TextField(n), collumn, row);


                collumn++;

                if (collumn == 9){
                    row++;
                    collumn = 0;
                }
            }
            numbers.clear();
        }
    }

    @FXML
    protected void zadani(){

        /*
        {5, 3, 4, 6, 7, 8, 9, 1, 2},
        {6, 7, 2, 1, 9, 5, 3, 4, 8},
        {1, 9, 8, 3, 4, 2, 5, 6, 7},
        {8, 5, 9, 7, 6, 1, 4, 2, 3},
        {4, 2, 6, 8, 5, 3, 7, 9, 1},
        {7, 1, 3, 9, 2, 4, 8, 5, 6},
        {9, 6, 1, 5, 3, 7, 2, 8, 4},
        {2, 8, 7, 4, 1, 9, 6, 3, 5},
        {3, 4, 5, 2, 8, 6, 1, 7, 9}

         */



        for (int j = 0; j < 9; j++){
            for(int k = 0; k < 9; k++){
                String n = String.valueOf(sudokuGrid[k][j]);

                if (Integer.parseInt(n) == 0){
                    n = null;
                }

                TextField tf = new TextField(n);

                tf.setPrefSize(30,30);
                tf.setAlignment(Pos.CENTER);

                gridPane.add(tf, j, k);
            }
        }
    }

    @FXML
    protected boolean kontrolujPole(ArrayList<Integer> numbers){

        ArrayList<Integer> numberOfInstances = new ArrayList<>();

        boolean ano = false;

        for (int i = 1; i <= 9; i++){
            numberOfInstances.add(0);
        }

        System.out.println(numbers);

        for (int i = 0; i < numbers.size(); i++){
            int number = numbers.get(i);

            if (number == 0){
                ano = false;
            }
            else if (numberOfInstances.get(number-1) != null){
                int numberTwink = numberOfInstances.get(number-1) + 1;

                numberOfInstances.set(number - 1, numberTwink);

            } else if (number < 1 || number > 9) {
                ano =  false;
            } else{
                numberOfInstances.set(number - 1, 1);
            }
        }

        System.out.println(numberOfInstances);



        for (int i = 0; i < numberOfInstances.size(); i++){
            if (numberOfInstances.get(i) != 1){
                ano = false;
                break;
            }
            else {
                ano = true;
            }
        }

        if (ano){
            System.out.println("ANO");
        }
        else {
            System.out.println("NE");
        }

        return ano;
    }

    @FXML
    protected boolean rowCheck(){
        ArrayList<Integer> numbers = new ArrayList<>();


        int column = 0;
        int row = 0;

        int num = 0;

        boolean correct = true;

        for (int i = 0; i < 9; i++){
            for  (Node node : gridPane.getChildren()) {
                if (node instanceof TextField) {
                    if (gridPane.getColumnIndex(node) == column && gridPane.getRowIndex(node) == row) {
                        TextField tf = (TextField) node;
                        if (tf.getText() != null && !tf.getText().isEmpty() && (Integer.parseInt(tf.getText()) <= 9 && Integer.parseInt(tf.getText()) >= 1)) {
                            num = Integer.parseInt(tf.getText());
                        } else {
                            num = 0;
                        }

                        numbers.add(num);

                        //System.out.println(tf.getText());
                        column++;
                    }

                }


                if (column == 9) {
                    row++;
                    column = 0;

                    //System.out.println(numbers);

                    System.out.println("\nRow " + row + "\n");

                    correct = kontrolujPole(numbers);


                    numbers.clear();
                }

                if (!correct) {
                    break;
                }
            }
        }


        return correct;
    }

    @FXML
    protected boolean columnCheck(){
        ArrayList<Integer> numbers = new ArrayList<>();


        int column = 0;
        int row = 0;

        int num = 0;

        boolean correct = true;

        for (int i = 0; i < 9; i++){
            for  (Node node : gridPane.getChildren()){
                if (node instanceof TextField) {
                    if (gridPane.getColumnIndex(node) == column && gridPane.getRowIndex(node) == row){
                        TextField tf = (TextField) node;
                        if (tf.getText() != null && !tf.getText().isEmpty() && (Integer.parseInt(tf.getText()) <= 9 && Integer.parseInt(tf.getText()) >= 1)){
                            num = Integer.parseInt(tf.getText());
                        }
                        else {
                            num = 0;
                        }

                        numbers.add(num);

                        //System.out.println(tf.getText());
                        row++;
                    }
                }


                if (row == 9){
                    column++;
                    row = 0;

                    //System.out.println(numbers);

                    System.out.println("\nColumn " +  column + "\n");

                    correct = kontrolujPole(numbers);

                    numbers.clear();
                }

                if (!correct) {
                    break;
                }
            }
        }
        return correct;
    }

    @FXML
    protected boolean boxCheck(){
        int box = 0;

        int num = 0;

        boolean correct = false;

        // Loop through each of the 9 boxes
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {

                ArrayList<Integer> numbers = new ArrayList<>();

                // Loop through the 3x3 cells inside the current box
                for (int r = boxRow; r < boxRow + 3; r++) {
                    for (int c = boxCol; c < boxCol + 3; c++) {

                        // Find the node at specific row 'r' and column 'c'
                        for (Node node : gridPane.getChildren()) {
                            Integer nodeCol = GridPane.getColumnIndex(node);
                            Integer nodeRow = GridPane.getRowIndex(node);

                            // Handle nulls (default is 0) and check if it's the right cell
                            if (nodeCol == null) nodeCol = 0;
                            if (nodeRow == null) nodeRow = 0;

                            if (node instanceof TextField && nodeRow == r && nodeCol == c) {
                                TextField tf = (TextField) node;
                                if (tf.getText() != null && !tf.getText().isEmpty() && (Integer.parseInt(tf.getText()) <= 9 && Integer.parseInt(tf.getText()) >= 1)){
                                    num = Integer.parseInt(tf.getText());
                                }
                                else {
                                    num = 0;
                                }

                                numbers.add(num);
                            }
                        }
                    }
                }
                // Check the box after filling the array for this specific 3x3

                box++;

                System.out.println("\nBox " +  box + "\n");

                correct = kontrolujPole(numbers);

                if (!correct) {
                    break;
                }
            }
        }

        return  correct;
    }

    @FXML
    protected void checkSudokuSolution(){

        if (rowCheck() && columnCheck() && boxCheck()){
            correctSolution();
        }
        else  {
            wrongSolution();
        }
    }

    @FXML
    protected void checkSudokuBase(){

        if (rowCheck() && columnCheck() && boxCheck()){
            correctBase();
        }
        else  {
            wrongBase();
        }
    }

    @FXML
    protected void locker(){
        for  (Node node : gridPane.getChildren()){
            if (node instanceof TextField && ((TextField) node).getText() != null && !((TextField) node).getText().equals("") ) {
                node.setDisable(true);
                sudokuGrid[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Integer.parseInt(((TextField) node).getText());
            }
            else if (node instanceof TextField) {
                sudokuGrid[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = 0;
            }
        }
    }

    @FXML
    protected void deleter(){
        for  (Node node : gridPane.getChildren()){
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                tf.setDisable(false);
                tf.setText(null);
            }
        }
    }


    @FXML
    protected void initialize(){


        gridPane.getChildren().removeIf(node -> node instanceof TextField);
        //generateNumbers();
        zadani();

        gridPane.getStyleClass().add("grid-pane");

        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                node.getStyleClass().add("sudoku-field");

                TextField tf = (TextField) node;
                if (((TextField) node).getText() != null && !((TextField) node).getText().equals("")) {
                    tf.getStyleClass().add("pre-filled");
                }
                else{
                    tf.getStyleClass().add("user-input");
                }
            }
        }

        rowCheck();
        System.out.println("--------------------------------------------------------------------------------------------");
        columnCheck();
        System.out.println("--------------------------------------------------------------------------------------------");
        boxCheck();
    }
}
