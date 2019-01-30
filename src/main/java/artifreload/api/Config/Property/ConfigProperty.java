package artifreload.api.Config.Property;

import net.minecraftforge.common.config.Configuration;


public abstract class ConfigProperty {


public Type type;
public String name;
public String category;
public String description;

public ConfigProperty(Type type, String name, String category, String description) {

	this.type = type;
	this.name = name;
	this.category = category;
	this.description = description;
}

public void formatDescription() {

	if (!this.description.isEmpty()) {
		this.description += Configuration.NEW_LINE;
	}
}

public enum Type {
	INTEGER,
	INTEGER_ARRAY,
	FLOAT,
	DOUBLE_ARRAY,
	BOOLEAN,
	STRING,
	STRING_ARRAY
}
}
