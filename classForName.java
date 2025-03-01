public class classForName {

    public static void main(String[] args) throws Exception {
        
        // Creating an object of class 'pqr' using 'new' keyword:
        // This will execute both static and instance blocks.
        // pqr p = new pqr(); 
        
        // Loading the class 'pqr' using Class.forName():
        // This will execute ONLY the static block of class 'pqr'.
        Class.forName("pqr");  
    }
}

class pqr {
    
    // Static block: Runs only once when the class is loaded into memory.
    // This block executes when Class.forName("pqr") is called.
    static {
        System.out.println("Static block");  // Output when the class is loaded
    }
    
    // Instance block: Runs every time an object of the class is created.
    // This block will NOT execute when Class.forName("pqr") is used.
    {
        System.out.println("Instance Block"); // Output when an object is created
    }
}
