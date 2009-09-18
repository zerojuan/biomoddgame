package com.biomodd.task.manager;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.newdawn.slick.util.Log;

import com.biomodd.client.data.GridsWrapper;
import com.biomodd.client.data.IntsWrapper;
import com.biomodd.client.data.LongsWrapper;
import com.biomodd.client.data.PositionsWrapper;
import com.biomodd.game.BiomoddGame;
import com.biomodd.task.ETask;
import com.biomodd.task.IRealTimeTask;
import com.biomodd.task.ITask;
import com.biomodd.task.ETask.ETaskType;
import com.biomodd.task.game.AddMOBTask;
import com.biomodd.task.game.EnemyChargeTask;
import com.biomodd.task.game.GrowPlantsTask;
import com.biomodd.task.game.InitBlocksTask;
import com.biomodd.task.game.InitEnemiesTask;
import com.biomodd.task.game.InitTerritoriesTask;
import com.biomodd.task.game.MovePlayerTask;
import com.biomodd.task.game.NewGameTask;
import com.biomodd.task.game.PlayerBuildTask;
import com.biomodd.task.game.PlayerTerritorizeTask;
import com.biomodd.task.game.RemoveBlockTask;
import com.biomodd.task.game.RemoveEnemyTask;
import com.biomodd.task.game.RemoveMOBTask;
import com.biomodd.task.game.RemovePlantTask;
import com.biomodd.task.game.RemoveTerritoryTask;
import com.biomodd.task.login.AuthenticateTask;
import com.biomodd.task.login.LoginSuccessTask;
import com.biomodd.task.login.ResetLoginTask;

public class TaskManager {
	/**
	 * The <code>TaskManager</code> instance.
	 */
	private static TaskManager instance;
	/**
	 * The <code>BiomoddGame</code> instance.
	 */
	private final BiomoddGame game;
	/**
	 * The maximum allowed execution time per cycle in milliseconds.
	 */
	private final float executeTime;
	/**
	 * The maximum allowed enqueuing time per cycle in milliseconds.
	 */
	private final float enqueueTime;
	/**
	 * The buffered <code>ITask</code> queue.
	 */
	private final ConcurrentLinkedQueue<ITask> taskQueue;
	/**
	 * The temporary <code>LinkedList</code> buffer of submitted <code>ITask</code>.
	 */
	private final ConcurrentLinkedQueue<ITask> submitted;
	/**
	 * The time before the last execution started in nanoseconds.
	 */
	private long starttime;
	/**
	 * The time after the last execution finished in nanoseconds.
	 */
	private long endtime;
	/**
	 * The time elapsed since the start of the current update cycle in milliseconds.
	 */
	private float totaltime;
	
	/**
	 * Constructor of <code>TaskManager</code>.
	 * @param game The <code>Game</code> instance.
	 */
	private TaskManager(BiomoddGame game){	
		this.game = game;
		this.executeTime = 20;
		this.enqueueTime = 5;
		this.taskQueue = new ConcurrentLinkedQueue<ITask>();
		this.submitted = new ConcurrentLinkedQueue<ITask>();
	}
	/**
	 * Create the task manager for the first time.
	 * @param game The <code>Game</code> instance.
	 * @return The <code>TaskManager</code> instance.
	 */
	public static TaskManager create(BiomoddGame game) {
		if(game == null) return null;
		if(TaskManager.instance == null) {
			TaskManager.instance = new TaskManager(game);
			Log.info("Created new TaskManager.");
		}
		return TaskManager.instance;
	}
	/**
	 * Retrieve the <code>TaskManager</code> singleton instance.
	 * @return The <code>TaskManager</code> instance.
	 */
	public static TaskManager getInstance() {
		return TaskManager.instance;
	}
	/**
	 * Update the <code>TaskManager</code> to execute the buffered task.
	 */
	public void update() {
		// Enqueue tasks.
		while(!this.submitted.isEmpty() && this.totaltime < this.enqueueTime) {
			this.starttime = System.nanoTime();
			this.enqueue(this.submitted.poll());
			this.endtime = System.nanoTime();
			this.totaltime += (this.endtime-this.starttime)/1000000.0f;
		}
		// Reset total time.
		this.totaltime = 0;
		// Execute as many tasks as possible.
		while(!this.taskQueue.isEmpty() && this.totaltime < this.executeTime) {
			this.starttime = System.nanoTime();
			this.taskQueue.poll().execute();
			this.endtime = System.nanoTime();
			this.totaltime += (this.endtime-this.starttime)/1000000.0f;
		}
		this.totaltime = 0;
	}
	/**
	 * Create a task with given type and submit it to the task execution queue.
	 * @param enumn The <code>ETask</code> enumeration.
	 * @param args The <code>Object</code> arguments for the task.
	 * @return The newly created <code>ITask</code>.
	 */
	public ITask createTask(ETask enumn, Object... args) {
		ITask task = null;
		switch(enumn){
			case Authenticate: 				
				task = new AuthenticateTask(this.game, (String)args[0], (String)args[1], (String)args[2], (String)args[3]);
				break;
			case ResetLogin:
				task = new ResetLoginTask(this.game, (String)args[0]);
				break;
			case LoginSuccess:
				task = new LoginSuccessTask(this.game);
				break;
			case NewGame:
				task = new NewGameTask(this.game, (Integer)args[0]);
				break;
			case AddPlayer:
				task = new AddMOBTask(game, (Integer)args[0], (Float)args[1], (Float)args[2], (Integer)args[5]);
				break;
			case RemovePlayer:
				task = new RemoveMOBTask(game, (Integer)args[0]);
				break;
			case PlayerMove:
				task = new MovePlayerTask(game, (Integer)args[0], (Float)args[1], (Float)args[2]);
				break;
			case PlayerBuild:
				task = new PlayerBuildTask(game, (Integer)args[0], (Float)args[1], (Float)args[2], 100);
				break;
			case PlayerTerritorize:
				task = new PlayerTerritorizeTask(game, (Integer)args[0], (Float)args[1], (Float)args[2], (Long)args[3]);
				break;
			case GrowPlant:
				task = new GrowPlantsTask(game, (GridsWrapper)args[0]);
				break;
			case EnemyCharge:
				task = new EnemyChargeTask(game, (PositionsWrapper)args[0], (IntsWrapper)args[1], (LongsWrapper)args[2]);
				break;
			case InitializeTerritories:
				task = new InitTerritoriesTask(game, (PositionsWrapper)args[0], (IntsWrapper)args[1], (LongsWrapper)args[2]);
				break;
			case InitializeEnemies:
				task = new InitEnemiesTask(game, (PositionsWrapper)args[0], (IntsWrapper)args[1], (LongsWrapper)args[2]);
				break;
			case InitializeBlocks:
				task = new InitBlocksTask(game, (PositionsWrapper)args[0], (IntsWrapper)args[1], (IntsWrapper)args[2]);
				break;
			case RemoveBlock:
				task = new RemoveBlockTask(game, (String)args[0]);
				break;
			case RemovePlant:
				task = new RemovePlantTask(game, (String)args[0]);
				break;
			case RemoveEnemy:
				task = new RemoveEnemyTask(game, (String)args[0]);
				break;
			case RemoveTerritory:
				task = new RemoveTerritoryTask(game, (String)args[0]);
				break;
		}
		this.submit(task);
		return task;
	}
	/**
	 * Submit the given task to the <code>TaskManager</code> for later execution.
	 * However, there is no guarantee that the given task will be enqueued.
	 * 
	 * @param task The <code>ITask</code> to be submitted.
	 * @return True if the task is successfully submitted.
	 */
	public boolean submit(ITask task) {
		if(task == null) return false;
		return this.submitted.add(task);
	}
	/**
	 * Enqueue the given task to the <code>TaskManager</code> for later execution.
	 * If there is an earlier <code>RealTimeTask</code> that is considered 'equal'
	 * as the newly given one, the older version is automatically removed before
	 * the new one is added. If the given task is earlier than the 'equal' one in
	 * the queue, the given task is discarded.
	 * @see <code>RealTimeTask</code> for 'equal' determination details.
	 * @param task The <code>ITask</code> to be added.
	 * @return True if the task is successfully enqueued. False if the given task is discarded.
	 */
	private void enqueue(ITask task) {
		// Check real time tasks.
		if(task.getEnumn().getType() == ETaskType.RealTime) {
			final IRealTimeTask given = (IRealTimeTask)task;
			IRealTimeTask inQueue = null;
			for(ITask t : this.taskQueue) {
				if(t.getEnumn().getType() == ETaskType.RealTime) {
					inQueue = (IRealTimeTask)t;
					if(inQueue.equals(given)) {
						// Remove existing one.
						if(given.isLaterThan(inQueue)) {
							this.taskQueue.remove(inQueue);
							Log.debug("Replaced older real time task " + inQueue.getEnumn());
							break;
							// Discard the given one.
						} else {
							Log.debug("Discarded given real time task " + given.getEnumn());
						}
					}
				}
			}
		}
		this.taskQueue.add(task);
	}
	
	public void cleanup() {
		this.taskQueue.clear();
	}

}
