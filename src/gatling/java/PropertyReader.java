public class PropertyReader {

    public static void main(String[] args) {

        // Accessing project property 'par'
        String par = System.getProperty("par");
        System.out.println("Project property 'par' is: " + par);

    }
}
