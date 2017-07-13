package game.ai;

import card.Ability;
import card.Card;
import card.EnergyCard;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import game.TargetSelector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import parser.abilities.AbilityPart;
import parser.abilities.AbilityPartDam;
import parser.cards.EnergyCost;
import parser.commons.TargetProperty;
import ui.selections.RewardSelector;
import ui.selections.TargetSelectorUI;

/**
 * Created by frede on 2017-07-08.
 */
public class IntelligentPlayer extends Player {
    boolean firstTurn = true;

    public IntelligentPlayer(List<Card> playerDeck) {
        super(playerDeck);
    }
    
    public void doTurn(GameBoard gameBoard){
        if(activePokemon == null){
            PokemonCard optimalActive = firstTurn?findOptimalPokemon(hand):findOptimalPokemon(bench);
            if(optimalActive != null) {
                if(firstTurn) {
                    gameBoard.onHandCardClicked(this, optimalActive);
                    firstTurn = false;
                }else{
                    gameBoard.onBenchCardClicked(this, optimalActive);
                }
                gameBoard.onActiveCardClicked(this, null);
            }
        }else{
            boolean putOnBench = true;
            if(activePokemon.getEnergyAttached().canSupport(activePokemon.getRetreatEnergyCost())) {
                List<Card> fullList = new ArrayList<>();
                fullList.addAll(bench);
                fullList.add(activePokemon);
                PokemonCard optimal = findOptimalPokemon(fullList);
                if (optimal != null && optimal != activePokemon) {
                    gameBoard.onRetreatButtonClicked(this);
                    gameBoard.onBenchCardClicked(this, optimal);
                    gameBoard.onActiveCardClicked(this, null);
                    putOnBench = false;
                }
            }
            if(putOnBench) {
                PokemonCard optimalBench = findOptimalPokemon(hand);
                if (optimalBench != null) {
                    gameBoard.onHandCardClicked(this, optimalBench);
                    gameBoard.onBenchCardClicked(this, null);
                }
            }
        }
        
        playEnergyCard(gameBoard);
        tryAttack(gameBoard);
    }
    
    private PokemonCard findOptimalPokemon(List<Card> cards){
        int bestScore = -1;
        
        PokemonCard bestCard = null;
        
        EnergyCost energyInHand = new EnergyCost();
        for(Card card : cards){
            if(card instanceof EnergyCard){
                EnergyCard energyCard = (EnergyCard)card;
                switch(energyCard.getEnergyType()){
                    case FIGHT:
                        energyInHand.fight++;
                        break;
                    case WATER:
                        energyInHand.water++;
                        break;
                    case LIGHTNING:
                        energyInHand.lightning++;
                        break;
                    case PSYCHIC:
                        energyInHand.psychic++;
                        break;
                }
            }
        }
        
        for(Card card : cards){
            if(card instanceof PokemonCard){
                int score = 0;
                
                PokemonCard pokemonCard = (PokemonCard)card;
                EnergyCost energyUsed = new EnergyCost();
                for(Ability ability : pokemonCard.getAbilities()){
                    energyUsed.add(ability.getEnergyCost());
                    
                    
                    for(AbilityPart part: ability.getTemplate().parts){
                        if(part instanceof AbilityPartDam){
                            AbilityPartDam abilityPartDam = (AbilityPartDam)part;
                            score += abilityPartDam .getAmmount().evaluateAsExpression();
                        }
                    }
                }
                int matchingEnergy = 0;
                int[] asArray = energyInHand.getAsArray();
                for (int i = 0; i < asArray.length; i++) {
                    int handEnergy = asArray[i];
                    int pokemonEnergy = energyUsed.getAsArray()[i];
                    
                    if(pokemonEnergy > 0 && handEnergy > 0){
                        matchingEnergy++;
                    }
                }
                
                score *= ((double)matchingEnergy)/5+0.2;
                
                if(score > bestScore){
                    bestScore = score;
                    bestCard = pokemonCard;
                }
            }
        }
        return bestCard;
    }
    
    private void playEnergyCard(GameBoard gameBoard){
        if(activePokemon != null){
            boolean enoughEnergy = true;
            for(Ability ability : activePokemon.getAbilities()){
                if(!ability.getEnergyCost().canSupport(activePokemon.getEnergyAttached())){
                    enoughEnergy = false;
                }
            }
            if(!enoughEnergy) {
                EnergyCard energyToPlay = findOptimalEnergy(activePokemon);

                if (energyToPlay != null) {
                    gameBoard.onHandCardClicked(this, energyToPlay);
                    gameBoard.onActiveCardClicked(this, activePokemon);
                }
            }else{
                PokemonCard target = findOptimalPokemon(bench);
                if(target != null){
                    EnergyCard energyToPlay = findOptimalEnergy(target);

                    if(energyToPlay != null){
                        gameBoard.onHandCardClicked(this, energyToPlay);
                        gameBoard.onBenchCardClicked(this, target);
                    }
                }
            }
            
        }
    }
    
    private EnergyCard findOptimalEnergy(PokemonCard target){
        EnergyCost neededEnergy = new EnergyCost();
        EnergyCost currentEnergy = target.getEnergyAttached();
        target.getAbilities().forEach(ability -> {
            int neededColorless = ability.getEnergyCost().colorless - currentEnergy.colorless;
            if(neededColorless > neededEnergy.colorless){
                neededEnergy.colorless = neededColorless;
            }

            int neededWater = ability.getEnergyCost().water - currentEnergy.water;
            if(neededWater > neededEnergy.water){
                neededEnergy.water = neededWater;
            }

            int neededLightning = ability.getEnergyCost().lightning - currentEnergy.lightning;
            if(neededLightning > neededEnergy.lightning){
                neededEnergy.lightning = neededLightning;
            }

            int neededPsychic = ability.getEnergyCost().psychic - currentEnergy.psychic;
            if(neededPsychic > neededEnergy.psychic){
                neededEnergy.psychic = neededPsychic;
            }

            int neededFight = ability.getEnergyCost().fight- currentEnergy.fight;
            if(neededFight > neededEnergy.fight){
                neededEnergy.fight = neededFight;
            }

        });

        EnergyCard energyToPlay = null;

        search:
        for(Card card : hand){
            if(card instanceof EnergyCard){
                EnergyCard energyCard = (EnergyCard)card;
                switch(energyCard.getEnergyType()){
                    case FIGHT:
                        if(neededEnergy.fight <= 0 && neededEnergy.colorless <=0){
                            break;
                        }

                    case WATER:
                        if(neededEnergy.water <= 0 && neededEnergy.colorless <=0){
                            break;
                        }
                    case PSYCHIC:
                        if(neededEnergy.psychic <= 0 && neededEnergy.colorless <=0){
                            break;
                        }
                    case LIGHTNING:
                        if(neededEnergy.lightning <= 0 && neededEnergy.colorless <=0){
                            break;
                        }

                    default:
                        energyToPlay = energyCard;
                        break search;

                }
            }
        }
        
        return energyToPlay;
        
    }
    
    private void tryAttack(GameBoard gameBoard){
        if(activePokemon != null){
            for(Ability ability : activePokemon.getAbilities()){
                if(ability.getTemplate().appliesDamage()){
                    gameBoard.onActiveAbilityClicked(this, activePokemon, ability);
                    return;
                }
            }
        }
    }
    public void choseRewardCard() {
        if(prizes.size() > 0) {
            int prizeId = new Random(System.currentTimeMillis()).nextInt(prizes.size());
            hand.add(prizes.remove(prizeId));
        }
        
    }
    public TargetSelector createTargetSelector(){
        return new AiTargetSelector();
    }
    
    public class AiTargetSelector extends TargetSelector{
        
        public Card getCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty){
            return getAiCard(gameBoard, callingPlayer, targetProperty);
        }

        public Card getChoiceCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty){
            return getAiCard(gameBoard, callingPlayer, targetProperty);
        }
        
        private Card getAiCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty){
            switch(targetProperty.target.value){
                case "choice":{
                    switch(targetProperty.modifier.value){
                        case "opponent-bench":{
                            Player otherPlayer = gameBoard.getOtherPlayer(callingPlayer);
                            if(otherPlayer.getBench().size() > 0) {
                                int cardToSelect = new Random(System.currentTimeMillis()).nextInt(
                                    otherPlayer.getBench().size());
                                return otherPlayer.getBench().get(cardToSelect);
                            }
                        }
                    }
                }

                case "opponent-active":{
                    return getOpponentActive(gameBoard, callingPlayer);
                }

                default: {
                    return null;
                }
            }
        }
    }
}
