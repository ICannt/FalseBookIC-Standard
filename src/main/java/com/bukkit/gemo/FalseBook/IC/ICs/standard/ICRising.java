package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import com.bukkit.gemo.utils.ICUtils;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICRising extends BaseIC {

    public ICRising() {
        this.ICName = "RISING TOGGLE";
        this.ICNumber = "ic.rising";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, false, false, "Clock", "", "");
        this.chipState.setOutputs("Output", "", "");
        this.ICDescription = "The ic.rising is a toggle flip flop that toggles its output state between low and high whenever the input (the \"clock\") changes from low to high.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow())) {
            if (ICUtils.isLeverActive(signBlock)) {
                switchLever(Lever.BACK, signBlock, false);
            } else {
                switchLever(Lever.BACK, signBlock, true);
            }
        }
    }
}