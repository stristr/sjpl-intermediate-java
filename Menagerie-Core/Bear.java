class Bear extends Animal {
    private String name;

    Bear(String requestedName) {
        name = requestedName;
    }

    String getName() {
        return name;
    }

    String getSound() {
        return "Roaaaaarrr";
    }

    String getPicture() {
        return "res/animals/bear.jpg";
    }
}
