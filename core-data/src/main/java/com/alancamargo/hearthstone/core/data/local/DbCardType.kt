package com.alancamargo.hearthstone.core.data.local

enum class DbCardType(val string: String) {

    HERO(string = "Hero"),
    MINION(string = "Minion"),
    SPELL(string = "Spell"),
    ENCHANTMENT(string = "Enchantment"),
    WEAPON(string = "Weapon"),
    HERO_POWER(string = "Hero Power"),
    LOCATION(string = "Location")
}
