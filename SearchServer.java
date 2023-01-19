import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class SearchHandler implements URLHandler {

    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String res = "List: ";
            for (String elem : list) {
                res += elem + " ";
            }
            return res;
        }

        else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list.add(parameters[1]);
                return parameters[1] + " added to the list!";
            }
        }

        else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String res = "Matches: ";
                for(String elem : list) {
                    if (elem.contains(parameters[1])) {
                        res += elem + " ";
                    }
                }
                return res;
            }
        }

        return "404 Not Found";


    }
}

public class SearchServer {
    
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchHandler());
    }
}
