package Threads.Seanca3;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Message msg = new Message();
        (new Thread(new Writer(msg))).start();
        (new Thread(new Reader(msg))).start();

    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized void write(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }

    public synchronized String read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = true;
        notifyAll();
        return message;

    }

}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String messages[] = {"Mesazhi i pare", "Mesazhi i dyte", "Mesazhi i trete", "Mesazhi i katert", "Fund"};
        Random rnd = new Random();

        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(rnd.nextInt(2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run() {
        Random rnd = new Random();
        for (String latestMessage = message.read(); !latestMessage.equals("Fund"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(rnd.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}
