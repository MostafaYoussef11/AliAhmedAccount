/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.Completions;
import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author mosta
 */
public class Autocomplete implements DocumentListener{
    private static enum mode{
        INSERT,
        COMPLETION  //COMPLETION
    };
    private JTextField textField;
    private final List<String> keywords;
    private mode mod = mode.INSERT;

    public Autocomplete(JTextField textField, List<String> keywords) {
        this.textField = textField;
        this.keywords = keywords;
        Collections.sort(keywords);
    }
    
    
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        if(e.getLength() != 1) return;
        int pos = e.getOffset();
        String content = null;
        try {
            content = textField.getText(0, pos+1);
        } catch (BadLocationException ex) {
            Logger.getLogger(Autocomplete.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Find where the word Starts
        int w ;
        for(w = pos ; w >= 0 ; w--){
            if(!Character.isLetter(content.charAt(w))){
                break;
            }
        }
        if(pos - w < 2) return;
        String prefix;
        prefix = content.substring(w+1).toLowerCase();
        int n = Collections.binarySearch(keywords, prefix);
        
        if( n<0 && -n <= keywords.size()){
            String match = keywords.get(-n-1);
            if(match.startsWith(prefix)){
                //a compliton is found 
                String completion = match.substring(pos-w);
                // We cannot modify Document from within notification,
                // so we submit a task that does the change later
                SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
            
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {}

    @Override
    public void changedUpdate(DocumentEvent e) {}

 public class CommitAction extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = 5794543109646743416L;

    @Override
    public void actionPerformed(ActionEvent ev) {
      if (mod == mode.COMPLETION) {
        int pos = textField.getSelectionEnd();
        StringBuffer sb = new StringBuffer(textField.getText());
        sb.insert(pos, " ");
        textField.setText(sb.toString());
        textField.setCaretPosition(pos + 1);
        mod = mode.INSERT;
      } else {
        textField.replaceSelection("\t");
      }
    }
  }   
 public class CompletionTask implements Runnable {
    private String completion;
    private int position;
    public CompletionTask(String completion, int position) {
        this.completion = completion;
        this.position = position;
    }

    @Override
    public void run() {
        StringBuffer sb = new StringBuffer(textField.getText());
        sb.insert(position, completion);
        textField.setText(sb.toString());
        textField.setCaretPosition(position + completion.length());
        textField.moveCaretPosition(position);
        mod = mode.COMPLETION;
        
    }
    
 } 
}

