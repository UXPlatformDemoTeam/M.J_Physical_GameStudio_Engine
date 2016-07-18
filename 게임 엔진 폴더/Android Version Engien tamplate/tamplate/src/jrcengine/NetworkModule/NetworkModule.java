
package jrcengine.NetworkModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import jrcengine.Basic.GL_Game;
import jrcengine.Basic.GL_Screen;
import jrcengine.MainGame.Screen_MainGame;
import jrcengine.Manage.Manage_Assets;
import android.util.Log;

public class NetworkModule {

    private static Thread thread;

    private static Socket socket;

    private static Timer msTimer;

    private static TimerTask msSecond;

    private static OutputStream outputStream;

    private static String id = "test";

    private static String password = "1";

    private static GL_Screen glMainGame;

    public static void startClient() {

        if (socket != null)
            return;

        thread = new Thread() {
            @Override
            public void run() {

                Log.d("mobile Network Module", "start success");
                try {
                    socket = new Socket();
                    // set the connection first argument is the server IP
                    // address and next is port number
                    socket.connect(new InetSocketAddress("192.168.0.2", 5001));

                    msTimer = new Timer();
                    msSecond = new TimerTask() {
                        public void run() {

                            sendPacket(1, Manage_Assets.NetworkProtocol._ACCESS_THE_SERVER + "");

                        }
                    };

                    // this thread run per 7seconds
                    msTimer.schedule(msSecond, 0, 5000);

                } catch (Exception e) {

                    System.err.println("you cannot connect the server");
                    if (!socket.isClosed()) {
                        stopClient();
                    }

                }

                sendPacket(3, Manage_Assets.NetworkProtocol._REQUEST_MOBILE_LOGIN + "", id,
                        password);

                receive();

            }
        };
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

    }

    public static void setglMainGame(GL_Screen gl_game) {
        glMainGame = gl_game;
    }

    public static void receive() {

        while (true) {

            try {

                byte[] byteArr = new byte[100];
                InputStream inputStream = socket.getInputStream();
                // 서버가 비정상적으로 종료했을 경우 IOException 발생

                int readByteCount = inputStream.read(byteArr); // 서버가 정상적으로
                // Socket의 close()를 호출했을 경우
                if (readByteCount == -1) {
                    throw new IOException();
                }

                String data = new String(byteArr, 0, readByteCount, "UTF-8");
            //    Log.d("mobile Network Module", data);

                /*
                 * processing all packet using next method for splitting the
                 * packet
                 */
                String[] multiplePackets = splitMultiplePacket(decrypt(data));

                for (int i = 0; i < multiplePackets.length; i++) {

                    /*
                     * processing all packet particle using next method for
                     * splitting the packet
                     */
                    String[] splitPacket = splitProtocol(multiplePackets[i]);

                    int protocol = Integer.parseInt(splitPacket[0]);

                    switch (protocol) {
                        case Manage_Assets.NetworkProtocol._ANSWER_LOGIN:
                            Log.d("mobile Network Module", "Login success id:" + id + " password:"
                                    + password);
                            break;

                        case Manage_Assets.NetworkProtocol._ANSWER_MOBILE_MAKE_GAME_UNIVERSE:

                            ((Screen_MainGame)glMainGame).getManage().addAsteroid(
                                    Float.parseFloat(splitPacket[1]),
                                    Float.parseFloat(splitPacket[2]));
                            break;

                        default:
                            System.err.println("[" + protocol
                                    + "] this protocol do not exist in the protocol list");
                            break;

                    }
                }

            } catch (Exception e) {
                System.err.println("[" + e + "] you cannot connect the server - receiving Error");
                stopClient();
                break;

            }

        }

    }

    /**
     * split the packet to each protocol.
     * 
     * @param packet
     * @return
     */
    public static String[] splitMultiplePacket(String packet) {
        int flag;
        int packetNumber = 0;
        String subPacket;

        packetNumber = packet.split("@").length;

        String _partitioningPacket[] = new String[packetNumber];
        try {
            for (int i = 0; i < packetNumber; i++) {

                flag = packet.indexOf("@");
                subPacket = packet.substring(0, flag);
                _partitioningPacket[i] = subPacket;
                if (packetNumber > 1)
                    packet = packet.substring(flag + 1, packet.length());
            }

            return _partitioningPacket;
        } catch (Exception e) {
            System.err.println(e + " occured Error");
            return null;
        }
    }

    /**
     * split the protocol to each token
     * 
     * @param packet
     * @return
     */
    public static String[] splitProtocol(String packet) {
        int flag = packet.indexOf("/");
        String subPacket = packet.substring(flag + 1, packet.length());
        packet = packet.substring(flag + 1, packet.length());

        flag = subPacket.indexOf("/");
        subPacket = subPacket.substring(0, flag);
        int packetTokenLength = Integer.parseInt(subPacket);

        packet = packet.substring(flag + 1, packet.length());

        String _partitioningPacket[] = new String[packetTokenLength];

        try {
            for (int i = 0; i < packetTokenLength; i++) {

                flag = packet.indexOf("/");

                subPacket = packet.substring(0, flag);
                _partitioningPacket[i] = subPacket;
                packet = packet.substring(flag + 1, packet.length());
            }

            return _partitioningPacket;
        } catch (Exception e) {
            System.err.println(e + " occured Error");
            return null;
        }
    }

    /**
     * send message to the game server
     * 
     * @param partitionPacketNumber
     * @param datas
     */
    public static void sendPacket(int partitionPacketNumber, String... datas) {
        String packet = new String();

        packet = packet.concat("/" + partitionPacketNumber + "/");

        for (int i = 0; i < partitionPacketNumber; i++) {
            packet = packet.concat(datas[i] + "/");
        }
        send(packet);
    }

    /**
     * send packet
     * 
     * @param data
     */
    public static void send(final String data) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    byte[] byteArr = encrypt(data).getBytes("UTF-8");
                    outputStream = socket.getOutputStream();
                    outputStream.write(byteArr);
                    outputStream.flush();
                    // System.out.println("[보내기 완료]");
                } catch (Exception e) {
                    System.err.println("[서버 통신 안됨] : " + e);
                    stopClient();
                }
            }
        };
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public static String encrypt(String message) throws Exception {

        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(
                Manage_Assets.NetworkProtocol.sEncryptKey.getBytes(), "AES");

        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(message.getBytes());

        return new String(Hex.encodeHex(encrypted));

    }

    public static String decrypt(String encrypted) throws Exception {

        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(
                Manage_Assets.NetworkProtocol.sEncryptKey.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] original = cipher.doFinal(Hex.decodeHex(encrypted.toCharArray()));

        String originalString = new String(original);

        return originalString;
    }
    
    public static void stopClient() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

            if (thread != null && thread.isAlive())
                thread.interrupt();
            if (msTimer != null) {
                msTimer.cancel();
                msTimer = null;
            }
            if (msSecond != null) {
                msSecond.cancel();
                msSecond = null;
            }

        } catch (IOException e) {
        }
    }

}
