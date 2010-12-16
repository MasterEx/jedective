package core;

import entities.Statistics;
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

    public static void start(String fname) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fname, true));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            out.write("\t\t\tStatistics\n\n---------------------------------------\nCreated at:"+dateFormat.format(date)+"\n\n");
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
            out.close();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while creating or writing to file: "+fname);
        }
    }

    public static void stop(String fname,int total,int matches,Statistics stats) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fname, true));
            out.write("\n---------------------------------------\n\nCellphones Found: "+stats.getCellphones()+"\nWebpages searched: "+total+"\n"
                    + "Success ratio:"+stats.getSuccessRatio()+"\n\n~/END\nCreated with Periklis Ntanasis' inspector");
            out.close();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("There was an error while creating or writing to file: "+fname);
        }
    }

}