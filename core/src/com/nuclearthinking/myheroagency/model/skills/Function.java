package com.nuclearthinking.myheroagency.model.skills;

import com.nuclearthinking.myheroagency.model.entity.systems.ActorSystem;
import com.nuclearthinking.myheroagency.model.skills.funcs.Func;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Izonami on 03.08.2016.
 */
@Slf4j
public class Function {

    public static final int MAX_STAT_VALUE = 100;

    private static final double[] WITbonus = new double[MAX_STAT_VALUE]; //castSpeed, mResist
    private static final double[] MENbonus = new double[MAX_STAT_VALUE]; //mpMax, mDef
    private static final double[] INTbonus = new double[MAX_STAT_VALUE]; //mAtkDmg, skillChance
    private static final double[] STRbonus = new double[MAX_STAT_VALUE]; //atkDmg, pDef
    private static final double[] DEXbonus = new double[MAX_STAT_VALUE]; //atackSpeed, speed
    private static final double[] CONbonus = new double[MAX_STAT_VALUE]; //hpMax, pResist

    private static final double P_ATK_MOD = 0.6;
    private static final double MAX_HP_MOD = 1.5;
    private static final double MAX_MP_MOD = 1.5;
    private static final double M_ATK_MOD = 1.5;
    private static final double M_ATK_SPEED_MOD = 0.4;
    private static final double RUN_SPEED_MOD = 0.5;

    //TODO: Это полностью переделать, желательно брать из файла или придумать формулу, сейчас просто затычка для проверки
    static {
        for (int i = 0; i < MAX_STAT_VALUE; i++) {
            STRbonus[i] = (200.0 + (i+.0)) / 100.0;
            INTbonus[i] = (200.0 + (i+.0)) / 100.0;
            CONbonus[i] = (200.0 + (i+.0)) / 100.0;
            MENbonus[i] = (200.0 + (i+.0)) / 100.0;
            WITbonus[i] = (200.0 + (i+.0)) / 100.0;
            DEXbonus[i] = (200.0 + (i+.0)) / 100.0;
        }
    }

    private static class FuncPAtkMod extends Func {

        private static final FuncPAtkMod func = new FuncPAtkMod();

        private FuncPAtkMod()
        {
            super(Stats.POWER_ATTACK, 0x20, null);
        }

        @Override
        public void calc(@NonNull final Env env) {
            log.debug("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            log.debug("STR: "+env.getCharacter().getBaseSTR());
            log.debug("ConBonus: "+STRbonus[env.getCharacter().getBaseSTR()]);
            log.debug("All Env :"+env.getValue() * STRbonus[env.getCharacter().getBaseSTR()] * P_ATK_MOD);
            env.setValue(env.getValue() * STRbonus[env.getCharacter().getBaseSTR()] * env.getCharacter().getLevel() * P_ATK_MOD);
        }
    }

    private static class FuncMAtkMod extends Func{

        private static final FuncMAtkMod func = new FuncMAtkMod();

        private FuncMAtkMod(){
            super(Stats.MAGIC_ATTACK, 0x20, null);
        }

        @Override
        public void calc(@NonNull final Env env) {
            log.debug("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            log.debug("INT: "+env.getCharacter().getBaseINT());
            log.debug("IntBonus: "+INTbonus[env.getCharacter().getBaseINT()]);
            log.debug("All Env :"+env.getValue() * INTbonus[env.getCharacter().getBaseINT()] * M_ATK_MOD);
            env.setValue(env.getValue() * INTbonus[env.getCharacter().getBaseINT()] * env.getCharacter().getLevel() * M_ATK_MOD);
        }
    }

    private static class FuncMaxHpMod extends Func {

        private static final FuncMaxHpMod func = new FuncMaxHpMod();

        public FuncMaxHpMod() {
            super(Stats.MAX_HP, 0x20, null);
        }

        @Override
        public void calc(@NonNull final Env env) {
            log.debug("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            log.debug("CON: "+env.getCharacter().getBaseCON());
            log.debug("ConBonus: "+CONbonus[env.getCharacter().getBaseCON()]);
            log.debug("All Env :"+env.getValue() * CONbonus[env.getCharacter().getBaseCON()] * MAX_HP_MOD);
            env.setValue(env.getValue() * CONbonus[env.getCharacter().getBaseCON()] * MAX_HP_MOD);
        }
    }

    private static class FuncMaxMpMod extends Func{

        private static final FuncMaxMpMod func = new FuncMaxMpMod();

        public FuncMaxMpMod() {
            super(Stats.MAX_MP, 0x20, null);
        }

        @Override
        public void calc(@NonNull final Env env) {
            log.debug("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            log.debug("MEN: "+env.getCharacter().getBaseMEN());
            log.debug("MenBonus: "+MENbonus[env.getCharacter().getBaseMEN()]);
            log.debug("All Env :"+env.getValue() * MENbonus[env.getCharacter().getBaseMEN()] * MAX_MP_MOD);
            env.setValue(env.getValue() * MENbonus[env.getCharacter().getBaseMEN()] * MAX_MP_MOD);
        }
    }

    private static class FuncCastSpeedMod extends Func{

        private static final FuncCastSpeedMod func = new FuncCastSpeedMod();

        public FuncCastSpeedMod(){
            super(Stats.MAGIC_ATTACK_SPEED, 0x20, null);
        }

        @Override
        public void calc(@NonNull final Env env){
            log.debug("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            log.debug("WIT: "+env.getCharacter().getBaseWIT());
            log.debug("WitBonus: "+WITbonus[env.getCharacter().getBaseWIT()]);
            log.debug("All Env :"+env.getValue() * WITbonus[env.getCharacter().getBaseWIT()] * M_ATK_SPEED_MOD);
            env.setValue(env.getValue() * WITbonus[env.getCharacter().getBaseWIT()] * M_ATK_SPEED_MOD);
        }
    }

    private static class FuncRunSpeedMod extends Func{

        private static final FuncRunSpeedMod func = new FuncRunSpeedMod();

        public FuncRunSpeedMod(){
            super(Stats.RUN_SPEED, 0x20, null);
        }

        @Override
        public void calc(@NonNull final Env env){
            log.info("Base Value "+getClass().getSimpleName()+": "+env.getValue());
            log.info("DEX: "+env.getCharacter().getBaseDEX());
            log.info("DexBonus: "+DEXbonus[env.getCharacter().getBaseDEX()]);
            log.info("All Env :"+env.getValue() * DEXbonus[env.getCharacter().getBaseDEX()] * RUN_SPEED_MOD);
            env.setValue(env.getValue() * DEXbonus[env.getCharacter().getBaseDEX()] * RUN_SPEED_MOD);
        }
    }

    public static void addFuncToChar(@NonNull final ActorSystem character){
        if(character.isPlayer()){
            character.addStatFunc(FuncPAtkMod.func);
            character.addStatFunc(FuncMAtkMod.func);
            character.addStatFunc(FuncMaxHpMod.func);
            character.addStatFunc(FuncMaxMpMod.func);
            character.addStatFunc(FuncCastSpeedMod.func);
            character.addStatFunc(FuncRunSpeedMod.func);
        }
        if(character.isNpc()){
            character.addStatFunc(FuncMaxHpMod.func);
        }
    }
}
