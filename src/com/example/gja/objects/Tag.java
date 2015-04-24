package com.example.gja.objects;

import com.vaadin.shared.ui.colorpicker.Color;


public class Tag {
	
	private Color color;
	private String name;

	/**
	 * General constructor for tags
	 * @param inputColor	selected color
	 * @param inputName		selected name
	 */
	public Tag(Color inputColor, String inputName) {
		color = inputColor;
		name = inputName;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
