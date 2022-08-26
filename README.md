# BansWebhook
* Sends punishment info to a discord webhook.

# Commands
* - /bwh reload : Reloads configuration  `bwh.admin`
* - /bwh debug <type> : Sends a debug Webhook with the given type  `bwh.admin`

# Dependencies and libraries used
* LiteBans : https://www.spigotmc.org/resources/3715/
* AdvancedBan : https://www.spigotmc.org/resources/8695/ (https://github.com/DevLeoko/AdvancedBan) 
* Discord-Webhooks : https://github.com/MinnDevelopment/discord-webhooks

# Configuration
```yaml
reload-message: "&aReloaded Configuration!"
litebans-do-not-send-silent-punishments: true
inline-webhooks: false

ban:
  enabled: true
  webhook-url: "enter-url-here"
  webhook-title: "New Ban!"
  webhook-thumbnail: ""
  webhook-color: "ff0000"
  fields:
    "Banned by": "{executor}"
    "Username": "{player}"
    "Reason": "{reason}"
    "Duration": "{duration}"

mute:
  enabled: false
  webhook-url: "enter-url-here"
  webhook-title: "New Mute!!"
  webhook-thumbnail: ""
  webhook-color: "ff0000"
  fields:
    "Banned by": "{executor}"
    "Username": "{player}"
    "Reason": "{reason}"
    "Duration": "{duration}"

warn:
  enabled: false
  webhook-url: "enter-url-here"
  webhook-title: "New Warn!"
  webhook-thumbnail: ""
  webhook-color: "ff0000"
  fields:
    "Banned by": "{executor}"
    "Username": "{player}"
    "Reason": "{reason}"
    "Duration": "{duration}"

kick:
  enabled: false
  webhook-url: "enter-url-here"
  webhook-title: "New Kick!"
  webhook-thumbnail: ""
  webhook-color: "ff0000"
  fields:
    "Banned by": "{executor}"
    "Username": "{player}"
    "Reason": "{reason}"
    "Duration": "{duration}"
```

# Download
* You can download this plugin from spigotmc
* https://www.spigotmc.org/resources/95159/

If there is any bug, feel free to open an issue.
