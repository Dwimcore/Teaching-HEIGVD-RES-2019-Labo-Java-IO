package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {
  int cpt = 1;
  String strLineNr = "";
  boolean firstLine = true;
  boolean lastwasr = false;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    if(firstLine) {
      strLineNr = String.valueOf(cpt++);
      super.out.write(strLineNr, 0, strLineNr.length());
      super.out.write('\t');
      firstLine = false;
    }

    for (int i = off; i < off + len; i++){
      if(cbuf[i] == '\n' || (cbuf[i] == '\r')){
        super.out.write(cbuf[i]);
        if(!(i+1 < len && cbuf[i+1]=='\n')) {
          strLineNr = String.valueOf(cpt++);
          super.out.write(strLineNr, 0, strLineNr.length());
          super.out.write('\t');
        }
      } else {
        super.out.write(cbuf[i]);
      }
      lastwasr = (cbuf[i] == '\r');
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
      if(firstLine) {
        strLineNr = String.valueOf(cpt++);
        super.out.write(strLineNr, 0, strLineNr.length());
        super.out.write('\t');
        firstLine = false;
      }
      super.out.write(c);
      if(c == '\n'){
        firstLine = true;
      }
  }

}
