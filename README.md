#INVENTORY FRAMEWORK


## Introduction
A completely **packet based** inventory framework supporting all versions from 1.8 to 1.18 cross-platform to allow easy creation of all types of inventory based GUIs.

The GUIs are based on a mask system. Each GUI has a mask for the items and each character in the mask can be associated with an item.

## Dependency
###Maven
```xml
    <repository>
        <id>zibble</id>
        <url>https://zibble.org/nexus/maven-public/</url>
    </repository>
```
```xml
    <dependency>
        <groupId>org.zibble</groupId>
        <artifactId>inventoryframework</artifactId>
        <version>VERSION</version>
    </dependency>
```

###Gradle
```groovy
    repositories {
        maven {
            id 'zibble'
            url 'https://zibble.org/nexus/maven-public/'
        }
    }
```

```groovy
    dependencies {
        implementation 'org.zibble:inventoryframework:VERSION'
    }
```

##Shading

**THIS FRAMEWORK IS COMPLETELY SHADEABLE**

You will have to shade both inventoryframework and packetevents to make the plugin work *(Unless packetevents is supplied from another source)*.

Relocating is entirely upto you but you should always do that for safety
###Maven
For maven, we are going to take the example of `maven-shade-plugin`.
Make sure to change the `[YOUR PACKAGE]` to your project's package

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.4</version>
    <configuration>
        <relocations>
            <relocation>
                <pattern>org.zibble.inventoryframework</pattern>
                <shadedPattern>[YOUR PACKAGE].inventoryframework</shadedPattern>
            </relocation>
        </relocations>
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

###Gradle
For gradle, we are going to take the example of `shadowJar` plugin. Make sure to change the `[YOUR PACKAGE]` to your project's package
```groovy
shadowJar {
    relocate 'org.zibble.inventoryframework', '[YOUR PACKAGE].inventoryframework'
}
```

##What is Component?
This framework uses <a href="https://github.com/KyoriPowered/adventure">Adventure</a> for most of the works like GUI titles, ItemStack display names, lore, book author etc. This allows us to serialize packets easily and provides a lot of features for you as a dev. If you have experience with Adventure, this should not be an issue

But if you don't and want to use legacy text like `&aStarThisRepoOrIWillKillYou`, You can turn them into components using the following code:

```java
    LegacyComponentSerializer.legacyAmpersand().deserialize(text);
```



