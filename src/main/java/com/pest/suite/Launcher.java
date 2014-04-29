package com.pest.suite;

/**
 * This class constitutes the main entry point for PESTSuite.
 * 
 *
 */

public class Launcher {

 public static String version = "1.0";
 String[] rawArgs;
 
 /**
  * Entry point.  Creates a Launcher instance and activates it.
  * @param args
  */
 public static void main(String[] args) {

  System.out.println("PESTSuite v" + version);
  new Launcher(args);
 }
 
 
 public Launcher(String[] args) {
  rawArgs = args;
 }
 
 public boolean processArgs(String[] args) {
  
  boolean result = true;
  
  String usageMessage = getUsageMessage();
  
  if (args.length == 0) {
   System.out.println(usageMessage);
   result = false;
  }
  
  return result;
 }
 
 public String getUsageMessage() {
  return "Usage: java -jar pestsuite-1.0-SNAPSHOT.jar";
 }

}
