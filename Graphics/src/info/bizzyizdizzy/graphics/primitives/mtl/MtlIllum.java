package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.List;

public class MtlIllum extends MtlColorIllumObject{

	public MtlIllum(String statementName, List<String> params) {
		super(statementName);
		formatParameters(params);
	}

	@Override
	protected void formatParams(List<String> params) {
		// TODO Auto-generated method stub
		
	}

}
