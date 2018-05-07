import java.net. *;
import java.io. *;
import java.util. *;

public class Crawler extends Thread{
    public static final String SEARCHING_PROTOCOL="http"; // Искомый протокол
    private LinkedList<URLDepthPair> foundURLs; // Найденные ссылки
    private LinkedList<URLDepthPair> rawURLs; // Ещё не обработанные ссылки
    private int port = 80;  // Порт
    private int maxDepth;   // Максимальная глубина
    private static final int PROTOCOL=0;
    private static final int HOST = 1;
    private static final int RESOURCE = 2;
    public static final String SOUGHT = "href=\""; // Ключевое слово для поиска ссылок

    // Конструктор
    Crawler(String URL, int depth){
        foundURLs=new LinkedList<>();
        rawURLs=new LinkedList<>();
        rawURLs.add(new URLDepthPair(URL, 0));
        maxDepth=depth;
    }

    public static void main(String[] args){
        // Проверяем количество аргументов
        if (args.length<2){
            System.out.println("Insufficient number of arguments.");
            return;
        }
        // Пересохраняем все ссылки
        String[] URLs = new String [args.length-1];
        for (int i=0; i<args.length-1;i++){
            URLs[i]=args[i];
        }
        // Сохраняем максимальную глубину
        int depth = Integer.parseInt(args[args.length-1]);
        // Создаём массив объектов
        Crawler[] crawler = new Crawler[URLs.length];
        for (int i=0; i<crawler.length;i++){
            crawler[i]=new Crawler(URLs[i], depth);
            crawler[i].start();
        }
    }

    // Функция потока
    public void run(){
        LinkedList<URLDepthPair> list = getSites(); // Получает список пар адрес-глубина
        while(!list.isEmpty()){
            System.out.println(list.removeFirst()); // Выводит этот список
        }
    }

    // Функция проверяет все ссылки из списка и находит следующие, если те не превышают глубину
    private LinkedList<URLDepthPair> getSites(){
        // Пока есть непроверенные сайты, удовлетворяющие условиям
        while(!rawURLs.isEmpty() && rawURLs.getFirst().getDepth()<=maxDepth){
            try{
                // Получаем первую ссылку из списка и "разбираем на части"
                String[] URLcomp = rawURLs.getFirst().parse();
                // Если протокол не http, то пропускаем
                if(!URLcomp[PROTOCOL].equalsIgnoreCase(SEARCHING_PROTOCOL)){
                    rawURLs.removeLast();
                    continue;
                }
                // Пересохраняем глубину
                int depth = rawURLs.getFirst().getDepth();
                // Создаём сокет
                Socket socket = new Socket(URLcomp[HOST], port);
                // Задаём время ожидания сокета
                socket.setSoTimeout(5000);

                // Получаем OutputStream, связанный с сокетом
                OutputStream outStream = socket.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outStream, true);
                // Отправляем сообщение серверу
                pWriter.println("GET "+URLcomp[RESOURCE]+" HTTP/1.1");
                pWriter.println("Host: "+URLcomp[HOST]);
                pWriter.println("Connection: close");
                pWriter.println();
                // Получаем InputStream, связанный с сокетом
                InputStream in = socket.getInputStream();
                // Делаем магию, чтобы получить весь текст целиком в bu
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader bu = new BufferedReader(inReader);

                // Читаем первую строку ответа
                String line = bu.readLine();
                // Пока строка не нулевая...
                while (line!=null){
                    // ... и пока содержит ссылку
                    while(line.contains(SOUGHT)){
                        // Находим индекс начала SOUGHT (то есть слова href=")
                        int start = line.indexOf(SOUGHT);
                        // Обрезаем строку сначала вместе с искомым словом
                        line = line.substring(start+SOUGHT.length());
                        // Сохраняем непосредственно ссылку в дргую переменную
                        String subline = line.substring(0,line.indexOf("\""));
                        // Создаём пару адрес-глубина и, если она ещё не фигурировала в списках, сохраняем
                        URLDepthPair pair = new URLDepthPair(subline, depth+1);
                        if (!rawURLs.contains(pair)&& !foundURLs.contains(pair))
                            rawURLs.add(pair);
                    }
                    // Читаем следующую строку
                    line=bu.readLine();
                }
                // Переносим ссылку из списка необработанных в список обработанных
                foundURLs.add(rawURLs.getFirst());
                rawURLs.removeFirst();
                // Закрываем сокет
                socket.close();
            }
            // Если не получилось проанализировать ссылку
            catch(IOException e){
                // Выводим соответствующее сообщение
                System.out.println("Unable to connect");
                // И переносим ссылку из списка в список 
                foundURLs.add(rawURLs.getFirst());
                rawURLs.removeFirst();
            }
        }
        // Возвращаем список найденных ссылок
        return foundURLs;
    }
}