package com.ramon.chickencore;

import org.bukkit.Sound;
//Nos permite usar sonidos de Minecraft.
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.EntityType;


//Paper crea un objeto Command.
//Y quien ejecuta el comando se guarda en CommandSender.



public class ChickenCore extends JavaPlugin implements Listener {



    private final HashMap<UUID, Location> deathLocations = new HashMap<>();
    private final HashMap<UUID, PlayerStats> playerStats = new HashMap<>();
    private PlayerStats test = new PlayerStats();
    private Location spawnLocation;

    //ChickenCore es un plugin de Paper

    @Override
    public void onEnable() {

        saveDefaultConfig();

        if (getConfig().contains("spawn")) {
            spawnLocation = getConfig().getLocation("spawn");
        }


        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("ChickenCore iniciado correctamente");
    }
//Se ejecuta cuando Paper carga tu plugin
    //Muestra en consola: [ChickenCore] ChickenCore iniciado correctamente

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)

    //Le dice a Paper:
    //Este metodo debe ejecutarse cuando ocurra el evento correspondiente
    //Cuando ocurra un PlayerJoinEvent
    //↓
    //Llama a onPlayerJoin()
    //
    //Sin @EventHandler, Paper ignoraría el metodo.


    {
        Player player = event.getPlayer();


        playerStats.putIfAbsent(
                player.getUniqueId(),
                new PlayerStats()
        );

        //Significa:
        //Del evento que acaba de ocurrir
        //Obtén el jugador involucrado
        //Guárdalo en una variable llamada player
        //Y  tambien Cuando el jugador entra:
        //si no tiene ficha de stats,
        //créale una.

        player.sendMessage("Bienvenido, " + player.getName() + ".");

        //Envía un mensaje al jugador
        //player es un objeto de tipo Player
        //Y ese objeto tiene métodos:
        //player.sendMessage(...)
        //player.sendTitle(...)
        //player.teleport(...)
        //player.getName(...)
        //player.getLocation(...)

        //Es parecido a:
        //Scanner teclado = new Scanner(System.in);
        //teclado.nextLine();
        //donde:
        //teclado = objeto
        //nextLine() = metodo

        //player = objeto
        //sendMessage() = metodo

        player.sendTitle("BIENVENIDO", "Disfruta el servidor", 10, 70, 20);

        //Simplemente muestra un título en pantalla.
        //
        //player.sendTitle(
        //    "BIENVENIDO",
        //    "Disfruta el servidor",
        //    10,
        //    70,
        //    20
        //);

        //Cuando alguien entre al servidor
        //↓
        //Obtén el jugador que ha entrado
        //↓
        //Envíale un mensaje
        //↓
        //Muéstrale un título en pantalla
    }

    //Una genialidad, le estas dando la forma a paper de que pueda ver cuando alguien entra al server
    //y en base a eso que pueda realizar acciones

    @Override
    public void onDisable() {
        getLogger().info("ChickenCore detenido");
    }

    //Se ejecuta cuando apagas el servidor.

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (command.getName().equalsIgnoreCase("PedroSanchez")) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage("Este comando solo puede usarlo un jugador.");
                return true;
            }

            player.sendMessage("Bienvenido al servidor.");
            player.sendTitle("BIENVENIDO", player.getName(), 10, 70, 20);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            return true;
        }

        if (command.getName().equalsIgnoreCase("deathcoords")) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage("Solo jugadores.");
                return true;
            }

            if (!deathLocations.containsKey(player.getUniqueId())) {
                player.sendMessage("Ute' no a muelto, que lo que hace cñ");
                return true;
            }

            Location location = deathLocations.get(player.getUniqueId());

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            player.sendMessage("Tus últimas coordenadas de muerte: " +
                    " X: "+ x +
                    " Y: " + y +
                    " Z: "+ z );


            return true; }

        if (command.getName().equalsIgnoreCase("setspawn")) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage("Solo jugadores.");
                return true;
            }

            spawnLocation = player.getLocation();

            getConfig().set("spawn", spawnLocation);
            //guarda el spawn en la configuración.

            saveConfig();

            //escribe los cambios físicamente en el archivo.
            //Después de usar /setspawn, se creará algo en:
            //server/plugins/ChickenCore/config.yml
            //Y cuando reinicies, esta línea lo recupera:
            //spawnLocation = getConfig().getLocation("spawn");
            //Así /spawn seguirá funcionando aunque cierres y abras el servidor.

            player.sendMessage("Spawn gualdao");

            return true;

        }

        if (command.getName().equalsIgnoreCase("spawn")){

            if (!(sender instanceof Player player)) {
                sender.sendMessage("Solo jugadores.");
                return true;
            }

            if(spawnLocation == null) {

                player.sendMessage("Todavia ute' no tiene spawn");
                return true;
            }

            player.teleport(spawnLocation);
            player.sendMessage("Llegate pal' spawn");
            return true;}


        if (command.getName().equalsIgnoreCase("stats")) {

            if(!(sender instanceof Player player)){
                /*un chin tarde pa explicar esto, pero practicamente
                Paper te da "sender" que significa " quien ejecuto el comando
                puede ser un jugador, la consola o commandblock

                instanceof pregunta a que clase pertenece el objeto
                instanceof debe ser un player, sender es un player?
                ! significa negacion, como siempre
                entonces " si sender NO es un player, le mandamos:*/

                sender.sendMessage("Solo jugadores.");
                return true;

            }

            PlayerStats stats = playerStats.get(player.getUniqueId());

            if (stats == null) {
                stats = new PlayerStats();
                playerStats.put(player.getUniqueId(), stats);
            }

            player.sendMessage("===== STATS =====");
            player.sendMessage("Nivel: " + stats.getLevel());
            player.sendMessage("XP: " + stats.getXp());
            player.sendMessage("Muertes: " + stats.getDeaths());
            player.sendMessage("Kills: " + stats.getKills());
            player.sendMessage("Bloques rotos: " + stats.getBlocksBroken());

            return true;




        }



        return false;
    }



    //lo proximo es /deathcoords para ver donde moriste

    //Jugador muere
    //↓
    //Guardamos dónde murió
    //↓
    //Jugador escribe /deathcoords
    //↓
    //Mostramos dónde murió

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getPlayer();


        deathLocations.put(player.getUniqueId(), player.getLocation());

        //Guarda:
        //clave → UUID del jugador
        //valor → ubicación donde murió

        PlayerStats stats = playerStats.get(player.getUniqueId());

        if (stats != null) {
            stats.addDeath();
        }

        player.sendMessage("Te moriste, que pringao");

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Player killer = event.getEntity().getKiller();

        if (killer == null) {
            return;
        }

        PlayerStats stats = playerStats.get(killer.getUniqueId());

        if (stats == null) {
            stats = new PlayerStats();
            playerStats.put(killer.getUniqueId(), stats);
        }

        EntityType type = event.getEntityType();

        int xpGained = switch (type) {
            case ZOMBIE -> 3;
            case SKELETON -> 7;
            case SPIDER -> 5;
            case CREEPER -> 7;
            case ENDERMAN -> 10;
            case BLAZE -> 15;
            case WITCH -> 12;
            case WITHER -> 250;
            case ENDER_DRAGON -> 500;
            default -> 1;
        };

        stats.addKill();
        stats.addXp(xpGained);

        if (type == EntityType.ENDERMAN ||
                type == EntityType.WITHER ||
                type == EntityType.ENDER_DRAGON) {

            killer.sendMessage("Has ganado " + xpGained + " XP por matar a " + type.name() + ".");
        }
    }

    






    }

