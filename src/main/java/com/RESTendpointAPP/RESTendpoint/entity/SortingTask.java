package com.RESTendpointAPP.RESTendpoint.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SortingTask  {

    public SortingTask(String listOfNumbersAsString, TypeOfSorting typeOfSorting) {
        this.typeOfSorting = typeOfSorting;
        this.listOfNumbersAsString = listOfNumbersAsString;
//        this.listOfNumbersToSort=returnListOfIntegers(listOfNumbersAsString);
    }

//    public List<Integer> listOfNumbersToSort = returnListOfIntegers();

    public TypeOfSorting typeOfSorting;

    public String listOfNumbersAsString;

    public List<Integer> returnListOfIntegers(String listOfNumbersAsString){
        List<Integer> listOfNumbersToSort = new ArrayList<>();
        String[] strings = listOfNumbersAsString.split(",");
        for(int i =0; i<strings.length; i++){
            Integer value = Integer.valueOf(strings[i]);
            listOfNumbersToSort.add(value);
        }
        return listOfNumbersToSort;
    }

}
