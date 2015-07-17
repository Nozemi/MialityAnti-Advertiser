# Miality-Anti-Advertiser
Miality Anti-Advertiser plugin.

There are several other plugins that does the exact same thing, however,
I will build my own custom one that does exactly what I want and to suit
my needs. Also I will do my best to filter out the advertisers.

This project is also for the fun and the learning purpose. I intend to learn
Java and the Bukkit API by making plugins, and at the same time it helps
me do what I want with my Bukkit server.

Features:
- Replaces Domain/IP-addresses with * in the chat. (Like: mc.miality.com or 98.134.234.92)
- Word blacklist
- Seperator blacklist
- More than two equal character in a row check
- Whitelist for players (For flatfile saving at least)
- Flatfile support

More than two equal characters in a row means that whenever a player
sends messages like "Heeeeeeeello??" it removes the uncessesary characters
from the message. In this case, it would return "Heello??".

Seperataor Blacklist?
That is for whenever a player tries to seperate the Domain/IP-address to avoid this plugin.
For instance if I tried to type mc(dot)miality(dot)com, and the (dot) was in that list,
it would be detected and filtered. However this method might make a lot of false positives.

Todo List:
- Database support
- Config support
- Add player to whitelist after X amount of online time
- Move players to another channel after X amount of attempts
- Make the advertiser see his original message while everyone else sees the redacted message.
