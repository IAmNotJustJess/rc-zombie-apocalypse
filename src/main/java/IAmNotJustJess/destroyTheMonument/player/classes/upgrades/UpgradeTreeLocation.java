package IAmNotJustJess.destroyTheMonument.player.classes.upgrades;

public enum UpgradeTreeLocation {
    BASIC_ONE,
    BASIC_TWO,
    SPECIAL_ONE,
    SPECIAL_TWO,
    SKILL_ONE,
    SKILL_TWO,
    ULTIMATE_ONE,
    ULTIMATE_TWO
}

/*
 * BASIC_ONE - Pierwsze podstawowe ulepszenie
 * BASIC_TWO - Drugie podstawowe ulepszenie, wymaga kupienia BASIC_ONE (przynajmniej jeden poziom).
 * SPECIAL_ONE - Pierwsze specjalne (nie działające na umiejętności), wymaga kupienia BASIC_TWO (przynajmniej jedne poziom).
 * SPECIAL_TWO - Drugie specjalnie (nie działające na umiejętności), wymaga kupienia SPECIAL_ONE (przynajmniej jedne poziom).
 * SKILL_ONE - Pierwsze umiejętności, wymaga kupienia BASIC_TWO (przynajmniej jedne poziom).
 * SKILL_TWO - Drugie umiejętnośc, wymaga kupienia SKILL_ONE (przynajmniej jeden poziom).
 * ULTIMATE_ONE - Pierwsze umiejętności ostatecznej, wymaga kupienia BASIC_TWO (przynajmniej jedne poziom).
 * ULTIMATE_TWO - Drugie umiejętności ostatecznej, wymaga kupienia ULTIMATE_ONE (przynajmniej jeden poziom).
 */