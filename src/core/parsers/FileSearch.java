package core.parsers;

/**
 * Jedective - a text parser
 * Copyright (C) 2010-2011 Periklis Ntanasis
 *
 * Licensed under GPL license : http://www.gnu.org/licenses/gpl.html
 *
 */

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
    //private static final String SYMBOLREGEX = "[\\p{Punct}&&[^(@|.|\\+)]]+";
    private static final String SYMBOLREGEX = "(!|\"|'|#|$|%|&|,|-|\\.|/|\\\\|:|_|;|\\?|\\^|\\{|\\})+";
    
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
                if(Matcher.isCellphone(tmp=in.next()))
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
        boolean match = false;
         Logger.add(jedective.jedetive.log, filename, 7);
         stats.addCellphone();
         Logger.add(jedective.jedetive.log, tmp, 0);
                for(int i=0;i<q.size();i++)
                {
                    if(Matcher.isName(q.pick(i)))
                    {
                        Logger.add(jedective.jedetive.log, q.pick(i), 5);
                        Logger.add(jedective.jedetive.log, q.toString(), 1);
                        match = true;
                    }
                    if(Matcher.isSurName(q.pick(i)))
                    {
                        Logger.add(jedective.jedetive.log, q.pick(i), 6);
                        Logger.add(jedective.jedetive.log, q.toString(), 2);
                        match = true;
                    }
                }
         int c = 0;
         String line="";
            while(in.hasNext())
            {
                c++;
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
                    Logger.add(jedective.jedetive.log, tmp, 5);
                    Logger.add(jedective.jedetive.log, line, 3);
                    match = true;
                    continue;
                }
                if(Matcher.isSurName(tmp))
                {
                    Logger.add(jedective.jedetive.log, tmp, 6);
                    Logger.add(jedective.jedetive.log, line, 4);
                    match = true;
                    continue;
                }
            }
        if(match)
        {
            stats.addPossibleMatch();
        }
    }

}
