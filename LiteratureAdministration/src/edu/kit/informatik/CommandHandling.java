package edu.kit.informatik;


import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Handles commands for the bibliography.
 * 
 * @author Frithjof Marquardt
 * @version 1.00, 10.02.2017
 */
public class CommandHandling<T, C extends CommandHandling.Command<T>> {
    private T target;
    private C[] commands;
    
    /**
     * Creates new CommandHandling object.
     * 
     * @param target the target system of the commands
     * @param commands the commands available for the target system
     */
    public CommandHandling(T target, C[] commands) {
        this.target = target;
        this.commands = commands;
    }
    
    /**
     * Checks whether provided String matches the pattern of a command 
     * and eventually calls the method to perform the command.
     * 
     * @param string the string
     * 
     * @return the matching command or null
     */
    public C accept(String string) {
        Matcher m;
        try {
            for (C c : commands) {
                m = c.pattern().matcher(string);
                if (m.matches()) {
                    c.apply(target, m.group(2));
                    return c;
                }
            }
            if (!string.equals("")) throw new NoSuchElementException("this command doesn't exist.");
        } catch (NoSuchElementException e) {
            Terminal.printError(e.getMessage());
        } catch (PatternSyntaxException e) {
            Terminal.printError(e.getMessage());
        } catch (IllegalArgumentException e) {
            Terminal.printError(e.getMessage());
        }
        
        return null;
    }
    
    
    public interface Command<T> {
        /**
         * Return the pattern of the command.
         * 
         * @return the pattern
         */
        Pattern pattern();
        
        /**
         * Implement the command's task.
         * 
         * @param target the target system of the command
         * @param string the command
         * 
         * @throws IllegalArgumentException if the string is not valid for this command
         */
        void apply(T target, String string) throws IllegalArgumentException;
        
        /**
         * Matches the input String against the provided pattern and after success returns an array of Strings 
         * with the individual tokens (parameters) of the input.
         * 
         * @param regex the pattern to match
         * @param input the input String
         * 
         * @return the parameters
         * @throws IllegalArgumentException if the number of tokens does not match the expected one
         */
        String[] matchParameters(String regex, String input) throws IllegalArgumentException;
    }
    
    
    
    
    
    
    
    
    
    
    
 /*   
    private static Bibliography bibliography;
    private static boolean isQuit;
    
    /**
     * Starts the program.
     * 
     * @param args arguments of command line.
     */
  /*  public static void main(String[] args) {
        isQuit = false;
        do {
            analyseCommand(Terminal.readLine());
        } while (!isQuit);
    }
    
    /**
     * Checks if provided String is a valid command and calls the according method.
     * 
     * @param command input command
     */
  /*  private static void analyseCommand(String command) {
        String[] commandParts = command.split(" ");
        
        if (commandParts[0].equals("quit")) {
            Terminal.printLine("Ok");
            isQuit = true;
        } else if (commandParts[0].equals("add")) {
            if (commandParts[1].equals("author")) {
                
            }
        } else if (commandParts[0].equals("written-by")) {
            
        } else if (commandParts[0].equals("cites")) {
            
        } else if (commandParts[0].equals("all")) {
            
        } else if (commandParts[0].equals("publications")) {
            
        } else if (commandParts[0].equals("in")) {
            
        } else if (commandParts[0].equals("find")) {
            
        } else if (commandParts[0].equals("jaccard")) {
            
        } else if (commandParts[0].equals("similarity")) {
            
        } else if (commandParts[0].equals("direct")) {
            
        } else if (commandParts[0].equals("h-index")) {
            
        } else if (commandParts[0].equals("coauthors")) {
            
        } else if (commandParts[0].equals("foreign")) {
            
        } else if (commandParts[0].equals("print")) {
            
        } else {
            
        }
    }
    */
}
