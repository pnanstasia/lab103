package ua.edu.ucu.apps;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TimeDocument implements Document {
    public String gcsPath;

    @Override
    public String parse() {
        SmartDocument smartDocument = new SmartDocument(gcsPath);
        long startTime = System.currentTimeMillis();
        smartDocument.parse();
        long endTime = System.currentTimeMillis();
        return Long.toString(endTime-startTime);
    }
}
