import java.net.*;

public class JWhichHelper {
    public static void main(String args[]) {
        URL url = JWhich.findClass(args[0]);//directly using this so that JWhich does not print classpath all the time
        if (url == null)
            System.exit(1);
    }
}