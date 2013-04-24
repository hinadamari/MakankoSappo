package hinadamari.makankosappo;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * MakankoSappo
 * @author hinadamari
 */
public class MakankoSappo extends JavaPlugin
{

    public final static Logger log = Logger.getLogger("Minecraft");

    /**
     * プラグイン起動処理
     */
    public void onEnable()
    {

        new MakankoSappoEventListener(this);
        getServer().getPluginManager().registerEvents(new MakankoSappoEventListener(this), this);

        log.info("[MakankoSappo] MakankoSappo is enabled!");

    }

    /**
     * プラグイン停止処理
     */
    public void onDisable()
    {
        this.getServer().getScheduler().cancelTasks(this);
    }

}