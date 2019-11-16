class Farm {
    public static void main(String[] args) {
        Menagerie menagerie = new Menagerie();

        // We can add animals to the farm using menagerie.addAnimal.
        // The method signature for adding an animal looks like this:
        // void addAnimal(Animal animal) {
        //   ...
        // }
        // This means you can add any animal as long as it extends Animal.
        // Check out Monkey, Blobfish, and Dog to see how.
        menagerie.addAnimal(new Monkey());
        menagerie.addAnimal(new Blobfish());
        menagerie.addAnimal(new Dog());
        menagerie.addAnimal(new Bear("Grizzly"));
    }
}
