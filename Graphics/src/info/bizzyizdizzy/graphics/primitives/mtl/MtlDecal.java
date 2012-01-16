package info.bizzyizdizzy.graphics.primitives.mtl;

import java.util.List;

public class MtlDecal extends MtlTextureMapObject{

	public MtlDecal(String statementName, List<String> params) {
		super(statementName);
		formatParameters(params);
	}

	@Override
	protected void formatParams(List<String> params) {
		// TODO Auto-generated method stub
		
	}

}
