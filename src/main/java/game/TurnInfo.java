package game;

/**
 * Created by frede on 2017-07-03.
 */
public class TurnInfo {

    int turnNum = 1;

    private TurnTrigger attackTrigger = new TurnTrigger();
    private TurnTrigger energyTrigger = new TurnTrigger();
    private TurnTrigger trainerTrigger = new TurnTrigger();


    public void reset(){
        attackTrigger.reset();
        energyTrigger.reset();
        trainerTrigger.reset();
    }

    public TurnTrigger getAttackTrigger(){
        return attackTrigger;
    }

    public TurnTrigger getEnergyTrigger(){
        return energyTrigger;
    }

    public TurnTrigger getTrainerTrigger(){
        return trainerTrigger;
    }

    public class TurnTrigger{
        private boolean status = false;

        public void trigger(){
            status = true;
        }

        public void reset(){
            status = false;
        }

        public boolean already(){
            return status;
        }
    }
}
