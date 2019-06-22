package amata1219.amachat.bungee;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class User {

	private final Amachat amachat = Amachat.getPlugin();
	private final UserManager userManager = Amachat.getUserManager();

	public final UUID uuid;
	public final String name;
	public final Set<User> hidden;
	public boolean useJapanize;

	public User(ProxiedPlayer player){
		uuid = player.getUniqueId();
		name = player.getName();
		hidden = new HashSet<>();
	}

	public User(UUID uuid, String name, Collection<UUID> hidden, boolean useJapanize){
		this.uuid = uuid;
		this.name = name;
		this.hidden = new HashSet<>(userManager.wrap(hidden));
		this.useJapanize = useJapanize;
	}

	public Optional<ProxiedPlayer> getPlayer(){
		return Optional.of(amachat.getProxy().getPlayer(uuid));
	}

	public void sendMessage(User sender, String message){
		if(!hidden.contains(sender))
			sendMessage(message);
	}

	public void sendMessage(String message){
		getPlayer().ifPresent((player) -> player.sendMessage(new TextComponent(message)));
	}

	public void information(String message){
		sendMessage(ChatColor.AQUA + message);
	}

	public void warning(String message){
		sendMessage(ChatColor.RED + message);
	}

	public void addition(String message){
		sendMessage(ChatColor.GRAY + message);
	}

}
