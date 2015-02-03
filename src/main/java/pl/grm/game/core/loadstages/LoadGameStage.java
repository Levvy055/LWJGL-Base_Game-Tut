package pl.grm.game.core.loadstages;

public abstract class LoadGameStage {
	protected static LoadGameStage	instance;
	private static LoadGameStage	nextStage;
	private boolean					running		= false;
	private boolean					initialized	= false;
	
	public static void renderStage() {
		if (instance != null && instance.isInitialized() && instance.isRunning())
			instance.render();
	}
	
	/**
	 * Renders a screen of stage
	 */
	protected abstract void render();
	
	public static void updateStage() {
		if (instance != null)
			instance.update();
	}
	
	protected abstract void update();
	
	public static void prepareStage(GameLoadStage stage) {
		switch (stage) {
			case INTRO :
				nextStage = new Intro();
				break;
			case MAIN_MENU :
				nextStage = new MainMenu();
				break;
			case GAME_LOADING :
				nextStage = new GameLoading();
				break;
			case GAME :
				// TODO: start game
				break;
			case CLOSING :
				if (instance != null && instance.isRunning()) {
					instance.setRunning(false);
				}
				break;
			default :
				break;
		}
	}
	
	public static boolean startNewStage() {
		if (nextStage == null) { return false; }
		if (instance != null) {
			instance.setRunning(false);
		}
		new Thread(() -> {
			try {
				Thread.sleep(200l);
				instance = nextStage;
				nextStage = null;
				instance.setRunning(true);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		return true;
	}
	
	public static boolean startOldStage() {
		if ((instance == null && nextStage == null) || instance.isRunning()) { return false; }
		instance.setRunning(true);
		return true;
	}
	
	public static void stopcurrentStage() {
		if (instance == null || !instance.isRunning()) { return; }
		instance.setRunning(false);
		new Thread(() -> {
			try {
				Thread.sleep(200l);
				instance = null;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	protected void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
