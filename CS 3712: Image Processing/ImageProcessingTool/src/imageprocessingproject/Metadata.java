/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingproject;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.imageio.metadata.*;
import javax.swing.JTextArea;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Metadata {
   
    JTextArea textArea=null;
    
    Metadata(){
     textArea = new JTextArea();
    }
    

   public JTextArea readAndDisplayMetadata( String fileName ) {
        try {

            File file = new File( fileName );
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

            if (readers.hasNext()) {

                // pick the first available ImageReader
                ImageReader reader = readers.next();

                // attach source to the reader
                reader.setInput(iis, true);

                // read metadata of first image
                IIOMetadata metadata = reader.getImageMetadata(0);

                String[] names = metadata.getMetadataFormatNames();
                int length = names.length;
                for (int i = 0; i < length; i++) {
                    textArea.append( "\nFormat name: " + names[ i ] );
                    displayMetadata((Node) metadata.getAsTree(names[i]));
                }
            }
            
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        return textArea;
    }

    public  void displayMetadata(Node root) {
        displayMetadata(root, 0);
    }

    public void indent(int level) {
        for (int i = 0; i < level; i++)
             textArea.append("    ");
    }

    public void displayMetadata(Node node, int level) {
        // print open tag of element
       
        indent(level);
        textArea.append("\n <" + node.getNodeName());
        NamedNodeMap map = node.getAttributes();
        if (map != null) {

            // print attribute values
            int length = map.getLength();
            for (int i = 0; i < length; i++) {
                Node attr = map.item(i);
                textArea.append(" " + attr.getNodeName() +
                                 "=\"" + attr.getNodeValue() + "\"");
            }
        }

        Node child = node.getFirstChild();
        if (child == null) {
            // no children, so close element and return
             textArea.append("/>");
            return ;
        }

        // children, so close current tag
         textArea.append(">");
        while (child != null) {
            // print children recursively
            displayMetadata(child, level + 1);
            child = child.getNextSibling();
        }

        // print close tag of element
        indent(level);
         textArea.append("\n</" + node.getNodeName() + "> \n");
    
    
    }

   

    
}