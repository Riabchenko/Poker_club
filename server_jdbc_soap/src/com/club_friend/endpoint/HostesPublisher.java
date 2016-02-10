package com.club_friend.endpoint;

import javax.xml.ws.Endpoint;
import com.club_friend.clients.ClassClients;
 
//Endpoint publisher
public class HostesPublisher{
 
	public static void main(String[] args) {
	   Endpoint.publish("http://192.168.3.102:9998/clients/hostes", new ClassClients());
  }
 
}