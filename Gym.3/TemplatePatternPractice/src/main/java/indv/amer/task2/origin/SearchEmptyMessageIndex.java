package indv.amer.task2.origin;

public class SearchEmptyMessageIndex {
    public int search(String[] messages) {
        int index = 0;
        while (index < messages.length && !messages[index].isEmpty()) {
            System.out.println(messages[index]);
            index ++;
        }
        return index >= messages.length ? -1 : index;
    }
}
