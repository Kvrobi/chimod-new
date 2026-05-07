package net.kvrobi.chimod.world.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public record LionShapedRecipePattern(int width, int height, NonNullList<Ingredient> ingredients, Map<Character, Ingredient> key, List<String> patternStrings) {

    public static final MapCodec<LionShapedRecipePattern> MAP_CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.unboundedMap(Codec.STRING.xmap(s -> s.charAt(0), String::valueOf), Ingredient.CODEC_NONEMPTY).fieldOf("key").forGetter(LionShapedRecipePattern::key),
            Codec.STRING.listOf().fieldOf("pattern").forGetter(LionShapedRecipePattern::patternStrings)
    ).apply(inst, LionShapedRecipePattern::of));

    public static final StreamCodec<RegistryFriendlyByteBuf, LionShapedRecipePattern> STREAM_CODEC = StreamCodec.of(
            LionShapedRecipePattern::toNetwork, LionShapedRecipePattern::fromNetwork
    );

    public static LionShapedRecipePattern of(Map<Character, Ingredient> key, List<String> pattern) {
        int height = pattern.size();
        int width = pattern.getFirst().length();
        NonNullList<Ingredient> ingredients = NonNullList.create();

        for (String row : pattern) {
            if (row.length() != width) {
                throw new IllegalArgumentException("Pattern rows must all be the same length!");
            }
            for (int i = 0; i < row.length(); i++) {
                char c = row.charAt(i);
                if (c == ' ') {
                    ingredients.add(Ingredient.EMPTY);
                } else {
                    Ingredient ingredient = key.get(c);
                    if (ingredient == null) {
                        throw new IllegalArgumentException("Pattern references symbol '" + c + "' but it is not defined in the key!");
                    }
                    ingredients.add(ingredient);
                }
            }
        }
        return new LionShapedRecipePattern(width, height, ingredients, key, pattern);
    }

    public boolean matches(CraftingInput input) {
        for (int i = 0; i <= input.width() - this.width; ++i) {
            for (int j = 0; j <= input.height() - this.height; ++j) {
                if (this.matches(input, i, j, true)) return true;
                if (this.matches(input, i, j, false)) return true;
            }
        }
        return false;
    }

    private boolean matches(CraftingInput input, int offsetX, int offsetY, boolean mirror) {
        for (int i = 0; i < input.width(); ++i) {
            for (int j = 0; j < input.height(); ++j) {
                int k = i - offsetX;
                int l = j - offsetY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    if (mirror) {
                        ingredient = this.ingredients.get(this.width - k - 1 + l * this.width);
                    } else {
                        ingredient = this.ingredients.get(k + l * this.width);
                    }
                }
                if (!ingredient.test(input.getItem(i + j * input.width()))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void toNetwork(RegistryFriendlyByteBuf buf, LionShapedRecipePattern pattern) {
        buf.writeVarInt(pattern.width);
        buf.writeVarInt(pattern.height);
        for (Ingredient ingredient : pattern.ingredients) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
        }
    }

    private static LionShapedRecipePattern fromNetwork(RegistryFriendlyByteBuf buf) {
        int width = buf.readVarInt();
        int height = buf.readVarInt();
        NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
        }
        return new LionShapedRecipePattern(width, height, ingredients, Map.of(), List.of());
    }
}