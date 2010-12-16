/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cellphonematcher;

import core.Logger;
import core.parsers.DirectorySearch;
import core.parsers.FileSearch;
import entities.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author periklis
 */
public class cmatch {

    private static final String HELP_OPT = "help";
    private static boolean help=false;
    private static final Statistics stats = new Statistics();

    public static void main(String[] args) {
        int i = 0;
        String path="";
        while(i<args.length)
        {
            if (args[i].equalsIgnoreCase(HELP_OPT)) {
                help = true;
                i++;
                continue;
            }
            path = args[i];
            i++;
        }
        if(args.length==0||help) {
            usage();
            System.exit(0);
        }
        System.out.println("HRER 1");
        Logger.start("log.txt");
        System.out.println("HRER 2");
        File fpath = new File(path);
        if(fpath.exists() && fpath.isDirectory()) {
        System.out.println("HRER 4");
            DirectorySearch searcher = new DirectorySearch(stats);
            try {
                searcher.dirParser(path);
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(cmatch.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(cmatch.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(fpath.exists() && fpath.isFile()) {
        System.out.println("HRER 5");
            FileSearch searcher = new FileSearch(fpath,stats);
            try {
                searcher.parseFile(fpath, true);
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(cmatch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("HRER 6");
        Logger.stop("log.txt",stats.gettotalwebpages(),stats.getPossiblematches(),stats.getCellphones(),stats.getSuccessRatio());
    }

    private static void usage() {
        System.err.printf("Usage: java -jar %s.jar [options] <file>\n"
				  + "\n\tOptions are:\n"
				  + "\t %s\tthis help message\n",
                                  cmatch.class.getSimpleName(),HELP_OPT);
    }

}
