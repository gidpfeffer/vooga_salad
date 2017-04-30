package XML.directorytree.classes;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import XML.directorytree.interfaces.BoolSwitch;
import XML.directorytree.interfaces.Pair;

public class DirectoryNode {

	private Collection<String> files;
	private Collection<DirectoryNode> subDirectories;
	private String rootDirectory;
	
	public DirectoryNode(String rootDirectory){
		this(rootDirectory, new ArrayList<>(), new ArrayList<>());
	}
	
	public DirectoryNode(String rootDirectory, Collection<String> files, Collection<DirectoryNode> directories){
		this.rootDirectory = rootDirectory;
		this.files = files;
		this.subDirectories = directories;
	}
	
	private boolean sharesFilepathWith(String filepath){
		if(filepath == null) return false;
		return filepath.split(rootDirectory).length > 1  
				&& filepath.split(rootDirectory)[1].length() > 1
				&& filepath.split(rootDirectory)[1].startsWith("/")
				|| filepath.equals(rootDirectory);
	}
	
	public boolean addDirectoryToTree(String filepath){
		if(isInvalidString(filepath)) return false;
		Pair<Integer, Collection<DirectoryNode>> finder = new ConcretePair<>(-1, subDirectories);
		findProperDirectory(this, filepath, 0, finder);
		finder.getValue().add(new DirectoryNode(filepath));
		return true;
	}
	
	private boolean isInvalidString(String filepath){
		return (filepath == null 
				|| filepath.length() == 0
				|| directoryExistsAlready(this, filepath).getKey()
				|| !this.sharesFilepathWith(filepath) );
	}
	
	public boolean addFileToTree(String filepath){
		if(isInvalidString(filepath) || filepath.indexOf("/") == -1 ) return false;
		if(filepath.split(rootDirectory).length < 2) return false;
		
		Pair<Boolean, DirectoryNode> indicator =  directoryExistsAlready(this, 
				filepath.substring(0, filepath.lastIndexOf("/")));
		if(!indicator.getKey()) return false;
		indicator.getValue().addFile(filepath);
		return true;
	}
	
	
	
	private void findProperDirectory(DirectoryNode root, String filepath, int depth, 
			Pair<Integer, Collection<DirectoryNode>> best){
		if(root == null || !root.sharesFilepathWith(filepath)) return;
		if(best.getKey() < depth){
			if(newIntermediateDirectoryExists(root, filepath)){
				String intermedDirectory = root.rootDirectory + getIntermediateDirectory(root, filepath);
				if(!directoryExistsAlready(root, intermedDirectory).getKey()){
					DirectoryNode newDir = new DirectoryNode(intermedDirectory);
					root.subDirectories.add(newDir);
					findProperDirectory(newDir, filepath, depth + 1, best);
				}
			}
			best.setKey(depth);
			best.setValue(root.subDirectories);
		}
		for(DirectoryNode dir: root.getSubDirectories()){
			findProperDirectory(dir, filepath, depth + 1, best);
		}
	}
	
	private void addFile(String filepath){
		files.add(filepath);
	}
	
	private Pair<Boolean, DirectoryNode> directoryExistsAlready(DirectoryNode root, String filepath) {
		BoolSwitch bswitch = new ConcreteBoolSwitch(false);
		Pair<BoolSwitch, DirectoryNode> indicator = new ConcretePair<>(bswitch, new DirectoryNode("foo"));
		directoryExistsHelper(root, filepath, indicator);
		return new ConcretePair<>(indicator.getKey().getState(), indicator.getValue());
	}
	
	private void directoryExistsHelper(DirectoryNode root, String filepath, Pair<BoolSwitch, DirectoryNode> pair){
		if(root == null) return;
		if(root.rootDirectory.equals(filepath)){
			pair.getKey().setTrue();
			pair.setValue(root);
			return;
		}
		for(DirectoryNode child: root.getSubDirectories()){
			directoryExistsHelper(child,filepath, pair);
		}
	}
	
	private boolean newIntermediateDirectoryExists(DirectoryNode root, String newDir){
		if(!root.sharesFilepathWith(newDir) || root.getRootDirectory().equals(newDir)) return false;
		String[] splitDir = newDir.split(root.getRootDirectory());
		return splitDir.length > 1 && splitDir[1].length() > 2 && splitDir[1].indexOf("/", 1) != -1;
	}
	
	private String getIntermediateDirectory(DirectoryNode root, String newDir){
		String[] splitDir = newDir.split(root.getRootDirectory());
		return splitDir[0] + splitDir[1].substring(0, splitDir[1].indexOf("/", 1));
	}
	
	public String getRootDirectory(){
		return rootDirectory;
	}
	
	public Collection<DirectoryNode> getSubDirectories(){
		return Collections.unmodifiableCollection(subDirectories);
	}
	
	public Collection<String> getFiles(){
		return Collections.unmodifiableCollection(files);
	}
	
	public void printTree(){
		printHelper("root", this);
	}
	
	private void printHelper(String parent, DirectoryNode child){
		if(child == null) return;
		System.out.println("DIRECTORY: " + parent + " --> " + child.getRootDirectory());
		for(String file: child.files){
			System.out.println("FILE: " + parent + " --> " + file);
		}
		for(DirectoryNode nextGen: child.subDirectories){
			printHelper(child.getRootDirectory(), nextGen);
		}
	}
	
}
