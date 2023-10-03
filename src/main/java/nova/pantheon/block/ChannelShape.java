package nova.pantheon.block;

import net.minecraft.util.StringIdentifiable;

public enum ChannelShape implements StringIdentifiable{
    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west"),

    NORTH_EAST("north-east"),
    NORTH_SOUTH("north-south"),
    NORTH_WEST("north-west"),
    SOUTH_EAST("south-east"),
    EAST_WEST("east-west"),
    SOUTH_WEST("south_west"),

    NORTH_EAST_SOUTH("north-east-south"),
    EAST_SOUTH_WEST("east-south-west"),
    NORTH_WEST_SOUTH("north-west-south"),
    EAST_WEST_NORTH("east-west-north"),

    ALL("all"),
    NONE("none");

    private final String name;

    private ChannelShape(String _name) {
        name = _name;
    }

    @Override
	public String asString() {
		return this.name;
	}
    
    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
