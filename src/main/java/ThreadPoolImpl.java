
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPoolImpl implements ThreadPool{

    private ConcurrentLinkedQueue listOfTask = new ConcurrentLinkedQueue();
    private int countThreads;
    private List<Thread> threads;

    public ThreadPoolImpl() {
        threads = new ArrayList <>();
    }

    public ThreadPoolImpl(int countThreads) {
        this();
        this.countThreads=countThreads;
    }

    @Override
    public void start() {

       if (threads.size()>0){
            throw new IllegalArgumentException("Повторный старт потока!");
        }

        for (int i=0; i < countThreads; i++) {
            Thread thread =  new Thread(new ThreadForPool(listOfTask), "ThreadForPool № " + i);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        listOfTask.add(runnable);
    }

    @Override
    public void stop() {
        for (Thread thread : threads){
            thread.interrupt();
        }
    }

    public class ThreadForPool implements Runnable {

        private ConcurrentLinkedQueue<Runnable> listOfTask;

        public ThreadForPool(ConcurrentLinkedQueue listOfTask) {
            this.listOfTask=listOfTask;
        }

        @Override
        public void run() {

            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try{
                while(!Thread.currentThread().isInterrupted()){

                    Runnable qt = listOfTask.poll();
                    if (qt!=null){

                        //для информации
                        QTask qTask = (QTask) qt;
                        System.out.println(Thread.currentThread().getName() + " запустил " + qTask.getNameTask());

                        Thread th = new Thread(qt);
                        th.run();
                        th.join();

                    }else{
                        Thread.sleep(100);
                    }
                }
            }

            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() +  " был прерван!" );
            }
        }
    }


}
