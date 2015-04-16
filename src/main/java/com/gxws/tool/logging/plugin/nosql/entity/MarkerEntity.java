package com.gxws.tool.logging.plugin.nosql.entity;

import java.io.Serializable;

import org.apache.logging.log4j.Marker;

/**
 * marker信息
 * 
 * @author zhuwl120820@gxwsxx.com
 *  2015年3月16日上午11:25:55
 *
 */
public class MarkerEntity implements Serializable {

	private static final long serialVersionUID = 5141613202541571898L;

	private String name;

	private MarkerEntity[] parents;

	public MarkerEntity(Marker m) {
		if (null == m) {
			return;
		}
		setName(m.getName());
		Marker[] pm = m.getParents();
		if (pm != null) {
			MarkerEntity[] pe = new MarkerEntity[pm.length];
			for (int i = 0; i < pm.length; i++) {
				pe[i] = new MarkerEntity(pm[i]);
			}
			setParents(pe);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MarkerEntity[] getParents() {
		return parents;
	}

	public void setParents(MarkerEntity[] parents) {
		this.parents = parents;
	}

}
