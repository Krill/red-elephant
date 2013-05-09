package Networking;

import Character.*;

import World.*;

import com.esotericsoftware.kryo.Kryo;

import com.esotericsoftware.kryonet.EndPoint;

public class Network {



	// This class is a convenient place to keep things common to both the client and server.
	static public final int tcpport = 55551;
	static public final int udpport = 55552;

	// This registers objects that are going to be sent over the network.
	static public void register (EndPoint endPoint){
		Kryo kryo = endPoint.getKryo();
		kryo.register(ClientPacket.class);
		kryo.register(ServerPacket.class);
		kryo.register(PlayerCharacter.class);
		
		kryo.register(java.util.ArrayList.class);
		kryo.register(java.util.Vector.class);
		kryo.register(java.util.HashMap.class);
		
		kryo.register(World.class);
		kryo.register(Map.class);
		kryo.register(Tile.class);
		kryo.register(BlockTile.class);
		kryo.register(DoorTile.class);
	}
}
