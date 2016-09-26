/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingproject;

import java.awt.image.BufferedImage;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author linganesan
 */
public abstract class ImageEncoder {

    public void writeHeader(BufferedImage image, DataOutput output) throws IOException {
        output.writeUTF(getMagicWord());
        output.writeShort((short) image.getWidth());
        output.writeShort((short) image.getWidth());
        output.writeInt(image.getType());
    }

    public void encode(BufferedImage image, File f) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(f);
        encode(image, fout);
        fout.close();
    }

    public abstract String getMagicWord();

    public abstract void encode(BufferedImage image, OutputStream os);

}
