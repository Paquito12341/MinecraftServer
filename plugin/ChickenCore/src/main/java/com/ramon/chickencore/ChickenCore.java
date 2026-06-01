package com.ramon.chickencore;

import org.bukkit.Sound;
//Nos permite usar sonidos de Minecraft.
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


//Paper crea un objeto Command.
//Y quien ejecuta el comando se guarda en CommandSender.

public class ChickenCore extends JavaPlugin implements Listener {
    //ChickenCore es un plugin de Paper

    @Override
    public void onEnable() {
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

        //Significa:
        //Del evento que acaba de ocurrir
        //Obtén el jugador involucrado
        //Guárdalo en una variable llamada player

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

            //10 = aparición
            //70 = duración
            //20 = desaparición

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

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




}