# MojangAPI
A java implementation of api.minetools.eu (Proxied Mojang API)

Creating MojangAPI instance:

```
MojangAPI api = new MojangAPI();
```

Checking mojang service status:
```
MojangAPI api = new MojangAPI();
api.getHTTPHandler().checkService(MojangService.SERVICE); // Where MojangService is the service you want to check.
```
The available services are:
```
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

Fetching player UUID:
```
MojangAPI api = new MojangAPI();
UUID uuid = api.getPlayerInfoHandler().fetchUUID("playerName");
```

Fetching player profile:

First we must get the player profile as a PlayerProfileJson object from the API.
```
MojangAPI api = new MojangAPI();
PlayerProfileJson profile = api.getPlayerInfoHandler().getPlayerProfile(uuid);
// getPlayerProfile() can also be called with the player's name as a String parameter.
```

Now we can access the following data:
```
String userName = profile.getName(); // Player's username
String id = profile.getId(); // Player's uuid as a String
UUID uuid = profile.getUUID(); // Player's uuid as a UUID
Textures textures = profile.getTextures(); // Player's textures
```

The Textures class contains two subclasses: Skin and Cape. Both classes contain a String representing the texture's url:
```
Textures textures = profile.getTextures();
Skin skin = textures.getSkin(); // Skin class
skin.getUrl();

Cape cape = textures.getCape(); // Cape class
cape.getUrl();
```
Beware that if the player does not exist profile.getTextures() will return null. If the player has no cape textures.getCape() will be null.
