package parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import net.htmlparser.jericho.Source;

/**
 *
 * @author Periklis Ntanasis
 */
public class html2Text {
    
    private BufferedReader htmlfile;

    public html2Text(File filename) throws FileNotFoundException {
        htmlfile = new BufferedReader(new FileReader(filename));
    }

    public String getText() throws IOException {
        return new Source(htmlfile).getTextExtractor().toString();
    }

}
