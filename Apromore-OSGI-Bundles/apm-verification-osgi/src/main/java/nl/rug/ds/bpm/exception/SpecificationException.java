package nl.rug.ds.bpm.exception;

import nl.rug.ds.bpm.log.LogEvent;
import nl.rug.ds.bpm.log.Logger;

/**
 * Created by Heerko Groefsema on 01-Mar-18.
 */
public class SpecificationException extends Exception {
	public SpecificationException(String message) {
		super(message);
		Logger.log(message, LogEvent.CRITICAL);
	}
}
