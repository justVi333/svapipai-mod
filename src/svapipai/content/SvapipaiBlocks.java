package svapipai.content;

import arc.graphics.Color;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.meta.*;

import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;
import static svapipai.content.SvapipaiItems.*;
import static svapipai.content.SvapipaiStatuses.*;
import static svapipai.content.SvapipaiUnits.*;

public class SvapipaiBlocks
{
    public static Block
    /*ores*/ palladiumOre,
    /*defense walls*/leadWall, leadWallLarge, palladiumWall, palladiumWallLarge,
    /*drills*/ stoneCrusher,
    /*power nodes*/ strongPowerNode,
    /*turrets*/ spreader,
    /*units*/ complexConstructor;

    public static void load()
    {
        palladiumOre = new OreBlock("ore-palladium", palladium)
        {{
                localizedName = "Palladium Ore";
                oreDefault = false;
                oreThreshold = 0.93f;
                oreScale = 25.7f;
        }};

        leadWall = new Wall("lead-wall")
        {{
            localizedName = "Lead Wall";
            health = 320;
            size = 1;
            requirements(Category.defense, with(
                    lead,6
            ));
        }};
        leadWallLarge = new Wall("lead-wall-large")
        {{
            localizedName = "Large Lead Wall";
            health = 1280;
            size = 2;
            requirements(Category.defense, with(
                    lead,24
            ));
        }};

        palladiumWall = new Wall("palladium-wall")
        {{
            localizedName = "Palladium Wall";
            health = 800;
            size = 1;
            requirements(Category.defense, with(
                    palladium,6
            ));
        }};
        palladiumWallLarge = new Wall("palladium-wall-large")
        {{
            localizedName = "Large Palladium Wall";
            health = 3200;
            size = 2;
            requirements(Category.defense, with(
                    palladium,24
            ));
        }};

        stoneCrusher = new WallCrafter("stone-crusher")
        {{
            localizedName = "Stone Crusher";
            health = 160;
            size = 2;
            drillTime = 360;
            output = sand;
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.085f;
            attribute = Attribute.sand;
            //attributes.set(Attribute.sand, 0.15f);
            requirements(Category.production, with(
                    copper,60,
                    lead,60,
                    graphite,30
            ));
        }};

        strongPowerNode = new PowerNode("strong-power-node")
        {{
            localizedName = "Strong Power Node";
            health = 300;
            size = 2;
            maxNodes = 20;
            laserRange = 17.5f;
            requirements(Category.power, with(
                    lead,40,
                    palladium,10,
                    silicon,5
            ));
        }};

        spreader = new ItemTurret("spreader")
        {{
            localizedName = "Spreader";
            health = 800;
            size = 2;
            shake = 0.5f;
            range = 150;
            ammoPerShot = 1;
            inaccuracy = 10;
            reload = 4;
            recoil = 2;
            rotateSpeed = 12;
            targetAir = false;
            shootSound = Sounds.flame;
            ammoUseEffect = Fx.none;

            shoot.shotDelay = 2;
            shoot.shots = 3;

            ammo
            (
                    sand,  new BasicBulletType(4f, 2) {{
                        width = 7f;
                        height = 9f;
                        lifetime = 65f;
                        ammoMultiplier = 3;
                        drag = 0.015f;
                        knockback = 0.5f;
                        lightOpacity = 0.01f;
                        backColor = Color.valueOf("c8a58f");
                        frontColor = Color.valueOf("ffd9b7");
                        status = cloggedMechanism;
                        statusDuration = 120;
                    }},
                    scrap,  new BasicBulletType(4f, 2) {{
                        width = 7f;
                        height = 9f;
                        lifetime = 65f;
                        ammoMultiplier = 3;
                        drag = 0.0125f;
                        lightOpacity = 0.01f;
                        backColor = Color.valueOf("c8a58f");
                        frontColor = Color.valueOf("ffd9b7");

                        fragBullets = 3;
                        fragBullet = new BasicBulletType(3f, 2)
                        {{
                            width = 4f;
                            height = 6f;
                            lifetime = 20f;
                            backColor = Color.valueOf("c8a58f");
                            frontColor = Color.valueOf("ffd9b7");
                            despawnEffect = Fx.none;
                            //collidesGround = false;
                        }};
                    }}
            );

            requirements(Category.turret, with(
                    sand,100,
                    copper,120,
                    lead,60
            ));
        }};

        complexConstructor = new UnitFactory("complex-constructor")
        {{
            localizedName = "Complex Constructor";
            health = 1200;
            size = 2;
            floating = true;

            consumePower(3f);
            consumeLiquid(cryofluid, 0.25f);


            requirements(Category.units, with
            (
                    palladium,400,
                    thorium,350,
                    silicon,500,
                    lead,200
            ));

            plans = Seq.with
            (
                new UnitPlan(palladiumCrawler, 60 * 20f, with(palladium, 50, silicon, 60, lead, 90)),
                new UnitPlan(palladiumPiranha, 60 * 20f, with(palladium, 50, silicon, 60, metaglass, 40)),
                new UnitPlan(palladiumSparrow, 60 * 20f, with(palladium, 50, silicon, 60, graphite, 50)),
                new UnitPlan(palladiumCaterpillar, 60 * 20f, with(palladium, 50, silicon, 60, titanium, 80))
            );
        }};

        /*Blocks.scatter.stats.add(Stat.ammo, StatValues.ammo(OrderedMap.of
        (
                palladium, new BasicBulletType(6f, 12) {{
                width = 8f;
                height = 10f;
                lifetime = 60f;
                ammoMultiplier = 2;
                reloadMultiplier = 0.75f;
                shootEffect = Fx.shootSmall;
                hitEffect = Fx.flakExplosion;
                splashDamage = 80;
                splashDamageRadius = 28;
                backColor = Color.valueOf("f37f6f");
                frontColor = Color.valueOf("e8bda4");
                }}
        )));*/
    }
}
