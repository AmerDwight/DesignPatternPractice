package indv.amer.task2.origin;

public class SearchLongestMessage {
    public String search(String[] messages) {
        String maxLengthMessage = "";
        for (String message : messages) {
            if (message.length() > maxLengthMessage.length()) {
                maxLengthMessage = message;
            }
            System.out.println(message);
        }
        return maxLengthMessage;
    }
}