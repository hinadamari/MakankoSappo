package hinadamari.makankosappo;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * MakankoSappo イベントリスナ
 * @author hinadamari
 */
public class MakankoSappoEventListener extends JavaPlugin implements Listener
{
    public MakankoSappo plugin;
    public final static Logger log = Logger.getLogger("Minecraft");

    public MakankoSappoEventListener(MakankoSappo instance)
    {
        plugin = instance;
    }

    /**
     * プレイヤーがクリックした時の処理
     * @param event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerClick(PlayerInteractEvent event) {

    	Player player = event.getPlayer();

    	if (event.getAction() == Action.LEFT_CLICK_BLOCK &&
    			player.isSneaking() &&
    			player.getItemInHand().getType() == Material.AIR &&
    			player.getLocation().getDirection().getY() < -0.3 &&
    			player.getFoodLevel() >= 10) {

    		// プレイヤー付近のエンティティを取得
    		List<Entity> mobs = player.getNearbyEntities(10, 6, 10);
    		int count = 0;

    		for(Entity mob : mobs) {
            	if (mob instanceof LivingEntity) {
            		LivingEntity le = (LivingEntity) mob;
            		le.damage(2, player);
            		// ノックバック
            		le.setVelocity(le.getVelocity().add(le.getLocation().toVector().subtract(event.getClickedBlock().getLocation().toVector()).normalize().multiply(2)));
            		count++;
            	}
            }

    		// 攻撃対象が１体以上いた時
			if (count > 0) {
				if (player.getGameMode() != GameMode.CREATIVE) player.setFoodLevel(player.getFoodLevel() - 6);
				player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
			}

    	}

    }

}