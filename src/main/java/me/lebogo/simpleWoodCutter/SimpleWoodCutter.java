package me.lebogo.simpleWoodCutter;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class SimpleWoodCutter extends JavaPlugin {

    private final static Set<Material> WOOD_MATERIALS = new HashSet<>();

    static {
        WOOD_MATERIALS.add(Material.OAK_LOG);
        WOOD_MATERIALS.add(Material.SPRUCE_LOG);
        WOOD_MATERIALS.add(Material.BIRCH_LOG);
        WOOD_MATERIALS.add(Material.JUNGLE_LOG);
        WOOD_MATERIALS.add(Material.ACACIA_LOG);
        WOOD_MATERIALS.add(Material.DARK_OAK_LOG);
        WOOD_MATERIALS.add(Material.MANGROVE_LOG);
        WOOD_MATERIALS.add(Material.CHERRY_LOG);
        WOOD_MATERIALS.add(Material.BAMBOO_BLOCK);
        WOOD_MATERIALS.add(Material.CRIMSON_STEM);
        WOOD_MATERIALS.add(Material.WARPED_STEM);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic


        for (Material material : WOOD_MATERIALS.toArray(new Material[0])) {
            Material strippedMaterial = Material.getMaterial("STRIPPED_" + material.name());
            if (strippedMaterial != null) {
                WOOD_MATERIALS.add(strippedMaterial);
            }
        }

        for (Material material : WOOD_MATERIALS.toArray(new Material[0])) {
            Material strippedMaterial = Material.getMaterial(material.name().replace("LOG", "WOOD").replace("STEM", "HYPHAE"));
            if (strippedMaterial != null) {
                WOOD_MATERIALS.add(strippedMaterial);
            }
        }

        Set<String> woodTypes = new HashSet<>();
        for (Material woodLog : WOOD_MATERIALS) {
            String woodType = woodLog.name().replace("_LOG", "").replace("_WOOD", "").replace("_STEM", "").replace("_HYPHAE", "").replace("STRIPPED_", "").replace("_BLOCK", "").toLowerCase();
            addPlanksRecipes(woodType, woodLog);
            if (!woodLog.toString().contains("STRIPPED")) {
                addStrippedRecipes(woodLog);
            }

            woodTypes.add(woodType);
        }


        for (String woodType : woodTypes) {
            addWoodRecipes(woodType);
        }


    }

    private void addRecipe(NamespacedKey key, ItemStack result, Material material) {
        getServer().addRecipe(new StonecuttingRecipe(key, result, material));
    }

    private void addStrippedRecipes(Material woodLog) {
        Material strippedMaterial = Material.getMaterial("STRIPPED_" + woodLog.toString());

        if (strippedMaterial == null) {
            return;
        }

        addRecipe(new NamespacedKey(this, "stripped_" + woodLog.toString().toLowerCase()), new ItemStack(strippedMaterial, 1), woodLog);
    }

    private void addPlanksRecipes(String woodType, Material woodLog) {
        Material planksMaterial = Material.getMaterial(woodType.toUpperCase() + "_PLANKS");

        if (planksMaterial == null) {
            return;
        }

        addRecipe(new NamespacedKey(this, woodType + "_planks_" + woodLog.toString().toLowerCase()), new ItemStack(planksMaterial, 5), woodLog);
    }

    private void addWoodRecipes(String woodType) {
        Material planksMaterial = Material.getMaterial(woodType.toUpperCase() + "_PLANKS");

        if (planksMaterial == null) {
            return;
        }

        Material stairMaterial = Material.getMaterial(woodType.toUpperCase() + "_STAIRS");
        Material slabMaterial = Material.getMaterial(woodType.toUpperCase() + "_SLAB");
        Material fenceMaterial = Material.getMaterial(woodType.toUpperCase() + "_FENCE");
        Material fenceGateMaterial = Material.getMaterial(woodType.toUpperCase() + "_FENCE_GATE");
        Material trapdoorMaterial = Material.getMaterial(woodType.toUpperCase() + "_TRAPDOOR");
        Material doorMaterial = Material.getMaterial(woodType.toUpperCase() + "_DOOR");
        Material pressurePlateMaterial = Material.getMaterial(woodType.toUpperCase() + "_PRESSURE_PLATE");
        Material buttonMaterial = Material.getMaterial(woodType.toUpperCase() + "_BUTTON");
        Material signMaterial = Material.getMaterial(woodType.toUpperCase() + "_SIGN");
        Material hangingSignMaterial = Material.getMaterial(woodType.toUpperCase() + "_HANGING_SIGN");


        addRecipe(new NamespacedKey(this, woodType + "_stick"), new ItemStack(Material.STICK, 4), planksMaterial);

        if (stairMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_stairs"), new ItemStack(stairMaterial, 1), planksMaterial);

        if (slabMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_slabs"), new ItemStack(slabMaterial, 2), planksMaterial);

        if (fenceMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_fence"), new ItemStack(fenceMaterial, 1), planksMaterial);

        if (fenceGateMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_fence_gate"), new ItemStack(fenceGateMaterial, 1), planksMaterial);

        if (trapdoorMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_trapdoor"), new ItemStack(trapdoorMaterial, 1), planksMaterial);

        if (doorMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_door"), new ItemStack(doorMaterial, 1), planksMaterial);

        if (pressurePlateMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_pressure_plate"), new ItemStack(pressurePlateMaterial, 1), planksMaterial);

        if (buttonMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_button"), new ItemStack(buttonMaterial, 1), planksMaterial);

        if (signMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_sign"), new ItemStack(signMaterial, 1), planksMaterial);

        if (hangingSignMaterial != null)
            addRecipe(new NamespacedKey(this, woodType + "_hanging_sign"), new ItemStack(hangingSignMaterial, 1), planksMaterial);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
