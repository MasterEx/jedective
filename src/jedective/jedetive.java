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
    private static final String OUTPUT_OPT = "out";
    private static boolean help=false;
    private static final Statistics stats = new Statistics();
    public static String log = "log.txt";

    public static void main(String[] args) {
        int i = 0;
        String path="";
        while(i<args.length)
        {
            if (args[i].equalsIgnoreCase(HELP_OPT)) {
                help = true;
                i++;
                continue;
            } else if (args[i].equalsIgnoreCase(OUTPUT_OPT)) {
                if(args.length>(i+1)) {
                    i++;
                    log = args[i];
                    i++;
                    continue;
                } else {
                    System.err.printf("Not log filename specified\n");
                    System.exit(1);
                }
            }
            path = args[i];
            i++;
        }
        if(args.length==0||help) {
            usage();
            System.exit(0);
        }
        if(path.length()==0) {
            System.err.printf("Not filepath specified\n");
            System.exit(1);
        }
        Logger.start(log);
        File fpath = new File(path);
        if(fpath.exists() && fpath.isDirectory()) {
            DirectorySearch searcher = new DirectorySearch(stats);
            try {
                searcher.dirParser(path);
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(jedetive.class.getName()).log(Level.SEVERE, null, ex);
                Logger.add(log," FileNotFoundException or IOException",8);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(jedetive.class.getName()).log(Level.SEVERE, null, ex);
                Logger.add(log," FileNotFoundException or IOException",8);
            }
        } else if(fpath.exists() && fpath.isFile()) {
            FileSearch searcher = new FileSearch(fpath,stats);
            try {
                searcher.parseFile(fpath, true);
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(jedetive.class.getName()).log(Level.SEVERE, null, ex);
                Logger.add(log," FileNotFoundException",8);
            }
        }
        Logger.stop(log,stats.gettotalwebpages(),stats.getPossiblematches(),stats.getCellphones(),stats.getSuccessRatio());
    }

    private static void usage() {
        System.err.printf("Usage: java -jar %s.jar [options] <file>\n"
				  + "\n\tOptions are:\n"
                                  + "\t %s <filename>\tsets the logfile name\n"
				  + "\t %s\t\tthis help message\n",
                                  jedetive.class.getSimpleName(),OUTPUT_OPT,HELP_OPT);
    }

}
