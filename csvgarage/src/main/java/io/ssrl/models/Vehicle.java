package io.ssrl.models;

import java.util.StringJoiner;

public abstract class Vehicle implements Validator {
    private static final int ALLOWED_FIELDS = 5;
    private static final int BRAND_FIELD_INDEX = 0;
    private static final int MODEL_FIELD_INDEX = 1;
    private static final int PRICE_FIELD_INDEX = 2;
    private static final int PLATE_FIELD_INDEX = 3;
    private static final int WHEELS_FIELD_INDEX = 4;
    private String brand;
    private String model;
    private String plate;
    private double price;

    public Vehicle(String inputtedBrand, String model, double price, String inputtedPlate) {
        plate = inputtedPlate.replaceAll("[\\s-]+", "");
        Validator.validatePlate(plate, getVehicleType());
        Validator.validatePrice(price);
        this.setAttributes(inputtedBrand.trim().toLowerCase(), model, price, plate);
    }

    private void setAttributes(String newBrand, String newModel, double newPrice, String newPlate) {
        this.brand = newBrand;
        this.model = newModel;
        this.plate = newPlate;
        this.price = newPrice;
    }

    abstract int getWheelNumber();

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public double getPrice() {
        return price;
    }

    public String getVehicleType() {
        return this.getClass().getSimpleName();
    }

    public String toCSVRecord() {
        StringJoiner record = new StringJoiner(";");
        record.add(getBrand())
                .add(getModel())
                .add(Double.toString(getPrice()))
                .add(getPlate())
                .add(Integer.toString(getWheelNumber()));
        return record.toString();
    }

    public static Vehicle getVehicleFromCSVRecord(String csvRecord) throws IllegalArgumentException {
        String[] fieldArray = csvRecord.split(";");
        if (fieldArray.length == ALLOWED_FIELDS) {
            double price = Double.parseDouble(fieldArray[PRICE_FIELD_INDEX]);
            int wheels = Integer.parseInt(fieldArray[WHEELS_FIELD_INDEX]);
            switch (wheels) {
                case Car.WHEEL_NUMBER:
                    return new Car(fieldArray[BRAND_FIELD_INDEX], fieldArray[MODEL_FIELD_INDEX], price,
                            fieldArray[PLATE_FIELD_INDEX]);
                case Moto.WHEEL_NUMBER:
                    return new Moto(fieldArray[BRAND_FIELD_INDEX], fieldArray[MODEL_FIELD_INDEX], price,
                            fieldArray[PLATE_FIELD_INDEX]);
                default:
                    throw new IllegalArgumentException("Vehicles with " + wheels + "are not defined");
            }
        }
        throw new IllegalArgumentException(
                "Vehicles String[] must have 5 fields (brand, model, price, plate, wheel_number)");
    }
}
