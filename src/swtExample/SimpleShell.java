package swtExample;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SimpleShell {

	public static void main(String[] args) {
		
		Display display = new Display();
		
		Shell shell = new Shell(display);
		
		GridLayout layout = new GridLayout();
		
		shell.setLayout(layout);
	}

}
