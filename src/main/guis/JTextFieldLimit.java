package main.guis;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class is used to set character limits on JTextFields. Found at this
 * link below:
 *
 * http://www.java2s.com/Code/Java/Swing-JFC/LimitJTextFieldinputtoamaximumlength.htm
 */
public class JTextFieldLimit extends PlainDocument {

    private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            if (limit == 4) {
                str = str.toUpperCase();
            }
            super.insertString(offset, str, attr);
        }
    }

    private static final long serialVersionUID = 1L;
}