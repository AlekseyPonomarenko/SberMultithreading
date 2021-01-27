public class QTask implements Runnable{

    private String nameTask;

    public QTask(String nameTask) {
        this.nameTask=nameTask;
    }

    @Override
    public void run() {

       //System.out.println("Выполняется задача :" + nameTask + " ThreadGroup " +  Thread.currentThread().getThreadGroup().getName());

        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
    }

    public String getNameTask() {
        return nameTask;
    }
}
