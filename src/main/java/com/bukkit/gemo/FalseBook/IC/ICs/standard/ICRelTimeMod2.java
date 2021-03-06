package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICRelTimeMod2 extends BaseIC {

    public ICRelTimeMod2() {
        this.ICName = "REL TIME MOD 2";
        this.ICNumber = "ic.reltime";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, false, false, "Clock", "", "");
        this.chipState.setOutputs("Output", "", "");
        this.ICDescription = "The ic.reltime outputs whether the world time is either odd or even based on the application of the modulus on the server time. If the server time is even, a low will be outputted. If the server time is odd, a high will be outputted.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow())) {
            if (signBlock.getWorld().getTime() % 2L != 0L) {
                switchLever(Lever.BACK, signBlock, true);
            } else {
                switchLever(Lever.BACK, signBlock, false);
            }
        }
    }
}