package chatrpc;


import java.util.ArrayList;
import org.apache.xmlrpc.*;


public class Serveur {
	
	private ArrayList<String> references = new ArrayList<String>();
        private String message = null;
	public Serveur() {
	}
         public boolean subscribe(String pseudo) {
             if(references.contains(pseudo)){
                 return false;
             }
            references.add(pseudo);
            this.message = pseudo +" connected";
            System.err.println(pseudo +" connected");
            return true;
        }

        public boolean unsubscribe(String pseudo) {
            references.remove(pseudo);
            System.out.println(pseudo+" disconected");
            return true;
        }

        public String postMessage(String pseudo, String message) {
            
            this.message = pseudo +"---"+message ;
            System.out.println(this.message);
            return this.message;
        }

        public String getMessage(){
            return this.message;
        }

	public static void main(String args[]) {
            try { 
                System.out.println("Attempting to start XML-RPC Server...");
                WebServer webServer = null ;
                if (webServer == null) { 
                    webServer = new WebServer(8090);
                    webServer.addHandler("sample", new Serveur());
                    webServer.start(); 
                    System.out.println("Started successfully.");
                    System.out.println("Accepting requests. (Halt program to stop.)");
                } 
                
            }
            catch (Exception exception){ 
                System.err.println("JavaServer: " + exception); 
            }
	}

    
   
}