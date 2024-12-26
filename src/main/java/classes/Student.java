package classes;

public class Student {
    private String groupNumber;
    private double averageScore;
    private String recordBookNumber;
    private boolean sortable;

    public Student() {
    }

    private Student(StudentBuilder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
        this.sortable = builder.sortable;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber='" + groupNumber + '\'' +
                ", averageScore=" + averageScore +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                '}';
    }

    public static class StudentBuilder {
        private String groupNumber;
        private double averageScore;
        private String recordBookNumber;
        private boolean sortable;

        public StudentBuilder setGroupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder setAverageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public StudentBuilder setRecordBookNumber(String recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public StudentBuilder setSortable(boolean sortable) {
            this.sortable = sortable;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }


}