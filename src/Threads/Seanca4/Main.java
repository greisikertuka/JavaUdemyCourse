package Threads.Seanca4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        MyProducer producer=new MyProducer(buffer,ThreadColor.ANSI_BLUE);
        MyConsumer consumer1=new MyConsumer(buffer,ThreadColor.ANSI_GREEN);
        MyConsumer consumer2=new MyConsumer(buffer,ThreadColor.ANSI_CYAN);
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}

class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        Random rnd = new Random();
        String[] nr = {"1", "2", "3", "4", "5"};

        for (String num : nr) {
            try {
                System.out.println(color + "Adding....." + num);
                buffer.add(num);

                Thread.sleep(rnd.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting");
        buffer.add("EOF");
    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        while (true) {
            synchronized (buffer){
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals("EOF")) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removing..." + buffer.remove(0));
                }
            }

        }
    }
}
