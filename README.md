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
    <version>2020-0.4</version>
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
### Verbose mode
The JSON and URLs that the API is fetching can be printed to standard output by using the verbose mode. To enable verbose mode the following constructor can be used:
```java
MojangAPI api = new MojangAPI(true);
```
The new instance of the API will be verbose. In order to enable or disable the verbose status of an already created instance thw following method can be used:
````java
MojangAPI api = new MojangAPI();
api.setVerbose(true); // Enable verbose
api.setVerbose(false); // Disable verbose
boolean isVerbose = api.isVerbose(); // If the api is in verbose mode returns true
````


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

## Caching
The API will automatically cache all requests made (UUID, decoded profiles, raw profiles) so that you don't need to request them again if needed.

### Internal cache
Every instance will save all requests inside a `MojangAPICache` object which can be obtained via:
```java
MojangAPI api = new MojangAPI();
MojangAPICache cache = api.getMojangAPICache();
```
These are the methods supplied by `MojangAPICache` class:
- Get cached UUID:
```java
cache.getUUID("playerName"); // Returns an object of type UUID or null if not cached.
```
- Get cached decoded profile:
```java
cache.getDecodedProfile("playerName"); // Returns an object of type PlayerProfileJson or null if not cached.
cache.getDecodedProfile(uuid); // Where uuid is an instance of player's unique id.
```
- Get cached raw profile:
```java
cache.getRawProfile("playerName"); // Returns an object of type RawPlayerProfileJson or null if not cached.
cache.getRawProfile(uuid); // Where uuid is an instance of player's unique id.
```
- Check if UUID is cached:
```java
cache.hasCachedUUID("playerName"); // Returns true if UUID is in cache or false if not.
```
- Check if decoded profile is cached:
```java
cache.hasCachedDecodedProfile("playerName"); // Returns true if PlayerProfileJson is in cache or false if not.
cache.hasCachedDecodedProfile(uuid); // Where uuid is an instance of player's unique id.
```
- Check if raw player profile is cached:
```java
cache.hasCachedRawProfile("playerName"); // Returns true if RawPlayerProfileJson is in cache or false if not.
cache.hasCachedRawProfile(uuid); // Where uuid is an instance of player's unique id.
```
- Clean cache:
```java
cache.cleanCache("playerName"); // Removes all cached data related to that player.
cache.cleanCache(uuid); // Removes all cached data related to that player.
cache.cleanCache(); // Resets all cache for that MojangAPICache instance.
```
- Get full cache:
```java
HashMap<String, UUID> uuidCache = cache.getUUIDcache();
HashMap<UUID, PlayerProfileJson> decodedProfilesCache = cache.getDecodedProfileCache();
HashMap<UUID, RawPlayerProfileJson> rawProfilesCache = cache.getRawProfileCache();
```
You can have multiple instances of the API, and each one will contain its own cache. Example:
```java
MojangAPI api1 = new MojangAPI();
MojangAPI api2 = new MojangAPI();
api1.fecthUUID("player1");
api2.fetchUUID("player2");

api1.getMojangAPICache().hasCachedUUID("player1"); // true
api1.getMojangAPICache().hasCachedUUID("player2"); // false

api2.getMojangAPICache().hasCachedUUID("player1"); // false
api2.getMojangAPICache().hasCachedUUID("player2"); // true
```
So you cannot access cached data made on a different api instance via internal cache.

### Static cache
Meanwhile, all requests made by all instances cache data into a static `MojangCacheAPI` which will contain all requests made by all instances:
```java
MojangAPI api1 = new MojangAPI();
MojangAPI api2 = new MojangAPI();
api1.fecthUUID("player1");
api2.fetchUUID("player2");

MojangAPI.getStaticCache().hasCachedUUID("player1"); // true
MojangAPI.getStaticCache().hasCachedUUID("player2"); // true
```
You can access cache data made on multiple api instances via static cache.