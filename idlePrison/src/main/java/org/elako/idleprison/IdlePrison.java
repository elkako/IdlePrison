package org.elako.idleprison;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import org.elako.idleprison.comandos.*;
import org.elako.idleprison.eventos.*;
import org.elako.idleprison.items.*;
import org.elako.idleprison.mina.BloqueManager;
import org.elako.idleprison.mina.MinaManager;
import org.elako.idleprison.player.*;

import java.io.File;
import java.util.*;


public final class IdlePrison extends JavaPlugin {
    private static PlayerManager playerManager;
    private static MinaManager mina;
    private static DineroManager dinero;
    private static RangosManager rango;
    private static MaterialesManager materiales;
    private static TreeSkillManager treeskill;
    private static IdleManager idle;
    private static BloqueManager bloque;
    private static NotaManager nota;
    private static VenderManager vender;
    private static IdlePrison plugin;

    public static IdlePrison getPlugin(){
        return plugin;
    }

    public void insertarConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

    public Location parsearUbicacion(String ruta, int tipo){
        String mundo = getConfig().getString(ruta + ".world");
        World world = getServer().getWorld(Objects.requireNonNull(mundo));
        double x;
        double y;
        double z;
        if (tipo == 1) {
            x = Double.parseDouble(Objects.requireNonNull(getConfig().getString(ruta + ".x1")));
            y = Double.parseDouble(Objects.requireNonNull(getConfig().getString(ruta + ".y1")));
            z = Double.parseDouble(Objects.requireNonNull(getConfig().getString(ruta + ".z1")));
        } else {
            x = Double.parseDouble(Objects.requireNonNull(getConfig().getString(ruta + ".x2")));
            y = Double.parseDouble(Objects.requireNonNull(getConfig().getString(ruta + ".y2")));
            z = Double.parseDouble(Objects.requireNonNull(getConfig().getString(ruta + ".z2")));
        }
        return new Location(world,x,y,z);
    }

    public static void broadcast(String mensaje){
        for (Player p:
             getPlugin().getServer().getOnlinePlayers()) {
            p.sendMessage(mensaje);
        }
    }

    public static void imprimirConsola(String mensaje){
        getPlugin().getServer().getConsoleSender().sendMessage(mensaje);
    }

    public void escribirMina(String mina, Location ubi1, Location ubi2){
        mina = "Minas." + mina;
        getConfig().set(mina + ".world" , Objects.requireNonNull(ubi1.getWorld()).getName());
        getConfig().set(mina + ".x1" , ubi1.getBlockX());
        getConfig().set(mina + ".y1" , ubi1.getBlockY());
        getConfig().set(mina + ".z1" , ubi1.getBlockZ());
        getConfig().set(mina + ".x2" , ubi2.getBlockX());
        getConfig().set(mina + ".y2" , ubi2.getBlockY());
        getConfig().set(mina + ".z2" , ubi2.getBlockZ());
        saveConfig();
    }

    public void escribirRango(String player, String rango){
        player = "Players." + player + ".rango";
        getConfig().set(player, rango.toUpperCase());
        saveConfig();
    }

    public void escribirIdle(String player, int cantidad, String idle){
        player = "Players." + player + ".idle" + idle;
        getConfig().set(player,cantidad);
        saveConfig();
    }

    public void escribirTreeSkill(String player, int cantidad, String ts){
        player = "Players." + player + ".treeskill" + ts;
        getConfig().set(player,cantidad);
        saveConfig();
    }

    public void escribirDinero(String player, double money) {
        player = "Players." + player + ".dinero";
        getConfig().set(player, money);
        saveConfig();
    }


    public void escribirDineroRenacer(String player, double money) {
        player = "Players." + player + ".dineroRenacer";
        getConfig().set(player, money);
        saveConfig();
    }

    public void escribirDineroRun(String player, double money) {
        player = "Players." + player + ".dineroRun";
        getConfig().set(player, money);
        saveConfig();
    }

    public void escribirPermisos(String player, String permisos){
        player = "Players." + player + ".permisos";
        getConfig().set(player, permisos);
        saveConfig();
    }

    public void escribirNotas(String player, String notas){
        player = "Players." + player + ".notas" ;
        getConfig().set(player, notas);
        saveConfig();
    }

    public void escribirNotasRecibidas(String player, String notas){
        player = "Players." + player + ".notasRecibidas" ;
        getConfig().set(player, notas);
        saveConfig();
    }

    public void escribirRecompensas(String player, String recompensas) {
        player = "Players." + player + ".recompensas" ;
        getConfig().set(player, recompensas);
        saveConfig();
    }

    public void escribirItemsVendidos(String player, int cantidad){
        player = "Players." + player + ".itemsVendidos";
        getConfig().set(player,cantidad);
        saveConfig();
    }

    public void escribirBloquesRotos(String player, int cantidad){
        player = "Players." + player + ".bloquesRotos";
        getConfig().set(player,cantidad);
        saveConfig();
    }

    private void addPicoRecetas(List<ItemStack> esencias) {
        ShapelessRecipe picoRecetaW = new ShapelessRecipe(new ItemStack(Material.WOODEN_PICKAXE));
        ShapelessRecipe picoRecetaS = new ShapelessRecipe(new ItemStack(Material.STONE_PICKAXE));
        ShapelessRecipe picoRecetaI = new ShapelessRecipe(new ItemStack(Material.IRON_PICKAXE));
        ShapelessRecipe picoRecetaD = new ShapelessRecipe(new ItemStack(Material.DIAMOND_PICKAXE));

        for (ItemStack esencia: esencias) {
            picoRecetaW.addIngredient(new RecipeChoice.ExactChoice(esencia));
            picoRecetaS.addIngredient(new RecipeChoice.ExactChoice(esencia));
            picoRecetaI.addIngredient(new RecipeChoice.ExactChoice(esencia));
            picoRecetaD.addIngredient(new RecipeChoice.ExactChoice(esencia));
        }

        picoRecetaW.addIngredient(Material.WOODEN_PICKAXE);
        getServer().addRecipe(picoRecetaW);

        picoRecetaS.addIngredient(Material.STONE_PICKAXE);
        getServer().addRecipe(picoRecetaS);

        picoRecetaI.addIngredient(Material.IRON_PICKAXE);
        getServer().addRecipe(picoRecetaI);

        picoRecetaD.addIngredient(Material.DIAMOND_PICKAXE);
        getServer().addRecipe(picoRecetaD);
    }

    public static void scoreboardNotification(Player p, LinkedList<String> s, int segs) {
        playerManager.reloadTimeScoreDetector(p.getName(), segs);
        ScoreboardManager manager = getPlugin().getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objetivo = scoreboard.registerNewObjective("miplugin","dummy");
        objetivo.setDisplaySlot(DisplaySlot.SIDEBAR);
        objetivo.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + p.getName());

        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).length() > 30){
                int n = 30;
                while (s.get(i).charAt(n) != ' ') n--;
                s.add(i,s.get(i).substring(0,n));
                s.set(i+1,s.get(i+1).substring(n));
            }
            if(i>1) if (s.get(i-2).equals(s.get(i))) s.remove(i);
            if(i>3) if (s.get(i-4).equals(s.get(i))) s.remove(i);
            if(i>5) if (s.get(i-6).equals(s.get(i))) s.remove(i);
        }

        for (int i = 0; i < s.size(); i++) {
            Score score = objetivo.getScore(s.get(i));
            score.setScore(s.size()-i);
        }

        p.setScoreboard(scoreboard);
    }


    private void tickScoreboard(Player p) {
        ScoreboardManager manager = getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objetivo = scoreboard.registerNewObjective("miplugin","dummy");
        objetivo.setDisplaySlot(DisplaySlot.SIDEBAR);
        objetivo.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + p.getName());

        Rangos r = rango.getPlayer(p);
        double diferencia = playerManager.getDiferenciaDinero(p.getName());
        double d = dinero.getDinero(p) - diferencia;
        double ascender = rango.getDineroAscender(r,p.getName())-d;
        String sr = r.toString();
        if (ascender<0) ascender = 0;

        String diferenciaDinero = "";
        if(playerManager.getDiferenciaDinero(p.getName()) > 0.0) { // positivo
             diferenciaDinero = " + " + DineroManager.dineroToString(diferencia);
        } else if (diferencia < 0.0) { // negativo
            diferenciaDinero = " - " + DineroManager.dineroToString( Math.abs(diferencia) );

        }

        playerManager.reiniciarDiferenciaDinero(p.getName());

        List<String> lineas;
        if (rango.isPermitido(p.getName(),Rangos.CAMPESINO1) || treeskill.getDineroRenacer(p.getName()) > 0 ) {
             lineas = Arrays.asList(
                    ChatColor.WHITE + "" + ChatColor.BOLD + "Dinero: " + ChatColor.WHITE + DineroManager.dineroToString(d) + diferenciaDinero + "E",
                    ChatColor.WHITE + "" + ChatColor.BOLD + "Rango: ",
                    ChatColor.WHITE + "" + sr.toUpperCase().charAt(0) + sr.substring(1).toLowerCase() ,
                    ChatColor.WHITE + "Siguiente: " + rango.siguienteRango(p.getName()).toLowerCase() ,
                    ChatColor.WHITE + "Necesario: " + DineroManager.dineroToString(ascender) + "E",
                    ChatColor.WHITE + "" + ChatColor.BOLD + "Acumulado en idle: ",
                    ChatColor.WHITE + "Dinero: " + DineroManager.dineroToString(playerManager.getDineroAcum(p.getName())) + "E",
                    ChatColor.WHITE + "Tiempo: " + IdleManager.tiempoToString(playerManager.getPlayer(p.getName()).getTimeTotal()),
                    ChatColor.WHITE + "" + ChatColor.BOLD + "Renacer: ",
                    ChatColor.WHITE + "Nivel: "+ treeskill.getNivelRenacer(p.getName()),
                    ChatColor.WHITE + "Nivel tras renacer: "+ treeskill.getNivelTotal(p.getName()),
                    ChatColor.WHITE + "Siguiente nivel: " + DineroManager.dineroToString( treeskill.ascenderRestos( treeskill.getDineroTotal(p.getName()) ) ) + " E"
            );
        } else
            lineas = Arrays.asList(
                ChatColor.WHITE + "" + ChatColor.BOLD + "Dinero: " + ChatColor.WHITE + DineroManager.dineroToString(d) + diferenciaDinero + "E",
                ChatColor.WHITE + "" + ChatColor.BOLD + "Rango: ",
                ChatColor.WHITE + "" + sr.toUpperCase().charAt(0) + sr.substring(1).toLowerCase() ,
                ChatColor.WHITE + "Siguiente: " + rango.siguienteRango(p.getName()).toLowerCase() ,
                ChatColor.WHITE + "Necesario: " + DineroManager.dineroToString(ascender) + "E",
                ChatColor.WHITE + "" + ChatColor.BOLD + "Acumulado en idle: ",
                ChatColor.WHITE + "Dinero: " + DineroManager.dineroToString(playerManager.getDineroAcum(p.getName())) + "E",
                ChatColor.WHITE + "Tiempo: " + IdleManager.tiempoToString(playerManager.getPlayer(p.getName()).getTimeTotal())

        );
        for (int i = 0; i < lineas.size(); i++) {
            Score score = objetivo.getScore(lineas.get(i));
            score.setScore(lineas.size()-i);
        }
        p.setScoreboard(scoreboard);
    }

    private void tickEffect(Player p) {
        int haste = TreeSkillManager.getHaste(p.getName());
        int regen = TreeSkillManager.getRegeneration(p.getName());
        int speed = TreeSkillManager.getSpeed(p.getName());
        if (haste > 0) p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,60, haste-1) );
        if (regen > 0) p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,60,regen-1 ) );
        if (speed > 0) p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,60,speed-1 ) );
    }

    @Override
    public void onEnable() {

        //inicializacion
        plugin = this;
        playerManager = new PlayerManager();
        dinero = new DineroManager(playerManager);
        nota = new NotaManager(dinero,playerManager);
        rango = new RangosManager(dinero,playerManager);
        vender = new VenderManager(dinero, rango,playerManager);
        materiales = new MaterialesManager(rango);
        bloque = new BloqueManager();
        idle = new IdleManager(dinero,playerManager);
        mina = new MinaManager(rango);
        treeskill = new TreeSkillManager(playerManager,rango,mina);

        insertarConfig();

        //eventos
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BreakPutBlockEvent(mina,dinero,playerManager,bloque),this);
        pm.registerEvents(new BlockDropEvent(mina), this);
        pm.registerEvents(new MesasCrafteoEvent(rango),this);
        pm.registerEvents(new JoinPlayerEvent(rango,dinero, playerManager),this);
        pm.registerEvents(new MenuListener(treeskill,mina,rango, idle, playerManager, materiales, vender),this);
        pm.registerEvents(new DeathPlayerEvent(vender,dinero), this);
        pm.registerEvents(new ItemsEvent(mina,bloque),this);
        pm.registerEvents(new ComerEvent(rango),this);
        pm.registerEvents(new ItemDropEvent(rango),this);
        pm.registerEvents(new InteractPlayerEvent(playerManager, rango, vender),this);

        //comandos
        getCommand("idleprison").setExecutor(new Idleprison(rango,dinero,playerManager,treeskill,mina));
        getCommand("ipcrear").setExecutor(new Ipcrear(mina, playerManager));
        getCommand("rango").setExecutor(new Rango(rango, playerManager));
        getCommand("dinero").setExecutor(new Dinero(dinero, playerManager));
        getCommand("mina").setExecutor(new Mina(mina,rango));
        getCommand("vender").setExecutor(new Vender(vender));
        getCommand("treeskill").setExecutor(new TreeSkill(treeskill,rango, playerManager));
        getCommand("crafteo").setExecutor(new Crafteo(materiales,rango));
        getCommand("idle").setExecutor(new Idle(playerManager,rango,idle));
        getCommand("renacer").setExecutor(new Renacer(treeskill,playerManager,rango));

        //recetas

        // recetas infierno
        ShapelessRecipe tablonesInfiernoReceta = new ShapelessRecipe(MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,7));
        tablonesInfiernoReceta.addIngredient(new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO)));
        getServer().addRecipe(tablonesInfiernoReceta);

        ShapelessRecipe tablonesInfierno2Receta = new ShapelessRecipe(MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,15));
        tablonesInfierno2Receta.addIngredient(new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD)));
        getServer().addRecipe(tablonesInfierno2Receta);

        ShapedRecipe picoReceta = new ShapedRecipe(PicosManager.getPicoMadera());
        picoReceta.shape("SSS" , " S " , " S ");
        picoReceta.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS)));
        getServer().addRecipe(picoReceta);

        ShapelessRecipe pescadoBrasaReceta = new ShapelessRecipe(MaterialesManager.getItem(IpMateriales.PESCADO_BRASA));
        pescadoBrasaReceta.addIngredient(new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.PESCADO_CRUDO)));
        pescadoBrasaReceta.addIngredient(new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS)));
        getServer().addRecipe(pescadoBrasaReceta);


        ShapedRecipe esenciaAzul1 = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL1));
        esenciaAzul1.shape("SSS" , "SSS" , "SSS");
        esenciaAzul1.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL1)));
        getServer().addRecipe(esenciaAzul1);

        addPicoRecetas(Arrays.asList( MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL1)) );

        // recetas afueras
        ShapedRecipe picoPiedraReceta = new ShapedRecipe(PicosManager.getPicoPiedra());
        picoPiedraReceta.shape("SSS" , "STS" , "SSS");
        picoPiedraReceta.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.ROCA,1)));
        picoPiedraReceta.setIngredient('T', new RecipeChoice.ExactChoice(PicosManager.getPicoMadera()));
        getServer().addRecipe(picoPiedraReceta);

        ShapedRecipe lente1 = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.LENTE));
        lente1.shape("SSS" , "TSR" , "SSS");
        lente1.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.ARENA)));
        lente1.setIngredient('T', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD)));
        lente1.setIngredient('R', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO)));
        getServer().addRecipe(lente1);

        ShapedRecipe lente2 = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.LENTE));
        lente2.shape("SSS" , "TSR" , "SSS");
        lente2.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.ARENA)));
        lente2.setIngredient('T', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.MADERA_ROBLE)));
        lente2.setIngredient('R', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO)));
        getServer().addRecipe(lente2);

        ShapedRecipe catalejo = new ShapedRecipe(PicosManager.getCatalejo());
        catalejo.shape("  S" , "TTS" , "   ");
        catalejo.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.LENTE)));
        catalejo.setIngredient('T', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE)));
        getServer().addRecipe(catalejo);

        ShapelessRecipe tablonesRobleReceta = new ShapelessRecipe(MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE,5));
        tablonesRobleReceta.addIngredient( new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.MADERA_ROBLE)));
        getServer().addRecipe(tablonesRobleReceta);

        ShapelessRecipe tablonesRobleReceta2 = new ShapelessRecipe(MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE,17));
        tablonesRobleReceta2.addIngredient(new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.MADERA_ROBLE,3)));
        getServer().addRecipe(tablonesRobleReceta2);

        ShapedRecipe esenciaRoja1 = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.ESENCIA_ROJA1));
        esenciaRoja1.shape("SSS" , "SSS" , "SSS");
        esenciaRoja1.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO1)));
        getServer().addRecipe(esenciaRoja1);

        addPicoRecetas(Arrays.asList( MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO1) ) );

        ShapedRecipe granGranito = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.GRAN_GRANITO));
        granGranito.shape("SSS" , "SSS" , "SSS");
        granGranito.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.GRANITO)));
        getServer().addRecipe(granGranito);

        ShapedRecipe esenciaVerde1 = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.ESENCIA_VERDE1));
        esenciaVerde1.shape("SSS" , "SSS" , "SSS");
        esenciaVerde1.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE1)));
        getServer().addRecipe(esenciaVerde1);

        addPicoRecetas(Arrays.asList( MaterialesManager.getItem(IpMateriales.ESENCIA_VERDE1) ) );

        ShapedRecipe esenciaAzul2 = new ShapedRecipe( MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL2) );
        esenciaAzul2.shape("SSS" , "SSS" , "SSS");
        esenciaAzul2.setIngredient('S', new RecipeChoice.ExactChoice( MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL2) ));
        getServer().addRecipe(esenciaAzul2);

        addPicoRecetas(Arrays.asList( MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL2) ) ) ;

        ShapedRecipe panReceta = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.PAN,2));
        panReceta.shape("   " , "SSS" , " T ");
        panReceta.setIngredient('S', new RecipeChoice.ExactChoice( MaterialesManager.getItem(IpMateriales.TRIGO) ));
        panReceta.setIngredient('T', new RecipeChoice.ExactChoice( MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE) ));
        getServer().addRecipe(panReceta);

        ShapedRecipe esenciaAmarilla1 = new ShapedRecipe(MaterialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1));
        esenciaAmarilla1.shape("SSS" , "SSS" , "SSS");
        esenciaAmarilla1.setIngredient('S', new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.FRAGMENTO_AMARILLO1)));
        getServer().addRecipe(esenciaAmarilla1);

        ShapelessRecipe detectorBloques = new ShapelessRecipe(new ItemStack(PicosManager.getDetectorBloques1()));
        detectorBloques.addIngredient(new RecipeChoice.ExactChoice(MaterialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1)));
        detectorBloques.addIngredient(new RecipeChoice.ExactChoice(PicosManager.getCatalejo()));
        getServer().addRecipe(detectorBloques);

        //cargar minas
       ConfigurationSection sec = getConfig().getConfigurationSection("Minas");

        if (sec != null){
            for(String key : sec.getKeys(false)){
                if (getConfig().get("Minas." + key) != "") {
                    // Parsear ubis
                    String s = "Minas." + key;
                    Location coords1 = parsearUbicacion(s,1);
                    Location coords2 = parsearUbicacion(s,2);
                    mina.setBloques(key,coords1,coords2);
                }
            }
        }

        //cargar datos
        ConfigurationSection rsec = getConfig().getConfigurationSection("Players");

        if (rsec != null){
            for(String key : rsec.getKeys(false)){
                if (getConfig().get("Players." + key) != "") {
                    // rangos
                    String rangoJugador = getConfig().getString("Players." + key + ".rango");

                    //money
                    double dineroJugador = getConfig().getDouble("Players." + key + ".dinero");

                    //money renacer
                    double dineroRenacer = getConfig().getDouble("Players." + key + ".dineroRenacer");

                    //money run
                    double dineroRun = getConfig().getDouble("Players." + key + ".dineroRun");

                    //idle
                    int idle1 = getConfig().getInt("Players." + key + ".idle1");
                    int idle2 = getConfig().getInt("Players." + key + ".idle2");
                    int idle3 = getConfig().getInt("Players." + key + ".idle3");
                    int idle4 = getConfig().getInt("Players." + key + ".idle4");
                    int idle5 = getConfig().getInt("Players." + key + ".idle5");
                    int idle6 = getConfig().getInt("Players." + key + ".idle6");

                    //treeskill
                    int treeskill1 = getConfig().getInt("Players." + key + ".treeskill1");
                    int treeskill2 = getConfig().getInt("Players." + key + ".treeskill2");
                    int treeskill3 = getConfig().getInt("Players." + key + ".treeskill3");

                    //permisos , notas Y recompensas
                    String permisos = getConfig().getString("Players." + key + ".permisos");
                    String notas = getConfig().getString("Players." + key + ".notas");
                    String notasRecibidas = getConfig().getString("Players." + key + ".notas");
                    String recompensas = getConfig().getString("Players." + key + ".recompensas");

                    int itemsVendidos = getConfig().getInt("Players." + key + ".itemsVendidos");
                    int bloquesRotos = getConfig().getInt("Players." + key + ".bloquesRotos");

                    if (!Rangos.contieneRango(rangoJugador)) return;

                    playerManager.addJugador(key, dineroJugador, dineroRenacer, dineroRun, Rangos.valueOf(rangoJugador.
                            toUpperCase()), idle1, idle2, idle3, idle4, idle5, idle6, treeskill1, treeskill2, treeskill3,
                            permisos, notas, notasRecibidas, recompensas, itemsVendidos, bloquesRotos);
                }
            }
        }

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : getServer().getOnlinePlayers()) { // a todos los jugadores en linea
                if (playerManager.getTimeScore(p.getName())<=0) tickScoreboard(p);
                else playerManager.reduceTimeScore(p.getName());
                tickEffect(p);
            }

            mina.tick();
            idle.tick();
        }, 20, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
