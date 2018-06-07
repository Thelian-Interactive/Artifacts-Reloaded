package artifreload.common.util.artifact.Factory;

import net.minecraft.item.Item;

import artifreload.common.block.IBlock.ITrap;


public class FactoryTrapBehaviors implements ITrapAPI {


@Override
public void addArrowTrapBehavior(Item item, IBehaviorITrap behavior) {
	ITrap.dispenseBehaviorRegistry.putObject(item, behavior);
}
}

