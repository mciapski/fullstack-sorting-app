package com.RESTendpointAPP.RESTendpoint.service;


import com.RESTendpointAPP.RESTendpoint.entity.SortingTask;
import com.RESTendpointAPP.RESTendpoint.exceptions.InvalidSortingDirectionException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SortingService {
public List<Integer> resultList;
    public List<Integer> sort(SortingTask sortingTask) {

        switch (sortingTask.typeOfSorting) {
            case ASC -> {
                List<Integer> ascendingList = sortingTask.returnListOfIntegers(sortingTask.getListOfNumbersAsString()).stream().sorted().toList();
                return ascendingList;
            }
            case DESC -> {
                List<Integer> descendingList = sortingTask.returnListOfIntegers(sortingTask.getListOfNumbersAsString()).stream().sorted(Comparator.reverseOrder()).toList();
                return descendingList;
            }
            default-> {
                throw  new InvalidSortingDirectionException(sortingTask.typeOfSorting);}
        }
    }
//    public List<Integer> result(){
//        return resultList.addAll(sort(S))
//    }
}
