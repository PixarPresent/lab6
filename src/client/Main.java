package client;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            UDPClient client = new UDPClient("localhost", Integer.parseInt(args[0]));
            client.start();
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
