package core.parsers;

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
public class Html2Text {
    
    private BufferedReader htmlfile;

    public Html2Text() {

    }

    public Html2Text(File filename) throws FileNotFoundException {
        htmlfile = new BufferedReader(new FileReader(filename));
    }

    public void setFile(File filename) throws FileNotFoundException {
        htmlfile = new BufferedReader(new FileReader(filename));
    }

    public String getText() throws IOException {
        return new Source(htmlfile).getTextExtractor().toString();
    }

}
