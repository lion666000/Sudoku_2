package com.example.sudoku_2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class HelloController {
    ArrayList<String> numbers =  new ArrayList<>();

    @FXML
    private GridPane gridPane;

    protected void generateNumbers(){
        for (int i = 1; i <= 9; i++){
            numbers.add(String.valueOf(i));
        }
    }

    @FXML
    protected void displayNumbers(){
        int collumn = 0;
        int row = 0;

        for  (String n : numbers){
            gridPane.add(new TextField(n), collumn, row);


            collumn++;

            if (collumn == 9){
                row++;
                collumn = 0;
            }
        }
    }

    @FXML
    protected void rowCheck(){
        ArrayList<String> rowNumbers = new ArrayList<>();
        ArrayList<Integer> numberOfInstances = new ArrayList<>();

        int column = 0;
        int row = 0;



        for  (Node node : gridPane.getChildren()){
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                rowNumbers.add(tf.getText());
            }


            column++;

            if (column == 9){
                row++;
                column = 0;
            }
        }

        for (int i = 1; i <= 9; i++){
            numberOfInstances.add(0);
        }

        System.out.println(rowNumbers);

        for (int i = 0; i < rowNumbers.size(); i++){
            int number = Integer.parseInt(rowNumbers.get(i));

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
    }

    @FXML
    protected void initialize(){
        generateNumbers();
        displayNumbers();
        rowCheck();
    }
}
