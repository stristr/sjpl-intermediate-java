/**
 * This is the animal "base class". All animals in the farm should inherit from this base class.
 */
class Animal {
    /**
     * This method returns the name of the animal.
     * <p>
     * It can be overridden in classes that extend Animal.
     */
    String getName() {
        return "Bob";
    }

    /**
     * This method returns the sound the animal makes.
     * <p>
     * It can be overridden in classes that extend Animal.
     */
    String getSound() {
        return "Grrrrr";
    }

    /**
     * This method returns the picture of the animal.
     * <p>
     * It can be overridden in classes that extend Animal.
     */
    String getPicture() {
        return "";
    }
}
