package artifreload.common.util.artifact.damage;

import net.minecraft.util.DamageSource;


public class SourceSword extends DamageSource{

public static DamageSource instance;

public SourceSword(String name) {
	super(name);
}
}
