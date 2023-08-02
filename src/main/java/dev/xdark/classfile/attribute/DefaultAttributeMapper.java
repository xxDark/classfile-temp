package dev.xdark.classfile.attribute;

public final class DefaultAttributeMapper implements AttributeMapper {

	@Override
	public AttributeInfo<?> getInfo(AttributeLocation location, String name) {
		AttributeInfo<?> info = AttributeInfo.byName(name);
		if (info != null && !info.locations().contains(location)) {
			info = null;
		}
		return info;
	}
}
