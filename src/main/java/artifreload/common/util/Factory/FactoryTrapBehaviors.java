package artifreload.common.util.Factory;

import net.minecraft.item.Item;

import artifreload.api.ITrapAPI;
import artifreload.api.interfaces.IBehaviorITrap;
import artifreload.common.block.EBlock.ITrap;


public class FactoryTrapBehaviors implements ITrapAPI {


@Override
public void addArrowTrapBehavior(Item item, IBehaviorITrap behavior) {
	ITrap.dispenseBehaviorRegistry.putObject(item, behavior);
}
}

