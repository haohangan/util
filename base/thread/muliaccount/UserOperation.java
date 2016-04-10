package ti2.muliaccount;

import java.util.concurrent.locks.ReadWriteLock;

public class UserOperation implements Runnable{
	private String name;
	private Account account;
	private Integer iocash;
	private ReadWriteLock lock;
	private boolean isquery = false;
	
	public UserOperation(String name, Account account, Integer iocash, ReadWriteLock lock, boolean isquery) {
		super();
		this.name = name;
		this.account = account;
		this.iocash = iocash;
		this.lock = lock;
		this.isquery = isquery;
	}

	public String getName() {
		return name;
	}
	
	public Integer getIocash() {
		return iocash;
	}
	public ReadWriteLock getLock() {
		return lock;
	}
	public boolean isIsquery() {
		return isquery;
	}

	public void setIsquery(boolean isquery) {
		this.isquery = isquery;
	}

	@Override
	public void run() {
		if(isquery){
			lock.readLock().lock();
			System.out.println("用户："+name+",正在查询:"+account+",当前余额为："+account.getCash());
			lock.readLock().unlock();
		}else{
			lock.writeLock().lock();
			System.out.println("用户："+name+",正在修改："+account+",当前余额为："+account.getCash());
			account.setCash(account.getCash()+iocash);
			System.out.println("用户："+name+",修改成功："+account+",当前余额为："+account.getCash());
			lock.writeLock().unlock();
		}
	}
	
	
}
