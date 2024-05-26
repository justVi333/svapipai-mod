package svapipai.content;

import arc.graphics.Color;
import arc.struct.ObjectSet;
import mindustry.ai.*;
import mindustry.content.Fx;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import svapipai.ai.*;
import svapipai.override.HealerUnitType;

import static mindustry.content.StatusEffects.*;

public class SvapipaiUnits
{
    public static UnitType
    /*palladium*/ palladiumCrawler, palladiumPiranha, palladiumSparrow, palladiumCaterpillar,
    repairer;

    public static void load()
    {
        palladiumCrawler = new UnitType("palladium-crawler") //legs
        {{
            //localizedName = "Palladium Crawler";
            health = 600;
            speed = 1.2f;
            armor = 2;
            hitSize = 17.5f;
            drag = 0.08f;
            accel = 0.08f;
            rotateSpeed = 6;
            legCount = 6;
            legLength = 12;
            legForwardScl = 0.8f;
            legMoveSpace = 1.4f;
            legGroupSize = 3;
            legMinLength = 0.2f;
            legMaxLength = 1f;
            legPhysicsLayer = false;
            hovering = true;
            groundLayer = 73;
            shadowElevation = 0.2f;
            targetAir = true;
            outlineColor = Color.valueOf("2d2f39"); //Pal.darkOutline

            weapons.add(new Weapon("svapipai-palladium-crawler-weapon")
            {{
                x = 5;
                y = 0;
                reload = 60;
                range = 55;
                inaccuracy = 15;
                shake = 1;
                top = false;
                mirror = true;
                alternate = true;
                ejectEffect = Fx.casing2;

                shoot.shots = 4;
                shoot.shotDelay = 4;

                bullet = new BasicBulletType(4f, 20)
                {{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    splashDamage = 20;
                    splashDamageRadius = 30;
                    hitEffect = Fx.blastExplosion;
                    backColor = Color.valueOf("f37f6f");
                    frontColor = Color.valueOf("e8bda4");
                }};
            }});

            abilities.add(new ShieldRegenFieldAbility(20, 80, 60f * 5, 20f));

            immunities = ObjectSet.with(burning, melting);

            constructor = LegsUnit::create;
        }};

        palladiumPiranha = new UnitType("palladium-piranha") //naval
        {{
            //localizedName = "Palladium Piranha";
            health = 1200;
            speed = 1f;
            armor = 2;
            hitSize = 15;
            drag = 0.08f;
            accel = 0.08f;
            rotateSpeed = 3;
            naval = true;
            hovering = true;
            targetAir = true;
            targetGround = true;
            outlineColor = Color.valueOf("2d2f39"); //Pal.darkOutline

            weapons.add(new Weapon("svapipai-palladium-piranha-weapon")
            {{
                x = 5.5f;
                y = -1;
                reload = 60;
                range = 55;
                inaccuracy = 15;
                shake = 1;
                rotateSpeed = 4;
                rotate = true;
                top = false;
                mirror = true;
                alternate = false;
                ejectEffect = Fx.casing2;

                shoot.shots = 2;
                shoot.shotDelay = 10;

                bullet = new BasicBulletType(3.75f, 40)
                {{
                    width = 12f;
                    height = 14f;
                    lifetime = 60f;
                    splashDamage = 20;
                    splashDamageRadius = 60;
                    hitEffect = Fx.massiveExplosion;
                    backColor = Color.valueOf("f37f6f");
                    frontColor = Color.valueOf("e8bda4");
                }};
            }});

            abilities.add(new ShieldRegenFieldAbility(20, 100, 60f * 5, 20f));

            immunities = ObjectSet.with(burning, melting);

            constructor = UnitWaterMove::create;
        }};

        palladiumSparrow = new UnitType("palladium-sparrow") //flying
        {{
            //localizedName = "Palladium Sparrow";
            health = 400;
            speed = 1.6f;
            armor = 2;
            hitSize = 15;
            drag = 0.065f;
            accel = 0.1f;
            rotateSpeed = 6;
            engineOffset = 6.5f;
            flying = true;
            hovering = true;
            targetAir = true;
            targetGround = true;
            outlineColor = Color.valueOf("2d2f39"); //Pal.darkOutline

            weapons.add(
            new Weapon("svapipai-palladium-sparrow-weapon")
            {{
                x = 4f;
                y = -0.5f;
                reload = 20;
                range = 60;
                inaccuracy = 10;
                shake = 1;
                top = false;
                mirror = true;
                alternate = true;
                ejectEffect = Fx.casing2;

                shoot.shots = 2;
                shoot.shotDelay = 4;

                bullet = new BasicBulletType(4f, 30)
                {{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    splashDamage = 20;
                    splashDamageRadius = 30;
                    hitEffect = Fx.blastExplosion;
                    backColor = Color.valueOf("f37f6f");
                    frontColor = Color.valueOf("e8bda4");
                }};
            }}
            );

            abilities.add(new ShieldRegenFieldAbility(20, 80, 60f * 5, 20f));

            immunities = ObjectSet.with(burning, melting);

            constructor = UnitEntity::create;
        }};

        palladiumCaterpillar = new UnitType("palladium-caterpillar") //flying
        {{
            //localizedName = "Palladium Caterpillar";
            health = 1400;
            speed = 1f;
            armor = 2;
            hitSize = 15;
            drag = 0.4f;
            segments = 3;
            segmentScl = 3;
            segmentPhase = 5;
            segmentMag = 0.5f;
            rotateSpeed = 6;
            omniMovement = false;
            drawBody = false;
            hovering = false;
            targetAir = true;
            targetGround = true;
            outlineColor = Color.valueOf("2d2f39"); //Pal.darkOutline

            weapons.add(new Weapon("svapipai-palladium-caterpillar-weapon")
            {{
                x = 5f;
                y = 0f;
                reload = 40;
                range = 55;
                inaccuracy = 15;
                shake = 1;
                rotateSpeed = 4;
                rotate = true;
                top = false;
                mirror = true;
                alternate = true;
                ejectEffect = Fx.casing2;

                shoot.shots = 4;
                shoot.shotDelay = 4;

                bullet = new BasicBulletType(4f, 20)
                {{
                    width = 12f;
                    height = 14f;
                    lifetime = 60f;
                    splashDamage = 20;
                    splashDamageRadius = 30;
                    hitEffect = Fx.massiveExplosion;
                    backColor = Color.valueOf("f37f6f");
                    frontColor = Color.valueOf("e8bda4");
                }};
            }});

            abilities.add(new ShieldRegenFieldAbility(40, 140, 60f * 4, 20f));

            immunities = ObjectSet.with(burning, melting);

            constructor = CrawlUnit::create;
        }};

        repairer = new HealerUnitType("repairer") //flying
        {{
            health = 800;
            speed = 1.5f;
            armor = 4;
            hitSize = 15;
            drag = 0.065f;
            accel = 0.1f;
            rotateSpeed = 6;
            engineOffset = 6.5f;
            healAmount = 0.05f;
            healPercent = 0.025f;
            flying = true;
            lowAltitude = true;
            range = 80;
            //outlineColor = Color.valueOf("2d2f39"); //Pal.darkOutline

            abilities.add(new RepairFieldAbility(5f, 60f * 5, 50f));

            UnitCommand healCommnand = new UnitCommand("heal", "defense", u -> new HealerAI());
            defaultCommand = healCommnand;
            commands = new UnitCommand[]
            {
                    UnitCommand.moveCommand,
                    UnitCommand.assistCommand,
                    healCommnand
            };

            constructor = UnitEntity::create;
        }};
    }
}
