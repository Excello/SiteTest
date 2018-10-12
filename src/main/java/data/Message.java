package data;

import utils.Environment;

public class Message {
    private String headline;
    private String text;

    public Message(String headline, String text) {
        this.headline = headline;
        this.text = text;
    }

    public static Message createRandom(){
        String randomHeadline = "headline" + Environment.generateUniqueString();
        String randomText = "text" + Environment.generateUniqueString();
        return new Message(randomHeadline, randomText);
    }

    public String getHeadline() {
        return headline;
    }

    public String getText() {
        return text;
    }
}
