/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingproject;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author linganesan
 */
public abstract class ImageDecoder {

    public boolean canDecode(File file) throws IOException {
        try {
            DataInputStream fin = new DataInputStream(new FileInputStream(file));
            String mw = fin.readUTF();
            fin.close();
            return (mw != null && mw.equals(getMagicWord()));
        } catch (Exception e) {
            return false;
        }
    }

    public BufferedImage decode(File f) throws FileNotFoundException, IOException {
        FileInputStream fin = new FileInputStream(f);
        BufferedImage result = decode(fin);
        fin.close();
        return result;
    }

    public abstract String getMagicWord();

    public abstract BufferedImage decode(InputStream is);

}
