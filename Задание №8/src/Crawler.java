import java.util. *;

public class Crawler{
    public static int maxDepth;   // Максимальная глубина

    public static void main(String[] args){
        // Проверяем количество аргументов
        if (args.length<3){
            System.out.println("Insufficient number of arguments.");
            return;
        }
        URLPool urlpool = new URLPool(args[0]);
        // Сохраняем максимальную глубину
        maxDepth = Integer.parseInt(args[1]);
        int threads = Integer.parseInt(args[2]);
        Thread[] mThreads = new Thread[threads];

        for(int i=0;i<threads;i++){
            CrawlerTask crTask = new CrawlerTask(urlpool);
            mThreads[i]= new Thread(crTask, "Crawler "+i);
            mThreads[i].start();
        }
        while(urlpool.sleepingThr<threads){
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){
                break;
            }
        }
        urlpool.terminate();
        for(int i=0; i<threads;i++){
            mThreads[i].interrupt();
        }
        LinkedList<URLDepthPair> pairs = urlpool.getPairList();
        while(!pairs.isEmpty())
            System.out.println(pairs.removeFirst());
    }
}