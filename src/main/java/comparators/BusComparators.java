package comparators;

import classes.Bus;

import java.util.Comparator;

public class BusComparators extends AbstractComparator {
    public Comparator<Bus> selectComparator(int index){
        if (index ==1)
            return BusComparators::modelComparator;
        if (index == 0)
            return BusComparators::numberComparator;
        if (index == 2)
            return BusComparators::mileageComparator;
        return null;
    }

    private static int modelComparator(Bus o1, Bus o2){
        return o1.getModel().compareTo(o2.getModel());
    }
    private static int numberComparator(Bus o1, Bus o2){
        return o1.getNumber().compareTo(o2.getNumber());
    }

    private static int mileageComparator(Bus o1, Bus o2){
        return Integer.compare(o1.getMileage(), o2.getMileage());
    }
}
