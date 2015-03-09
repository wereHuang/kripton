package com.abubusoft.kripton.binder.schema;


/**
 * This bean stores mapping between an XML/JSON/DB element and a POJO field
 * 
 * @author bulldog
 * 
 */
public class ElementSchema extends AbstractSchema {

	protected String wrapperName;

	public String getWrapperName() {
		return wrapperName;
	}

	public void setWrapperName(String xmlWrapperName) {
		this.wrapperName = xmlWrapperName;
	}

	private boolean data;

	private boolean list = false;
	
	private boolean array=false;
	
	private int arraySize;

	public int getArraySize() {
		return arraySize;
	}

	public void setArraySize(int arraySize) {
		this.arraySize = arraySize;
	}

	public boolean isArray() {
		return array;
	}

	public void setArray(boolean array) {
		this.array = array;
	}

	private Class<?> parameterizedType;

	/**
	 * Check if this is a java.util.List filed, such as List<T>
	 * 
	 * @return true or false
	 */
	public boolean isList() {
		return list;
	}

	/**
	 * Set if this is a java.util.List field or not.
	 * 
	 * @param collection
	 */
	public void setList(boolean list) {
		this.list = list;
	}

	/**
	 * Get parameterized type for a java.util.List field.
	 * 
	 * @return parameterized type.
	 */
	public Class<?> getParameterizedType() {
		return this.parameterizedType;
	}

	/**
	 * Set parameterized type for a java.util.List field
	 * 
	 * @param type
	 */
	public void setParameterizedType(Class<?> type) {
		this.parameterizedType = type;
	}

	/**
	 * indica se ha un nome da usare come wrapper per incapsulare la lista.
	 * 
	 * @return
	 */
	public boolean hasWrapperName() {
		return wrapperName != null;
	}

	/**
	 * Indicates if the string content of the field should be put in a CDATA container on serialization
	 * 
	 * @return true or false
	 */
	public boolean isData() {
		return data;
	}

	/**
	 * Set if the string content of the field should be put in a CDATA container on serialization
	 * 
	 * @param data
	 */
	public void setData(boolean data) {
		this.data = data;
	}
}