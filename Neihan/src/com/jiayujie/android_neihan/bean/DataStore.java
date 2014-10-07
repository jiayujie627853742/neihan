package com.jiayujie.android_neihan.bean;

import java.util.LinkedList;
import java.util.List;
/**
 * 段子列表数据存储区
 * @author jiayujie
 *
 */
public class DataStore {

	private static DataStore dataStore;
	private DataStore(){
		textEntities=new LinkedList<>();
		imageEntities=new LinkedList<>();
	}
	public static DataStore getInstance(){
		if (dataStore==null) {
			dataStore=new DataStore();
		}
		return dataStore;
	}
	private List<TextEntity> textEntities;
	
	private List<TextEntity>imageEntities;
	/**
	 * 把获取到的文本段子列表放到最前边
	 * 这个方法是下拉刷新的操作数据
	 * @param entities
	 */
	public void addTextEntities(List<TextEntity> entities){
		if (entities!=null) {
			textEntities.addAll(0, entities);
		}
	}
	/**
	 * 把获取到的文本段子列表放到最后边
	 * 这个方法是上拉查看旧数据的操作
	 * @param entities
	 */
	public void appendTextEntities(List<TextEntity> entities){
		if (entities!=null) {
			textEntities.addAll(0, entities);
		}
	}
	//===============================
	/**
	 * 把获取到的图片段子列表放到最前边
	 * 这个方法是下拉刷新的操作数据
	 * @param entities
	 */
	public void addImageEntities(List<TextEntity> entities){
		if (entities!=null) {
			imageEntities.addAll(0, entities);
		}
	}
	/**
	 * 把获取到的图片段子列表放到最后边
	 * 这个方法是上拉查看旧数据的操作
	 * @param entities
	 */
	public void appendImageEntities(List<TextEntity> entities){
		if (entities!=null) {
			imageEntities.addAll(0, entities);
		}
	}
	
	//获取文本段子列表
	public  List<TextEntity> getTextEntityes(){
		return textEntities;
	}
	//获取图片段子列表
	public List<TextEntity> getImageEntities(){
		return imageEntities;
	}
	
}
