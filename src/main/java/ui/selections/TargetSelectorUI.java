/*
 * description:  Displays info about an ability
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-29
 */

package ui.selections;

import card.Card;
import game.GameBoard;
import game.Player;
import game.TargetSelector;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Optional;
import parser.abilities.filters.Filter;

public class TargetSelectorUI extends TargetSelector{

    
    
    
    public static Card selectCard(List<Card> cards){
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Target Selector");
        alert.setHeaderText("Please chose the target card");
        alert.setContentText("Options: ");

        HashMap<ButtonType, Card> possibleResults = new HashMap<>();

        alert.getButtonTypes().clear();
        
           cards.forEach((card) -> {
               ButtonType buttonType = new ButtonType(card.getCardName());
               alert.getButtonTypes().add(buttonType);
               possibleResults.put(buttonType, card);
           });

        alert.getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            return possibleResults.get(result.get());
        }

        return null;
    }

    @Override
    public Card choseOpponentCard(GameBoard gameBoard, Player callingPlayer, Filter filter) {
        Player opponent = gameBoard.getOtherPlayer(callingPlayer);

        List<Card> choices = new ArrayList<>();
        if(opponent.getActivePokemon() != null) {
            if(filter.evaluate(gameBoard, callingPlayer, opponent.getActivePokemon())) {
                choices.add(opponent.getActivePokemon());
            }
        }

        filter.evaluate(gameBoard, callingPlayer, opponent.getBench()).forEach(card->{
            choices.add(card);
        });

        return TargetSelectorUI.selectCard(choices);
    }

    @Override
    public Card choseOpponentBench(GameBoard gameBoard, Player callingPlayer, Filter filter) {
        Player opponent = gameBoard.getOtherPlayer(callingPlayer);

        return TargetSelectorUI.selectCard(filter.evaluate(gameBoard, callingPlayer, opponent.getBench()));
    }

    @Override
    public Card choseYourCard(GameBoard gameBoard, Player callingPlayer, Filter filter) {
        List<Card> choices = new ArrayList<>();
        if(callingPlayer.getActivePokemon() != null && filter.evaluate(gameBoard, callingPlayer, callingPlayer.getActivePokemon())) {
            choices.add(callingPlayer.getActivePokemon());
        }
        filter.evaluate(gameBoard, callingPlayer, callingPlayer.getBench()).forEach(card->{
            choices.add(card);
        });

        return TargetSelectorUI.selectCard(choices);
    }

    @Override
    public Card choseYourBench(GameBoard gameBoard, Player callingPlayer, Filter filter) {
        return TargetSelectorUI.selectCard(filter.evaluate(gameBoard, callingPlayer, callingPlayer.getBench()));
    }
}
