package ch.heigvd.res.labio.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String[] result = new String[2];
    result[0] = new String("");
    boolean containsLineSep = true;
    List<String> t;
    String separator ="";
    System.out.print("Le separateur est : ");


    if(lines.contains("\n\r")){
      separator = "\n\r";
      System.out.println("win");
    } else if(lines.contains("\n")){
      separator = "\n";
      System.out.println("nux");
    } else if(lines.contains("\r")){
      separator = "\r";
      System.out.println("mac");
    } else {
      System.out.println("aucun");
      containsLineSep = false;
    }

    t = new ArrayList<>(Arrays.asList(lines.split(separator)));

    if(containsLineSep) {
      result[1] = new String("");
      result[0] = (t.get(0)+separator);
      t.remove(0);
      for(String s : t){
        result[1] = result[1].concat(s+separator);
      }
    }
    else
      result[1] = lines;

    return result;
  }

}
