package parser.abilities.parts;

import card.Card;
import game.GameBoard;
import game.Player;
import java.util.List;
import java.util.Random;
import parser.abilities.properties.TargetProperty;
import parser.abilities.properties.TokenProperty;
import parser.tokenizer.Token;
import parser.tokenizer.TokenString;
import ui.selections.TargetSelectorUI;

/**
 * 
 */
public class AbilityPartDeck extends AbilityPart{
  
  private TargetProperty target;
  private TargetProperty destination;
  private TokenString choice;
  private Token amount;
  
  public AbilityPartDeck(TargetProperty target, TargetProperty destination,
      TokenString choice, Token amount) {
    super("Deck");
    this.target = target;
    this.destination = destination;
    this.choice = choice;
    this.amount = amount;

    properties.add(target);
    properties.add(destination);
    properties.add(new TokenProperty("Choice", choice));
    properties.add(new TokenProperty("Amount", amount));
  }

  @Override
  public boolean use(GameBoard targetBoard, Player owner, Card callingCard) { 


    Player targetPlayer = owner.getTargetPlayer(targetBoard, target);
    
    List<Card> targetList = null;
    
    switch(destination.target.value){
      case "deck":{
          targetList = targetPlayer.getDeck();
      }break;
      
      case "discard":{
        targetList = targetPlayer.getDiscardPile();
      }break;
    }
    
    if(targetList != null){
        int amountOfCards = amount.evaluateAsExpression(targetBoard, owner);
        
        for(int i=0; i < amountOfCards; i++){
          Card cardToRemove = null;

          int CardIndex = new Random(System.currentTimeMillis()).nextInt(targetPlayer.getHand().size());
          cardToRemove = targetPlayer.getHand().get(CardIndex);
          
          if(choice != null && choice.value != null){
            switch(choice.value){
              case "them":
                cardToRemove = owner.selectCardToDiscardFromHand();
                break;
              case "you":
                cardToRemove = targetPlayer.selectCardToDiscardFromHand();
                break;
            }
          }

          if(cardToRemove != null) {
            targetList.remove(cardToRemove);
            boolean addToTop = true;
            switch (destination.modifier.value){
              case "top":{
                addToTop = true;
              }break;
              case "bottom":{
                addToTop = false;
              }break;
            }
            switch(destination.target.value){
              case "discard":{
                if(addToTop) {
                  targetPlayer.getDiscardPile().add(cardToRemove);
                }else{
                  targetPlayer.getDiscardPile().add(0,cardToRemove);
                }
              }break;
              case "deck":{
                if(addToTop) {
                  targetPlayer.getDeck().add(cardToRemove);
                }else{
                  targetPlayer.getDeck().add(0, cardToRemove);
                }
              }break;
            }
          }

          
        }
        return true;
    }
    
    
    return false;
  }

  @Override
  public String getDescriptionString() {
    return "Deck from "+target + " to " + destination + " with choice "+choice + " for "+amount ; //TODO add description
  }

  public String getCurrentDescription(GameBoard targetBoard, Player callingPlayer){
    return "Deck from "+target + " to " + destination + " with choice "+choice + " for "+amount.evaluateAsExpression(targetBoard, callingPlayer) + " ["+amount.getDisplayString()+"]" ;
  }
}
