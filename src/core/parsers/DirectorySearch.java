package core.parsers;

import entities.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 *
 * @author Periklis Ntanasis
 */
public class DirectorySearch {
    
    private FileSearch filesearcher;
    private Statistics stats;

    public DirectorySearch() {

    }

    public DirectorySearch(Statistics stats) {
        this.stats = stats;
    }
    
    public void dirParser(String path) throws FileNotFoundException, IOException {
        File dir = new File(path),file;
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return (!name.startsWith(".") || name.endsWith(".html") || name.endsWith(".htm"));
            }
        };
        String[] f = dir.list(filter);
        for (int i=0;i<f.length;i++)
        {
            if((new File(path+File.separatorChar+f[i])).isDirectory())
                this.dirParser(path+File.separatorChar+f[i]);
            else if((file = new File(path+File.separatorChar+f[i])).isFile())
            {
                filesearcher = new FileSearch(new File(path+File.separatorChar+f[i]),stats);
                filesearcher.parseFile(file, true);
                stats.addtotalwebpages();
            }
        }
    }

}
