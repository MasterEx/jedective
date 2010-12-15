/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cellphonematcher;

/**
 *
 * @author periklis
 */
public class cmatch {

    private static final String HELP_OPT = "help";

    public static void main(String[] args) {
        // TODO code application logic here
        usage();
    }

    private static void usage() {
        System.err.printf("Usage: java -jar %s.jar [options] <file>\n"
				  + "\n\tOptions are:\n"
				  + "\t %s\tthis help message\n",
                                  cmatch.class.getSimpleName(),HELP_OPT);
    }

}
