package data;

import utils.Environment;

public class Message {
    private String headline;
    private String text;

    public Message() {
        this.headline = createRandom();
        this.text = createRandom();
    }

    private static String createRandom(){
        return Environment.generateUniqueString();
    }

    public String getHeadline() {
        return headline;
    }

    public String getText() {
        return text;
    }
}
