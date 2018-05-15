import java.util.LinkedList;

public class URLPool{
    private LinkedList<URLDepthPair> foundURLs; // Найденные ссылки
    private LinkedList<URLDepthPair> rawURLs; // Ещё не обработанные ссылки
    private boolean isTerm=false;
    public int sleepingThr;

    URLPool(String url){
        rawURLs=new LinkedList<>();
        rawURLs.add(new URLDepthPair(url, 0));
        foundURLs=new LinkedList<>();
        sleepingThr=0;
    }

    public synchronized URLDepthPair fetch(){
        if(isTerm)
            return null;
        while (rawURLs.isEmpty()){
            try{
                sleepingThr++;
                wait();
                sleepingThr--;
            }
            catch(InterruptedException e){
                break;
            }
        }
        try {
            URLDepthPair pair = rawURLs.removeFirst();
            foundURLs.add(pair);
            return pair;
        }
        catch (Exception e) {
            return null;
        }
    }

    public synchronized void add(URLDepthPair p){
        if(rawURLs.contains(p)||foundURLs.contains(p))
            return;

        if (p.getDepth()>=Crawler.maxDepth){
            foundURLs.add(p);
            return;
        }

        rawURLs.add(p);
        notify();
    }

    public LinkedList<URLDepthPair> getPairList(){
        return foundURLs;
    }

    public void terminate(){
        isTerm=true;
    }

    public boolean isTerminate(){
        return isTerm;
    }

}