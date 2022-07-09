package ru.kuznetsov.seabattle.server.network;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class ClientServerConnectionTest {
    @Test
    public void isAliveTest() throws IOException {
        AtomicReference<Socket> clientSocket = new AtomicReference<>();

        try (ServerSocket server = new ServerSocket(2000)) {
            new Thread(() -> {
                try {
                    clientSocket.set(server.accept());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            Socket client = new Socket("127.0.0.1", 2000);
            client.close();

            assertFalse("Connection closing!", clientSocket.get().isConnected());
        }
    }

    @SneakyThrows
    @Test
    public void isAliveClientTest() {
    }
}




















