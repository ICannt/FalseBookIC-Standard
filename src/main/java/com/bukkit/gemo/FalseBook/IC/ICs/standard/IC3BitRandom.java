package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import java.util.Random;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class IC3BitRandom extends BaseIC {

    Random rGen;

    public IC3BitRandom() {
        this.ICName = "3-BIT RANDOM";
        this.ICNumber = "ic.random3";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, false, false, "Clock", "", "");
        this.chipState.setOutputs("Random bit 1", "Random bit 2", "Random bit 3");
        this.ICDescription = "The ic.random3 generates 3 random bits whenever the input (the \"clock\") goes from low to high.";
        this.rGen = new Random();
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if ((currentInputs.isInputOneHigh()) && (previousInputs.isInputOneLow())) {
            switchLever(Lever.BACK, signBlock, this.rGen.nextBoolean());
            switchLever(Lever.LEFT, signBlock, this.rGen.nextBoolean());
            switchLever(Lever.RIGHT, signBlock, this.rGen.nextBoolean());
        }
    }
}