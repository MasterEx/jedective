package core;

/**
 * Jedective - a text parser
 * Copyright (C) 2010-2011 Periklis Ntanasis
 *
 * Licensed under GPL license : http://www.gnu.org/licenses/gpl.html
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Periklis Ntanasis
 */
public class Logger {

    private static long starttime;

    public static void start(String fname) {
        starttime = System.currentTimeMillis();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fname, true));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            out.write("\n\t\t\tStatistics\n\n---------------------------------------\nCreated at:"+dateFormat.format(date)+"\n\n");
            out.close();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while creating or writing to file: "+fname);
        }
    }

    public static void add(String fname,String w,int n) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fname, true));
            w = w.replaceAll("empty", "");
            if(n==0)
                out.write("Cellphone: "+w+"\n");
            else if(n==1)
                out.write("Match 1(name):"+w+"\n");
            else if(n==2)
                out.write("Match 1(surname):"+w+"\n");
            else if(n==3)
                out.write("Match 2(name):"+w+"\n");
            else if(n==4)
                out.write("Match 2(surname):"+w+"\n");
            else if(n==5)
                out.write("Possible Name:"+w+"\n");
            else if(n==6)
                out.write("Possible Surname:"+w+"\n");
            else if(n==7)
                out.write("\nFile: "+w+"\n");
            else if(n==8)
                out.write("*Error* "+w+"\n");
            out.close();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while creating or writing to file: "+fname);
        }
    }

    public static void stop(String fname,int total,int matches,int cellphones,float ratio) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fname, true));
            out.write("\n---------------------------------------\n\nCellphones Found: "
                    +cellphones+"\nWebpages searched: "+total+"\n"
                    + "Success ratio: "+ratio+"\n\n"
                    +"Execution Time: "+(System.currentTimeMillis()-starttime)/1000+" sec"
                    +"\n\n~/END\nCreated with Periklis Ntanasis jedective");
            out.close();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while creating or writing to file: "+fname+"\n");
        }
    }

}
