package artifreload.api.Config.Property;

public class ConfigPropertyInt extends ConfigProperty {


public int minValueInt;
public int maxValueInt;
public int valueInt;

public ConfigPropertyInt( String name, String category, String description, int defaultValue, int minValueInt, int maxValueInt) {

	super(Type.INTEGER, name, category, description);

	this.valueInt = defaultValue;
	this.minValueInt = minValueInt;
	this.maxValueInt = maxValueInt;

	this.formatDescription();
}

public int get() {
	return this.valueInt;
}

public void set(int value) {
	this.valueInt = value;
}
}
