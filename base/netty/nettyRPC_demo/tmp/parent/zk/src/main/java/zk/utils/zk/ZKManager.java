package zk.utils.zk;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * zk 操作接口
 * 
 * @author Administrator
 *
 */
public interface ZKManager {
	/** 增加 */
	public void create(String path, byte[] data) throws KeeperException, InterruptedException;

	/** 删除 */
	public void delete(String path) throws KeeperException, InterruptedException;

	/** 更新 */
	public void update(String path, byte[] data) throws KeeperException, InterruptedException;

	/** 查询 */
	public Stat getZNodeStats(String path) throws KeeperException, InterruptedException;

	/** 查询 */
	public String getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException;

	/** 查询 */
	public List<String> getZNodeChildren(String path) throws KeeperException, InterruptedException;
}
