import java.io.*;
import java.net.Socket;
public class CrawlerTask implements Runnable{
    private int port = 80;  // Порт
    private static final String PROT = "http"; // Искомый протокол
    private static final int HOST = 1;
    private static final int RESOURCE = 2;
    public static final String SOUGHT = "href=\""; // Ключевое слово для поиска ссылок
    private URLPool urlpool;

    CrawlerTask(URLPool p){
        urlpool=p;
    }

    public void run(){
        while(!urlpool.isTerminate()){
            try{
                URLDepthPair pair=urlpool.fetch();
                if(pair!=null){
                    // Получаем первую ссылку из списка и "разбираем на части"
                    String[] URLcomp = pair.parse();
                    // Пересохраняем глубину
                    int depth = pair.getDepth();
                    // Создаём сокет
                    Socket socket = new Socket(URLcomp[HOST], port);
                    // Задаём время ожидания сокета
                    socket.setSoTimeout(5000);

                    // Получаем OutputStream, связанный с сокетом
                    OutputStream outStream = socket.getOutputStream();
                    PrintWriter pWriter = new PrintWriter(outStream, true);
                    // Отправляем сообщение серверу
                    pWriter.println("GET "+pair.getURL()+" HTTP/1.1");
                    pWriter.println("Host: "+URLcomp[HOST]);
                    pWriter.println("Connection: close");
                    pWriter.println();
                    // Получаем InputStream, связанный с сокетом
                    InputStream in = socket.getInputStream();
                    // Получаем весь текст целиком в bu
                    InputStreamReader inReader = new InputStreamReader(in);
                    BufferedReader bu = new BufferedReader(inReader);

                    // Читаем первую строку ответа
                    String line = bu.readLine();
                    // Пока строка не нулевая...
                    while (line!=null){
                        //System.out.println(line);
                        // ... и пока содержит ссылку
                        while(line.contains(SOUGHT+PROT)){
                            // Находим индекс начала SOUGHT (то есть слова href=")
                            int start = line.indexOf(SOUGHT);
                            // Обрезаем строку сначала вместе с искомым словом
                            line = line.substring(start+SOUGHT.length());
                            // Сохраняем непосредственно ссылку в дргую переменную
                            String subline;
                            try {
                                subline = line.substring(0, line.indexOf("\""));
                                urlpool.add(new URLDepthPair(subline, depth + 1));
                            }
                            catch (Exception e){
                                continue;
                            }
                        }
                        // Читаем следующую строку
                        line=bu.readLine();
                    }
                    // Закрываем сокет
                    socket.close();
                }
            } catch(IOException e){
                System.out.println("Unable to connect");
            }
        }
    }
}