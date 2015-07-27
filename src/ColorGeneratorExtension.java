import java.util.ArrayList;
import java.util.List;

import org.nlogo.api.Argument;
import org.nlogo.api.ClassManager;
import org.nlogo.api.CompilerException;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.ExtensionManager;
import org.nlogo.api.ExtensionObject;
import org.nlogo.api.ImportErrorHandler;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.PrimitiveManager;
import org.nlogo.api.Syntax;


public class ColorGeneratorExtension implements ClassManager {

	@Override
	public List<String> additionalJars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public StringBuilder exportWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importWorld(List<String[]> arg0, ExtensionManager arg1,
			ImportErrorHandler arg2) throws ExtensionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(PrimitiveManager pm) throws ExtensionException {
		pm.addPrimitive("get-n-colors", new GetNColors());
		// TODO Auto-generated method stub

	}

	@Override
	public ExtensionObject readExtensionObject(ExtensionManager arg0,
			String arg1, String arg2) throws ExtensionException,
			CompilerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void runOnce(ExtensionManager arg0) throws ExtensionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unload(ExtensionManager arg0) throws ExtensionException {
		// TODO Auto-generated method stub

	}
	
	
	public static class GetNColors extends DefaultReporter{
		public Syntax getSyntax(){
            return Syntax.reporterSyntax(
                    // we take in nothing
                    new int[] {Syntax.NumberType()}, Syntax.ListType());
		}

		@Override
		public Object report(Argument[] arg0, Context arg1)
				throws ExtensionException, LogoException {
			double n = arg0[0].getDoubleValue();
			float num = (float)n;
			
			LogoListBuilder llb = new LogoListBuilder();
		
			float dx = 1.0f / (float) (num - 1);
			for (int i = 0; i < num; i++) {
				llb.add(get(i * dx));
			}

			return llb.toLogoList();

		}
		
		public LogoList get(float x) {
			float r = 0.0f;
			float g = 0.0f;
			float b = 1.0f;
			if (x >= 0.0f && x < 0.2f) {
				x = x / 0.2f;
				r = 0.0f;
				g = x;
				b = 1.0f;
			} else if (x >= 0.2f && x < 0.4f) {
				x = (x - 0.2f) / 0.2f;
				r = 0.0f;
				g = 1.0f;
				b = 1.0f - x;
			} else if (x >= 0.4f && x < 0.6f) {
				x = (x - 0.4f) / 0.2f;
				r = x;
				g = 1.0f;
				b = 0.0f;
			} else if (x >= 0.6f && x < 0.8f) {
				x = (x - 0.6f) / 0.2f;
				r = 1.0f;
				g = 1.0f - x;
				b = 0.0f;
			} else if (x >= 0.8f && x <= 1.0f) {
				x = (x - 0.8f) / 0.2f;
				r = 1.0f;
				g = 0.0f;
				b = x;
			}
			double dr=(double)r * 255;
			double dg=(double)g * 255;
			double db=(double)b * 255;
			LogoListBuilder colorLB = new LogoListBuilder();
			colorLB.add(dr);
			colorLB.add(dg);
			colorLB.add(db);
			return colorLB.toLogoList();
		}
		
	}
}
