package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICFullAdder extends BaseIC {

    public ICFullAdder() {
        this.ICName = "FULL ADDER";
        this.ICNumber = "ic.fulladd";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, true, "First bit to add", "Second bit to add", "Third bit to add (carry in)");
        this.chipState.setOutputs("Sum", "Carry out", "Carry out (always the same as Output2)");
        this.ICDescription = "The ic.fulladd implements a full adder. It adds the two input bits, adds the carry in (third bit), and returns the sum and the carry.";
    }

    public void checkCreation(SignChangeEvent event) {
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        boolean inputA = currentInputs.isInputOneHigh();
        boolean inputB = currentInputs.isInputTwoHigh();
        boolean inputC = currentInputs.isInputThreeHigh();
        boolean result = inputA ^ inputB ^ inputC;
        boolean carry = inputA & inputB | (inputA ^ inputB) & inputC;

        switchLever(Lever.BACK, signBlock, result, 3);
        switchLever(Lever.LEFT, signBlock, carry, 2);
        switchLever(Lever.RIGHT, signBlock, carry, 2);
    }
}