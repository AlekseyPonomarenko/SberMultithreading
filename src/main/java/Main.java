public class Main {
    public static void main(String[] args) {


        ThreadPoolImpl threadPool = new ThreadPoolImpl(5);
        threadPool.start();

        //Придеживаем
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Добавляем задачи1
        for (int i=0; i < 20; i++) {
            threadPool.execute(new QTask("Часть1 qtask(" + i + ")"));
        }

        //Придеживаем
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Добавляем задачи2
        for (int i=0; i < 20; i++) {
            threadPool.execute(new QTask("Часть2 qtask(" + i + ")"));
        }

        //Придеживаем
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.stop();

    }

}
