public class Student implements Sortable{
    private String groupNumber;
    private double averageScore;
    private String recordBookNumber;
    private boolean sortable;

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber='" + groupNumber + '\'' +
                ", averageScore=" + averageScore +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                ", sortable=" + sortable +
                '}';
    }

    public Student() {
    }
    public boolean isSortable() {

        return sortable;
    }
    private Student(StudentBuilder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
        this.sortable = builder.sortable;
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