package swtExample;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SimpleShell {

	static Image dogPhoto = null;
	
	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		shell.setLayout(layout);

		Label dogName = new Label(shell, SWT.SINGLE);
		Text dogNameText = new Text(shell, 0);

		dogName.setText("Dog's name");
		shell.setText("A new window");

		Label breed = new Label(shell, SWT.SINGLE);
		breed.setText("breed");

		Combo list = new Combo(shell, SWT.SINGLE);
		list.setItems(new String[] { "dog1", "dog2" });

		Label picture = new Label(shell, SWT.SINGLE);
		picture.setText("Picture");

		Canvas dogPicture = new Canvas(shell, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 80;
		gridData.heightHint = 80;
		dogPicture.setLayoutData(gridData);

		dogPicture.addPaintListener(new PaintListener() {

			@SuppressWarnings("unused")
			public void paintControl(final PaintEvent event) {

				if (dogPhoto != null) {

					event.gc.drawImage(dogPhoto, 0, 0);

				}

			}

		});

		Button browse = new Button(shell, SWT.PUSH);
		browse.setText("Browse...");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalIndent = 5;
		browse.setLayoutData(gridData);
		browse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {

				String fileName = new FileDialog(shell).open();

				if (fileName != null) {

					dogPhoto = new Image(display, fileName);
					
					GridData gd = new GridData();
					gd.widthHint = dogPhoto.getBounds().width;
					gd.heightHint = dogPhoto.getBounds().height;
					dogPicture.setLayoutData(gd);
					dogPicture.redraw();
					shell.pack();
				}

			}

		});

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		if (dogPhoto != null)
			dogPhoto.dispose();
		display.dispose();
	}
}
