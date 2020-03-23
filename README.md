# MojangAPI
A java implementation of api.minetools.eu (Proxied Mojang API)

### Importing

Dependency import:
```xml
<repositories>
  <repository>
    <id>PauMAVen</id>
    <url>https://raw.githubusercontent.com/PauMAVA/PauMAVen/master</url>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>me.PauMAVA</groupId>
    <artifactId>MojangAPI</artifactId>
    <version>2020-0.1</version>
    <scope>provided</scope>
	</dependency>
</dependencies>
```

### Basic usage

Creating MojangAPI instance:

```java
MojangAPI api = new MojangAPI();
```

Checking mojang service status:
```java
MojangAPI api = new MojangAPI();
api.getHTTPHandler().checkService(MojangService.SERVICE); // Where MojangService is the service you want to check.
```
The available services are:
```java
MINECRAFT("minecraft.net"),
MINECRAFT_SESSION("session.minecraft.net"),
MOJANG_ACCOUNTS("account.mojang.com"),
MOJANG_AUTHSERVER("authserver.mojang.com"),
MOJANG_SESSIONSERVER("sessionserver.mojang.com"),
MOJANG_API("api.minetools.eu"),
MINECRAFT_TEXTURES("textures.minecraft.net"),
MOJANG_WEB("mojang.com"),
MOJANG_STATUS("status.mojang.com");
```

### UUID Handling

Fetching player UUID:
```java
MojangAPI api = new MojangAPI();
UUID uuid = api.getPlayerInfoHandler().fetchUUID("playerName");
```

### Decoded player profiles

First we must get the player profile as a PlayerProfileJson object from the API.
```java
MojangAPI api = new MojangAPI();
PlayerProfileJson profile = api.getPlayerInfoHandler().getPlayerProfile(uuid);
// getPlayerProfile() can also be called with the player's name as a String parameter instead of UUID.
```

Now we can access the following data:
```java
String userName = profile.getName(); // Player's username
String id = profile.getId(); // Player's uuid as a String
UUID uuid = profile.getUUID(); // Player's uuid as a UUID
Textures textures = profile.getTextures(); // Player's textures
```

The Textures class contains two subclasses: Skin and Cape. Both classes contain a String representing the texture's url:
```java
Textures textures = profile.getTextures();
Skin skin = textures.getSkin(); // Skin class
skin.getUrl();

Cape cape = textures.getCape(); // Cape class
cape.getUrl();
```
Beware that if the player does not exist `profile.getTextures()` will return null. If the player has no cape textures.getCape() will be null.

### Raw player profiles

First we must fetch the raw data from the API and store it on a RawPlayerProfileJson object.
```java
MojangAPI api = new MojangAPI();
RawPlayerProfileJson profile = api.getPlayerInfoHandler().getRawPlayerProfile(uuid);
// getRawPlayerProfile() can also be called with the player's name as a String parameter instead of UUID.
```
Now we can fetch the following data:
```java
String playerName = profile.getName(); // Player's username
String id = profile.getId(); // Player's uuid as a String
UUID uuid = profile.getUUID(); // Player's uuid as a UUID
List<RawPlayerProfileProperty> properties = profile.getProperties(); // Player's properties as RawPlayerProfileProperty
```
Usually the textures property will be stored in position `0` in the List:
```java
RawPlayerProfileProperty textures = properties.get(0);
String name = textures.getName(); // The property name (for texture properties is 'textures')
String value = textures.getValue(); // The encoded property value
String signature = textures.getSignature(); // The property signature.
```
