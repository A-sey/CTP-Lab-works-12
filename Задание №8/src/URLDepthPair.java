import java.net.MalformedURLException;

public class URLDepthPair{
    private String URL;
    private int depth;

    /**
     * @param url - add URL
     * @param d - depth of the URL
     */
    URLDepthPair(String url, int d){
        URL=url;
        depth=d;
    }

    /**
     * @return - address-depth pair
     */
    public String toString(){
        return "URL: "+URL+"; Depth: "+depth;
    }

    /**
     * @param object - compared UDP
     */
    public boolean equals(Object object){
        URLDepthPair UDP = (URLDepthPair) object;
        return URL.equals(UDP.URL);
    }

    public int getDepth(){
        return depth;
    }

    /**
     * @return - array of the string containing parts of the URL
     */
    public String[] parse(){
        // Создаём массив из трёх строк
        String[] comp = new String[3];
        comp[0]=comp[1]=comp[2]="";
        try{
            java.net.URL url = new java.net.URL(URL);
            // Сохраняем протокол URL
            comp[0]=url.getProtocol();
            // Сохраняем хост
            comp[1]=url.getHost();
            // Сохраняем оставшуюся часть адреса
            comp[2]=url.getPath();
        }
        catch(MalformedURLException e){
            System.out.println("Parsing error");
        }
        //Возвращаем массив строк
        return comp;
    }

    public  String getURL(){
        return URL;
    }
}