package ohtu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }
        
        JsonWebParser parser = new JsonWebParser();
        StudentPrinter printer = new StudentPrinter();
        
        printer.printStudentSubmissions(parser, studentNr);
    }
}