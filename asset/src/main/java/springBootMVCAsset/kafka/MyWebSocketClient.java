package springBootMVCAsset.kafka;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient extends WebSocketClient {
   public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }
   public static void main(String[] args) {
      URI uri;
      try {
         uri = new URI("ws://172.16.110.141:8888");
         MyWebSocketClient client = new MyWebSocketClient(uri);
         client.connect();
      } catch (URISyntaxException e) {
         e.printStackTrace();
      }
   }
   @Override
   public void onOpen(ServerHandshake handshakedata) {
      System.out.println("Connected to server");
      new Thread(()->{
         Scanner sc = new Scanner(System.in);
         String message = sc.nextLine();
         while(true) {
           
        	 send(message);
            
             
         }
      }).start();
   }
   @Override
   public void onMessage(String message) {
      System.out.println("Received message: " + message);
   }
   @Override
   public void onClose(int code, String reason, boolean remote) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void onError(Exception ex) {
      // TODO Auto-generated method stub
      
   }
   
}