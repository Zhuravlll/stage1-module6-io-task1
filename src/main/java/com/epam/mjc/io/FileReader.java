package com.epam.mjc.io;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class FileReader {
    public Profile getDataFromFile(File file) {
        String filePath = file.getAbsolutePath();
        try {
            return printFileWithBuffer(filePath);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Profile printFileWithBuffer(String path) throws FileNotFoundException {
        Map<String, String> temp = new LinkedHashMap<>();
        String[] tempArr = new String[2];
            try (var br = new BufferedReader(new java.io.FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempArr = (line.split(": "));
                temp.put(tempArr[0], tempArr[1]);
            }
        } catch (IOException e) {
                throw new FileNotFoundException();
            }
        return new Profile(temp.get("Name"), Integer.parseInt(temp.get("Age")), temp.get("Email"), Long.parseLong(temp.get("Phone")));
    }
}
