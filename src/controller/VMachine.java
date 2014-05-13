package controller;

import java.io.File;
import java.util.HashMap;

public class VMachine {

	public static String[] differentTypeVM = {"vmdk","vmx","iso","raw"};
	private String name;
	private String description;
	private HashMap<String, String> listDifferentTypeVM;

	public VMachine() {
		super();
		this.name = "";
		this.description = "";
		this.listDifferentTypeVM = new HashMap<String, String>();
	}
	public VMachine(String name) {
		super();
		this.name = name;
		this.description = "";
		this.listDifferentTypeVM = new HashMap<String, String>();
	}
	public VMachine(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.listDifferentTypeVM = new HashMap<String, String>();
	}

	public void fillInlistDifferentTypeVM(File[] listFiles) {

		for (int i=0;i<listFiles.length;i++) {
			for (int j=0;j<differentTypeVM.length;j++) {
				String[] temp = listFiles[i].getName().split("\\.");
				if (temp.length != 0) {
					if (temp[temp.length-1].equals(differentTypeVM[j])) {
						listDifferentTypeVM.put(differentTypeVM[j],listFiles[i].getName());
					}
				}
			}
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HashMap<String, String> getListDifferentTypeVM() {
		return listDifferentTypeVM;
	}
	public void setListDifferentTypeVM(HashMap<String, String> listDifferentTypeVM) {
		this.listDifferentTypeVM = listDifferentTypeVM;
	}
	@Override
	public String toString() {
		return "VMachine [name=" + name + ", description=" + description
				+ ", listDifferentTypeVM=" + listDifferentTypeVM + "]";
	}


}
