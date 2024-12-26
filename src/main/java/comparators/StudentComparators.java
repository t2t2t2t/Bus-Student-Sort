package comparators;

import classes.Student;

import java.util.Comparator;

public class StudentComparators extends AbstractComparator {
    public Comparator<Student> selectComparator(int index){
        if (index ==1)
            return StudentComparators::averageScoreComparator;
        if (index == 0)
            return StudentComparators::groupNumberComparator;
        if (index == 2)
            return StudentComparators::recordBookNumberComparator;
        return null;
    }

    private static int averageScoreComparator(Student o1, Student o2){
        return Double.compare(o1.getAverageScore(), o2.getAverageScore());
    }
    private static int groupNumberComparator(Student o1, Student o2){
        return o1.getGroupNumber().compareTo(o2.getGroupNumber());
    }

    private static int recordBookNumberComparator(Student o1, Student o2){
        return o1.getRecordBookNumber().compareTo(o2.getRecordBookNumber());
    }
}
