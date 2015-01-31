package pl.grm.game.core.events;

import java.util.*;

import pl.grm.game.core.inputs.*;

public class KeyEvent implements GameEvent {
	private int						eventKey;
	private String					buttonKey;
	private boolean					eventKeyState;
	private long					eventTime;
	private boolean					repeatEvent;
	private ArrayList<GameListener>	listeners;
	
	public KeyEvent(int eventKey, boolean eventKeyState, long eventTime, boolean repeatEvent,
			ArrayList<GameListener> eventListeners) {
		this.setEventKey(eventKey);
		this.setEventKeyState(eventKeyState);
		this.setEventTime(eventTime);
		this.setRepeatEvent(repeatEvent);
		this.listeners = eventListeners;
	}
	
	public KeyEvent(String eventButton, boolean eventKeyState, long eventTime, boolean repeatEvent,
			ArrayList<GameListener> eventListeners) {
		this.setButtonKey(eventButton);
		this.setEventKeyState(eventKeyState);
		this.setEventTime(eventTime);
		this.setRepeatEvent(repeatEvent);
		this.listeners = new ArrayList<GameListener>();
		this.listeners = eventListeners;
	}
	
	@Override
	public void perform() {
		for (GameListener gameKeyListener : listeners) {
			System.out.println("listener performed");
			if (isEventKeyState()) {
				if (isRepeatEvent()) {
					((GameKeyListener) gameKeyListener).keyTyped(this);
				} else {
					((GameKeyListener) gameKeyListener).keyPressed(this);
				}
			} else {
				((GameKeyListener) gameKeyListener).keyReleased(this);
			}
		}
	}
	
	public void addListener(GameKeyListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public int getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(int eventKey) {
		this.eventKey = eventKey;
	}
	
	public boolean isEventKeyState() {
		return eventKeyState;
	}
	
	public void setEventKeyState(boolean eventKeyState) {
		this.eventKeyState = eventKeyState;
	}
	
	public long getEventTime() {
		return eventTime;
	}
	
	public void setEventTime(long eventTime) {
		this.eventTime = eventTime;
	}
	
	public boolean isRepeatEvent() {
		return repeatEvent;
	}
	
	public void setRepeatEvent(boolean repeatEvent) {
		this.repeatEvent = repeatEvent;
	}
	
	public ArrayList<GameListener> getListeners() {
		return listeners;
	}
	
	public String getButtonKey() {
		return buttonKey;
	}
	
	public void setButtonKey(String eventButton) {
		this.buttonKey = eventButton;
	}
}
