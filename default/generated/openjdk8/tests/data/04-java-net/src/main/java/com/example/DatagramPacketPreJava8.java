// src/main/java/com/example/DatagramPacketPreJava8.java
package com.example;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DatagramPacketPreJava8 {
    public static void main(String[] args) {
        try {
            // Create a DatagramPacket using a SocketAddress
            InetAddress address = InetAddress.getByName("localhost");
            byte[] data = "Hello".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, 12345);
            System.out.println("DatagramPacket created successfully: " + packet);
        } catch (SocketException e) {
            System.out.println("SocketException caught: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException caught: " + e.getMessage());
        }
    }
}