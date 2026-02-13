package AirlineSystem;

public class BackupDaemon extends Thread {
    public void run() {
        try {
             {
                System.out.println("[Daemon] Auto backup running...");
                Thread.sleep(15000); 
            }
        } catch (InterruptedException e) {
           
        }
    }
}