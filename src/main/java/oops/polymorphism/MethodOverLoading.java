package oops.polymorphism;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MethodOverLoading {

    public static void main(String[] args) {

        ConfusionOverLoading confusionOverLoading = new ConfusionOverLoading();
        List<String> names = new ArrayList<>();
        List<String> boys = new LinkedList<>();
        confusionOverLoading.checkDuplicates(names);
        confusionOverLoading.checkDuplicates(boys);

        //for both the above call the method with parameter as List<String> is called

    }


}

class ConfusionOverLoading {

    /**
     * avoid using the same number of parameter and similar types for method overloading
     */
    public void checkDuplicates(List<String> names) {
        System.out.println("checkDuplicates with List as argument is called");
    }

    public void checkDuplicates(ArrayList<String> names) {
        System.out.println("checkDuplicates with ArrayList as argument is called");
    }

    public void checkDuplicates(LinkedList<String> names) {
        System.out.println("checkDuplicates with LinkedList as argument is called");
    }
}

