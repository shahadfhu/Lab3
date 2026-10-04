import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(62000);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter website name: ");
        String host = sc.nextLine();

        byte[] data = host.getBytes();
        DatagramPacket request = new DatagramPacket(
            data, data.length, InetAddress.getByName("localhost"), 5300);
        socket.send(request);

        byte[] buf = new byte[1024];
        DatagramPacket response = new DatagramPacket(buf, buf.length);
        socket.receive(response);

        System.out.println("IP Address: " +
            new String(response.getData(), 0, response.getLength()));

        socket.close();
        sc.close();
    }
}
