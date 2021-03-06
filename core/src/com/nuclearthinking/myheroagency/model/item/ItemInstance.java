package com.nuclearthinking.myheroagency.model.item;

import com.nuclearthinking.myheroagency.controller.loader.file.JsonToObject;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Created by mkuksin on 23.11.2016.
 */
public class ItemInstance {

    private static final ItemInstance instance = new ItemInstance();

    private @Getter final ArrayList<Weapon> weapons = AssetsManager.getInstance().get(Constants.ITEM_JSON, JsonToObject.class).getItemParser().getWeapons();

    public Weapon getWeapon(final int id){
        return weapons.get(id);
    }

    private ItemInstance(){}

    public static ItemInstance getInstance(){
        return instance;
    }
}
