import java.net.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5300);
        System.out.println("DNS Server running on port 5300...");

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket request = new DatagramPacket(buf, buf.length);
            socket.receive(request);

            String host = new String(request.getData(), 0, request.getLength()).trim();
            String reply;
            try {
                reply = InetAddress.getByName(host).getHostAddress();
            } catch (UnknownHostException e) {
                reply = "Unknown host";
            }

            byte[] out = reply.getBytes();
            DatagramPacket response = new DatagramPacket(
                out, out.length, request.getAddress(), request.getPort());
            socket.send(response);
        }
    }
}
