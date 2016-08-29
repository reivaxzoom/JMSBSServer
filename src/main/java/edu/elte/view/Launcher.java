/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.view;

import edu.elte.client.QueryProcessor;
import org.apache.log4j.Logger;

/**
 *
 * @author Xavier
 */
public class Launcher {

    static Logger log = Logger.getLogger(Launcher.class.getName());
    
    /**
     * @param args
     */
    public static void main(String[] args)  {
        log.warn("Starting program");
        if (args != null && args.length > 0) {
            String option = args[0];
            String[] args2 = new String[0];
            if (args.length > 1) {
                args2 = new String[args.length - 1];
                System.arraycopy(args, 1, args2, 0, args2.length);
            }

            switch (option) {
                case "process":
                    {
                        log.warn("Processing transaction");
                        new QueryProcessor().consumeAll();
                        break;
                    }
                case "dboperations":
                    {
                        log.warn("processing regenerationDb");
                        new QueryProcessor().regenerateDb();
                        break;
                    }
                default:
                {
                    log.warn("no valid option selected");
                    System.out.println("Not valid option");
                }
            }
        }
    }
}