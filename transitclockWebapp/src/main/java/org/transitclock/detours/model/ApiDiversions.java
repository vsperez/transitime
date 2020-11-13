package org.transitclock.detours.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.transitclock.ipc.data.IpcDiversion;

@XmlRootElement
public class ApiDiversions implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8945488673196807736L;
	protected ApiDiversions() {
				
	}


	@XmlElement(name="diversions")
	private List<ApiDiversion> diversions;	
}
