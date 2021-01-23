# MYSlots
![Build and Test](https://github.com/4ndyZ/MYSlots/workflows/Build%20and%20Test/badge.svg) ![Release](https://github.com/4ndyZ/MYSlots/workflows/Release/badge.svg)

[MYSlots](https://dev.bukkit.org/projects/myslots/) is a Bukkit which allows you to increase the slots of your server, even if your server has a slot limit! The provided slots are real usable slots, not any kind of fake slots like other plugins provide. 

## Example
![Example](https://i.imgur.com/IN68zmA.png)

This server only has 4 slots but with this plugin the server has 500 slots.

## Installation

[Download](https://dev.bukkit.org/projects/myslotsfiles) the lastest version of the plugin and put it in the `plugin` directory of your Bukkit/Spigot server. Reload or restart your Minecraft server.

## Commands
```
/myslots - Command to see the slots
/myslots [Slots] - Command to set the slots
```

## Permissions
```
myslots.use - Admin Permission
myslots.full - Join full server Permission
```

## Configuration
Bukkit/Spigot:

The configuration file is located under `plugins/MYSlots/config.yml`.
```
MYSlots:
  Enabled: true/false # Should the plugin be enabled
  Language: en # The name of the language file without '.yml' 
Slots: 40 # The number of slots for your server
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GPL-3.0](https://github.com/4ndyZ/JoinMessagePlus/blob/master/LICENSE)
