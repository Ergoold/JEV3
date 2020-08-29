package jev3.rsf;

import jev3.exceptions.FileFormatException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.io.*;

/**
 * A class containing static convenience methods for decoding sound files in RSF format.
 */
public class RsfReader {

    /**
     * Constructor is private to prevent instantiation.
     */
    private RsfReader() {
    }

    /**
     * Decodes the input stream as an RSF sound file into an {@code AudioInputStream}.
     *
     * @param stream the input stream
     * @return an {@code AudioInputStream} decoded from the input stream
     * @throws IOException if an error occurs during reading, or if the file is not in RSF format
     */
    public static AudioInputStream read(InputStream stream) throws IOException {
        if (stream.read() != 0x01) throw new FileFormatException("Header in RSF file is incorrect");
        if (stream.read() != 0x00) throw new FileFormatException("Header in RSF file is incorrect");
        int length = stream.read();
        length = length * 256 + stream.read();
        int sampleRate = stream.read();
        sampleRate = sampleRate * 256 + stream.read();
        if (stream.read() != 0x00) throw new FileFormatException("Header in RSF file is incorrect");
        if (stream.read() != 0x00) throw new FileFormatException("Header in RSF file is incorrect");
        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, true);
        return new AudioInputStream(stream, format, length);
    }

    /**
     * Decodes the file as an RDF sound file into an {@code AudioInputStream}.
     *
     * @param file the RSF sound file
     * @return an {@code AudioInputStream} decoded from the file
     * @throws IOException if an error occurs during reading, or if the file is not in RSF format
     */
    public static AudioInputStream read(File file) throws IOException {
        return read(new FileInputStream(file));
    }
}
