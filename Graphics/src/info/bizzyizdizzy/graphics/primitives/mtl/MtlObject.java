package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.List;

import org.apache.log4j.Logger;

public abstract class MtlObject {
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * Initial statement name.
	 * Kept just for informational purposes.
	 */
	private String statementName;
	
	/**
	 * @see #statementName
	 * @param statementName
	 */
	public MtlObject(String statementName){
		this.statementName = statementName;
	}
	
	/**
	 * @see #statementName
	 * @return String
	 */
	public String getStatementName(){
		if(this.statementName == null){
			logger.warn("statementName is null!");
			return "";
		}
		return this.statementName;
	}
	
	public void formatParameters(List<String> params){
		logger.error("You can not format parameters of this base class!");
	}
	
	public String toString(){
		return "Statement: "+this.statementName;
	}

}
