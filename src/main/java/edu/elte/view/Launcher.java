/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.view;

import edu.elte.client.QueryProcessor;

/**
 *
 * @author Xavier
 */
public class Launcher {

    /**
     * @param args
     */
    public static void main(String[] args)  {
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
                        new QueryProcessor().consumeAll();
                        break;
                    }
                case "dboperations":
                    {
                        new QueryProcessor().regenerateDb();
                        break;
                    }
                default:
                {
                    System.out.println("Not valid option");
                }
            }
        }
    }
}