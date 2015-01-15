package pl.grm.game.core.events;

import java.util.*;

import pl.grm.game.core.inputs.*;

public class KeyEvent implements GameEvent {
	
	private int						eventKey;
	private boolean					eventKeyState;
	private long					eventTime;
	private boolean					repeatEvent;
	private List<GameKeyListener>	listeners;
	
	public KeyEvent(int eventKey, boolean eventKeyState, long eventTime, boolean repeatEvent) {
		this.setEventKey(eventKey);
		this.setEventKeyState(eventKeyState);
		this.setEventTime(eventTime);
		this.setRepeatEvent(repeatEvent);
		this.listeners = new ArrayList<GameKeyListener>();
	}
	
	@Override
	public void perform() {
		for (GameKeyListener gameKeyListener : listeners) {
			if (isEventKeyState()) {
				if (isRepeatEvent()) {
					gameKeyListener.keyTyped(this);
				} else {
					gameKeyListener.keyPressed(this);
				}
			} else {
				gameKeyListener.keyReleased(this);
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
	
	public List<GameKeyListener> getListeners() {
		return listeners;
	}
}
