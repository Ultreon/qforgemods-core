package com.qtech.filters;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrCrayfish
 */
public class FilterEntry
{
    private ResourceLocation tag;
    private String translationKey;
    private ItemStack icon;
    private boolean enabled = true;
    private List<Item> items = new ArrayList<>();

    public FilterEntry(ResourceLocation tag, ItemStack icon)
    {
        this.tag = tag;
        this.translationKey = String.format("gui.tag_filter.%s.%s", tag.getNamespace(), tag.getPath().replace("/", "."));
        this.icon = icon;
    }

    public ResourceLocation getTag()
    {
        return tag;
    }

    public ItemStack getIcon()
    {
        return this.icon;
    }

    public TranslationTextComponent getName()
    {
        return new TranslationTextComponent(this.translationKey);
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isEnabled()
    {
        return this.enabled;
    }

    void add(Item item)
    {
        this.items.add(item);
    }

    void add(Block block)
    {
        this.items.add(block.asItem());
    }

    void clear()
    {
        this.items.clear();
    }

    public List<Item> getItems()
    {
        return this.items;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        com.qtech.filters.FilterEntry that = (com.qtech.filters.FilterEntry) o;
        return this.tag.equals(that.tag);
    }

    @Override
    public int hashCode()
    {
        return this.tag.hashCode();
    }
}
