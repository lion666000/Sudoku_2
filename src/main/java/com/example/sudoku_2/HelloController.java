package com.example.sudoku_2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class HelloController {


    @FXML
    private GridPane gridPane;

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
    protected boolean kontrolujPole(ArrayList<Integer> numbers){

        ArrayList<Integer> numberOfInstances = new ArrayList<>();

        for (int i = 1; i <= 9; i++){
            numberOfInstances.add(0);
        }

        System.out.println(numbers);

        for (int i = 0; i < numbers.size(); i++){
            int number = numbers.get(i);

            if (numberOfInstances.get(number-1) != null){
                int numberTwink = numberOfInstances.get(number-1) + 1;

                numberOfInstances.set(number - 1, numberTwink);

            }
            else {
                numberOfInstances.set(number - 1, 1);
            }
        }

        System.out.println(numberOfInstances);

        boolean ano = false;

        for (int i = 0; i < numberOfInstances.size(); i++){
            if (numberOfInstances.get(i) != 1){
                System.out.println("NE");
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

        return ano;
    }

    @FXML
    protected void rowCheck(){
        ArrayList<Integer> numbers = new ArrayList<>();


        int column = 0;
        //int row = 0;



        for  (Node node : gridPane.getChildren()){
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                numbers.add(Integer.parseInt(tf.getText()));

                //System.out.println(tf.getText());
                column++;
            }




            if (column == 9){
                //row++;
                column = 0;

                //System.out.println(numbers);

                kontrolujPole(numbers);

                numbers.clear();
            }
        }


    }

    @FXML
    protected void columnCheck(){
        ArrayList<Integer> numbers = new ArrayList<>();


        int column = 0;
        int row = 0;



        for (int i = 0; i < 9; i++){
            for  (Node node : gridPane.getChildren()){
                if (node instanceof TextField) {
                    if (gridPane.getColumnIndex(node) == column && gridPane.getRowIndex(node) == row){
                        TextField tf = (TextField) node;
                        numbers.add(Integer.parseInt(tf.getText()));

                        //System.out.println(tf.getText());
                        row++;
                    }
                }


                if (row == 9){
                    column++;
                    row = 0;

                    //System.out.println(numbers);

                    System.out.println("\nColumn " +  column + "\n");

                    kontrolujPole(numbers);

                    numbers.clear();
                }
            }
        }
    }

    @FXML
    protected void boxCheck(){
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
                                String text = ((TextField) node).getText();
                                if (!text.isEmpty()) {
                                    numbers.add(Integer.parseInt(text));
                                }
                            }
                        }
                    }
                }
                // Check the box after filling the array for this specific 3x3
                kontrolujPole(numbers);
            }
        }

        /*
        ArrayList<Integer> numbers = new ArrayList<>();


        int column = 0;
        int row = 0;

        int boxColumn = 0;
        int boxRow = 0;



        for (int i = 0; i < 9; i++){
            for  (Node node : gridPane.getChildren()){
                if (node instanceof TextField) {
                    if (gridPane.getColumnIndex(node) == boxColumn + column && gridPane.getRowIndex(node) == boxRow + row){
                        TextField tf = (TextField) node;
                        numbers.add(Integer.parseInt(tf.getText()));

                        //System.out.println(tf.getText());
                        column++;
                    }
                }

                if (column == boxColumn + 3){
                    if (row != boxRow + 2){
                        column = 0;
                        row++;
                    }
                    else{
                        kontrolujPole(numbers);
                        boxColumn += 3;
                        row = boxRow;
                        numbers.clear();
                    }

                    if (boxColumn == 9){
                        boxColumn = 0;
                        boxRow += 3;
                    }
                }


            }
        }

         */
    }

    @FXML
    protected void initialize(){
        generateNumbers();
        rowCheck();
        System.out.println("--------------------------------------------------------------------------------------------");
        columnCheck();
        System.out.println("--------------------------------------------------------------------------------------------");
        boxCheck();
    }
}
