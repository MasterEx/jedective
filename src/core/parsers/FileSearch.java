package core.parsers;

import core.Logger;
import entities.CycleQ;
import entities.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Periklis Ntanasis
 */
public class FileSearch {
    
    private static final int bound = 12;
    private Statistics stats;
    private File file;
    private Html2Text converter = new Html2Text();
    private static final String SYMBOLREGEX = "(,|.|-|;|:|\\*|\\?|\\\\|/|>|<|!)*";
    
    public FileSearch(File file) {
        this.file = file;
        this.stats = new Statistics();
    }

    public FileSearch(File file,Statistics stats) {
        this.file = file;
        this.stats = stats;
    }

    public void parseFile(File file,boolean converthtml) throws FileNotFoundException {
        try {
            System.out.println("Filename: "+file.getAbsolutePath());
            Scanner in;
            if(converthtml) {
                converter.setFile(file);
                String htmlfile = converter.getText();
                // also remove all symbols
                htmlfile = htmlfile.replaceAll(SYMBOLREGEX, " ");
                in = new Scanner(htmlfile);
            } else {
                in = new Scanner(file);
            }
            String tmp;
            CycleQ q = new CycleQ(bound);
            while(in.hasNext())
            {
                if(Matcher.isCellphone(tmp=in.next().replaceAll(SYMBOLREGEX, " ")))
                {
                   hit(tmp,q,in,file.getAbsolutePath(),stats);
                }
                q.add(tmp);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("*Warning* Possible not valid html\n"
                    + "Will try search the original html file for cellphones");
        }
    }

    private static void hit(String tmp,CycleQ q,Scanner in,String filename,Statistics stats) {
         Logger.add("log.txt", filename, 7);
         stats.addCellphone();
         Logger.add("log.txt", tmp, 0);
                for(int i=0;i<q.size();i++)
                {
                    if(Matcher.isName(q.pick(i)))
                    {
                        Logger.add("log.txt", q.pick(i), 5);
                        Logger.add("log.txt", q.toString(), 1);
                        stats.addPossibleMatch();
                    }
                    if(Matcher.isSurName(q.pick(i)))
                    {
                        Logger.add("log.txt", q.pick(i), 6);
                        Logger.add("log.txt", q.toString(), 2);
                        stats.addPossibleMatch();
                    }
                }
         int c = 0;
         String line="";
            while(in.hasNext())
            {
                if(c==bound)
                    break;
                if(Matcher.isCellphone(tmp=in.next()))
                {
                    hit(tmp,q,in,filename,stats);
                    break;
                }
                line += " "+tmp;
                if(Matcher.isName(tmp))
                {
                    Logger.add("log.txt", tmp, 5);
                    Logger.add("log.txt", line, 3);
                        stats.addPossibleMatch();
                    break;
                }
                if(Matcher.isSurName(tmp))
                {
                    Logger.add("log.txt", tmp, 6);
                    Logger.add("log.txt", line, 4);
                        stats.addPossibleMatch();
                    break;
                }
            }
    }

}
