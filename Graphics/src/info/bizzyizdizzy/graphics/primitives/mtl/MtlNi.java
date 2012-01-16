package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.List;

public class MtlNi extends MtlColorIllumObject{

	public MtlNi(String statementName, List<String> params) {
		super(statementName);
		formatParameters(params);
	}

	@Override
	protected void formatParams(List<String> params) {
		
	}

}
