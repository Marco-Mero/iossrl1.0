package io.ssrl.models;

public interface Validator {
    float MIN_PRICE = 1;
    float CAR_PLATE_LENGTH = 7;
    float MOTO_PLATE_LENGTH = 6;

    static void validatePrice(double price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("Price cannot be lower than" + MIN_PRICE);
        }
    }

    static void validatePlate(String plate, String vehicleType) {
        switch (vehicleType) {
            case "Car":
                Validator.validateCarPlate(plate);
                break;
            case "Moto":
                Validator.validateMotoPlate(plate);
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type.");
        }
    }

    static void validateCarPlate(String plate) throws IllegalArgumentException {
        if (plate.length() != CAR_PLATE_LENGTH) {
            throw new IllegalArgumentException("Expected 7 character long plate number.");
        }
        if (plate.matches("[IOQU]")) {
            throw new IllegalArgumentException("License plate cannot contain I O Q U.");
        }
        if (!Character.isAlphabetic(plate.charAt(0)) && Character.isAlphabetic(plate.charAt(1))) {
            throw new IllegalArgumentException("License plate does not begin with two letters.");
        }
    }

    static void validateMotoPlate(String plate) throws IllegalArgumentException {
        if (plate.length() != MOTO_PLATE_LENGTH) {
            throw new IllegalArgumentException(plate + "Expected 6 character long plate number.");
        }
        if (plate.matches("[IOQU]")) {
            throw new IllegalArgumentException("Licence plate cannot contain I O Q U.");
        }
        if (!Character.isAlphabetic(plate.charAt(0)) && Character.isAlphabetic(plate.charAt(1))) {
            throw new IllegalArgumentException("Licence plate does not begin with two letters.");
        }
    }
}
