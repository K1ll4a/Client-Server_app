package com.K1ll4a.Client;




import com.K1ll4a.Client.managers.SocketClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        SocketClient client = new SocketClient("localhost",5432);
        client.start();
    }
}