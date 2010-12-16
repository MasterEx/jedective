/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jedective;

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
 * @author Periklis Ntanasis
 */
public class jedetive {

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
        Logger.start("log.txt");
        File fpath = new File(path);
        if(fpath.exists() && fpath.isDirectory()) {
            DirectorySearch searcher = new DirectorySearch(stats);
            try {
                searcher.dirParser(path);
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(jedetive.class.getName()).log(Level.SEVERE, null, ex);
                Logger.add("log.txt"," FileNotFoundException or IOException",8);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(jedetive.class.getName()).log(Level.SEVERE, null, ex);
                Logger.add("log.txt"," FileNotFoundException or IOException",8);
            }
        } else if(fpath.exists() && fpath.isFile()) {
            FileSearch searcher = new FileSearch(fpath,stats);
            try {
                searcher.parseFile(fpath, true);
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(jedetive.class.getName()).log(Level.SEVERE, null, ex);
                Logger.add("log.txt"," FileNotFoundException",8);
            }
        }
        Logger.stop("log.txt",stats.gettotalwebpages(),stats.getPossiblematches(),stats.getCellphones(),stats.getSuccessRatio());
    }

    private static void usage() {
        System.err.printf("Usage: java -jar %s.jar [options] <file>\n"
				  + "\n\tOptions are:\n"
				  + "\t %s\tthis help message\n",
                                  jedetive.class.getSimpleName(),HELP_OPT);
    }

}
