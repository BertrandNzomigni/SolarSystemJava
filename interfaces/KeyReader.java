package interfaces;

import java.awt.event.KeyEvent;

public interface KeyReader {
    /** Return the last key pressed by the user. It will put the output of newKey at false. */
    public KeyEvent lastInput();
    /** Returns true if a new key was pressed between the last usage of lastInput and now. Otherwise, return false. */
    public Boolean newKey();
}
