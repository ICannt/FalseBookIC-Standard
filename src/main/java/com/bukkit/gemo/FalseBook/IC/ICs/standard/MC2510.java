package com.bukkit.gemo.FalseBook.IC.ICs.standard;

import com.bukkit.gemo.FalseBook.IC.FalseBookICCore;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseChip;
import com.bukkit.gemo.FalseBook.IC.ICs.BaseIC;
import com.bukkit.gemo.FalseBook.IC.ICs.ICGroup;
import com.bukkit.gemo.FalseBook.IC.ICs.InputState;
import com.bukkit.gemo.utils.ICUtils;
import com.bukkit.gemo.utils.SignUtils;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class MC2510 extends BaseIC {

    public HashMap<String, SchedulerClass> TaskList = new HashMap<String, SchedulerClass>();

    public MC2510() {
        this.ICName = "LOW PULSER";
        this.ICNumber = "[MC2510]";
        setICGroup(ICGroup.STANDARD);
        this.chipState = new BaseChip(true, false, false, "Clock", "", "");
        this.chipState.setOutputs("Pulsing output", "", "");
        this.chipState.setLines("[pulselength[:startdelay]]", "[[pulsecount][:pauselength in serverticks]]");
        this.ICDescription = "The MC2510 fires a (choosable) pulse of high-signals with a choosable length of the signal and the pause between the pulses when the input goes from high to low.<br /><br />Standard is one pulse with a length of 5 ticks.";
    }

    public void checkCreation(SignChangeEvent event) {
        try {
            String[] lines = event.getLine(2).split(":");
            if (lines.length == 1) {
                int val = Integer.valueOf(lines[0]).intValue();
                if (val < 0) {
                    val = -val;
                    if (val < 1) {
                        val = 1;
                    }
                }
                event.setLine(2, ((Integer) val).toString());
            } else if (lines.length > 1) {
                int val = Integer.valueOf(lines[0]).intValue();
                if (val < 0) {
                    val = -val;
                    if (val < 1) {
                        val = 1;
                    }
                }
                int valDelay = Integer.valueOf(lines[1]).intValue();
                if (valDelay < 0) {
                    valDelay = -valDelay;
                    if (valDelay < 1) {
                        valDelay = 1;
                    }
                }
                event.setLine(2, val + ":" + valDelay);
            }
        } catch (Exception e) {
            event.setLine(2, "5");
            return;
        }
        if (event.getLine(3).length() > 1) {
            String[] split = event.getLine(3).split(":");
            if (split.length < 2) {
                try {
                    int val = Integer.valueOf(split[0]).intValue();
                    if (val < 0) {
                        val = -val;
                        if (val < 1) {
                            val = 1;
                        }
                    }
                    event.setLine(3, ((Integer) val).toString());
                } catch (Exception e) {
                    SignUtils.cancelSignCreation(event, "Wrong syntax in Line 4. Use: Pulsecount[:Pauselength] (Integer only)");
                    return;
                }
            } else {
                try {
                    int val = Integer.valueOf(split[0]).intValue();
                    if (val < 0) {
                        val = -val;
                        if (val < 1) {
                            val = 1;
                        }
                    }
                    int val2 = Integer.valueOf(split[1]).intValue();
                    if (val2 < 0) {
                        val2 = -val2;
                    }
                    event.setLine(3, val + ":" + val2);
                } catch (Exception e) {
                    SignUtils.cancelSignCreation(event, "Wrong syntax in Line 4. Use: Pulsecount[:Pauselength] (Integer only)");
                    return;
                }
            }
        }
    }

    public void Execute(Sign signBlock, InputState currentInputs, InputState previousInputs) {
        if ((currentInputs.isInputOneLow()) && (previousInputs.isInputOneHigh())) {
            for (SchedulerClass sched : this.TaskList.values()) {
                if (sched.equalsLoc(signBlock.getBlock().getLocation())) {
                    return;
                }

            }

            int pulseLength = 5;
            int pulseCount = 1;
            int pauseLength = 5;
            int pulseDelay = 1;
            try {
                String[] lines = signBlock.getLine(2).split(":");
                pulseDelay = 1;
                if (lines.length == 1) {
                    pulseLength = Integer.valueOf(lines[0]).intValue();
                    if (pulseLength < 0) {
                        pulseLength = -pulseLength;
                        if (pulseLength < 1) {
                            pulseLength = 5;
                        }
                    }
                } else if (lines.length > 1) {
                    pulseLength = Integer.valueOf(lines[0]).intValue();
                    if (pulseLength < 0) {
                        pulseLength = -pulseLength;
                        if (pulseLength < 1) {
                            pulseLength = 5;
                        }
                    }
                    pulseDelay = Integer.valueOf(lines[1]).intValue();
                    if (pulseDelay < 0) {
                        pulseDelay = -pulseDelay;
                    }
                }

                if (signBlock.getLine(3).length() > 0) {
                    String[] split = signBlock.getLine(3).split(":");
                    if (split.length < 2) {
                        pulseCount = Integer.valueOf(split[0]).intValue();
                        pauseLength = pulseLength;
                    } else {
                        pulseCount = Integer.valueOf(split[0]).intValue();
                        pauseLength = Integer.valueOf(split[1]).intValue();
                    }
                }
            } catch (Exception e) {
                pulseLength = 5;
                pulseCount = 1;
                pauseLength = 5;
                pulseDelay = 1;
            }

            SchedulerClass newSched = new SchedulerClass(this, signBlock.getBlock().getLocation(), pulseLength, pulseCount, pauseLength);
            newSched.TaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FalseBookICCore.getInstance(), newSched, pulseDelay, 1L);
            this.TaskList.put(signBlock.getBlock().getLocation().toString(), newSched);
        }
    }

    public class SchedulerClass
            implements Runnable {

        public int TaskID = -1;
        public MC2510 father;
        public Location signLoc;
        public int countedTicks = 0;
        public int pulseLength = 5;
        public int pulseCount = 1;
        public int pauseLength = 5;
        public int curPulse = 0;
        public boolean curState = true;
        public boolean first = true;
        public Sign signBlock = null;

        public SchedulerClass(MC2510 father, Location signLoc, int pulseLength, int pulseCount, int pauseLength) {
            this.father = father;
            this.signLoc = signLoc;
            this.pulseLength = pulseLength;
            this.pulseCount = pulseCount;
            this.pauseLength = pauseLength;
            this.signBlock = ((Sign) signLoc.getBlock().getState());
        }

        public void run() {
            if (this.first) {
                ICUtils.switchLever(this.signBlock, true);
                this.first = false;
            }
            this.countedTicks += 1;
            if (((this.curState) && (this.pulseLength == this.countedTicks)) || ((!this.curState) && (this.pauseLength == this.countedTicks))) {
                this.countedTicks = 0;
                if ((this.countedTicks == 0) && (this.curState)) {
                    this.pulseCount -= 1;
                }
                this.curState = (!this.curState);
                try {
                    ICUtils.switchLever((Sign) this.signLoc.getBlock().getState(), this.curState);
                } catch (Exception e) {
                    this.father.TaskList.remove(this.signLoc.getBlock().getLocation().toString());
                    Bukkit.getServer().getScheduler().cancelTask(this.TaskID);
                }
            }

            if (this.pulseCount < 1) {
                this.father.TaskList.remove(this.signLoc.getBlock().getLocation().toString());
                Bukkit.getServer().getScheduler().cancelTask(this.TaskID);
            }
        }

        public boolean equalsLoc(Location loc) {
            return this.signLoc.equals(loc);
        }
    }
}