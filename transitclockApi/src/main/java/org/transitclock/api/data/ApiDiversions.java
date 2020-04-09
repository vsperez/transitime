package org.transitclock.api.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.transitclock.ipc.data.IpcDiversion;

@XmlRootElement(name = "diversions")
public class ApiDiversions {
	
	public ApiDiversions() {
				
	}

	public ApiDiversions(List<IpcDiversion> ipcDiversions) {
		diversions=new ArrayList<ApiDiversion>();
		for(IpcDiversion ipcDiversion:ipcDiversions)
		{
			diversions.add(new ApiDiversion(ipcDiversion));
		}		
	}
	@XmlElement(name="diversion")
	private List<ApiDiversion> diversions;
	public List<ApiDiversion> getDiversions() {
		return diversions;
	}

	public void setDiversions(List<ApiDiversion> diversions) {
		this.diversions = diversions;
	}
	
}
