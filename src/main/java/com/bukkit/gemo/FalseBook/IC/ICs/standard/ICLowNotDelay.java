package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.FalseBookICCore;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.FalseBook.IC.ICs.Lever;
import com.bukkit.gemo.utils.ICUtils;
import com.bukkit.gemo.utils.Parser;
import com.bukkit.gemo.utils.SignUtils;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class ICLowNotDelay extends BaseIC {

    public HashMap<String, SchedulerClass> TaskList = new HashMap<String, SchedulerClass>();

    public ICLowNotDelay() {
        this.ICName = "LOW NOT-DELAYER";
        this.ICNumber = "ic.lownotdelay";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, true, false, "Clock", "Reset", "");
        this.chipState.setOutputs("Output", "", "");
        this.chipState.setLines("delay in seconds", "");
        this.ICDescription = "The ic.lownotdelay delays a low signal for [x] seconds (Line 3) and inverts it, if the signal is stable.";
    }

    public void checkCreation(SignChangeEvent event) {
        if (!Parser.isInteger(event.getLine(1))) {
            SignUtils.cancelSignCreation(event, "Enter the delay in seconds in line 3.");
            return;
        }
        event.setLine(1, String.valueOf(Math.abs(Integer.valueOf(event.getLine(1)).intValue())));
        event.setLine(2, "");
        event.setLine(3, "");
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        boolean input1High = currentInputs.isInputOneHigh();
        boolean input2High = currentInputs.isInputTwoHigh();
        Object newSched;
        if ((!input1High) && (!input2High)) {
            for (SchedulerClass sched : this.TaskList.values()) {
                if (sched.equalsLoc(signBlock.getBlock().getLocation())) {
                    Bukkit.getServer().getScheduler().cancelTask(sched.TaskID);
                    this.TaskList.remove(signBlock.getBlock().getLocation().toString());
                    break;
                }
            }

            if (!Parser.isInteger(signBlock.getLine(1))) {
                return;
            }
            int secs = Math.abs(Parser.getInteger(signBlock.getLine(1), 1));
            newSched = new SchedulerClass(this, signBlock.getBlock().getLocation());
            ((SchedulerClass) newSched).TaskID = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(FalseBookICCore.getInstance(), (Runnable) newSched, 20 * secs);
            this.TaskList.put(signBlock.getBlock().getLocation().toString(), (SchedulerClass) newSched);
        } else if ((input1High) || (input2High)) {
            for (newSched = this.TaskList.values().iterator(); ((Iterator) newSched).hasNext();) {
                SchedulerClass sched = (SchedulerClass) ((Iterator) newSched).next();
                if (sched.equalsLoc(signBlock.getBlock().getLocation())) {
                    Bukkit.getServer().getScheduler().cancelTask(sched.TaskID);
                    this.TaskList.remove(signBlock.getBlock().getLocation().toString());
                    break;
                }
            }
            switchLever(Lever.BACK, signBlock, false);
        }
    }

    public class SchedulerClass
            implements Runnable {

        public int TaskID = -1;
        public ICLowNotDelay father;
        public Location signLoc;

        public SchedulerClass(ICLowNotDelay father, Location signLoc) {
            this.father = father;
            this.signLoc = signLoc;
        }

        public void run() {
            ICUtils.switchLever((Sign) this.signLoc.getBlock().getState(), true);
            this.father.TaskList.remove(this.signLoc.getBlock().getLocation().toString());
            Bukkit.getServer().getScheduler().cancelTask(this.TaskID);
        }

        public boolean equalsLoc(Location loc) {
            return this.signLoc.equals(loc);
        }
    }
}