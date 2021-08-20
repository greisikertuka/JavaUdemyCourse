package Threads.Seanca1;

public class U11 extends Thread{
    @Override
    public void run(){
       System.out.println(ThreadColor.ANSI_GREEN+"Jemi te threadi: "+currentThread().getName());
       try{
           Thread.sleep(2000);
       }
       catch(InterruptedException e){
           System.out.println("Nje thread tjeter na nxorri nga sleep");
           return;   //Perfundon
       }
       System.out.println("Kaluan 2 sekonda te Threadi 1!");
    }
}
