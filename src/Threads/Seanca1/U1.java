package Threads.Seanca1;

public class U1 {
    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_BLUE+"Jemi te threadi main");
        Thread thread1=new U11();
        thread1.setName("Thread 1");
        //thread1.run();    //Nuk printohet Thread 2, por main pasi kur thirrim  metoden run behet run te maini (threadi aktual)
        thread1.start();
        //thread1.start();   Do kishte Exception,pasi nuk lejohet te behet run 2 here

        new Thread(){          //Kjo eshte Anonymous Class
            public void run(){
                System.out.println(ThreadColor.ANSI_PURPLE+"Hello nga annonymous classs qe sapo krijuam");
                try{
                    thread1.join();   //pritet sa te mbaroje threadi 1,pastaj ekzekutohet threadi 2
                    //thread1.join(2000);    //do  printoheshin mesazhet ne te njejten kohe,pasi presim 2 sekonda dhe eksekutohet rreshti poshte
                    System.out.println(ThreadColor.ANSI_RED+"Thread 1 mbaroi,po behet run perseri threadi te annonymous class");

                }
                catch(InterruptedException e){
                    System.out.println("U pengua threadi");
                }
            }
        }.start();
        System.out.println(ThreadColor.ANSI_CYAN+"Jemi te main threadi perseri");
        Thread runnableThread=new Thread(new U2Runnable());
        Thread runnableThread1=new Thread(new U2Runnable(){
            @Override
            public void run(){
                System.out.println(ThreadColor.ANSI_BLACK+"I bejme override run te U2Runnable");
            }
        });
        runnableThread.start();
        //thread1.interrupt();  //e bejme interrupt
        runnableThread1.start();

    }
}
