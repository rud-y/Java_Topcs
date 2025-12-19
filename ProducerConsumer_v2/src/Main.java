package src.com.rz;

import java.io.Reader;
import java.io.StringReader;
import java.util.Random;

class MessageRepository {

    private String message;
    private boolean hasMessage = false;

    // read method
    public synchronized String read() {
        while(!hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasMessage = false;
//        return message;
        String tempMessage = message; // local variable for safety
        notifyAll(); // Notify the writer that the message slot is empty
        return tempMessage;
    }

    // write method
    public synchronized void write(String message) {
        while(hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasMessage = true;
        notifyAll(); // Notify reader message is ready
        this.message = message;
    }
}

// MessageWriter
class MessageWriter implements Runnable {
    private MessageRepository outgoingMessage;

    private final String text = """
            Humpty dumpty sat on a wall
            Humpty dumpty had a great fall
            All the king's horses and all the king's men,
            couldn't put Humpty together again""";

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500, 1000));

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        outgoingMessage.write("FINISHED!");
    }
}

// MessageReader
class MessageReader implements Runnable {

    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500,1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("FINISHED!"));
    }

}


// MAIN
public class Main {

    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));

        reader.start();
        writer.start();
    }
}
