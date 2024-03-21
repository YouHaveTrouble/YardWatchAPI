# YardWatchAPI

API to unify protection plugins for minecraft bukkit servers to allow easy protection queries without having to import
10 different plugin apis with separate implementations.

Current version: [![](https://jitpack.io/v/YouHaveTrouble/YardWatchAPI.svg)](https://jitpack.io/#YouHaveTrouble/YardWatchAPI)

If you're looking for a plugin implementing YardWatchAPI for common protection plugins, see [YardWatch plugin](https://github.com/YouHaveTrouble/YardWatch).

# Usage

### Import the api using dependency manager

In any case of usage you will need to import the API. Replace `VERSION` with current version tag. You should also adjust your `<scope>` to `provided` if you're not implementing the api and just querying it.

#### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
<dependency>
    <groupId>com.github.YouHaveTrouble</groupId>
    <artifactId>YardWatchAPI</artifactId>
    <version>VERSION</version>
    <scope>compile</scope>
</dependency>
```
#### Gradle
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    implementation 'com.github.YouHaveTrouble:YardWatchAPI:VERSION'
}
```

## For plugins wanting to see if something is protected

### Check if player can break a block

Example handling of block breaking check. There's no need to depend on any plugins for this.

```java
public boolean canBreakBlock(Player player, Block block) {
    ServicesManager servicesManager = getServer().getServicesManager();
    Collection<RegisteredServiceProvider<Protection>> protections = servicesManager.getRegistrations(Protection.class);
    for (RegisteredServiceProvider<Protection> protection : protections) {
        if (protection.getProvider().canBreakBlock(player, block.getState(true))) continue;
            return false; // if any protection plugin disallowed breaking the block, return false
        }
    // If all protection plugins allowed breaking the block, return true
    return true;
}
```

## For protection plugins

### Implement Protection interface

Implement all the methods required by the interface

```java
public class YourPluginProtection implements Protection {}
```

### Register your implementation as a service

```java
@Override
public void onEnable() {
    getServer().getServicesManager().register(
        Protection.class,
        new YourPluginProtection(),
        this,
        ServicePriority.Normal
    );
}
```
