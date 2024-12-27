package classes;

public class Bus {
    private String number;
    private String model;
    private int mileage;


    public Bus() {
    }

    private Bus(BusBuilder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;

    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return getNumber() + "/"
                + getModel() + "/"
                +getMileage();
    }

    public static class BusBuilder {
        private String number;
        private String model;
        private int mileage;
        private boolean sortable;

        public BusBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }



        public Bus build() {
            return new Bus(this);
        }
    }


}