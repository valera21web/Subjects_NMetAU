package objects;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFilter extends FileFilter {
	private String[] extensions;
	
	@SuppressWarnings("unused")
	private String description;
	
	public ExtensionFilter(String description, String extension)
    {
		this(description, new String[] { extension });
	}
	
	public ExtensionFilter(String description, String extensions[])
    {
		this.description = description;
		this.extensions = extensions.clone();
	}
	
	public boolean accept(File file)
    {
		if (file.isDirectory()) {
			return true;
		}
		String path = file.getAbsolutePath();
		for (int i = 0; i < this.extensions.length; i++) {
			String ext = this.extensions[i];
			if (path.endsWith(ext) && (path.charAt(path.length() - ext.length()) == '.'))
            {
				return true;
			}
		}
		return false;
    }

	@Override
	public String getDescription() {
		return null;
	}
}
