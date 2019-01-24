package artifreload.common.util.artifact.damage;


import net.minecraft.util.DamageSource;


public class SourceQuicksand extends DamageSource{

public static final DamageSource IN_QUICKSAND = (new DamageSource("drown")).setDamageBypassesArmor();

public SourceQuicksand(String name) {

	super(name);

}



}

