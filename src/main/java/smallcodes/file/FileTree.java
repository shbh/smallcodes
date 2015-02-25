package smallcodes.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTree {

	public FileTree(String location) {
		super();
		this.location = location;
	}

	private String location;

	public void tree() throws IOException {

		File root = new File(location);
		Folder folder = null;
		if (root.exists()) {
			File[] allFiles = root.listFiles();
			folder = new Folder(location, root.getCanonicalPath());
			readDirectory(folder, allFiles);

		}

		readFolder(folder);

	}

	private void readFolder(Folder folder) {
		System.out.println(folder.toString());
		// List of Folder
		List<Folder> folders = folder.getFolders();
		System.out.println("Sub-Folder under Folder :" + folder.getPath());
		System.out.println("*******************************************");

		for (Folder subfolder : folders) {
			System.out.println(subfolder.toString());
			readFolder(subfolder);
		}
		// List of Files
		List<FolderFile> files = folder.getFiles();
		System.out.println("File under Folder :" + folder.getPath());
		System.out.println("*******************************************");
		for (FolderFile folderFile : files) {

			System.out.println(folderFile);
		}

	}

	private void readDirectory(Folder folder, File[] allFiles)
			throws IOException {

		for (File file : allFiles) {
			if (file.isDirectory()) {
				Folder subfolder = new Folder(file.getName(),
						file.getCanonicalPath());
				folder.addFolder(subfolder);
				readDirectory(subfolder, file.listFiles());

			} else {
				folder.addFolderFile(file.getName(), file.getCanonicalPath());
			}
		}
	}
}

class Folder {
	private List<Folder> folders = new ArrayList<Folder>();
	private List<FolderFile> files = new ArrayList<FolderFile>();
	private String name;
	private String path;

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public List<FolderFile> getFiles() {
		return files;
	}

	public void setFiles(List<FolderFile> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Folder(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public void addFolder(Folder subfolder) {
		folders.add(subfolder);

	}

	public void addFolderFile(String name, String canonicalPath) {
		files.add(new FolderFile(name, canonicalPath));

	}

	@Override
	public String toString() {
		return "Folder [name=" + name + ", path=" + path + "]";
	}

}

class FolderFile {
	private String name;
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FolderFile(String name, String path) {
		this.name = name;
		this.path = path;
	}

	@Override
	public String toString() {
		return "FolderFile [name=" + name + ", path=" + path + "]";
	}

}
