package server.managers;

import server.exceptions.RootException;
import server.filelogic.ReaderCSV;
import server.filelogic.WriterCSV;

import java.io.*;

public class FileManager {
    public static void readFromPath(String path) throws Exception {
        ReaderCSV.read(path);
    }

    public static void writeToPath(String path) throws IOException, RootException {
        WriterCSV.write(path);
    }
}
